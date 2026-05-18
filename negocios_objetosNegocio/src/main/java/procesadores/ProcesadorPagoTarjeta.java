/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesadores;

import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import dtos_infraestructura.RespuestaTerminalDTO;
import dtos_infraestructura.SolicitudTerminalDTO;
import entidades.DetallePagoTarjeta;
import entidades.Pago;
import enums.MetodoPago;
import excepcion.NegocioException;
import excepciones.InfraestructuraException;
import excepciones.InfraestructuraTerminalException;
import fachada.TerminalFachada;
import interfaces.ISistemaTerminal;

/**
 *
 * @author valeria
 */
public class ProcesadorPagoTarjeta implements IProcesadorPago {
    private final ISistemaTerminal terminal;

    public ProcesadorPagoTarjeta() {
        this.terminal = new TerminalFachada();
    }

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
        
        SolicitudTerminalDTO solicitudTerminal = new SolicitudTerminalDTO(solicitud.getMonto());

        try {
            RespuestaTerminalDTO respuesta = terminal.cobrarTarjeta(solicitudTerminal);
            
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
                    respuesta.isAprobado(),
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
