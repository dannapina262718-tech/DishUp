package gestionComandas;

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
 * Clase de control encargada de validar y gestionar las operaciones relacionadas con las comandas.
 *
 * Esta clase actúa como intermediario entre la capa de presentación y la capa de negocio (ComandaBO),
 * validando datos de entrada, aplicando reglas de negocio básicas y gestionando excepciones.
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
     * @param nombreCliente nombre del cliente asociado a la comanda
     * @param numeroMesa número de la mesa asignada
     * @param pedidos lista de pedidos que conforman la comanda
     * @param empleadoActual empleado que registra la comanda
     * @throws ComandasException si los datos son inválidos o ocurre un error en el proceso
     */
    protected void crearComanda(String nombreCliente, int numeroMesa,
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
     * @param numeroMesa número de la mesa a consultar
     * @return lista de comandas encontradas
     * @throws ComandasException si el número de mesa es inválido o ocurre un error
     */
    protected List<ComandaDTO> obtenerComandasPorMesa(int numeroMesa) throws ComandasException {

        if (numeroMesa <= 0) {
            throw new ComandasException("Número de mesa inválido");
        }

        return comandaBO.obtenerComandasPorMesa(numeroMesa);
    }

    /**
     * Agrega pedidos a una comanda existente.
     *
     * @param idComanda identificador de la comanda
     * @param pedidos lista de pedidos a agregar
     * @throws ComandasException si los datos son inválidos o la operación falla
     */
    protected void agregarPedidoAComanda(String idComanda, List<PedidoDTO> pedidos) throws ComandasException {

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
     * Actualiza una comanda existente.
     *
     * @param comanda objeto comanda con datos actualizados
     * @throws ComandasException si la comanda es inválida o ocurre un error
     */
    protected void actualizarComanda(ComandaDTO comanda) throws ComandasException {

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
     * Elimina una comanda si todos sus pedidos están en estado PENDIENTE.
     *
     * @param idComanda identificador de la comanda
     * @param mesa mesa asociada a la comanda
     * @return true si la eliminación fue exitosa
     * @throws ComandasException si la comanda no es válida o no cumple condiciones
     */
    protected boolean eliminarComanda(String idComanda, MesaDTO mesa) throws ComandasException {

        if (idComanda == null || idComanda.isBlank()) {
            throw new ComandasException("Id de comanda inválido");
        }

        try {
            ComandaDTO comanda = comandaBO.obtenerComandaPorId(idComanda);

            if (comanda == null) {
                throw new ComandasException("La comanda no existe");
            }

            for (PedidoDTO pedido : comanda.getPedidos()) {
                if (pedido.getEstado() != EstadoPedidoDTO.PENDIENTE) {
                    throw new ComandasException("Solo se puede eliminar si todos los pedidos están en PENDIENTE");
                }
            }

            return comandaBO.eliminarComanda(idComanda, mesa);

        } catch (NegocioException ex) {
            throw new ComandasException("No fue posible eliminar la comanda: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todas las comandas en estado LISTA.
     *
     * @return lista de comandas listas
     * @throws ComandasException si ocurre un error en la consulta
     */
    protected List<ComandaDTO> obtenerComandasListas() throws ComandasException {

        try {
            return comandaBO.obtenerComandasListas();
        } catch (NegocioException ex) {
            throw new ComandasException("No fue posible obtener las comandas listas");
        }
    }

    /**
     * Marca una comanda como entregada.
     *
     * @param idComanda identificador de la comanda
     * @throws ComandasException si la comanda no existe o no tiene pedidos listos
     */
    protected void entregarComanda(String idComanda) throws ComandasException {

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

            comandaBO.actualizarComanda(comanda);

        } catch (NegocioException ex) {
            throw new ComandasException("Error al entregar comanda: " + ex.getMessage());
        }
    }

    /**
     * Obtiene una comanda por su identificador.
     *
     * @param idComanda identificador de la comanda
     * @return comanda encontrada
     * @throws ComandasException si ocurre un error en la consulta
     */
    protected ComandaDTO obtenerComandaPorId(String idComanda) throws ComandasException {

        try {
            return comandaBO.obtenerComandaPorId(idComanda);
        } catch (NegocioException ex) {
            throw new ComandasException("Error al obtener comanda: " + ex.getMessage());
        }
    }

    /**
     * Cancela un pedido dentro de una comanda.
     *
     * @param idComanda identificador de la comanda
     * @param idPedido identificador del pedido
     * @throws ComandasException si los datos son inválidos o falla la operación
     */
    protected void cancelarPedidoDeComanda(String idComanda, String idPedido) throws ComandasException {

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

    /**
     * Edita un pedido dentro de una comanda.
     *
     * @param idComanda identificador de la comanda
     * @param pedido pedido con información actualizada
     * @throws ComandasException si los datos son inválidos o falla la operación
     */
    protected void editarPedidoDeComanda(String idComanda, PedidoDTO pedido) throws ComandasException {

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