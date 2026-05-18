package control;

import dtos.ComandaDTO;
import dtos.EmpleadoDTO;
import dtos.PedidoDTO;
import enums.EstadoPedidoDTO;
import excepcion.NegocioException;
import excepciones.ComandasException;
import java.util.List;
import objetosNegocio.ComandaBO;

public class ComandaControl {

    private final ComandaBO comandaBO;

    public ComandaControl() {
        this.comandaBO = new ComandaBO();
    }

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
            throw new ComandasException("Error al crear la comanda: " + ex.getMessage());
        }
    }

    public List<ComandaDTO> obtenerComandasPorMesa(int numeroMesa) throws ComandasException {
        if (numeroMesa <= 0) {
            throw new ComandasException("Número de mesa inválido");
        }
        return comandaBO.obtenerComandasPorMesa(numeroMesa);
    }

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

    public boolean eliminarComanda(String idComanda) throws ComandasException {
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
            return comandaBO.eliminarComanda(idComanda);
        } catch (NegocioException ex) {
            throw new ComandasException("No fue posible eliminar la comanda: " + ex.getMessage());
        }
    }

    public List<ComandaDTO> obtenerComandasListas() throws ComandasException {
        try {
            return comandaBO.obtenerComandasListas();
        } catch (NegocioException ex) {
            throw new ComandasException("No fue posible obtener las comandas listas");
        }
    }

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

            // recalcular estado 
            comandaBO.recalcularYActualizarEstadoComanda(comanda);

        } catch (NegocioException ex) {
            throw new ComandasException("Error al entregar comanda: " + ex.getMessage());
        }
    }
}
