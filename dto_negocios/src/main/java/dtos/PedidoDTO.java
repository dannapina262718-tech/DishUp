package dtos;

import enums.EstadoPedidoDTO;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Objeto de transferencia de datos (DTO) que representa un pedido dentro del sistema del restaurante.
 *
 * Este DTO transporta la información de un pedido entre las distintas capas de la aplicación,
 * incluyendo producto, cantidad, estado, observaciones del cliente, ingredientes removidos,
 * precio y fecha de creación.
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
     * @param id identificador del pedido
     * @param idProducto identificador del producto solicitado
     * @param nombreProducto nombre del producto solicitado
     * @param cantidad cantidad solicitada
     * @param estado estado actual del pedido
     * @param descripcion observaciones o especificaciones del pedido
     * @param ingredientesRemovidos lista de ingredientes removidos del producto
     * @param precioProducto precio del producto
     * @param fechaPedido fecha y hora del pedido
     */
    public PedidoDTO(String id, String idProducto, String nombreProducto, Integer cantidad,
                     EstadoPedidoDTO estado, String descripcion,
                     List<String> ingredientesRemovidos, float precioProducto,
                     LocalDateTime fechaPedido) {

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
     * @return id del pedido
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del pedido.
     *
     * @param id nuevo identificador del pedido
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del producto.
     *
     * @return id del producto
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto.
     *
     * @param idProducto nuevo identificador del producto
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return nombre del producto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombreProducto nuevo nombre del producto
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene la cantidad solicitada.
     *
     * @return cantidad del pedido
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad solicitada.
     *
     * @param cantidad nueva cantidad del pedido
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el estado del pedido.
     *
     * @return estado del pedido
     */
    public EstadoPedidoDTO getEstado() {
        return estado;
    }

    /**
     * Establece el estado del pedido.
     *
     * @param estado nuevo estado del pedido
     */
    public void setEstado(EstadoPedidoDTO estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la descripción del pedido.
     *
     * @return descripción del pedido
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del pedido.
     *
     * @param descripcion nueva descripción del pedido
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene los ingredientes removidos del pedido.
     *
     * @return lista de ingredientes removidos
     */
    public List<String> getIngredientesRemovidos() {
        return ingredientesRemovidos;
    }

    /**
     * Establece los ingredientes removidos del pedido.
     *
     * @param ingredientesRemovidos lista de ingredientes removidos
     */
    public void setIngredientesRemovidos(List<String> ingredientesRemovidos) {
        this.ingredientesRemovidos = ingredientesRemovidos;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return precio del producto
     */
    public float getPrecioProducto() {
        return precioProducto;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precioProducto nuevo precio del producto
     */
    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * Obtiene la fecha del pedido.
     *
     * @return fecha del pedido
     */
    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Establece la fecha del pedido.
     *
     * @param fechaPedido nueva fecha del pedido
     */
    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /**
     * Devuelve una representación en texto del pedido.
     *
     * @return cadena con información del pedido
     */
    @Override
    public String toString() {

        if (descripcion == null || descripcion.isBlank()) {
            return nombreProducto + " $" + precioProducto;
        }

        return nombreProducto + " (" + descripcion + ") $" + precioProducto;
    }
}