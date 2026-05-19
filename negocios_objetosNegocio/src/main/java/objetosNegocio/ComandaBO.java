package objetosNegocio;

import adaptadores.ComandaNegocioAdapter;
import adaptadores.MesaNegocioAdapter;
import adaptadores.PedidoNegocioAdapter;
import daos.ComandaDAO;
import daos.MesaDAO;
import dtos.ComandaDTO;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import dtos.PedidoDTO;
import dtos_infraestructura.InventarioRequestDTO;
import entidades.Comanda;
import entidades.Pedido;
import enums.EstadoComanda;
import enums.EstadoMesa;
import enums.EstadoPedido;
import enums.EstadoPedidoDTO;
import excepcion.NegocioException;
import excepciones.InfraestructuraException;
import excepciones.PersistenciaException;
import fachada.InventarioFachada;
import interfaces.IComandaDAO;
import interfaces.IMesaDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Clase de Objeto de Negocio (BO) para la gestión y control de Comandas.
 * * Contiene la lógica de negocio del restaurante referente al ciclo de vida de una comanda,
 * la adición, edición o cancelación de pedidos, el cálculo automatizado de montos totales,
 * la determinación de estados operativos y la coordinación con el inventario externo de productos.
 * * Se comunica con la capa de persistencia mediante abstracciones (interfaces) para evitar el acoplamiento.
 * * @author valeria
 */
public class ComandaBO {

    private final IComandaDAO comandaDAO;
    private final IMesaDAO mesaDAO;
    private final ComandaNegocioAdapter comandaAdapter;
    private final MesaNegocioAdapter mesaAdapter;
    private final PedidoNegocioAdapter pedidoAdapter;
    private final InventarioFachada inventarioAPI;
    private final ProductoBO productoBO;
    private final MesaBO mesaBO;

    /**
     * Constructor por defecto.
     * Inicializa las implementaciones de los DAOs, adaptadores de negocio y 
     * componentes de lógica o infraestructura necesarios para procesar comandas.
     */
    public ComandaBO() {
        this.comandaDAO = new ComandaDAO();
        this.mesaDAO = new MesaDAO();
        this.comandaAdapter = new ComandaNegocioAdapter();
        this.mesaAdapter = new MesaNegocioAdapter();
        this.pedidoAdapter = new PedidoNegocioAdapter();
        this.inventarioAPI = new InventarioFachada();
        this.productoBO = new ProductoBO(inventarioAPI);
        this.mesaBO = new MesaBO();
    }

    /**
     * Registra una nueva comanda en el sistema asociada a una mesa específica.
     * * Valida y descuenta las existencias de stock correspondientes a los pedidos solicitados,
     * inicializa los identificadores y estados de cada pedido, calcula el importe inicial,
     * persiste el documento de la comanda y actualiza el estado de la mesa seleccionada a OCUPADA.
     *
     * @param nombreCliente nombre o alias del cliente titular de la mesa
     * @param numeroMesa número identificador de la mesa física asignada
     * @param pedidosDTO lista de transferibles con la información de los platillos o bebidas pedidos
     * @param empleadoActual empleado (mesero) responsable de la apertura de la comanda
     * @throws NegocioException si hay desabasto de insumos en inventario o si la persistencia falla
     */
    public void crearComanda(String nombreCliente, int numeroMesa, List<PedidoDTO> pedidosDTO, EmpleadoDTO empleadoActual) throws NegocioException {
        try {
            procesarComanda(pedidosDTO);

            for (PedidoDTO pedido : pedidosDTO) {
                pedido.setEstado(EstadoPedidoDTO.PENDIENTE);
                if (pedido.getId() == null) {
                    pedido.setId(new ObjectId().toHexString());
                }
            }

            Comanda comanda = comandaAdapter.aEntidad(
                    nombreCliente,
                    numeroMesa,
                    pedidosDTO,
                    empleadoActual
            );

            comanda.setEstado(calcularEstadoComanda(comanda.getPedidos()));
            calcularMontoTotalComanda(comanda);

            Comanda insertada = comandaDAO.insertarComanda(comanda);
            if (insertada == null) {
                throw new NegocioException("La comanda no se logró insertar");
            }

            mesaDAO.cambiarEstadoMesaPorNumero(
                    comanda.getMesa().getNumero(),
                    EstadoMesa.OCUPADA
            );

        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Verifica la disponibilidad de los productos e ingredientes en el inventario.
     * * Analiza recursivamente las recetas de los productos y realiza la solicitud 
     * a la fachada de infraestructura para reducir los stocks correspondientes de forma atómica.
     *
     * @param pedidos lista de pedidos DTO cuyo inventario se desea comprometer
     * @throws NegocioException si la API de inventario detecta stock insuficiente o falla la comunicación
     */
    public void procesarComanda(List<PedidoDTO> pedidos) throws NegocioException {
        List<InventarioRequestDTO> inventarioList = new ArrayList<>();

        for (PedidoDTO pedido : pedidos) {
            productoBO.obtenerIngredientesDeProducto(pedido.getIdProducto());
            
            InventarioRequestDTO dto = new InventarioRequestDTO();
            dto.setIdProducto(pedido.getIdProducto());
            dto.setCantidad(pedido.getCantidad());
            
            inventarioList.add(dto);
        }

        try {
            inventarioAPI.descontarStock(inventarioList);
        } catch (InfraestructuraException e) {
            e.printStackTrace();
            throw new NegocioException("Error al descontar stock: " + e.getMessage(), e);
        }
    }

    /**
     * Recupera las comandas activas o registradas para un número de mesa específico.
     *
     * @param numeroMesa número de la mesa a consultar
     * @return lista de objetos ComandaDTO listos para la capa de presentación
     * @throws RuntimeException si ocurre una falla imprevista en la base de datos
     */
    public List<ComandaDTO> obtenerComandasPorMesa(int numeroMesa) {
        try {
            List<Comanda> comandas = comandaDAO.obtenerComandasPorMesa(numeroMesa);
            List<ComandaDTO> listaDTO = new ArrayList<>();

            for (Comanda c : comandas) {
                listaDTO.add(comandaAdapter.aDTO(c));
            }

            return listaDTO;
        } catch (PersistenciaException e) {
            throw new RuntimeException("Error al obtener comandas", e);
        }
    }

    /**
     * Agrega una colección de nuevos pedidos a una comanda ya existente.
     * * Evalúa las materias primas en el inventario para los nuevos consumos, inyecta identificadores
     * únicos BSON en formato String a los nuevos renglones, y actualiza de manera segura el 
     * documento padre recalculando importes financieros y estados operativos vigentes.
     *
     * @param idComanda identificador único de la comanda destino
     * @param pedidosDTO lista de pedidos adicionales que se añadirán a la cuenta
     * @throws NegocioException si la comanda no existe, si no hay insumos suficientes o si falla la BD
     */
    public void agregarPedidosAComanda(String idComanda, List<PedidoDTO> pedidosDTO) throws NegocioException {
        try {
            Comanda comandaActual = comandaDAO.obtenerPorId(idComanda);
            if (comandaActual == null) {
                throw new NegocioException("No se encontró la comanda");
            }

            for (PedidoDTO pedido : pedidosDTO) {
                pedido.setEstado(EstadoPedidoDTO.PENDIENTE);
                if (pedido.getId() == null) {
                    pedido.setId(new ObjectId().toHexString());
                }
            }

            procesarComanda(pedidosDTO);

            List<Pedido> pedidosActuales = comandaActual.getPedidos();
            List<Pedido> pedidosNuevos = pedidoAdapter.listaAEntidad(pedidosDTO);
            List<InventarioRequestDTO> inventarioList = new ArrayList<>();

            for (PedidoDTO pedido : pedidosDTO) {
                InventarioRequestDTO dto = new InventarioRequestDTO();
                dto.setIdProducto(pedido.getIdProducto());
                dto.setCantidad(pedido.getCantidad());
                
                inventarioList.add(dto);
            }

            for (Pedido nuevo : pedidosNuevos) {
                if (nuevo.getEstado() == null) {
                    nuevo.setEstado(EstadoPedido.PENDIENTE);
                }

                boolean ok = comandaDAO.agregarPedidoAComanda(idComanda, nuevo);
                if (!ok) {
                    throw new NegocioException("No se pudo agregar el pedido");
                }
                pedidosActuales.add(nuevo);
            }

            comandaDAO.actualizarComanda(idComanda, pedidosActuales);
            comandaDAO.recalcularMonto(idComanda);

            EstadoComanda nuevoEstado = calcularEstadoComanda(pedidosActuales);
            comandaDAO.actualizarEstado(idComanda, nuevoEstado.name());

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al agregar pedidos", e);
        }
    }

    /**
     * Sobreescribe o actualiza la lista total de pedidos dentro de una comanda.
     * * Reestablece las métricas financieras e inspecciona el estado global de preparación
     * de la cocina para actualizar los estados de seguimiento.
     *
     * @param comandaDTO comanda de transferencia que contiene las modificaciones
     * @throws NegocioException si no es posible ubicar el registro original o falla la persistencia
     */
    public void actualizarComanda(ComandaDTO comandaDTO) throws NegocioException {
        List<Pedido> pedidos = pedidoAdapter.listaAEntidad(comandaDTO.getPedidos());

        try {
            comandaDAO.actualizarComanda(comandaDTO.getId(), pedidos);
            comandaDAO.recalcularMonto(comandaDTO.getId());

            Comanda comandaActualizada = comandaDAO.obtenerPorId(comandaDTO.getId());
            if (comandaActualizada == null) {
                throw new NegocioException("No se pudo obtener la comanda actualizada");
            }

            EstadoComanda nuevoEstado = calcularEstadoComanda(comandaActualizada.getPedidos());
            comandaDAO.actualizarEstado(comandaDTO.getId(), nuevoEstado.name());

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar comanda", e);
        }
    }

    /**
     * Remueve de manera permanente una comanda del almacén de datos.
     * * Si tras la remoción de la comanda la mesa no posee ninguna otra cuenta pendiente, 
     * el estado operativo de la mesa física es restaurado automáticamente a LIBRE.
     *
     * @param idComanda identificador de la comanda a eliminar
     * @param mesa transferencia que describe la mesa vinculada
     * @return true si la operación se concretó con éxito
     * @throws NegocioException si la base de datos rechaza o no encuentra el registro a eliminar
     */
    public boolean eliminarComanda(String idComanda, MesaDTO mesa) throws NegocioException {
        try {
            boolean eliminada = comandaDAO.eliminarComanda(idComanda);
            if (!eliminada) {
                throw new NegocioException("No se pudo eliminar la comanda");
            }

            List<Comanda> comandas = comandaDAO.obtenerComandasPorMesa(mesa.getNumeroMesa());
            if (comandas.isEmpty()) {
                mesaDAO.cambiarEstadoMesaPorNumero(mesa.getNumeroMesa(), EstadoMesa.LIBRE);
            }

            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar la comanda", e);
        }
    }

    /**
     * Obtiene el listado de todas las comandas cuyos platillos se reportan listos desde la cocina.
     * * Facilita el monitoreo a los meseros para agilizar la entrega en las mesas.
     *
     * @return lista de comandas con estatus LISTA en modelo DTO
     * @throws NegocioException si el motor de persistencia genera un error de lectura
     */
    public List<ComandaDTO> obtenerComandasListas() throws NegocioException {
        try {
            List<Comanda> comandas = comandaDAO.obtenerComandasListas();
            List<ComandaDTO> listaDTO = new ArrayList<>();

            for (Comanda c : comandas) {
                listaDTO.add(comandaAdapter.aDTO(c));
            }

            return listaDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener comandas listas", e);
        }
    }

    /**
     * Busca y retorna una comanda mapeada por su identificador único.
     *
     * @param id identificador único del documento comanda
     * @return comanda mapeada al modelo de transferencia DTO
     * @throws NegocioException si no se localiza ninguna coincidencia en el almacén de datos
     */
    public ComandaDTO obtenerComandaPorId(String id) throws NegocioException {
        try {
            Comanda comanda = comandaDAO.obtenerPorId(id);
            if (comanda == null) {
                throw new NegocioException("No existe la comanda");
            }

            return comandaAdapter.aDTO(comanda);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener comanda", e);
        }
    }

    /**
     * Algoritmo determinante que calcula el Estado Global de la Comanda en función de sus pedidos.
     * * Reglas de estado jerárquicas:
     * * 1. Si existe al menos un renglón PENDIENTE, la comanda es PENDIENTE.
     * * 2. Si no hay pendientes pero hay en preparación, la comanda está EN_PREPARACION.
     * * 3. Si no hay pendientes ni en preparación pero hay listos, la comanda está LISTA.
     * * 4. Si todos los elementos han sido servidos, la comanda pasa a ser ENTREGADA.
     *
     * @param pedidos lista de entidades de tipo Pedido analizadas
     * @return EstadoComanda resultante; null si la lista se encuentra vacía
     */
    private EstadoComanda calcularEstadoComanda(List<Pedido> pedidos) {
        if (pedidos == null || pedidos.isEmpty()) {
            return null; 
        }

        boolean hayPendiente = false;
        boolean hayPreparacion = false;
        boolean hayLista = false;

        for (Pedido p : pedidos) {
            switch (p.getEstado()) {
                case PENDIENTE:
                    hayPendiente = true;
                    break;
                case EN_PREPARACION:
                    hayPreparacion = true;
                    break;
                case LISTA:
                    hayLista = true;
                    break;
                case ENTREGADO:
                    break;
            }
        }

        if (hayPendiente) {
            return EstadoComanda.PENDIENTE;
        }
        if (hayPreparacion) {
            return EstadoComanda.EN_PREPARACION;
        }
        if (hayLista) {
            return EstadoComanda.LISTA;
        }

        return EstadoComanda.ENTREGADA;
    }

    /**
     * Recalcula el estado operativo de una comanda y lo persiste de forma inmediata en la base de datos.
     *
     * @param comandaDTO comanda cuyos pedidos individuales sufrieron cambios de estatus
     * @throws NegocioException si ocurre un error de conversión o persistencia
     */
    public void recalcularYActualizarEstadoComanda(ComandaDTO comandaDTO) throws NegocioException {
        try {
            Comanda comanda = comandaAdapter.aEntidad(
                    comandaDTO.getNombreCliente(),
                    comandaDTO.getNumMesa(),
                    comandaDTO.getPedidos(),
                    null
            );

            EstadoComanda estado = calcularEstadoComanda(comanda.getPedidos());
            comandaDAO.actualizarEstado(comandaDTO.getId(), estado.name());

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al recalcular estado", e);
        }
    }

    /**
     * Calcula y muta internamente el importe monetario total de una comanda sumando los precios base de sus pedidos.
     *
     * @param comanda objeto de dominio de la comanda a totalizar
     */
    private void calcularMontoTotalComanda(Comanda comanda) {
        float total = 0;

        for (Pedido pedido : comanda.getPedidos()) {
            total += pedido.getPrecioProducto();
        }

        comanda.setMontoTotal(total);
    }

    /**
     * Cancela un pedido específico de una comanda, reintegrando las materias primas consumidas.
     * * El sistema restringe la cancelación únicamente a renglones con estado PENDIENTE.
     * Tras eliminar exitosamente el ítem de la comanda, se dispara una notificación de retorno de stock 
     * a la API de inventario. Si la comanda se queda sin pedidos activos, se destruye automáticamente
     * liberando la mesa física.
     *
     * @param idComanda identificador de la comanda contenedora
     * @param idPedido identificador único del pedido a revocar
     * @throws NegocioException si el pedido ya está en preparación, no existe o falla la BD
     */
    public void cancelarPedidoDeComanda(String idComanda, String idPedido) throws NegocioException {
        try {
            Comanda comanda = comandaDAO.obtenerPorId(idComanda);
            if (comanda == null) {
                throw new NegocioException("La comanda no existe");
            }

            Pedido pedidoCancelar = null;
            for (Pedido p : comanda.getPedidos()) {
                if (p.getId() != null && p.getId().equals(idPedido)) {
                    if (p.getEstado() != EstadoPedido.PENDIENTE) {
                        throw new NegocioException("Solo se pueden cancelar pedidos PENDIENTES");
                    }
                    pedidoCancelar = p;
                    break;
                }
            }

            if (pedidoCancelar == null) {
                throw new NegocioException("Pedido no encontrado");
            }

            boolean eliminado = comandaDAO.cancelarPedidoDeComanda(idComanda, idPedido);
            if (!eliminado) {
                throw new NegocioException("No se pudo cancelar el pedido");
            }

            // Regresar stock del pedido cancelado
            try {
                List<InventarioRequestDTO> stockARegresar = new ArrayList<>();
                
                InventarioRequestDTO dto = new InventarioRequestDTO();
                dto.setIdProducto(pedidoCancelar.getIdProducto());
                dto.setCantidad(pedidoCancelar.getCantidad());
                stockARegresar.add(dto);

                List<String> removidos = pedidoCancelar.getIngredientesRemovidos() != null
                        ? pedidoCancelar.getIngredientesRemovidos()
                        : new ArrayList<>();

                inventarioAPI.regresarStock(stockARegresar, removidos);

            } catch (InfraestructuraException e) {
                System.err.println("Advertencia: no se pudo regresar stock: " + e.getMessage());
            }

            Comanda actualizada = comandaDAO.obtenerPorId(idComanda);

            // Si no quedan pedidos, eliminar la comanda y liberar la mesa
            if (actualizada.getPedidos() == null || actualizada.getPedidos().isEmpty()) {
                comandaDAO.eliminarComanda(idComanda);
                
                List<Comanda> comandasRestantes = comandaDAO.obtenerComandasPorMesa(actualizada.getMesa().getNumero());
                if (comandasRestantes.isEmpty()) {
                    mesaDAO.cambiarEstadoMesaPorNumero(actualizada.getMesa().getNumero(), EstadoMesa.LIBRE);
                }
            } else {
                EstadoComanda estadoNuevo = calcularEstadoComanda(actualizada.getPedidos());
                
                comandaDAO.actualizarEstado(idComanda, estadoNuevo.name());
                comandaDAO.recalcularMonto(idComanda);
            }

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al cancelar pedido", e);
        }
    }

    /**
     * Modifica las especificaciones de un pedido en comanda siempre que su estado sea PENDIENTE.
     * * Transforma el DTO y actualiza de manera segura el ítem de la comanda, ordenando posteriormente
     * un recalculo financiero del documento para mantener la consistencia en caja.
     *
     * @param idComanda identificador de la comanda contenedora
     * @param pedidoDTO transferible que incluye los campos actualizados del pedido
     * @throws NegocioException si el pedido ya está en cocina, no existe o la actualización es rechazada
     */
    public void editarPedidoDeComanda(String idComanda, PedidoDTO pedidoDTO) throws NegocioException {
        try {
            Comanda comanda = comandaDAO.obtenerPorId(idComanda);
            if (comanda == null) {
                throw new NegocioException("La comanda no existe");
            }

            Pedido pedidoExistente = null;
            for (Pedido p : comanda.getPedidos()) {
                if (p.getId() != null && p.getId().equals(pedidoDTO.getId())) {
                    if (p.getEstado() != EstadoPedido.PENDIENTE) {
                        throw new NegocioException("Solo se pueden editar pedidos PENDIENTES");
                    }
                    pedidoExistente = p;
                    break;
                }
            }

            if (pedidoExistente == null) {
                throw new NegocioException("Pedido no encontrado");
            }

            Pedido pedidoActualizado = pedidoAdapter.aEntidad(pedidoDTO);
            
            boolean actualizado = comandaDAO.editarPedidoDeComanda(idComanda, pedidoActualizado);
            if (!actualizado) {
                throw new NegocioException("No se pudo editar el pedido");
            }

            comandaDAO.recalcularMonto(idComanda);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al editar pedido", e);
        }
    }
}