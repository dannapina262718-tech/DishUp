package dtos;

import enums.EstadoPedidoDTO;
import java.time.LocalDateTime;
import java.util.List;

/**
 * PedidoDTO.
 * Representa la informacion de un pedido dentro del sistema.
 *
 * Este DTO se utiliza para transportar los datos de un pedido
 * entre las distintas capas de la aplicacion.
 *
 * Contiene informacion del producto solicitado, la cantidad,
 * el estado actual del pedido, observaciones realizadas por el cliente,
 * el precio del producto y la fecha en la que se realizo el pedido.
 *
 * @author DishUp
 */
public class PedidoDTO {

    private String id;
    private String idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private EstadoPedidoDTO estado;
    private String descripcion;
    private List<String> ingredientesRemovidos;
    private float precioProducto;
    private LocalDateTime fechaPedido;

    /**
     * Constructor que permite inicializar todos los atributos del pedido.
     *
     * @param id Identificador del pedido.
     * @param idProducto Identificador del producto solicitado.
     * @param nombreProducto Nombre del producto solicitado.
     * @param cantidad Cantidad solicitada.
     * @param estado Estado actual del pedido.
     * @param descripcion Observaciones o especificaciones del pedido.
     * @param precioProducto Precio del producto.
     * @param fechaPedido Fecha y hora en la que se realizo el pedido.
     */
    public PedidoDTO(String id, String idProducto, String nombreProducto, Integer cantidad, EstadoPedidoDTO estado, String descripcion, List<String> ingredientesRemovidos, float precioProducto, LocalDateTime fechaPedido) {    
        this.id = id;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.estado = estado;
        this.descripcion = descripcion;
        this.ingredientesRemovidos = ingredientesRemovidos;
        this.precioProducto = precioProducto;
        this.fechaPedido = fechaPedido;
    }

    /**
     * Constructor por defecto.
     */
    public PedidoDTO() {
    }

    /**
     * Obtiene el identificador del pedido.
     *
     * @return Identificador del pedido.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del pedido.
     *
     * @param id Nuevo identificador del pedido.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del producto solicitado.
     *
     * @return Identificador del producto.
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto solicitado.
     *
     * @param idProducto Nuevo identificador del producto.
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el nombre del producto solicitado.
     *
     * @return Nombre del producto.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Establece el nombre del producto solicitado.
     *
     * @param nombreProducto Nuevo nombre del producto.
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene la cantidad solicitada.
     *
     * @return Cantidad del producto.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad solicitada.
     *
     * @param cantidad Nueva cantidad del producto.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el estado actual del pedido.
     *
     * @return Estado del pedido.
     */
    public EstadoPedidoDTO getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual del pedido.
     *
     * @param estado Nuevo estado del pedido.
     */
    public void setEstado(EstadoPedidoDTO estado) {
        this.estado = estado;
    }

    /**
     * Obtiene las observaciones o especificaciones del pedido.
     *
     * @return Descripcion del pedido.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece las observaciones o especificaciones del pedido.
     *
     * @param descripcion Nueva descripcion del pedido.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto solicitado.
     *
     * @return Precio del producto.
     */
    public float getPrecioProducto() {
        return precioProducto;
    }

    /**
     * Establece el precio del producto solicitado.
     *
     * @param precioProducto Nuevo precio del producto.
     */
    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * Obtiene la fecha y hora en la que se realizo el pedido.
     *
     * @return Fecha del pedido.
     */
    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Establece la fecha y hora en la que se realizo el pedido.
     *
     * @param fechaPedido Nueva fecha del pedido.
     */
    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public List<String> getIngredientesRemovidos() {
        return ingredientesRemovidos;
    }

    public void setIngredientesRemovidos(List<String> ingredientesRemovidos) {
        this.ingredientesRemovidos = ingredientesRemovidos;
    }
    
    /**
     * Devuelve una representacion en texto del pedido.
     *
     * Si el pedido contiene descripcion, esta se muestra junto
     * al nombre y precio del producto.
     *
     * @return Cadena con la informacion del pedido.
     */
    @Override
    public String toString() {

        if (descripcion == null || descripcion.isBlank()) {
            return nombreProducto + " $" + precioProducto;
        }

        return nombreProducto + " (" + descripcion + ") $" + precioProducto;
    }
}