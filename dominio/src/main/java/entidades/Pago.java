package entidades;

import enums.EstadoPagoIndividual;
import enums.MetodoPago;
import java.time.LocalDateTime;

/**
 * Pago.
 * 
 * Representa un pago realizado dentro del sistema asociado
 * a una comanda.
 * 
 * Esta entidad almacena la informacion principal de una
 * transaccion, incluyendo el metodo de pago utilizado,
 * el monto pagado, el estado del pago, la fecha en la que
 * se realizo y los detalles especificos correspondientes
 * al tipo de pago.
 * 
 * La clase permite manejar distintos tipos de pagos mediante
 * composicion y polimorfismo utilizando la jerarquia de
 * {@link DetallePago}.
 * 
 * @author valeria
 */
public class Pago {

    private String id;
    private MetodoPago metodoPago;
    private float monto;
    private EstadoPagoIndividual estadoPago;
    private LocalDateTime fechaPago;

    private DetallePago detalles;

    /**
     * Constructor por defecto.
     */
    public Pago() {
    }

    /**
     * Constructor que inicializa todos los datos del pago.
     *
     * @param id identificador del pago
     * @param metodoPago metodo utilizado para realizar el pago
     * @param monto cantidad pagada
     * @param estadoPago estado actual del pago
     * @param fechaPago fecha y hora en que se realizo el pago
     * @param detalles detalles especificos del pago
     */
    public Pago(String id, MetodoPago metodoPago, float monto,
                EstadoPagoIndividual estadoPago,
                LocalDateTime fechaPago, DetallePago detalles) {
        this.id = id;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.estadoPago = estadoPago;
        this.fechaPago = fechaPago;
        this.detalles = detalles;
    }

    /**
     * Obtiene el identificador del pago.
     *
     * @return id del pago
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del pago.
     *
     * @param id nuevo identificador
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el metodo de pago utilizado.
     *
     * @return metodo de pago
     */
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    /**
     * Establece el metodo de pago utilizado.
     *
     * @param metodoPago nuevo metodo de pago
     */
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene el monto del pago realizado.
     *
     * @return monto pagado
     */
    public float getMonto() {
        return monto;
    }

    /**
     * Establece el monto del pago realizado.
     *
     * @param monto nuevo monto pagado
     */
    public void setMonto(float monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el estado actual del pago.
     *
     * @return estado del pago
     */
    public EstadoPagoIndividual getEstadoPago() {
        return estadoPago;
    }

    /**
     * Establece el estado actual del pago.
     *
     * @param estadoPago nuevo estado del pago
     */
    public void setEstadoPago(EstadoPagoIndividual estadoPago) {
        this.estadoPago = estadoPago;
    }

    /**
     * Obtiene la fecha y hora en que se realizo el pago.
     *
     * @return fecha del pago
     */
    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    /**
     * Establece la fecha y hora del pago.
     *
     * @param fechaPago nueva fecha del pago
     */
    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Obtiene los detalles especificos del pago.
     *
     * @return detalles del pago
     */
    public DetallePago getDetalles() {
        return detalles;
    }

    /**
     * Establece los detalles especificos del pago.
     *
     * @param detalles nuevos detalles del pago
     */
    public void setDetalles(DetallePago detalles) {
        this.detalles = detalles;
    }
}