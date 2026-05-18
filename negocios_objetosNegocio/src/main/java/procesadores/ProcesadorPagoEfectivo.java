/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesadores;

import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import entidades.DetallePagoEfectivo;
import entidades.Pago;
import enums.EstadoPago;
import enums.MetodoPago;
import excepcion.NegocioException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author valeria
 */
public class ProcesadorPagoEfectivo implements IProcesadorPago {

    public ProcesadorPagoEfectivo() {
    }

    @Override
    public ResultadoPagoDTO procesarPago(SolicitudPagoDTO solicitud) throws NegocioException {
        if (solicitud == null) {
            throw new NegocioException("La solicitud de pago es inválida.");
        }

        if (solicitud.getIdComanda() == null || solicitud.getIdComanda().isBlank()) {
            throw new NegocioException("El id de la comanda es inválido.");
        }

        if (solicitud.getMetodoPago() != MetodoPago.EFECTIVO) {
            throw new NegocioException("El método de pago no corresponde a efectivo.");
        }

        if (solicitud.getMonto() <= 0) {
            throw new NegocioException("El monto a pagar debe ser mayor a cero.");
        }

        if (solicitud.getDetallePago() == null) {
            throw new NegocioException("El detalle del pago es obligatorio.");
        }

        if (!(solicitud.getDetallePago() instanceof DetallePagoEfectivo)) {
            throw new NegocioException("El detalle del pago efectivo es inválido.");
        }

        DetallePagoEfectivo detalle =
                (DetallePagoEfectivo) solicitud.getDetallePago();

        if (detalle.getMontoRecibido() <= 0) {
            throw new NegocioException("El monto recibido debe ser mayor a cero.");
        }

        if (detalle.getMontoRecibido() < solicitud.getMonto()) {
            throw new NegocioException("El monto recibido es insuficiente.");
        }

        float cambio = detalle.getMontoRecibido() - solicitud.getMonto();

        detalle.setCambio(cambio);

        return new ResultadoPagoDTO(
                true,
                "Pago en efectivo aprobado.",
                solicitud.getMonto(),
                MetodoPago.EFECTIVO,
                detalle
        );
    }
}
