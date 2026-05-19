package entidades;

/**
 * Detalle de pago.
 * 
 * Representa la información adicional asociada a un pago dentro del sistema.
 * 
 * Esta clase abstracta sirve como base para los diferentes tipos de detalles de pago,
 * permitiendo manejar distintas formas de pago mediante herencia y polimorfismo.
 * 
 * Las clases hijas contienen los atributos específicos requeridos para cada método de pago,
 * como datos de tarjeta, información bancaria o referencias de transacciones.
 * 
 * @author valeria
 */
public abstract class DetallePago {

    /**
     * Constructor por defecto de la clase abstracta DetallePago.
     * Permite la inicialización básica de las clases hijas que extienden este modelo.
     */
    protected DetallePago() {
        // Constructor protegido para uso exclusivo de herencia
    }
}