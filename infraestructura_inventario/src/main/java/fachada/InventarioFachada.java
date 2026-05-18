/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import dtos_infraestructura.*;
import excepciones.InfraestructuraException;
import interfaces.ISistemaInventario;
import inventario.SistemaInventario;
import java.util.List;

/**
 *
 * @author DishUp
 */
public class InventarioFachada implements ISistemaInventario{
    private final SistemaInventario sistema;

    public InventarioFachada() {
        this.sistema = new SistemaInventario();
    }

    @Override
    public List<ProductoDTOInfraestructura> obtenerProductos() throws InfraestructuraException {
        return sistema.obtenerProductos();
    }

    @Override
    public ProductoDTOInfraestructura obtenerProductoPorId(String idProducto) throws InfraestructuraException {
        return sistema.obtenerProductoPorId(idProducto);
    }

    @Override
    public boolean descontarStock(List<InventarioRequestDTO> pedidos) throws InfraestructuraException {
        return sistema.descontarStock(pedidos);
    }

    @Override
    public boolean agregarStock(String ingrediente, int cantidad) throws InfraestructuraException {
        return sistema.agregarStock(ingrediente, cantidad);
    }
}
