package excepciones;

/**
 * ProductosException.
 * Representa errores relacionados con la gestion de productos en el sistema.
 *
 * Esta excepcion se utiliza cuando ocurre un problema al consultar,
 * filtrar o procesar informacion de productos dentro del sistema.
 */
public class ProductosException extends Exception {

    /**
     * Constructor vacio.
     */
    public ProductosException() {
    }

    /**
     * Constructor que recibe un mensaje de error.
     *
     * @param message Mensaje que describe el error ocurrido.
     */
    public ProductosException(String message) {
        super(message);
    }
}