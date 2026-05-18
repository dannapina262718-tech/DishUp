package interfaz;

import dtos.ProductoDTO;
import dtos.IngredienteEnProductoDTO;
import dtos.ProductoIngredienteDTO;
import enums.TipoProductoDTO;
import excepciones.ProductosException;

import java.util.List;

public interface IGestionProductos {

    List<ProductoDTO> obtenerProductosPorTipo(TipoProductoDTO tipo) throws ProductosException;

    List<IngredienteEnProductoDTO> obtenerIngredientesRemovibles(String idProducto) throws ProductosException;

    List<ProductoIngredienteDTO> obtenerIngredientesDeProducto(String idProducto) throws ProductosException;
    
    //List<ProductoDTO> obtenerTodos() throws ProductosException;
}