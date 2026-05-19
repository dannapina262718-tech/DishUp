package gestionProductos;

import dtos.ProductoDTO;
import dtos.IngredienteEnProductoDTO;
import dtos.ProductoIngredienteDTO;
import enums.TipoProductoDTO;
import excepciones.ProductosException;

import java.util.List;

/**
 * IGestionProductos.
 * Define las operaciones disponibles para la gestion de productos.
 *
 * Esta interfaz establece los metodos que permiten consultar productos,
 * obtener ingredientes y filtrar informacion relacionada con el menu.
 */
public interface IGestionProductos {

    /**
     * Obtiene una lista de productos filtrados por tipo.
     *
     * @param tipo tipo de producto (COMIDA, BEBIDA, BOTANA)
     * @return lista de productos del tipo solicitado
     * @throws ProductosException si ocurre un error al obtener los productos
     */
    List<ProductoDTO> obtenerProductosPorTipo(TipoProductoDTO tipo) throws ProductosException;

    /**
     * Obtiene los ingredientes que pueden ser removidos de un producto.
     *
     * @param idProducto identificador del producto
     * @return lista de ingredientes removibles
     * @throws ProductosException si ocurre un error al consultar los ingredientes
     */
    List<IngredienteEnProductoDTO> obtenerIngredientesRemovibles(String idProducto) throws ProductosException;
            
    /**
     * Obtiene todos los ingredientes asociados a un producto.
     *
     * @param idProducto identificador del producto
     * @return lista de ingredientes del producto
     * @throws ProductosException si ocurre un error al consultar los ingredientes
     */
    List<ProductoIngredienteDTO> obtenerIngredientesDeProducto(String idProducto) throws ProductosException;     
}