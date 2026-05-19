package adaptadores;

import dtos.PedidoDTO;
import entidades.Pedido;
import enums.EstadoPedido;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * PedidoNegocioAdapter.
 * 
 * Clase encargada de convertir objetos PedidoDTO a entidades de negocio
 * Pedido.
 * 
 * Su funcion es adaptar la informacion de los pedidos recibidos desde
 * las capas superiores o de transporte de datos hacia la capa de dominio,
 * asegurando consistencia en los valores por defecto como estado y fecha.
 * 
 * Permite tambien convertir listas completas de pedidos.
 * 
 * @author valeria
 */
public class PedidoNegocioAdapter {

    /**
     * Convierte un PedidoDTO a una entidad Pedido.
     *
     * @param dto objeto DTO del pedido
     * @return entidad Pedido construida
     */
    public Pedido aEntidad(PedidoDTO dto) {

        Pedido p = new Pedido();

        p.setId(dto.getId());
        p.setIdProducto(dto.getIdProducto());
        p.setNombreProducto(dto.getNombreProducto());
        p.setCantidad(dto.getCantidad());
        p.setDescripcion(dto.getDescripcion());

        if (dto.getEstado() != null) {
            p.setEstado(
                    EstadoPedido.valueOf(dto.getEstado().name())
            );
        } else {
            p.setEstado(EstadoPedido.PENDIENTE);
        }

        p.setFechaPedido(
                dto.getFechaPedido() != null
                        ? dto.getFechaPedido()
                        : LocalDateTime.now()
        );

        p.setPrecioProducto(dto.getPrecioProducto());

        return p;
    }

    /**
     * Convierte una lista de PedidoDTO a una lista de entidades Pedido.
     *
     * @param dtos lista de pedidos en formato DTO
     * @return lista de entidades Pedido
     */
    public List<Pedido> listaAEntidad(List<PedidoDTO> dtos) {

        List<Pedido> pedidos = new ArrayList<>();

        if (dtos == null) {
            return pedidos;
        }

        for (PedidoDTO dto : dtos) {
            pedidos.add(aEntidad(dto));
        }

        return pedidos;
    }
}