package excepcion;

/**
 * NegocioException.
 * 
 * Representa una excepcion personalizada de la capa de negocio.
 * 
 * Esta excepcion se utiliza para indicar errores ocurridos durante
 * la ejecucion de la logica de negocio, como validaciones fallidas,
 * reglas de negocio incumplidas o errores en procesos internos del sistema.
 * 
 * Su objetivo es encapsular errores del dominio para que puedan ser
 * manejados adecuadamente por las capas superiores sin exponer detalles
 * internos de implementacion.
 * 
 * @author DishUp
 */
public class NegocioException extends Exception {

    /**
     * Constructor que recibe un mensaje descriptivo del error.
     *
     * @param message mensaje de la excepcion
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     * Constructor que recibe un mensaje y la causa original del error.
     *
     * @param message mensaje de la excepcion
     * @param cause causa original del error
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}