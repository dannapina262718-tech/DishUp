package dtos_infraestructura;

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

    /**
     * Constructor por defecto.
     */
    public InventarioRequestDTO() {
    }

    /**
     * Constructor que inicializa la solicitud de inventario.
     *
     * @param idProducto Identificador del producto.
     * @param cantidad Cantidad solicitada o a modificar.
     */
    public InventarioRequestDTO(String idProducto, Integer cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
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
}