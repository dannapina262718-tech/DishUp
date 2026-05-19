package control;

import dtos.ComandaDTO;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import dtos.PedidoDTO;
import enums.EstadoPedidoDTO;
import excepcion.NegocioException;
import excepciones.ComandasException;
import java.util.List;
import objetosNegocio.ComandaBO;

/**
 * ComandaControl. Clase de control encargada de validar y gestionar las
 * operaciones relacionadas con las comandas dentro del sistema.
 *
 * Esta clase actua como intermediario entre la capa de presentacion y la capa
 * de negocio (ComandaBO), validando la informacion antes de enviarla y
 * manejando las excepciones del sistema.
 *
 * @author DishUp
 */
public class ComandaControl {

    private final ComandaBO comandaBO;

    /**
     * Constructor que inicializa la capa de negocio de comandas.
     */
    public ComandaControl() {
        this.comandaBO = new ComandaBO();
    }

    /**
     * Crea una nueva comanda en el sistema.
     *
     * Valida que el nombre del cliente no este vacio y que la comanda contenga
     * al menos un pedido valido antes de enviarla a la capa de negocio.
     *
     * @param nombreCliente Nombre del cliente asociado a la comanda.
     * @param numeroMesa Numero de la mesa asignada.
     * @param pedidos Lista de pedidos que conforman la comanda.
     * @param empleadoActual Empleado que registra la comanda.
     * @throws ComandasException Si los datos son invalidos o ocurre un error en
     * el proceso.
     */
    public void crearComanda(String nombreCliente, int numeroMesa,
            List<PedidoDTO> pedidos, EmpleadoDTO empleadoActual) throws ComandasException {

        if (nombreCliente == null || nombreCliente.isBlank()) {
            throw new ComandasException("El nombre del cliente es obligatorio");
        }

        if (pedidos == null || pedidos.isEmpty()) {
            throw new ComandasException("La comanda debe tener al menos un pedido");
        }

        for (PedidoDTO p : pedidos) {
            if (p.getIdProducto() == null || p.getIdProducto().isBlank()) {
                throw new ComandasException("El pedido '" + p.getNombreProducto() + "' no tiene producto asignado");
            }
        }

        try {
            comandaBO.crearComanda(nombreCliente, numeroMesa, pedidos, empleadoActual);
        } catch (NegocioException ex) {
            throw new ComandasException(ex.getMessage());
        }
    }

    /**
     * Obtiene todas las comandas asociadas a una mesa.
     *
     * @param numeroMesa Numero de la mesa a consultar.
     * @return Lista de comandas encontradas.
     * @throws ComandasException Si el numero de mesa es invalido o ocurre un
     * error.
     */
    public List<ComandaDTO> obtenerComandasPorMesa(int numeroMesa) throws ComandasException {

        if (numeroMesa <= 0) {
            throw new ComandasException("Número de mesa inválido");
        }

        return comandaBO.obtenerComandasPorMesa(numeroMesa);
    }

    /**
     * Agrega uno o varios pedidos a una comanda existente.
     *
     * Valida que el id de la comanda sea correcto y que los pedidos contengan
     * informacion valida antes de enviarlos a negocio.
     *
     * @param idComanda Identificador de la comanda.
     * @param pedidos Lista de pedidos a agregar.
     * @throws ComandasException Si los datos son invalidos o falla la
     * operacion.
     */
    public void agregarPedidoAComanda(String idComanda, List<PedidoDTO> pedidos) throws ComandasException {

        if (idComanda == null || idComanda.isBlank()) {
            throw new ComandasException("Id de comanda inválido");
        }

        if (pedidos == null || pedidos.isEmpty()) {
            throw new ComandasException("Debe agregar al menos un pedido");
        }

        for (PedidoDTO p : pedidos) {
            if (p.getIdProducto() == null || p.getIdProducto().isBlank()) {
                throw new ComandasException("El pedido '" + p.getNombreProducto() + "' no tiene producto asignado");
            }
        }

        try {
            comandaBO.agregarPedidosAComanda(idComanda, pedidos);
        } catch (NegocioException ex) {
            throw new ComandasException("No fue posible agregar pedidos: " + ex.getMessage());
        }
    }

    /**
     * Actualiza la informacion completa de una comanda.
     *
     * @param comanda Objeto comanda con los datos actualizados.
     * @throws ComandasException Si la comanda es invalida o ocurre un error.
     */
    public void actualizarComanda(ComandaDTO comanda) throws ComandasException {

        if (comanda == null || comanda.getId() == null || comanda.getId().isBlank()) {
            throw new ComandasException("Comanda inválida");
        }

        if (comanda.getPedidos() == null || comanda.getPedidos().isEmpty()) {
            throw new ComandasException("La comanda debe tener al menos un pedido");
        }

        try {
            comandaBO.actualizarComanda(comanda);
        } catch (NegocioException ex) {
            throw new ComandasException("No fue posible actualizar la comanda: " + ex.getMessage());
        }
    }

    /**
     * Elimina una comanda siempre que todos sus pedidos esten en estado
     * PENDIENTE.
     *
     * @param idComanda Identificador de la comanda a eliminar.
     * @param mesa a la que se le puede hacer el cambio de estado
     * @return true si la eliminacion fue exitosa.
     * @throws ComandasException Si la comanda no es valida o no cumple las
     * condiciones.
     */
    public boolean eliminarComanda(String idComanda, MesaDTO mesa) throws ComandasException {

        if (idComanda == null || idComanda.isBlank()) {
            throw new ComandasException("Id de comanda inválido");
        }

        try {
            ComandaDTO comanda = comandaBO.obtenerComandaPorId(idComanda);

            if (comanda == null) {
                throw new ComandasException("La comanda no existe");
            }

            boolean todosPendientes = true;

            for (PedidoDTO pedido : comanda.getPedidos()) {
                if (pedido.getEstado() != EstadoPedidoDTO.PENDIENTE) {
                    todosPendientes = false;
                    break;
                }
            }

            if (!todosPendientes) {
                throw new ComandasException("Solo se puede eliminar si todos los pedidos están en PENDIENTE");
            }

            return comandaBO.eliminarComanda(idComanda, mesa);

        } catch (NegocioException ex) {
            throw new ComandasException("No fue posible eliminar la comanda: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todas las comandas que se encuentran en estado LISTA.
     *
     * @return Lista de comandas listas.
     * @throws ComandasException Si ocurre un error en la consulta.
     */
    public List<ComandaDTO> obtenerComandasListas() throws ComandasException {

        try {
            return comandaBO.obtenerComandasListas();
        } catch (NegocioException ex) {
            throw new ComandasException("No fue posible obtener las comandas listas");
        }
    }

    /**
     * Marca una comanda como entregada, actualizando los pedidos en estado
     * LISTA.
     *
     * @param idComanda Identificador de la comanda.
     * @throws ComandasException Si la comanda no existe o no hay pedidos
     * listos.
     */
    public void entregarComanda(String idComanda) throws ComandasException {

        try {
            ComandaDTO comanda = comandaBO.obtenerComandaPorId(idComanda);

            if (comanda == null) {
                throw new ComandasException("La comanda no existe");
            }

            boolean hayListos = false;

            for (PedidoDTO p : comanda.getPedidos()) {
                if (p.getEstado() == EstadoPedidoDTO.LISTA) {
                    hayListos = true;
                    break;
                }
            }

            if (!hayListos) {
                throw new ComandasException("No hay pedidos listos");
            }

            for (PedidoDTO p : comanda.getPedidos()) {
                if (p.getEstado() == EstadoPedidoDTO.LISTA) {
                    p.setEstado(EstadoPedidoDTO.ENTREGADO);
                }
            }

            // persistir pedidos
            comandaBO.actualizarComanda(comanda);

        } catch (NegocioException ex) {
            throw new ComandasException("Error al entregar comanda: " + ex.getMessage());
        }
    }

    public void cancelarPedidoDeComanda(String idComanda, String idPedido) throws ComandasException {

        if (idComanda == null || idComanda.isBlank()) {
            throw new ComandasException("Id comanda inválido");
        }

        if (idPedido == null || idPedido.isBlank()) {
            throw new ComandasException("Id pedido inválido");
        }

        try {

            comandaBO.cancelarPedidoDeComanda(idComanda, idPedido);

        } catch (NegocioException ex) {
            throw new ComandasException(ex.getMessage());
        }
    }

    public void editarPedidoDeComanda(String idComanda, PedidoDTO pedido) throws ComandasException {

        if (idComanda == null || idComanda.isBlank()) {
            throw new ComandasException("Id comanda inválido");
        }

        if (pedido == null) {
            throw new ComandasException("Pedido inválido");
        }

        try {
            comandaBO.editarPedidoDeComanda(idComanda, pedido);

        } catch (NegocioException ex) {
            throw new ComandasException(ex.getMessage());
        }
    }
}
