package excepciones;

/**
 * InfraestructuraException.
 * Excepcion personalizada utilizada para manejar errores
 * relacionados con la comunicacion con sistemas externos.
 *
 * Esta excepcion se lanza cuando ocurre un problema en la capa
 * de infraestructura, como fallos de conexion, parsing de datos
 * o respuestas invalidas de servicios externos.
 *
 * @author DishUp
 */
public class InfraestructuraException extends Exception {

    /**
     * Constructor que recibe un mensaje de error.
     *
     * @param message Mensaje descriptivo del error.
     */
    public InfraestructuraException(String message) {
        super(message);
    }

    /**
     * Constructor que recibe un mensaje de error y la causa original.
     *
     * @param message Mensaje descriptivo del error.
     * @param cause Excepcion original que provoco el error.
     */
    public InfraestructuraException(String message, Throwable cause) {
        super(message, cause);
    }
}
