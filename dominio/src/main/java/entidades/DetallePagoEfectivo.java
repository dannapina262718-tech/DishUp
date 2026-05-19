package entidades;

/**
 * Detalle de pago en efectivo.
 * 
 * Representa la informacion especifica de un pago realizado
 * en efectivo dentro del sistema.
 * 
 * Esta entidad almacena el monto recibido por parte del cliente
 * y el cambio devuelto durante la transaccion.
 * 
 * Hereda de la clase {@link DetallePago}, permitiendo manejar
 * diferentes tipos de detalles de pago mediante polimorfismo.
 * 
 * @author valeria
 */
public class DetallePagoEfectivo extends DetallePago {

    private float montoRecibido;
    private float cambio;

    /**
     * Constructor por defecto.
     */
    public DetallePagoEfectivo() {
    }

    /**
     * Constructor que inicializa todos los datos del detalle
     * de pago en efectivo.
     *
     * @param montoRecibido cantidad entregada por el cliente
     * @param cambio cantidad devuelta al cliente
     */
    public DetallePagoEfectivo(float montoRecibido, float cambio) {
        this.montoRecibido = montoRecibido;
        this.cambio = cambio;
    }

    /**
     * Obtiene el monto recibido por parte del cliente.
     *
     * @return monto recibido
     */
    public float getMontoRecibido() {
        return montoRecibido;
    }

    /**
     * Establece el monto recibido por parte del cliente.
     *
     * @param montoRecibido nuevo monto recibido
     */
    public void setMontoRecibido(float montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    /**
     * Obtiene el cambio entregado al cliente.
     *
     * @return cambio del pago
     */
    public float getCambio() {
        return cambio;
    }

    /**
     * Establece el cambio entregado al cliente.
     *
     * @param cambio nuevo cambio
     */
    public void setCambio(float cambio) {
        this.cambio = cambio;
    }

}