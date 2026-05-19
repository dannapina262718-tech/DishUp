package entidades;

/**
 * Detalle de pago CoDi.
 * 
 * Representa la informacion especifica de un pago realizado
 * mediante la plataforma CoDi dentro del sistema.
 * 
 * Esta entidad almacena los datos necesarios para identificar
 * y procesar la transaccion, incluyendo la referencia bancaria,
 * el folio de operacion y el codigo QR generado para el pago.
 * 
 * Hereda de la clase {@link DetallePago}, permitiendo manejar
 * distintos tipos de detalles de pago mediante polimorfismo.
 * 
 * @author valeria
 */
public class DetallePagoCodi extends DetallePago {

    private String referencia;
    private String folio;
    private String qrBase64;

    /**
     * Constructor por defecto.
     */
    public DetallePagoCodi() {
    }

    /**
     * Constructor que inicializa todos los datos del detalle
     * de pago CoDi.
     *
     * @param referencia referencia de la transaccion
     * @param folio folio asociado al pago
     * @param qrBase64 codigo QR del pago codificado en Base64
     */
    public DetallePagoCodi(String referencia, String folio, String qrBase64) {
        this.referencia = referencia;
        this.folio = folio;
        this.qrBase64 = qrBase64;
    }

    /**
     * Obtiene la referencia de la transaccion.
     *
     * @return referencia del pago
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Establece la referencia de la transaccion.
     *
     * @param referencia nueva referencia
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Obtiene el folio asociado al pago.
     *
     * @return folio del pago
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Establece el folio asociado al pago.
     *
     * @param folio nuevo folio
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * Obtiene el codigo QR del pago en formato Base64.
     *
     * @return codigo QR codificado
     */
    public String getQrBase64() {
        return qrBase64;
    }

    /**
     * Establece el codigo QR del pago en formato Base64.
     *
     * @param qrBase64 nuevo codigo QR codificado
     */
    public void setQrBase64(String qrBase64) {
        this.qrBase64 = qrBase64;
    }
}