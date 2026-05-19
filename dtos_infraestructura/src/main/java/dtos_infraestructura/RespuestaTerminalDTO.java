package dtos_infraestructura;

import enums.Banco;
import enums.TipoTarjeta;

/**
 * RespuestaTerminalDTO.
 * 
 * Representa la respuesta generada por el sistema externo de terminal
 * de pago dentro de la capa de infraestructura.
 * 
 * Este DTO contiene la informacion resultante de una operacion de pago
 * con tarjeta, incluyendo si fue aprobada, el mensaje de respuesta,
 * el monto procesado y los datos asociados a la transaccion bancaria.
 * 
 * Sirve como objeto de transferencia de datos entre la infraestructura
 * externa (terminal de pago) y el sistema interno, sin exponer
 * detalles de implementacion.
 * 
 * @author valeria
 */
public class RespuestaTerminalDTO {

    private boolean aprobado;
    private String mensaje;
    private float monto;
    private String numeroAutorizacion;
    private String ultimos4Digitos;
    private Banco banco;
    private TipoTarjeta tipoTarjeta;

    /**
     * Constructor por defecto.
     */
    public RespuestaTerminalDTO() {
    }

    /**
     * Constructor que inicializa todos los datos de la respuesta
     * del pago en terminal.
     *
     * @param aprobado indica si el pago fue aprobado
     * @param mensaje mensaje de respuesta de la transaccion
     * @param monto monto procesado en el pago
     * @param numeroAutorizacion numero de autorizacion bancaria
     * @param ultimos4Digitos ultimos cuatro digitos de la tarjeta
     * @param banco banco emisor de la tarjeta
     * @param tipoTarjeta tipo de tarjeta utilizada
     */
    public RespuestaTerminalDTO(boolean aprobado, String mensaje, float monto,
                                String numeroAutorizacion, String ultimos4Digitos,
                                Banco banco, TipoTarjeta tipoTarjeta) {
        this.aprobado = aprobado;
        this.mensaje = mensaje;
        this.monto = monto;
        this.numeroAutorizacion = numeroAutorizacion;
        this.ultimos4Digitos = ultimos4Digitos;
        this.banco = banco;
        this.tipoTarjeta = tipoTarjeta;
    }

    /**
     * Indica si la transaccion fue aprobada.
     *
     * @return true si fue aprobada, false en caso contrario
     */
    public boolean isAprobado() {
        return aprobado;
    }

    /**
     * Establece si la transaccion fue aprobada.
     *
     * @param aprobado nuevo estado de aprobacion
     */
    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    /**
     * Obtiene el mensaje de respuesta de la transaccion.
     *
     * @return mensaje de respuesta
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje de respuesta de la transaccion.
     *
     * @param mensaje nuevo mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el monto procesado en la transaccion.
     *
     * @return monto del pago
     */
    public float getMonto() {
        return monto;
    }

    /**
     * Establece el monto procesado en la transaccion.
     *
     * @param monto nuevo monto
     */
    public void setMonto(float monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el numero de autorizacion bancaria.
     *
     * @return numero de autorizacion
     */
    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    /**
     * Establece el numero de autorizacion bancaria.
     *
     * @param numeroAutorizacion nuevo numero de autorizacion
     */
    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    /**
     * Obtiene los ultimos cuatro digitos de la tarjeta.
     *
     * @return ultimos 4 digitos
     */
    public String getUltimos4Digitos() {
        return ultimos4Digitos;
    }

    /**
     * Establece los ultimos cuatro digitos de la tarjeta.
     *
     * @param ultimos4Digitos nuevos digitos
     */
    public void setUltimos4Digitos(String ultimos4Digitos) {
        this.ultimos4Digitos = ultimos4Digitos;
    }

    /**
     * Obtiene el banco emisor de la tarjeta.
     *
     * @return banco
     */
    public Banco getBanco() {
        return banco;
    }

    /**
     * Establece el banco emisor de la tarjeta.
     *
     * @param banco nuevo banco
     */
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    /**
     * Obtiene el tipo de tarjeta utilizada.
     *
     * @return tipo de tarjeta
     */
    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Establece el tipo de tarjeta utilizada.
     *
     * @param tipoTarjeta nuevo tipo de tarjeta
     */
    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }
}