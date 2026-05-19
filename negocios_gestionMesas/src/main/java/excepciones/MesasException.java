package excepciones;


/**
 * MesasException.
 * Excepcion personalizada utilizada para manejar errores
 * relacionados con las operaciones de mesas dentro del sistema.
 *
 * Esta excepcion se utiliza cuando ocurre un problema de validacion,
 * reglas de negocio o procesamiento en el manejo de mesas.
 *
 * @author DishUp
 */
public class MesasException extends Exception {

    /**
     * Constructor por defecto.
     */
    public MesasException() {
    }

    /**
     * Constructor que permite enviar un mensaje de error.
     *
     * @param message Mensaje descriptivo del error ocurrido.
     */
    public MesasException(String message) {
        super(message);
    }
}
