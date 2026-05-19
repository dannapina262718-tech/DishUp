package entidades;

import enums.EstadoPedido;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad de dominio que representa un Pedido individual dentro de una comanda.
 * * Esta clase contiene la información detallada de un producto o platillo ordenado 
 * por un cliente, incluyendo la cantidad solicitada, especificaciones o notas de preparación,
 * la lista de ingredientes que el comensal solicitó remover, así como las marcas de tiempo 
 * y estados operativos necesarios para su seguimiento desde la cocina hasta su entrega en mesa.
 * * Pertenece al modelo de dominio central de la aplicación.
 * * @author DishUp
 * @author valeria
 */
public class Pedido {

    private String id;
    private String idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private String descripcion;
    private List<String> ingredientesRemovidos;
    private float precioProducto;
    private EstadoPedido estado;
    private LocalDateTime fechaPedido;

    /**
     * Constructor vacío requerido para marcos de trabajo de persistencia o serialización.
     */
    public Pedido() {
    }

    /**
     * Constructor completo para inicializar una instancia de Pedido con todos sus atributos.
     *
     * @param id identificador único del pedido (mapeado típicamente a un identificador hexadecimal BSON)
     * @param idProducto identificador único del producto base en el catálogo
     * @param nombreProducto nombre comercial del producto ordenado
     * @param cantidad número de unidades solicitadas de este producto
     * @param descripcion notas especiales de preparación o comentarios adjuntos por el cliente
     * @param ingredientesRemovidos listado de identificadores o nombres de ingredientes excluidos de la receta base
     * @param precioProducto importe monetario unitario o calculado del producto al momento de ordenar
     * @param estado estado de progreso actual del pedido (e.g., PENDIENTE, EN_PREPARACION, LISTO, ENTREGADO)
     * @param fechaPedido marca de tiempo exacta en la que se registró la orden en el sistema
     */
    public Pedido(String id, String idProducto, String nombreProducto, Integer cantidad, String descripcion, List<String> ingredientesRemovidos, float precioProducto, EstadoPedido estado, LocalDateTime fechaPedido) {
        this.id = id;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.ingredientesRemovidos = ingredientesRemovidos;
        this.precioProducto = precioProducto;
        this.estado = estado;
        this.fechaPedido = fechaPedido;
    }

    /**
     * Obtiene el identificador único del pedido.
     *
     * @return identificador del pedido
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único del pedido.
     *
     * @param id identificador del pedido
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del producto base asociado.
     *
     * @return identificador del producto comercial
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto base asociado.
     *
     * @param idProducto identificador del producto comercial
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el nombre comercial del producto ordenado.
     *
     * @return nombre del producto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Establece el nombre comercial del producto ordenado.
     *
     * @param nombreProducto nombre del producto
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene el número de unidades o porciones solicitadas.
     *
     * @return cantidad del pedido
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece el número de unidades o porciones solicitadas.
     *
     * @param cantidad cantidad del pedido
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene las especificaciones, comentarios o notas especiales de preparación.
     *
     * @return descripción o comentarios del pedido
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece las especificaciones, comentarios o notas especiales de preparación.
     *
     * @param descripcion descripción o comentarios del pedido
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el listado de ingredientes que deben ser excluidos de la preparación del platillo.
     *
     * @return lista de ingredientes removidos
     */
    public List<String> getIngredientesRemovidos() {
        return ingredientesRemovidos;
    }

    /**
     * Establece el listado de ingredientes que deben ser excluidos de la preparación del platillo.
     *
     * @param ingredientesRemovidos lista de ingredientes removidos
     */
    public void setIngredientesRemovidos(List<String> ingredientesRemovidos) {
        this.ingredientesRemovidos = ingredientesRemovidos;
    }

    /**
     * Obtiene el precio asignado al producto al momento de generar la orden.
     *
     * @return precio unitario o base del producto
     */
    public float getPrecioProducto() {
        return precioProducto;
    }

    /**
     * Establece el precio asignado al producto al momento de generar la orden.
     *
     * @param precioProducto precio unitario o base del producto
     */
    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * Obtiene el estado operativo actual en el que se encuentra el pedido dentro del flujo de cocina.
     *
     * @return estado del pedido en base al enumerador {@link EstadoPedido}
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Establece el estado operativo actual del pedido dentro del flujo de cocina.
     *
     * @param estado estado del pedido en base al enumerador {@link EstadoPedido}
     */
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la marca de tiempo (fecha y hora) en que se registró el pedido.
     *
     * @return fecha y hora del pedido
     */
    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Establece la marca de tiempo (fecha y hora) en que se registró el pedido.
     *
     * @param fechaPedido fecha y hora del pedido
     */
    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}