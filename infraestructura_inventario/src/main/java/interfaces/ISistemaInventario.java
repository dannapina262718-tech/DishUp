/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos_infraestructura.*;
import java.util.List;
import excepciones.InfraestructuraException;

/**
 *
 * @author DishUp
 */
public interface ISistemaInventario {

    List<ProductoDTOInfraestructura> obtenerProductos() throws InfraestructuraException;

    ProductoDTOInfraestructura obtenerProductoPorId(String idProducto) throws InfraestructuraException;

    boolean descontarStock(List<InventarioRequestDTO> pedidos) throws InfraestructuraException;

    boolean agregarStock(String ingrediente, int cantidad) throws InfraestructuraException;

}
