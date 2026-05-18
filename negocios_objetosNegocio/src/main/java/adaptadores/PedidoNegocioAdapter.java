/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import dtos.PedidoDTO;
import entidades.Pedido;
import enums.EstadoPedido;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DishUp
 */
public class PedidoNegocioAdapter {

    public Pedido aEntidad(PedidoDTO dto) {
        Pedido p = new Pedido();
        p.setIdProducto(dto.getIdProducto());
        p.setNombreProducto(dto.getNombreProducto());
        p.setCantidad(dto.getCantidad());
        p.setDescripcion(dto.getDescripcion());
        if (dto.getEstado() != null) {
            p.setEstado(
                    EstadoPedido.valueOf(dto.getEstado().name())
            );
        }else{
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

    public List<Pedido> listaAEntidad(List<PedidoDTO> dtos) {
        List<Pedido> pedidos = new ArrayList<>();
        for (PedidoDTO dto : dtos) {
            pedidos.add(aEntidad(dto));
        }
        return pedidos;
    }
}
