package adaptadores;

import dtos.ComandaDTO;
import dtos.EmpleadoDTO;
import dtos.PagoDTO;
import dtos.PedidoDTO;
import entidades.Comanda;
import entidades.Pago;
import entidades.Pedido;
import enums.EstadoComanda;
import enums.EstadoPedido;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ComandaNegocioAdapter.
 * 
 * Clase adaptadora encargada de convertir objetos de la capa de
 * negocio (entidades) a objetos DTO y viceversa.
 * 
 * Su funcion es desacoplar la logica interna del sistema de las
 * estructuras utilizadas para transporte de datos entre capas,
 * facilitando la comunicacion entre la capa de dominio y las capas
 * superiores o externas.
 * 
 * Permite transformar la entidad {@link Comanda} en {@link ComandaDTO}
 * y construir una entidad a partir de datos recibidos en DTOs.
 * 
 * @author DishUp
 */
public class ComandaNegocioAdapter {

    /**
     * Convierte una entidad Comanda a su representacion DTO.
     *
     * @param comanda entidad de negocio
     * @return objeto DTO con la informacion de la comanda
     */
    public ComandaDTO aDTO(Comanda comanda) {

        if (comanda == null) {
            return null;
        }

        ComandaDTO dto = new ComandaDTO();

        dto.setId(comanda.getId());
        dto.setNombreCliente(comanda.getNombreCliente());
        dto.setNombreEmpleado(
                comanda.getEmpleado().getNombres()
                + comanda.getEmpleado().getApellidoPaterno()
                + comanda.getEmpleado().getApellidoMaterno()
        );

        if (comanda.getFecha() != null) {
            dto.setFecha(comanda.getFecha());
        }

        dto.setTotal(comanda.getMontoTotal());

        dto.setEstado(
                enums.EstadoComandaDTO.valueOf(
                        comanda.getEstado().name()
                )
        );

        if (comanda.getMesa() != null) {
            dto.setNumMesa(comanda.getMesa().getNumero());
        }

        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        if (comanda.getPedidos() != null) {

            for (Pedido pedido : comanda.getPedidos()) {

                PedidoDTO pedidoDTO = new PedidoDTO();

                pedidoDTO.setId(pedido.getId());
                pedidoDTO.setIdProducto(pedido.getIdProducto());
                pedidoDTO.setNombreProducto(pedido.getNombreProducto());
                pedidoDTO.setCantidad(pedido.getCantidad());
                pedidoDTO.setDescripcion(pedido.getDescripcion());
                pedidoDTO.setPrecioProducto(pedido.getPrecioProducto());
                pedidoDTO.setFechaPedido(pedido.getFechaPedido());
                pedidoDTO.setEstado(
                        enums.EstadoPedidoDTO.valueOf(
                                pedido.getEstado().name()
                        )
                );

                pedidosDTO.add(pedidoDTO);
            }
        }

        dto.setPedidos(pedidosDTO);

        List<PagoDTO> pagosDTO = new ArrayList<>();

        if (comanda.getPagos() != null) {

            for (Pago pago : comanda.getPagos()) {

                PagoDTO pagoDTO = new PagoDTO();

                pagoDTO.setId(pago.getId());
                pagoDTO.setMetodoPago(pago.getMetodoPago());
                pagoDTO.setMonto(pago.getMonto());
                pagoDTO.setEstadoPago(pago.getEstadoPago());
                pagoDTO.setFechaPago(pago.getFechaPago());
                pagoDTO.setDetalles(pago.getDetalles());

                pagosDTO.add(pagoDTO);
            }
        }

        dto.setPagos(pagosDTO);

        return dto;
    }

    /**
     * Convierte datos recibidos en DTOs a una entidad Comanda.
     *
     * @param nombreCliente nombre del cliente
     * @param numeroMesa numero de la mesa asignada
     * @param pedidosDTO lista de pedidos en formato DTO
     * @param empleadoDTO empleado que atiende la comanda
     * @return entidad Comanda construida
     */
    public Comanda aEntidad( String nombreCliente, int numeroMesa, List<PedidoDTO> pedidosDTO, EmpleadoDTO empleadoDTO ) {

        Comanda comanda = new Comanda();

        comanda.setNombreCliente(nombreCliente);
        comanda.setFecha(LocalDateTime.now());
        comanda.setEstado(EstadoComanda.PENDIENTE);

        // Mesa
        entidades.Mesa mesa = new entidades.Mesa();
        mesa.setNumero(numeroMesa);
        comanda.setMesa(mesa);

        // Empleado
        EmpleadoNegocioAdapter empleadoAdapter = new EmpleadoNegocioAdapter();
        comanda.setEmpleado(empleadoAdapter.aEntidad(empleadoDTO));

        // Pedidos
        List<Pedido> pedidos = new ArrayList<>();

        if (pedidosDTO != null) {
            for (PedidoDTO dto : pedidosDTO) {

                Pedido pedido = new Pedido();

                pedido.setId(dto.getId());
                pedido.setNombreProducto(dto.getNombreProducto());
                pedido.setIdProducto(dto.getIdProducto());
                pedido.setCantidad(dto.getCantidad());
                pedido.setDescripcion(dto.getDescripcion());
                pedido.setEstado(EstadoPedido.PENDIENTE);
                pedido.setFechaPedido(LocalDateTime.now());
                pedido.setPrecioProducto(dto.getPrecioProducto());

                pedidos.add(pedido);
            }
        }

        comanda.setPedidos(pedidos);

        return comanda;
    }
}