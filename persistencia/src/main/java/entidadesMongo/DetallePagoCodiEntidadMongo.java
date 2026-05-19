package entidadesMongo;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 * Entidad de persistencia para el detalle de pago mediante CoDi (Cobro Digital) en MongoDB.
 * * Esta clase hereda de {@link DetallePagoEntidadMongo} y representa la estructura específica
 * de los datos requeridos para registrar un pago electrónico por medio de la plataforma CoDi.
 * Incluye datos de rastreo como la referencia y el folio de la transacción.
 * * Se utiliza únicamente en la capa de persistencia y no debe contener lógica de negocio.
 * * @author valeria
 */
@BsonDiscriminator(value = "codi")
public class DetallePagoCodiEntidadMongo extends DetallePagoEntidadMongo {
    
    private String referencia;
    private String folio;

    /**
     * Constructor vacío requerido por MongoDB.
     */
    public DetallePagoCodiEntidadMongo() {
    }

    /**
     * Constructor completo de DetallePagoCodiEntidadMongo.
     *
     * @param referencia número de referencia único de la transacción CoDi
     * @param folio folio de autorización emitido por la plataforma bancaria
     */
    public DetallePagoCodiEntidadMongo(String referencia, String folio) {
        this.referencia = referencia;
        this.folio = folio;
    }

    /**
     * Obtiene la referencia de la transacción CoDi.
     *
     * @return referencia del pago CoDi
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Establece la referencia de la transacción CoDi.
     *
     * @param referencia número de referencia de la transacción
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Obtiene el folio de la transacción bancaria.
     *
     * @return folio del pago CoDi
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Establece el folio de la transacción bancaria.
     *
     * @param folio folio de autorización de la transacción
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }
}