package excepciones;


/**
 * EmpleadosException.
 * Excepcion personalizada utilizada para manejar errores
 * relacionados con las operaciones de empleados dentro del sistema.
 *
 * Esta excepcion se utiliza cuando ocurre un problema de validacion,
 * reglas de negocio o procesamiento en el manejo de empleados.
 *
 * @author DishUp
 */
public class EmpleadosException extends Exception {

    /**
     * Constructor por defecto.
     */
    public EmpleadosException() {
    }

    /**
     * Constructor que permite enviar un mensaje de error.
     *
     * @param message Mensaje descriptivo del error ocurrido.
     */
    public EmpleadosException(String message) {
        super(message);
    }
}
