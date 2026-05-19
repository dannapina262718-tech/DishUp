/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import enums.EstadoPagoIndividual;
import enums.MetodoPago;
import java.time.LocalDateTime;

/**
 *
 * @author DishUp
 */
public class PagoDTO {
    private String id;
    private MetodoPago metodoPago;
    private float monto;
    private EstadoPagoIndividual estadoPago;
    private LocalDateTime fechaPago;
    private Object detalles;

    public PagoDTO() {
    }

    public PagoDTO(String id, MetodoPago metodoPago, float monto, EstadoPagoIndividual estadoPago, LocalDateTime fechaPago, Object detalles) {
        this.id = id;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.estadoPago = estadoPago;
        this.fechaPago = fechaPago;
        this.detalles = detalles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public EstadoPagoIndividual getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPagoIndividual estadoPago) {
        this.estadoPago = estadoPago;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Object getDetalles() {
        return detalles;
    }

    public void setDetalles(Object detalles) {
        this.detalles = detalles;
    }
}
 