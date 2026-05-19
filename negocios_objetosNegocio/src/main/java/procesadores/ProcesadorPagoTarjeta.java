package procesadores;

import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import dtos_infraestructura.RespuestaTerminalDTO;
import dtos_infraestructura.SolicitudTerminalDTO;
import entidades.DetallePagoTarjeta;
import enums.MetodoPago;
import excepcion.NegocioException;
import excepciones.InfraestructuraTerminalException;
import fachada.TerminalFachada;
import interfaces.ISistemaTerminal;

/**
 * Procesador de pagos con tarjeta.
 * <p>
 * Encapsula la comunicación con la terminal bancaria, valida la solicitud
 * y transforma la respuesta en un DTO de resultado estándar.
 * </p>
 * 
 * @author valeria
 */
public class ProcesadorPagoTarjeta implements IProcesadorPago {

    private final ISistemaTerminal terminal;

    public ProcesadorPagoTarjeta() {
        this.terminal = new TerminalFachada();
    }

    /**
     * Procesa un pago con tarjeta bancaria.
     *
     * @param solicitud información del pago a procesar
     * @return resultado del pago con estado de aprobación y detalle de transacción
     * @throws NegocioException si la solicitud es inválida o falla la comunicación con la terminal
     */
    @Override
    public ResultadoPagoDTO procesarPago(SolicitudPagoDTO solicitud) throws NegocioException {

        if (solicitud == null) {
            throw new NegocioException("La solicitud de pago es inválida.");
        }

        if (solicitud.getIdComanda() == null || solicitud.getIdComanda().isBlank()) {
            throw new NegocioException("El id de la comanda es inválido.");
        }

        if (solicitud.getMetodoPago() != MetodoPago.TARJETA) {
            throw new NegocioException("El método de pago no corresponde a tarjeta.");
        }

        if (solicitud.getMonto() <= 0) {
            throw new NegocioException("El monto a pagar debe ser mayor a cero.");
        }

        try {
            SolicitudTerminalDTO solicitudTerminal =
                    new SolicitudTerminalDTO(solicitud.getMonto());

            RespuestaTerminalDTO respuesta =
                    terminal.cobrarTarjeta(solicitudTerminal);

            if (respuesta == null) {
                throw new NegocioException("La terminal no regresó respuesta.");
            }

            if (!respuesta.isAprobado()) {
                return new ResultadoPagoDTO(
                        false,
                        respuesta.getMensaje(),
                        solicitud.getMonto(),
                        MetodoPago.TARJETA,
                        null
                );
            }

            DetallePagoTarjeta detalle = new DetallePagoTarjeta(
                    respuesta.getNumeroAutorizacion(),
                    respuesta.getUltimos4Digitos(),
                    respuesta.getBanco(),
                    respuesta.getTipoTarjeta()
            );

            return new ResultadoPagoDTO(
                    true,
                    respuesta.getMensaje(),
                    solicitud.getMonto(),
                    MetodoPago.TARJETA,
                    detalle
            );

        } catch (InfraestructuraTerminalException ex) {
            throw new NegocioException(
                    "Error al conectar con la terminal bancaria.",
                    ex
            );
        }
    }
}