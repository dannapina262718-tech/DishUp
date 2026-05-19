package procesadores;

import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import entidades.DetallePagoEfectivo;
import enums.MetodoPago;
import excepcion.NegocioException;

/**
 * Procesador de pagos en efectivo.
 * <p>
 * Valida el monto recibido, calcula el cambio y construye
 * el resultado del pago sin depender de infraestructura externa.
 * </p>
 * 
 * @author valeria
 */
public class ProcesadorPagoEfectivo implements IProcesadorPago {

    public ProcesadorPagoEfectivo() {
    }

    /**
     * Procesa un pago en efectivo.
     *
     * @param solicitud datos del pago en efectivo
     * @return resultado del pago con cambio calculado
     * @throws NegocioException si la solicitud es inválida o el monto es incorrecto
     */
    @Override
    public ResultadoPagoDTO procesarPago(SolicitudPagoDTO solicitud) throws NegocioException {

        if (solicitud == null) {
            throw new NegocioException("La solicitud de pago es inválida.");
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