/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import entidades.Pedido;
import enums.EstadoComanda;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;

/**
 *
 * @author DishUp
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
    

    public ComandaEntidadMongo() {
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public List<PedidoEntidadMongo> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoEntidadMongo> pedidos) {
        this.pedidos = pedidos;
    }

    public List<PagoEntidadMongo> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoEntidadMongo> pagos) {
        this.pagos = pagos;
    }
}
