package excepciones;

/**
 * ComandasException.
 * Excepcion personalizada utilizada para manejar errores
 * relacionados con las operaciones de comandas dentro del sistema.
 *
 * Esta excepcion se lanza cuando ocurre un problema de validacion,
 * procesamiento o reglas de negocio en el manejo de comandas.
 *
 * @author DishUp
 */
public class ComandasException extends Exception {

    /**
     * Constructor por defecto.
     */
    public ComandasException() {
    }

    /**
     * Constructor que permite enviar un mensaje de error.
     *
     * @param message Mensaje descriptivo del error ocurrido.
     */
    public ComandasException(String message) {
        super(message);
    }
}
