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

public class ComandaBO {

    private final IComandaDAO comandaDAO;
    private final IMesaDAO mesaDAO;
    private final ComandaNegocioAdapter comandaAdapter;
    private final MesaNegocioAdapter mesaAdapter;
    private final PedidoNegocioAdapter pedidoAdapter;
    private final InventarioFachada inventarioAPI;
    private final ProductoBO productoBO;
    private final MesaBO mesaBO;

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

    public void crearComanda(String nombreCliente, int numeroMesa, List<PedidoDTO> pedidosDTO, EmpleadoDTO empleadoActual) throws NegocioException {
        try {
            procesarComanda(pedidosDTO);

            for (PedidoDTO pedido : pedidosDTO) {
                pedido.setEstado(EstadoPedidoDTO.PENDIENTE);
            }

            Comanda comanda = comandaAdapter.aEntidad(nombreCliente, numeroMesa, pedidosDTO, empleadoActual);

            comanda.setEstado(calcularEstadoComanda(comanda.getPedidos()));
            Comanda insertada = comandaDAO.insertarComanda(comanda);

            if (insertada == null) {
                throw new NegocioException("la mesa no se logro insertar");
            }

            comandaDAO.recalcularMonto(insertada.getId());

            mesaDAO.cambiarEstadoMesaPorNumero(comanda.getMesa().getNumero(), EstadoMesa.OCUPADA);

        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public void procesarComanda(List<PedidoDTO> pedidos) throws NegocioException {
        List<InventarioRequestDTO> inventarioList = new ArrayList<>();
        for (PedidoDTO pedido : pedidos) {
            productoBO.obtenerIngredientesDeProducto(pedido.getIdProducto());
            InventarioRequestDTO dto = new InventarioRequestDTO();
            dto.setIdProducto(pedido.getIdProducto());
            dto.setCantidad(pedido.getCantidad());
            inventarioList.add(dto);
        }
//        try {
//           // inventarioAPI.descontarStock(inventarioList);
//            System.out.println("DESCONTANTO STOCK");
//        } catch (InfraestructuraException e) {
//            throw new NegocioException("Error al descontar stock", e);
//        }
    }

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

    public void agregarPedidosAComanda(String idComanda, List<PedidoDTO> pedidosDTO) throws NegocioException {

        try {

            Comanda comandaActual = comandaDAO.obtenerPorId(idComanda);

            if (comandaActual == null) {
                throw new NegocioException("No se encontró la comanda");
            }

            for (PedidoDTO pedido : pedidosDTO) {
                pedido.setEstado(EstadoPedidoDTO.PENDIENTE);
            }

            procesarComanda(pedidosDTO);

            List<Pedido> pedidosActuales = comandaActual.getPedidos();

            List<Pedido> pedidosNuevos = pedidoAdapter.listaAEntidad(pedidosDTO);

            for (Pedido nuevo : pedidosNuevos) {

                if (nuevo.getEstado() == null) {
                    nuevo.setEstado(EstadoPedido.PENDIENTE);
                }

                Pedido existente = null;

                for (Pedido actual : pedidosActuales) {

                    String descActual = "";
                    String descNuevo = "";

                    if (actual.getDescripcion() != null) {
                        descActual = actual.getDescripcion().trim().toLowerCase();
                    }

                    if (nuevo.getDescripcion() != null) {
                        descNuevo = nuevo.getDescripcion().trim().toLowerCase();
                    }

                    boolean mismoProducto = actual.getIdProducto().trim().equalsIgnoreCase(nuevo.getIdProducto().trim());

                    boolean mismaDesc = descActual.equals(descNuevo);

                    boolean pedidoEditable = actual.getEstado() == EstadoPedido.PENDIENTE;

                    if (mismoProducto && mismaDesc && pedidoEditable) {
                        existente = actual;
                        break;
                    }
                }

                if (existente != null) {

                    existente.setCantidad(existente.getCantidad() + nuevo.getCantidad());

                } else {

                    boolean ok = comandaDAO.agregarPedidoAComanda(
                            idComanda,
                            nuevo
                    );

                    if (!ok) {
                        throw new NegocioException("No se pudo agregar el pedido");
                    }

                    pedidosActuales.add(nuevo);
                }
            }

            comandaDAO.actualizarComanda(idComanda, pedidosActuales);
            comandaDAO.recalcularMonto(idComanda);
            EstadoComanda nuevoEstado = calcularEstadoComanda(pedidosActuales);
            comandaDAO.actualizarEstado(idComanda, nuevoEstado.name());

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al agregar pedidos", e);
        }
    }

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

    private EstadoComanda calcularEstadoComanda(List<Pedido> pedidos) {

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

    public void cancelarPedidoDeComanda(String idComanda, String idPedido) throws NegocioException {

        try {

            Comanda comanda = comandaDAO.obtenerPorId(idComanda);

            if (comanda == null) {
                throw new NegocioException("La comanda no existe");
            }

            Pedido pedidoCancelar = null;

            for (Pedido p : comanda.getPedidos()) {

                if (p.getId().equals(idPedido)) {

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

            Comanda actualizada = comandaDAO.obtenerPorId(idComanda);

            EstadoComanda estadoNuevo = calcularEstadoComanda(actualizada.getPedidos());

            comandaDAO.actualizarEstado(idComanda, estadoNuevo.name());

            comandaDAO.recalcularMonto(idComanda);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al cancelar pedido", e);
        }
    }

    public void editarPedidoDeComanda(String idComanda, PedidoDTO pedidoDTO) throws NegocioException {

        try {
            Comanda comanda = comandaDAO.obtenerPorId(idComanda);

            if (comanda == null) {
                throw new NegocioException("La comanda no existe");
            }

            Pedido pedidoExistente = null;

            for (Pedido p : comanda.getPedidos()) {

                if (p.getId().equals(pedidoDTO.getId())) {

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
