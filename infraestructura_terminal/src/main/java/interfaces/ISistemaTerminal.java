package interfaces;

import dtos_infraestructura.RespuestaCodiDTO;
import dtos_infraestructura.RespuestaTerminalDTO;
import dtos_infraestructura.SolicitudCodiDTO;
import dtos_infraestructura.SolicitudTerminalDTO;
import excepciones.InfraestructuraTerminalException;

/**
 * ISistemaTerminal.
 * 
 * Define el contrato para la comunicacion con el sistema de terminal
 * de pagos dentro de la capa de infraestructura.
 * 
 * Esta interfaz establece las operaciones basicas que debe soportar
 * cualquier implementacion del sistema de terminal, incluyendo el
 * cobro con tarjeta y el cobro mediante CoDi.
 * 
 * Su objetivo es desacoplar la logica del sistema de la implementacion
 * concreta, permitiendo flexibilidad y facilidad de mantenimiento.
 * 
 * @author valeria
 */
public interface ISistemaTerminal {

    /**
     * Realiza un cobro con tarjeta en el sistema de terminal.
     *
     * @param solicitud datos necesarios para el cobro con tarjeta
     * @return respuesta del sistema de terminal
     * @throws InfraestructuraTerminalException si ocurre un error en la infraestructura
     */
    RespuestaTerminalDTO cobrarTarjeta(SolicitudTerminalDTO solicitud)
            throws InfraestructuraTerminalException;

    /**
     * Realiza un cobro mediante CoDi en el sistema de terminal.
     *
     * @param solicitud datos necesarios para el cobro CoDi
     * @return respuesta del sistema CoDi
     * @throws InfraestructuraTerminalException si ocurre un error en la infraestructura
     */
    RespuestaCodiDTO cobrarCodi(SolicitudCodiDTO solicitud)
            throws InfraestructuraTerminalException;
}