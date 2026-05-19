package gestionProductos;

import dtos.IngredienteEnProductoDTO;
import dtos.ProductoDTO;
import dtos.ProductoIngredienteDTO;
import enums.TipoProductoDTO;
import excepcion.NegocioException;
import excepciones.ProductosException;
import interfaces.ISistemaInventario;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.ProductoBO;

/**
 * ProductoControl.
 * Controla las operaciones relacionadas con productos.
 *
 * Esta clase actua como intermediario entre la capa de presentacion
 * y la logica de negocio (ProductoBO).
 *
 * Se encarga de obtener productos, filtrarlos por tipo y consultar
 * ingredientes relacionados a cada producto.
 */
public class ProductoControl {

    private final ProductoBO productoBO;

    /**
     * Constructor que inicializa el control de productos.
     *
     * @param inventario sistema de inventario externo usado por la logica de negocio
     */
    protected ProductoControl(ISistemaInventario inventario) {
        this.productoBO = new ProductoBO(inventario);
    }

    /**
     * Obtiene productos filtrados por tipo.
     *
     * @param tipo tipo de producto (comida, bebida, botana)
     * @return lista de productos del tipo solicitado
     * @throws excepciones.ProductosException
     */
    protected List<ProductoDTO> obtenerProductosPorTipo(TipoProductoDTO tipo) throws ProductosException {
        try {
            return productoBO.obtenerProductosPorTipo(tipo);
        } catch (NegocioException ex) {
            throw new ProductosException("No fue posible obtener los productos del tipo seleccionado.");
        }
    }

    /**
     * Obtiene los ingredientes removibles de un producto.
     *
     * @param idProducto id del producto
     * @return lista de ingredientes que pueden ser removidos
     * @throws excepciones.ProductosException
     */
    protected List<IngredienteEnProductoDTO> obtenerIngredientesRemovibles(String idProducto) throws ProductosException {
        try {
            return productoBO.obtenerIngredientesRemoviblesPorProducto(idProducto);
        } catch (NegocioException ex) {
            throw new ProductosException("No fue posible obtener los ingredientes removibles del producto.");
        }
    }

    /**
     * Obtiene todos los ingredientes de un producto.
     *
     * @param idProducto id del producto
     * @return lista de ingredientes del producto
     * @throws excepciones.ProductosException
     */
    protected List<ProductoIngredienteDTO> obtenerIngredientesDeProducto(String idProducto) throws ProductosException {
        try {
            return productoBO.obtenerIngredientesDeProducto(idProducto);
        } catch (NegocioException ex) {
            throw new ProductosException("No fue posible obtener los ingredientes del producto.");
        }
    }

    /**
     * Obtiene todos los productos del sistema.Junta productos de comida, bebida y botana en una sola lista.
     *
     *
     * @return lista completa de productos
     * @throws excepciones.ProductosException
     */
    protected List<ProductoDTO> obtenerTodos() throws ProductosException {

        List<ProductoDTO> lista = new ArrayList<>();

        lista.addAll(obtenerProductosPorTipo(TipoProductoDTO.COMIDA));
        lista.addAll(obtenerProductosPorTipo(TipoProductoDTO.BEBIDA));
        lista.addAll(obtenerProductosPorTipo(TipoProductoDTO.BOTANA));

        return lista;
    }
}