/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos_infraestructura.RespuestaCodiDTO;
import dtos_infraestructura.RespuestaTerminalDTO;
import dtos_infraestructura.SolicitudCodiDTO;
import dtos_infraestructura.SolicitudTerminalDTO;
import excepciones.InfraestructuraTerminalException;


/**
 *
 * @author valeria
 */
public interface ISistemaTerminal {
    RespuestaTerminalDTO cobrarTarjeta(SolicitudTerminalDTO solicitud) throws InfraestructuraTerminalException;
    RespuestaCodiDTO cobrarCodi(SolicitudCodiDTO solicitud) throws InfraestructuraTerminalException;
}
