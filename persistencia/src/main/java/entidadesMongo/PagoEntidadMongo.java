package entidadesMongo;

import enums.EstadoPagoIndividual;
import enums.MetodoPago;
import java.time.LocalDateTime;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 * Entidad de persistencia Pago para MongoDB.
 * * Esta clase representa la estructura de un pago individual asociado a una comanda 
 * dentro de la base de datos. Almacena información sobre el método empleado, el monto 
 * total cobrado, el estado de la transacción, la marca de tiempo y el objeto polimórfico 
 * con los detalles particulares del cobro.
 * * Se utiliza únicamente en la capa de persistencia y no debe contener lógica de negocio.
 * * @author valeria
 */
public class PagoEntidadMongo {
    
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;

    private MetodoPago metodoPago;
    private float monto;
    private EstadoPagoIndividual estadoPago;
    private LocalDateTime fechaPago;
    private DetallePagoEntidadMongo detalles;

    /**
     * Constructor vacío requerido por MongoDB.
     */
    public PagoEntidadMongo() {
    }

    /**
     * Constructor completo de PagoEntidadMongo.
     *
     * @param id identificador único del registro de pago en formato ObjectId
     * @param metodoPago método seleccionado para efectuar el pago (e.g., TARJETA, EFECTIVO, CODI)
     * @param monto cantidad económica total cubierta en la transacción
     * @param estadoPago estado actual de la transacción individual (e.g., PAGADO, CANCELADO)
     * @param fechaPago fecha y hora exacta en la que se procesó el movimiento bancario o físico
     * @param detalles objeto con la información específica y extendida de la forma de pago elegida
     */
    public PagoEntidadMongo(String id, MetodoPago metodoPago, float monto, EstadoPagoIndividual estadoPago, LocalDateTime fechaPago, DetallePagoEntidadMongo detalles) {
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
     * @param id identificador único del pago
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el método o canal de pago utilizado.
     *
     * @return método de pago de la transacción
     */
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    /**
     * Establece el método o canal de pago utilizado.
     *
     * @param metodoPago método de pago de la transacción
     */
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene el importe económico total del pago.
     *
     * @return monto del pago
     */
    public float getMonto() {
        return monto;
    }

    /**
     * Establece el importe económico total del pago.
     *
     * @param monto monto del pago
     */
    public void setMonto(float monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el estado operativo actual de este pago.
     *
     * @return estado del pago individual
     */
    public EstadoPagoIndividual getEstadoPago() {
        return estadoPago;
    }

    /**
     * Establece el estado operativo actual de este pago.
     *
     * @param estadoPago estado del pago individual
     */
    public void setEstadoPago(EstadoPagoIndividual estadoPago) {
        this.estadoPago = estadoPago;
    }

    /**
     * Obtiene el registro temporal (fecha y hora) en que ocurrió la transacción.
     *
     * @return fecha y hora del pago
     */
    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    /**
     * Establece el registro temporal (fecha y hora) en que ocurrió la transacción.
     *
     * @param fechaPago fecha y hora del pago
     */
    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Obtiene los detalles específicos incrustados según la naturaleza del pago.
     *
     * @return instancia hija de {@link DetallePagoEntidadMongo}
     */
    public DetallePagoEntidadMongo getDetalles() {
        return detalles;
    }

    /**
     * Establece los detalles específicos incrustados según la naturaleza del pago.
     *
     * @param detalles instancia hija de {@link DetallePagoEntidadMongo}
     */
    public void setDetalles(DetallePagoEntidadMongo detalles) {
        this.detalles = detalles;
    }
}