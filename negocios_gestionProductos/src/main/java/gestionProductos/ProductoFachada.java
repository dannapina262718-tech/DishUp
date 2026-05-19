package gestionProductos;

import gestionProductos.ProductoControl;
import dtos.IngredienteEnProductoDTO;
import dtos.ProductoDTO;
import dtos.ProductoIngredienteDTO;
import enums.TipoProductoDTO;
import excepciones.ProductosException;
import fachada.InventarioFachada;
import interfaces.ISistemaInventario;
import gestionProductos.IGestionProductos;
import java.util.List;

/**
 * ProductoFachada.
 * Sirve como punto de acceso a las operaciones de productos.
 *
 * Esta clase conecta la capa de interfaz con la logica de control,
 * permitiendo obtener productos y consultar informacion relacionada
 * como ingredientes y tipos de producto.
 */
public class ProductoFachada implements IGestionProductos {

    private final ProductoControl productoControl;

    /**
     * Constructor que inicializa la fachada de productos.
     *
     * Se conecta con el sistema de inventario mediante la fachada correspondiente.
     */
    public ProductoFachada() {
        ISistemaInventario inventario = new InventarioFachada();
        this.productoControl = new ProductoControl(inventario);
    }

    /**
     * Obtiene productos filtrados por tipo.
     *
     * @param tipo tipo de producto (COMIDA, BEBIDA, BOTANA)
     * @return lista de productos del tipo solicitado
     * @throws ProductosException si ocurre un error al obtener los productos
     */
    @Override
    public List<ProductoDTO> obtenerProductosPorTipo(TipoProductoDTO tipo) throws ProductosException {
        return productoControl.obtenerProductosPorTipo(tipo);
    }

    /**
     * Obtiene los ingredientes que pueden ser removidos de un producto.
     *
     * @param idProducto identificador del producto
     * @return lista de ingredientes removibles
     * @throws ProductosException si ocurre un error al obtener los ingredientes
     */
    @Override
    public List<IngredienteEnProductoDTO> obtenerIngredientesRemovibles(String idProducto)
            throws ProductosException {
        return productoControl.obtenerIngredientesRemovibles(idProducto);
    }

    /**
     * Obtiene todos los ingredientes de un producto.
     *
     * @param idProducto identificador del producto
     * @return lista de ingredientes del producto
     * @throws ProductosException si ocurre un error al obtener los ingredientes
     */
    @Override
    public List<ProductoIngredienteDTO> obtenerIngredientesDeProducto(String idProducto)
            throws ProductosException {
        return productoControl.obtenerIngredientesDeProducto(idProducto);
    }
}