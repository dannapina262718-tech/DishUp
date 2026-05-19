package dtos_infraestructura;

import java.util.List;

/**
 * InventarioRequestDTO.
 * Representa la solicitud de inventario enviada hacia un sistema externo.
 *
 * Este DTO se utiliza para mandar informacion desde el sistema principal
 * hacia la infraestructura externa relacionada con inventario.
 *
 * Contiene el identificador del producto y la cantidad requerida
 * para actualizar o consultar el stock.
 *
 * @author DishUp
 */
public class InventarioRequestDTO {

    private String idProducto;
    private Integer cantidad;
    private List<String> ingredientesRemovidos;

    /**
     * Constructor por defecto.
     */
    public InventarioRequestDTO() {
    }

    public InventarioRequestDTO(String idProducto, Integer cantidad, List<String> ingredientesRemovidos) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.ingredientesRemovidos = ingredientesRemovidos;
    }

    
    /**
     * Obtiene el identificador del producto.
     *
     * @return id del producto.
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto.
     *
     * @param idProducto nuevo id del producto.
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene la cantidad solicitada o a procesar.
     *
     * @return cantidad de producto.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad solicitada o a procesar.
     *
     * @param cantidad nueva cantidad.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<String> getIngredientesRemovidos() {
        return ingredientesRemovidos;
    }

    public void setIngredientesRemovidos(List<String> ingredientesRemovidos) {
        this.ingredientesRemovidos = ingredientesRemovidos;
    }
    
}