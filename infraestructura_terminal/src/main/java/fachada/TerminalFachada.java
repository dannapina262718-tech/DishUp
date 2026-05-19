package fachada;

import dtos_infraestructura.RespuestaCodiDTO;
import dtos_infraestructura.RespuestaTerminalDTO;
import dtos_infraestructura.SolicitudCodiDTO;
import dtos_infraestructura.SolicitudTerminalDTO;
import excepciones.InfraestructuraTerminalException;
import interfaces.ISistemaTerminal;
import terminal.SistemaTerminal;

/**
 * TerminalFachada.
 * 
 * Implementa el patron de diseño Fachada para simplificar el acceso
 * al sistema de terminal de pago.
 * 
 * Esta clase actua como punto unico de entrada hacia la logica de la
 * infraestructura de pagos, ocultando la complejidad de la clase
 * {@link SistemaTerminal} y exponiendo una interfaz mas simple
 * definida por {@link ISistemaTerminal}.
 * 
 * Permite realizar operaciones de cobro con tarjeta y CoDi sin que
 * las capas superiores conozcan la implementacion interna del sistema.
 * 
 * @author valeria
 */
public class TerminalFachada implements ISistemaTerminal {

    private final SistemaTerminal sistema;

    /**
     * Constructor que inicializa la fachada y su sistema interno.
     */
    public TerminalFachada() {
        this.sistema = new SistemaTerminal();
    }

    /**
     * Realiza un cobro con tarjeta a traves del sistema de terminal.
     *
     * @param solicitud datos necesarios para el cobro con tarjeta
     * @return respuesta del sistema de terminal
     * @throws InfraestructuraTerminalException si ocurre un error en la infraestructura
     */
    @Override
    public RespuestaTerminalDTO cobrarTarjeta(SolicitudTerminalDTO solicitud)
            throws InfraestructuraTerminalException {
        return sistema.cobrarTarjeta(solicitud);
    }

    /**
     * Realiza un cobro mediante CoDi a traves del sistema de terminal.
     *
     * @param solicitud datos necesarios para el cobro CoDi
     * @return respuesta del sistema CoDi
     * @throws InfraestructuraTerminalException si ocurre un error en la infraestructura
     */
    @Override
    public RespuestaCodiDTO cobrarCodi(SolicitudCodiDTO solicitud)
            throws InfraestructuraTerminalException {
        return sistema.cobrarCodi(solicitud);
    }
}