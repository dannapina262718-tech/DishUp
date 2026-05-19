package entidades;

import enums.EstadoPedido;
import java.time.LocalDateTime;

/**
 * Pedido.
 * Representa un pedido dentro del sistema.
 *
 * Esta clase contiene la informacion de un producto ordenado por un cliente,
 * incluyendo cantidad, estado, descripcion y fecha del pedido.
 *
 * Se utiliza para dar seguimiento al flujo del pedido desde que se crea
 * hasta que se entrega.
 *
 * @author DishUp
 */
public class Pedido {

    private String id;
    private String idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private String descripcion;
    private float precioProducto;
    private EstadoPedido estado;
    private LocalDateTime fechaPedido;

    /**
     * Constructor vacio.
     */
    public Pedido() {
    }

    /**
     * Constructor con todos los atributos del pedido.
     *
     * @param id Identificador del pedido.
     * @param idProducto Identificador del producto.
     * @param nombreProducto Nombre del producto.
     * @param cantidad Cantidad solicitada.
     * @param descripcion Descripcion del pedido.
     * @param precioProducto Precio del producto.
     * @param estado Estado actual del pedido.
     * @param fechaPedido Fecha en la que se realizo el pedido.
     */
    public Pedido(String id, String idProducto, String nombreProducto, Integer cantidad, String descripcion, float precioProducto, EstadoPedido estado, LocalDateTime fechaPedido) {
        this.id = id;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precioProducto = precioProducto;
        this.estado = estado;
        this.fechaPedido = fechaPedido;
    }

    /**
     * Obtiene el id del pedido.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el id del pedido.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el id del producto.
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el id del producto.
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el nombre del producto.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Establece el nombre del producto.
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene la cantidad del pedido.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del pedido.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene la descripcion del pedido.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion del pedido.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto.
     */
    public float getPrecioProducto() {
        return precioProducto;
    }

    /**
     * Establece el precio del producto.
     */
    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * Obtiene el estado del pedido.
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Establece el estado del pedido.
     */
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha del pedido.
     */
    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Establece la fecha del pedido.
     */
    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}