package excepciones;

/**
 * PagosException.
 * 
 * Representa una excepcion personalizada utilizada en la capa de
 * control y servicios relacionada con el proceso de pagos.
 * 
 * Esta excepcion se lanza cuando ocurre un error durante la
 * validacion, procesamiento o registro de pagos dentro del sistema.
 * 
 * Permite encapsular errores de negocio o infraestructura relacionados
 * con pagos, facilitando su manejo centralizado en las capas superiores.
 * 
 * @author valeria
 */
public class PagosException extends Exception {

    /**
     * Constructor por defecto.
     */
    public PagosException() {
    }

    /**
     * Constructor que recibe un mensaje descriptivo del error.
     *
     * @param message mensaje de la excepcion
     */
    public PagosException(String message) {
        super(message);
    }
}