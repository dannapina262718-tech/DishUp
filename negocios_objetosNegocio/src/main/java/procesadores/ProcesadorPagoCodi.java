package procesadores;

import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import dtos_infraestructura.RespuestaCodiDTO;
import dtos_infraestructura.SolicitudCodiDTO;
import entidades.DetallePagoCodi;
import enums.MetodoPago;
import excepcion.NegocioException;
import excepciones.InfraestructuraTerminalException;
import terminal.SistemaTerminal;

/**
 * Procesador de pago para el método CoDi.
 *
 * Se encarga de validar la solicitud de pago, comunicarse con la terminal
 * de infraestructura y construir el resultado del pago en formato DTO.
 *
 * Implementa el patrón Strategy como parte del sistema de pagos.
 * 
 * @author valeria
 */
public class ProcesadorPagoCodi implements IProcesadorPago {

    private final SistemaTerminal terminal;

    public ProcesadorPagoCodi() {
        this.terminal = new SistemaTerminal();
    }

    /**
     * Procesa un pago mediante CoDi.
     *
     * Valida la solicitud, la envía a la terminal de infraestructura
     * y construye la respuesta del pago con base en la respuesta obtenida.
     *
     * @param solicitud datos del pago a procesar
     * @return resultado del pago procesado
     * @throws NegocioException si la solicitud es inválida o falla la comunicación con la terminal
     */
    @Override
    public ResultadoPagoDTO procesarPago(SolicitudPagoDTO solicitud) throws NegocioException {

        if (solicitud == null) {
            throw new NegocioException("La solicitud es inválida.");
        }

        if (solicitud.getMetodoPago() != MetodoPago.CODI) {
            throw new NegocioException("El método no corresponde a CoDi.");
        }

        if (solicitud.getMonto() <= 0) {
            throw new NegocioException("El monto debe ser mayor a cero.");
        }

        SolicitudCodiDTO solicitudCodi =
                new SolicitudCodiDTO(solicitud.getMonto());

        try {

            RespuestaCodiDTO respuesta =
                    terminal.cobrarCodi(solicitudCodi);

            DetallePagoCodi detalle = new DetallePagoCodi(
                    respuesta.getReferencia(),
                    respuesta.getFolio(),
                    respuesta.getQrBase64()
            );

            return new ResultadoPagoDTO(
                    respuesta.isAprobado(),
                    respuesta.getMensaje(),
                    solicitud.getMonto(),
                    MetodoPago.CODI,
                    detalle
            );

        } catch (InfraestructuraTerminalException ex) {
            throw new NegocioException(
                    "Error al conectar con CoDi.",
                    ex
            );
        }
    }
}