package fachada;

import dtos_infraestructura.*;
import excepciones.InfraestructuraException;
import interfaces.ISistemaInventario;
import inventario.SistemaInventario;
import java.util.List;

/**
 * InventarioFachada. Clase fachada que expone las operaciones del sistema de
 * inventario hacia el resto del sistema.
 *
 * Esta clase actua como intermediario entre la logica del sistema principal y
 * el sistema externo de inventario, delegando las operaciones a la clase
 * SistemaInventario.
 *
 * @author DishUp
 */
public class InventarioFachada implements ISistemaInventario {

    private final SistemaInventario sistema;

    /**
     * Constructor que inicializa la conexion con el sistema de inventario.
     */
    public InventarioFachada() {
        this.sistema = new SistemaInventario();
    }

    /**
     * Obtiene todos los productos disponibles en inventario.
     *
     * @return lista de productos del sistema externo.
     * @throws InfraestructuraException si ocurre un error de comunicacion.
     */
    @Override
    public List<ProductoDTOInfraestructura> obtenerProductos()
            throws InfraestructuraException {
        return sistema.obtenerProductos();
    }

    /**
     * Obtiene un producto especifico por su id.
     *
     * @param idProducto identificador del producto.
     * @return producto encontrado o null si no existe.
     * @throws InfraestructuraException si ocurre un error de comunicacion.
     */
    @Override
    public ProductoDTOInfraestructura obtenerProductoPorId(String idProducto)
            throws InfraestructuraException {
        return sistema.obtenerProductoPorId(idProducto);
    }

    /**
     * Descuenta stock de varios productos en inventario.
     *
     * @param pedidos lista de productos con cantidades a descontar.
     * @return true si la operacion fue exitosa.
     * @throws InfraestructuraException si ocurre un error de comunicacion.
     */
    @Override
    public boolean descontarStock(List<InventarioRequestDTO> pedidos)
            throws InfraestructuraException {
        return sistema.descontarStock(pedidos);
    }
    
    /**
     * Regresa stock de una lista de productos al inventario.
     *
     * @param pedidos lista de productos con cantidades a regresar.
     * @param ingredientesRemovidos ingredientes que no se regresan.
     * @return true si la operacion fue exitosa.
     * @throws InfraestructuraException si ocurre un error de comunicacion.
     */
    @Override
    public boolean regresarStock(List<InventarioRequestDTO> pedidos, List<String> ingredientesRemovidos) throws InfraestructuraException {
        return sistema.regresarStock(pedidos, ingredientesRemovidos);
    }
}
