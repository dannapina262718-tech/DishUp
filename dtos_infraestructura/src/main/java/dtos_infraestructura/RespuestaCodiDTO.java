package dtos_infraestructura;

/**
 * Respuesta CoDi DTO.
 * 
 * Representa la respuesta generada por el servicio de pagos
 * CoDi dentro de la capa de infraestructura.
 * 
 * Este DTO contiene la informacion relacionada con el resultado
 * de una operacion de pago, incluyendo si fue aprobada,
 * el mensaje de respuesta, el monto procesado, el folio de
 * la transaccion, la referencia asociada y el codigo QR
 * generado para el pago.
 * 
 * Su finalidad es transportar datos entre la infraestructura
 * externa y las demas capas del sistema sin exponer detalles
 * internos de implementacion.
 * 
 * @author valeria
 */
public class RespuestaCodiDTO {

    private boolean aprobado;
    private String mensaje;
    private float monto;
    private String folio;
    private String qrBase64;
    private String referencia;

    /**
     * Constructor por defecto.
     */
    public RespuestaCodiDTO() {
    }

    /**
     * Constructor que inicializa todos los datos de la respuesta CoDi.
     *
     * @param aprobado indica si el pago fue aprobado
     * @param mensaje mensaje descriptivo de la respuesta
     * @param monto monto procesado en la operacion
     * @param folio folio de la transaccion
     * @param qrBase64 codigo QR codificado en Base64
     * @param referencia referencia asociada al pago
     */
    public RespuestaCodiDTO(boolean aprobado, String mensaje, float monto,
                            String folio, String qrBase64, String referencia) {
        this.aprobado = aprobado;
        this.mensaje = mensaje;
        this.monto = monto;
        this.folio = folio;
        this.qrBase64 = qrBase64;
        this.referencia = referencia;
    }

    /**
     * Indica si la operacion fue aprobada.
     *
     * @return true si el pago fue aprobado, false en caso contrario
     */
    public boolean isAprobado() {
        return aprobado;
    }

    /**
     * Establece si la operacion fue aprobada.
     *
     * @param aprobado nuevo estado de aprobacion
     */
    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    /**
     * Obtiene el mensaje de respuesta de la operacion.
     *
     * @return mensaje de respuesta
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje de respuesta de la operacion.
     *
     * @param mensaje nuevo mensaje de respuesta
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el monto procesado en la operacion.
     *
     * @return monto procesado
     */
    public float getMonto() {
        return monto;
    }

    /**
     * Establece el monto procesado en la operacion.
     *
     * @param monto nuevo monto procesado
     */
    public void setMonto(float monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el folio de la transaccion.
     *
     * @return folio del pago
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Establece el folio de la transaccion.
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

    /**
     * Obtiene la referencia asociada al pago.
     *
     * @return referencia del pago
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Establece la referencia asociada al pago.
     *
     * @param referencia nueva referencia
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}