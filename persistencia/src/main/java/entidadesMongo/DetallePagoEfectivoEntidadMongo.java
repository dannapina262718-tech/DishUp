package entidadesMongo;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 * Entidad de persistencia para el detalle de pago en efectivo en MongoDB.
 * * Esta clase hereda de {@link DetallePagoEntidadMongo} y representa la estructura específica
 * de los datos requeridos para registrar un pago físico en efectivo. Almacena el desglose
 * del monto entregado por el cliente y el cambio devuelto.
 * * Se utiliza únicamente en la capa de persistencia y no debe contener lógica de negocio.
 * * @author valeria
 */
@BsonDiscriminator(value = "efectivo")
public class DetallePagoEfectivoEntidadMongo extends DetallePagoEntidadMongo {
    
    private float montoRecibido;
    private float cambio;

    /**
     * Constructor vacío requerido por MongoDB.
     */
    public DetallePagoEfectivoEntidadMongo() {
    }

    /**
     * Constructor completo de DetallePagoEfectivoEntidadMongo.
     *
     * @param montoRecibido cantidad de dinero en efectivo entregada por el cliente
     * @param cambio cantidad de dinero devuelta al cliente
     */
    public DetallePagoEfectivoEntidadMongo(float montoRecibido, float cambio) {
        this.montoRecibido = montoRecibido;
        this.cambio = cambio;
    }

    /**
     * Obtiene el monto total en efectivo recibido del cliente.
     *
     * @return monto recibido
     */
    public float getMontoRecibido() {
        return montoRecibido;
    }

    /**
     * Establece el monto total en efectivo recibido del cliente.
     *
     * @param montoRecibido monto recibido
     */
    public void setMontoRecibido(float montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    /**
     * Obtiene el cambio total devuelto al cliente.
     *
     * @return cambio entregado
     */
    public float getCambio() {
        return cambio;
    }

    /**
     * Establece el cambio total devuelto al cliente.
     *
     * @param cambio cambio entregado
     */
    public void setCambio(float cambio) {
        this.cambio = cambio;
    }
}