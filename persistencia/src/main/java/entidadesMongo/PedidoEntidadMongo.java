package entidadesMongo;

import enums.EstadoPedido;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 * Entidad de persistencia Pedido para MongoDB.
 * * Esta clase representa cómo se almacena un pedido dentro de la base de datos.
 * Contiene la información del producto solicitado, cantidad, precio, estado del pedido
 * y la fecha en la que fue realizado.
 * * Se utiliza únicamente en la capa de persistencia y no debe contener lógica de negocio.
 */
public class PedidoEntidadMongo {

    @BsonId
    @BsonRepresentation(BsonType.STRING)
    private String id;

    private String idProducto;
    private String nombreProducto;
    private int cantidad;
    private String descripcion;
    private float precioProducto;
    private EstadoPedido estado;
    private LocalDateTime fechaPedido;
    private List<String> ingredientesRemovidos;

    /**
     * Constructor vacío requerido por MongoDB.
     */
    public PedidoEntidadMongo() {
    }

    /**
     * Constructor completo de PedidoEntidadMongo.
     *
     * @param id identificador del pedido
     * @param idProducto identificador del producto
     * @param nombreProducto nombre del producto
     * @param cantidad cantidad solicitada
     * @param descripcion descripción adicional del pedido
     * @param precioProducto precio unitario del producto
     * @param estado estado actual del pedido
     * @param fechaPedido fecha en la que se realizó el pedido
     * @param ingredientesRemovidos lista de ingredientes removidos del producto
     */
    public PedidoEntidadMongo(String id, String idProducto, String nombreProducto, int cantidad, String descripcion, float precioProducto, EstadoPedido estado, LocalDateTime fechaPedido, List<String> ingredientesRemovidos) {    
        this.id = id;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precioProducto = precioProducto;
        this.estado = estado;
        this.fechaPedido = fechaPedido;
        this.ingredientesRemovidos = ingredientesRemovidos;
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
     * @param id identificador del pedido
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
     * @param idProducto id del producto
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
     * @param nombreProducto nombre del producto
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene la cantidad solicitada.
     *
     * @return cantidad del pedido
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad solicitada.
     *
     * @param cantidad cantidad del pedido
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
     * @param descripcion descripción del pedido
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * @param precioProducto precio del producto
     */
    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * Obtiene el estado del pedido.
     *
     * @return estado del pedido
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Establece el estado del pedido.
     *
     * @param estado estado del pedido
     */
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha en que se realizó el pedido.
     *
     * @return fecha del pedido
     */
    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Establece la fecha del pedido.
     *
     * @param fechaPedido fecha del pedido
     */
    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /**
     * Obtiene la lista de ingredientes que fueron removidos del producto.
     *
     * @return lista de ingredientes removidos
     */
    public List<String> getIngredientesRemovidos() {
        return ingredientesRemovidos;
    }

    /**
     * Establece la lista de ingredientes que se deben remover del producto.
     *
     * @param ingredientesRemovidos lista de ingredientes a remover
     */
    public void setIngredientesRemovidos(List<String> ingredientesRemovidos) {
        this.ingredientesRemovidos = ingredientesRemovidos;
    }
}