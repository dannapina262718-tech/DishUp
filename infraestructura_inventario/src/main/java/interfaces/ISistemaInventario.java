package interfaces;

import dtos_infraestructura.*;
import excepciones.InfraestructuraException;
import java.util.List;

/**
 * ISistemaInventario. Interfaz que define las operaciones de comunicación con
 * el sistema de inventario.
 *
 * Se encarga de permitir el acceso a productos y la gestión de stock desde la
 * capa de infraestructura, manteniendo desacoplada la lógica del sistema
 * principal.
 *
 * @author DishUp
 */
public interface ISistemaInventario {

    /**
     * Obtiene la lista de productos disponibles en el sistema de inventario.
     *
     * @return Lista de productos en formato ProductoDTOInfraestructura.
     * @throws InfraestructuraException si ocurre un error al consultar el
     * inventario.
     */
    List<ProductoDTOInfraestructura> obtenerProductos() throws InfraestructuraException;

    /**
     * Obtiene un producto específico por su identificador.
     *
     * @param idProducto Identificador del producto.
     * @return Producto correspondiente al id.
     * @throws InfraestructuraException si ocurre un error o no se encuentra el
     * producto.
     */
    ProductoDTOInfraestructura obtenerProductoPorId(String idProducto) throws InfraestructuraException;

    /**
     * Descuenta stock de los productos en el inventario según los pedidos
     * recibidos.
     *
     * @param pedidos Lista de solicitudes de descuento de inventario.
     * @return true si el stock se descontó correctamente.
     * @throws InfraestructuraException si ocurre un error al actualizar el
     * inventario.
     */
    boolean descontarStock(List<InventarioRequestDTO> pedidos) throws InfraestructuraException;

    /**
     * Regresa stock de una lista de productos al inventario.
     *
     * @param pedidos lista de productos con cantidades a regresar.
     * @param ingredientesRemovidos ingredientes que no se regresan.
     * @return true si la operacion fue exitosa.
     * @throws InfraestructuraException si ocurre un error de comunicacion.
     */
    boolean regresarStock(List<InventarioRequestDTO> pedidos, List<String> ingredientesRemovidos) throws InfraestructuraException;
}
