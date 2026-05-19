package entidades;

import enums.EstadoComanda;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Comanda.
 * Representa una comanda dentro del sistema en la capa de dominio.
 *
 * Esta entidad contiene toda la informacion principal de una comanda,
 * incluyendo el cliente, el empleado que la atiende, la mesa asignada,
 * los pedidos relacionados y los pagos realizados.
 *
 * Es una entidad central del sistema porque agrupa el flujo completo
 * de consumo dentro del restaurante.
 *
 * @author DishUp
 */
public class Comanda {

    private String id;
    private String nombreCliente;
    private Empleado empleado;
    private Mesa mesa;
    private LocalDateTime fecha;
    private float montoTotal;
    private EstadoComanda estado;
    private int tiempoEstimado;
    private List<Pedido> pedidos;
    private List<Pago> pagos;

    /**
     * Constructor por defecto.
     */
    public Comanda() {
    }
    /**
     * Constructor que inicializa todos los datos de la comanda.
     *
     * @param id identificador de la comanda
     * @param nombreCliente nombre del cliente
     * @param empleado empleado que atiende la comanda
     * @param mesa mesa asignada
     * @param fecha fecha y hora de la comanda
     * @param montoTotal total acumulado de la comanda
     * @param estado estado actual de la comanda
     * @param tiempoEstimado 
     * @param pedidos lista de pedidos asociados
     * @param pagos lista de pagos realizados
     */
    public Comanda(String id, String nombreCliente, Empleado empleado, Mesa mesa, LocalDateTime fecha, float montoTotal, EstadoComanda estado, int tiempoEstimado, List<Pedido> pedidos, List<Pago> pagos) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.empleado = empleado;
        this.mesa = mesa;
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
     * @param id nuevo identificador
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente asociado a la comanda.
     *
     * @return nombre del cliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Establece el nombre del cliente asociado a la comanda.
     *
     * @param nombreCliente nuevo nombre del cliente
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Obtiene el empleado que atiende la comanda.
     *
     * @return empleado asignado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Establece el empleado que atiende la comanda.
     *
     * @param empleado nuevo empleado asignado
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * Obtiene la mesa asignada a la comanda.
     *
     * @return mesa de la comanda
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Establece la mesa asignada a la comanda.
     *
     * @param mesa nueva mesa
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    /**
     * Obtiene la fecha de creación de la comanda.
     *
     * @return fecha y hora de la comanda
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la comanda.
     *
     * @param fecha nueva fecha
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el monto total de la comanda.
     *
     * @return total acumulado
     */
    public float getMontoTotal() {
        return montoTotal;
    }

    /**
     * Establece el monto total de la comanda.
     *
     * @param montoTotal nuevo total
     */
    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    /**
     * Obtiene el estado actual de la comanda.
     *
     * @return estado de la comanda
     */
    public EstadoComanda getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la comanda.
     *
     * @param estado nuevo estado
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
     * Obtiene la lista de pedidos de la comanda.
     *
     * @return lista de pedidos
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * Establece la lista de pedidos de la comanda.
     *
     * @param pedidos nuevos pedidos
     */
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    /**
     * Obtiene la lista de pagos de la comanda.
     *
     * @return lista de pagos
     */
    public List<Pago> getPagos() {
        return pagos;
    }

    /**
     * Establece la lista de pagos de la comanda.
     *
     * @param pagos nuevos pagos
     */
    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}
