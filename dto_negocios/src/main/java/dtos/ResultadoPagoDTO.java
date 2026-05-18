/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import entidades.DetallePago;
import enums.MetodoPago;

/**
 *
 * @author valeria
 */
public class ResultadoPagoDTO {
    private boolean aprobado;
    private String mensaje;
    private float montoPagado;
    private MetodoPago metodoPago;
    private DetallePago detallePago;
    
    private float saldoRestante;

    public ResultadoPagoDTO() {
    }

    public ResultadoPagoDTO(boolean aprobado, String mensaje, float montoPagado, MetodoPago metodoPago, DetallePago detallePago) {
        this.aprobado = aprobado;
        this.mensaje = mensaje;
        this.montoPagado = montoPagado;
        this.metodoPago = metodoPago;
        this.detallePago = detallePago;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public float getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(float montoPagado) {
        this.montoPagado = montoPagado;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public DetallePago getDetallePago() {
        return detallePago;
    }

    public void setDetallePago(DetallePago detallePago) {
        this.detallePago = detallePago;
    }

    public float getSaldoRestante() {
        return saldoRestante;
    }

    public void setSaldoRestante(float saldoRestante) {
        this.saldoRestante = saldoRestante;
    }
    
}
