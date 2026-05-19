package excepciones;

/**
 * InfraestructuraTerminalException.
 * 
 * Representa una excepcion personalizada utilizada para manejar
 * errores relacionados con la comunicacion o procesamiento en la
 * infraestructura de la terminal de pago.
 * 
 * Esta excepcion se lanza cuando ocurre un problema durante la
 * integracion con el sistema externo de terminal, como errores
 * de conexion, respuestas invalidas o fallos en la conversion
 * de datos.
 * 
 * Permite encapsular y controlar errores especificos de la capa
 * de infraestructura sin afectar directamente la logica del
 * sistema principal.
 * 
 * @author valeria
 */
public class InfraestructuraTerminalException extends Exception {

    /**
     * Constructor que recibe un mensaje descriptivo del error.
     *
     * @param message mensaje de la excepcion
     */
    public InfraestructuraTerminalException(String message) {
        super(message);
    }

    /**
     * Constructor que recibe un mensaje y la causa del error.
     *
     * @param message mensaje de la excepcion
     * @param cause causa original del error
     */
    public InfraestructuraTerminalException(String message, Throwable cause) {
        super(message, cause);
    }
}