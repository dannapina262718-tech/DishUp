/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.EstadoComanda;
import java.time.LocalDateTime;
import java.util.List;

/**
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

    public Comanda() {
    }

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

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
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
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

}
