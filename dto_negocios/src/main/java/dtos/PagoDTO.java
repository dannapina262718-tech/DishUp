package dtos;

import enums.EstadoPagoIndividual;
import enums.MetodoPago;
import java.time.LocalDateTime;

/**
 * Objeto de transferencia de datos (DTO) que representa un pago dentro del sistema del restaurante.
 * <p>
 * Este DTO se utiliza para transportar la información relacionada con un pago entre las distintas capas
 * del sistema, permitiendo registrar el método de pago utilizado, el monto total, el estado del pago,
 * la fecha en que fue realizado y los detalles específicos asociados a la transacción.
 * <p>
 * Facilita el manejo de pagos individuales dentro de una comanda, así como su integración con
 * procesos de facturación y control financiero.
 *
 * @author valeria
 */
public class PagoDTO {

    private String id;
    private MetodoPago metodoPago;
    private float monto;
    private EstadoPagoIndividual estadoPago;
    private LocalDateTime fechaPago;
    private Object detalles;

    /**
     * Constructor vacío requerido para frameworks de serialización o mapeo de datos.
     */
    public PagoDTO() {
    }

    /**
     * Constructor que inicializa un pago con todos sus atributos principales.
     *
     * @param id identificador único del pago
     * @param metodoPago método utilizado para realizar el pago
     * @param monto cantidad total pagada
     * @param estadoPago estado actual del pago individual
     * @param fechaPago fecha y hora en la que se registró el pago
     * @param detalles información adicional específica del método de pago utilizado
     */
    public PagoDTO(String id, MetodoPago metodoPago, float monto, EstadoPagoIndividual estadoPago, LocalDateTime fechaPago, Object detalles) {
        this.id = id;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.estadoPago = estadoPago;
        this.fechaPago = fechaPago;
        this.detalles = detalles;
    }

    /**
     * Obtiene el identificador único del pago.
     *
     * @return id del pago
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único del pago.
     *
     * @param id identificador del pago
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el método utilizado para realizar el pago.
     *
     * @return método de pago
     */
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    /**
     * Establece el método utilizado para realizar el pago.
     *
     * @param metodoPago método de pago
     */
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene el monto total del pago.
     *
     * @return monto pagado
     */
    public float getMonto() {
        return monto;
    }

    /**
     * Establece el monto total del pago.
     *
     * @param monto cantidad a registrar como pago
     */
    public void setMonto(float monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el estado actual del pago individual.
     *
     * @return estado del pago
     */
    public EstadoPagoIndividual getEstadoPago() {
        return estadoPago;
    }

    /**
     * Establece el estado actual del pago individual.
     *
     * @param estadoPago estado del pago
     */
    public void setEstadoPago(EstadoPagoIndividual estadoPago) {
        this.estadoPago = estadoPago;
    }

    /**
     * Obtiene la fecha y hora en que se realizó el pago.
     *
     * @return fecha del pago
     */
    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    /**
     * Establece la fecha y hora en que se realizó el pago.
     *
     * @param fechaPago fecha del pago
     */
    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Obtiene los detalles adicionales asociados al pago.
     *
     * @return información específica del método de pago
     */
    public Object getDetalles() {
        return detalles;
    }

    /**
     * Establece los detalles adicionales asociados al pago.
     *
     * @param detalles información específica del método de pago
     */
    public void setDetalles(Object detalles) {
        this.detalles = detalles;
    }
}