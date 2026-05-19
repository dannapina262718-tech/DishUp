package dtos;

import enums.EstadoComandaDTO;
import java.time.LocalDateTime;
import java.util.List;


/**
 * ComandaDTO.
 * Representa la informacion de una comanda dentro del sistema.
 *
 * Este DTO se utiliza para transportar los datos de una comanda entre
 * las distintas capas de la aplicacion.
 *
 * Contiene informacion general como el identificador de la comanda,
 * el nombre del cliente, el empleado responsable, la fecha,
 * el total acumulado, el estado actual y el numero de mesa.
 *
 * Tambien almacena los pedidos y pagos relacionados con la comanda,
 * permitiendo consultar toda la informacion necesaria de la misma.
 *
 * @author DishUp
 */
public class ComandaDTO {
    
    private String id;
    private String nombreCliente;
    private String nombreEmpleado;
    private LocalDateTime fecha;
    private float total;
    private EstadoComandaDTO estado;
    private int tiempoEstimado;
    private Integer numMesa;
    private List<PedidoDTO> pedidos;
    private List<PagoDTO> pagos;

    /**
     * Constructor por defecto.
     */
    public ComandaDTO() {
    }
    /**
     * Constructor que permite inicializar todos los atributos de la comanda.
     *
     * @param id Identificador de la comanda.
     * @param nombreCliente Nombre del cliente asociado.
     * @param nombreEmpleado Nombre del empleado responsable.
     * @param fecha Fecha y hora de registro de la comanda.
     * @param total Total acumulado de la comanda.
     * @param estado Estado actual de la comanda.
     * @param numMesa Numero de mesa asignado.
     * @param pedidos Lista de pedidos de la comanda.
     * @param pagos Lista de pagos realizados.
     */
    public ComandaDTO(String id, String nombreCliente, String nombreEmpleado, LocalDateTime fecha, float total, EstadoComandaDTO estado, int tiempoEstimado, Integer numMesa, List<PedidoDTO> pedidos, List<PagoDTO> pagos) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.nombreEmpleado = nombreEmpleado;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.tiempoEstimado = tiempoEstimado;
        this.numMesa = numMesa;
        this.pedidos = pedidos;
        this.pagos = pagos;
    }

    public ComandaDTO(String id, String nombreCliente, String nombreEmpleado, LocalDateTime fecha, float total, EstadoComandaDTO estado, Integer numMesa, List<PedidoDTO> pedidos, List<PagoDTO> pagos) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.nombreEmpleado = nombreEmpleado;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.numMesa = numMesa;
        this.pedidos = pedidos;
        this.pagos = pagos;
    }
    /**
     * Obtiene el identificador de la comanda.
     *
     * @return Identificador de la comanda.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador de la comanda.
     *
     * @param id Nuevo identificador de la comanda.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombreCliente Nuevo nombre del cliente.
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Obtiene el nombre del empleado responsable.
     *
     * @return Nombre del empleado.
     */
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    /**
     * Establece el nombre del empleado responsable.
     *
     * @param nombreEmpleado Nuevo nombre del empleado.
     */
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    /**
     * Obtiene la fecha de registro de la comanda.
     *
     * @return Fecha de la comanda.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de registro de la comanda.
     *
     * @param fecha Nueva fecha de la comanda.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el total acumulado de la comanda.
     *
     * @return Total de la comanda.
     */
    public float getTotal() {
        return total;
    }

    /**
     * Establece el total acumulado de la comanda.
     *
     * @param total Nuevo total de la comanda.
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * Obtiene el estado actual de la comanda.
     *
     * @return Estado de la comanda.
     */
    public EstadoComandaDTO getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la comanda.
     *
     * @param estado Nuevo estado de la comanda.
     */
    public void setEstado(EstadoComandaDTO estado) {
        this.estado = estado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    /**
     * Obtiene el numero de mesa asignado.
     *
     * @return Numero de mesa.
     */
    public Integer getNumMesa() {
        return numMesa;
    }

    /**
     * Establece el numero de mesa asignado.
     *
     * @param mesa Nuevo numero de mesa.
     */
    public void setNumMesa(Integer mesa) {
        this.numMesa = mesa;
    }

    /**
     * Obtiene la lista de pedidos de la comanda.
     *
     * @return Lista de pedidos.
     */
    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }

    /**
     * Establece la lista de pedidos de la comanda.
     *
     * @param pedidos Nueva lista de pedidos.
     */
    public void setPedidos(List<PedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }

    /**
     * Obtiene la lista de pagos realizados.
     *
     * @return Lista de pagos.
     */
    public List<PagoDTO> getPagos() {
        return pagos;
    }

    /**
     * Establece la lista de pagos realizados.
     *
     * @param pagos Nueva lista de pagos.
     */
    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }
    
}