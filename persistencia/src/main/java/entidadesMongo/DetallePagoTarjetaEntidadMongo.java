package entidadesMongo;

import enums.Banco;
import enums.TipoTarjeta;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 * Entidad de persistencia para el detalle de pago con tarjeta en MongoDB.
 * * Esta clase hereda de {@link DetallePagoEntidadMongo} y representa la estructura específica
 * de los datos requeridos para registrar un pago mediante tarjeta bancaria (crédito o débito).
 * Guarda información de control y auditoría como el número de autorización, los últimos 4 dígitos,
 * la entidad financiera emisora y la naturaleza de la tarjeta.
 * * Se utiliza únicamente en la capa de persistencia y no debe contener lógica de negocio.
 * * @author valeria
 */
@BsonDiscriminator(value = "tarjeta")
public class DetallePagoTarjetaEntidadMongo extends DetallePagoEntidadMongo {
    
    private String numeroAutorizacion;
    private String ultimos4Digitos;
    private Banco banco;
    private TipoTarjeta tipoTarjeta;

    /**
     * Constructor vacío requerido por MongoDB.
     */
    public DetallePagoTarjetaEntidadMongo() {
    }

    /**
     * Constructor completo de DetallePagoTarjetaEntidadMongo.
     *
     * @param numeroAutorizacion código de autorización emitido por la terminal punto de venta o banco
     * @param ultimos4Digitos cadena con los últimos cuatro dígitos de la tarjeta para fines de identificación
     * @param banco entidad financiera o banco emisor de la tarjeta
     * @param tipoTarjeta tipo de la tarjeta utilizada (e.g., CRÉDITO o DÉBITO)
     */
    public DetallePagoTarjetaEntidadMongo(String numeroAutorizacion, String ultimos4Digitos, Banco banco, TipoTarjeta tipoTarjeta) {
        this.numeroAutorizacion = numeroAutorizacion;
        this.ultimos4Digitos = ultimos4Digitos;
        this.banco = banco;
        this.tipoTarjeta = tipoTarjeta;
    }

    /**
     * Obtiene el número de autorización de la transacción bancaria.
     *
     * @return número de autorización del pago
     */
    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    /**
     * Establece el número de autorización de la transacción bancaria.
     *
     * @param numeroAutorizacion número de autorización del pago
     */
    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    /**
     * Obtiene los últimos 4 dígitos de la tarjeta bancaria.
     *
     * @return últimos 4 dígitos de la tarjeta
     */
    public String getUltimos4Digitos() {
        return ultimos4Digitos;
    }

    /**
     * Establece los últimos 4 dígitos de la tarjeta bancaria.
     *
     * @param ultimos4Digitos últimos 4 dígitos de la tarjeta
     */
    public void setUltimos4Digitos(String ultimos4Digitos) {
        this.ultimos4Digitos = ultimos4Digitos;
    }

    /**
     * Obtiene el banco emisor asignado a la transacción.
     *
     * @return banco emisor de la tarjeta
     */
    public Banco getBanco() {
        return banco;
    }

    /**
     * Establece el banco emisor de la tarjeta.
     *
     * @param banco banco emisor de la tarjeta
     */
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    /**
     * Obtiene el tipo de tarjeta (crédito o débito).
     *
     * @return tipo de tarjeta utilizado
     */
    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Establece el tipo de tarjeta (crédito o débito).
     *
     * @param tipoTarjeta tipo de tarjeta utilizado
     */
    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }
}