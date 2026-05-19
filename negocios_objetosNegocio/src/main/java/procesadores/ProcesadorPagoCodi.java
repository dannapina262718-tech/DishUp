/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesadores;

import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import dtos_infraestructura.RespuestaCodiDTO;
import dtos_infraestructura.SolicitudCodiDTO;
import entidades.DetallePagoCodi;
import entidades.Pago;
import enums.MetodoPago;
import excepcion.NegocioException;
import excepciones.InfraestructuraTerminalException;
import terminal.SistemaTerminal;

/**
 *
 * @author valeria
 */
public class ProcesadorPagoCodi implements IProcesadorPago {
    private final SistemaTerminal terminal;
    
    public ProcesadorPagoCodi() {
        this.terminal = new SistemaTerminal();
    }

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
                new SolicitudCodiDTO(
                        solicitud.getMonto()
                );

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
