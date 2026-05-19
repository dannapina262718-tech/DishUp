package excepciones;

/**
 * Excepción personalizada para la capa de persistencia.
 * 
 * Esta excepción se utiliza para encapsular errores relacionados con el acceso a datos,
 * incluyendo fallos en MongoDB, problemas de conversión entre entidades o cualquier
 * error ocurrido durante operaciones de base de datos.
 * 
 * Su objetivo es centralizar el manejo de errores de persistencia y permitir
 * una comunicación clara hacia las capas superiores del sistema.
 */
public class PersistenciaException extends Exception {

    /**
     * Constructor que recibe un mensaje descriptivo del error.
     *
     * @param mensaje descripción del error ocurrido
     */
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor que recibe un mensaje y la causa original del error.
     *
     * @param mensaje descripción del error ocurrido
     * @param causa excepción original que provocó el error
     */
    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}