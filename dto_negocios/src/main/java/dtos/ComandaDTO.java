/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dtos;

import enums.EstadoComandaDTO;
import java.time.LocalDateTime;
import java.util.List;


/**
 * ComandaDTO.
 * Representa la informacion de una comanda que ya fue registrada en el sistema.
 *
 * Este DTO contiene los datos principales de la comanda, como su identificador,
 * su estado actual y los pedidos que la conforman, permitiendo consultar
 * o dar seguimiento a su proceso dentro del sistema.
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

    public ComandaDTO() {
    }

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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public EstadoComandaDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoComandaDTO estado) {
        this.estado = estado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public Integer getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(Integer mesa) {
        this.numMesa = mesa;
    }

    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public List<PagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }
    
}