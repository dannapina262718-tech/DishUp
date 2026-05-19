package entidadesMongo;

import enums.EstadoComanda;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 * Entidad de persistencia Comanda para MongoDB.
 * 
 * Esta clase representa cómo se almacena una comanda dentro de la base de datos.
 * Contiene la información principal del pedido del cliente, incluyendo datos del cliente,
 * empleado, mesa, estado, fecha, monto total, así como la lista de pedidos y pagos asociados.
 * 
 * Se utiliza únicamente para la capa de persistencia y no debe contener lógica de negocio.
 */
public class ComandaEntidadMongo {

    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;

    private String nombreCliente;
    private String nombreEmpleado;
    private Integer numeroMesa;
    private LocalDateTime fecha;
    private float montoTotal;
    private EstadoComanda estado;
    private int tiempoEstimado;

    private List<PedidoEntidadMongo> pedidos;
    private List<PagoEntidadMongo> pagos;
    

    /**
     * Constructor vacío requerido por MongoDB.
     */
    public ComandaEntidadMongo() {
    }

    /**
     * Constructor completo de ComandaEntidadMongo.
     *
     * @param id identificador único de la comanda
     * @param nombreCliente nombre del cliente asociado
     * @param nombreEmpleado nombre del empleado que atendió la comanda
     * @param numeroMesa número de mesa asignada
     * @param fecha fecha y hora de la comanda
     * @param montoTotal total a pagar de la comanda
     * @param estado estado actual de la comanda
     * @param tiempoEstimado
     * @param pedidos lista de pedidos asociados a la comanda
     * @param pagos lista de pagos asociados a la comanda
     */
    public ComandaEntidadMongo(String id, String nombreCliente, String nombreEmpleado, Integer numeroMesa, LocalDateTime fecha, float montoTotal, EstadoComanda estado, int tiempoEstimado, List<PedidoEntidadMongo> pedidos, List<PagoEntidadMongo> pagos) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.nombreEmpleado = nombreEmpleado;
        this.numeroMesa = numeroMesa;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.estado = estado;
        this.tiempoEstimado = tiempoEstimado;
        this.pedidos = pedidos;
        this.pagos = pagos;
    }

    /**
     * Obtiene el identificador de la comanda.
     *
     * @return id de la comanda
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador de la comanda.
     *
     * @param id identificador de la comanda
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return nombre del cliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombreCliente nombre del cliente
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Obtiene el nombre del empleado que atendió la comanda.
     *
     * @return nombre del empleado
     */
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    /**
     * Establece el nombre del empleado.
     *
     * @param nombreEmpleado nombre del empleado
     */
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    /**
     * Obtiene el número de mesa asignada.
     *
     * @return número de mesa
     */
    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    /**
     * Establece el número de mesa asignada.
     *
     * @param numeroMesa número de mesa
     */
    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    /**
     * Obtiene la fecha de la comanda.
     *
     * @return fecha de la comanda
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la comanda.
     *
     * @param fecha fecha de la comanda
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el monto total de la comanda.
     *
     * @return monto total
     */
    public float getMontoTotal() {
        return montoTotal;
    }

    /**
     * Establece el monto total de la comanda.
     *
     * @param montoTotal monto total
     */
    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    /**
     * Obtiene el estado de la comanda.
     *
     * @return estado de la comanda
     */
    public EstadoComanda getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la comanda.
     *
     * @param estado estado de la comanda
     */
    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    /**
     * Obtiene la lista de pedidos asociados a la comanda.
     *
     * @return lista de pedidos
     */
    public List<PedidoEntidadMongo> getPedidos() {
        return pedidos;
    }

    /**
     * Establece la lista de pedidos asociados a la comanda.
     *
     * @param pedidos lista de pedidos
     */
    public void setPedidos(List<PedidoEntidadMongo> pedidos) {
        this.pedidos = pedidos;
    }

    /**
     * Obtiene la lista de pagos asociados a la comanda.
     *
     * @return lista de pagos
     */
    public List<PagoEntidadMongo> getPagos() {
        return pagos;
    }

    /**
     * Establece la lista de pagos asociados a la comanda.
     *
     * @param pagos lista de pagos
     */
    public void setPagos(List<PagoEntidadMongo> pagos) {
        this.pagos = pagos;
    }
}