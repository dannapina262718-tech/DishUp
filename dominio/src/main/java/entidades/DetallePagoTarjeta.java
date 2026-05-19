package entidades;

import enums.Banco;
import enums.TipoTarjeta;

/**
 * Detalle de pago con tarjeta.
 * 
 * Representa la informacion especifica de un pago realizado
 * mediante tarjeta bancaria dentro del sistema.
 * 
 * Esta entidad almacena los datos necesarios para identificar
 * la transaccion realizada, incluyendo el numero de autorizacion,
 * los ultimos cuatro digitos de la tarjeta, el banco emisor y
 * el tipo de tarjeta utilizado.
 * 
 * Hereda de la clase {@link DetallePago}, permitiendo manejar
 * distintos tipos de detalles de pago mediante polimorfismo.
 * 
 * @author valeria
 */
public class DetallePagoTarjeta extends DetallePago {

    private String numeroAutorizacion;
    private String ultimos4Digitos;
    private Banco banco;
    private TipoTarjeta tipoTarjeta;

    /**
     * Constructor por defecto.
     */
    public DetallePagoTarjeta() {
    }

    /**
     * Constructor que inicializa todos los datos del detalle
     * de pago con tarjeta.
     *
     * @param numeroAutorizacion numero de autorizacion de la transaccion
     * @param ultimos4Digitos ultimos cuatro digitos de la tarjeta
     * @param banco banco emisor de la tarjeta
     * @param tipoTarjeta tipo de tarjeta utilizada
     */
    public DetallePagoTarjeta(String numeroAutorizacion, String ultimos4Digitos,
                              Banco banco, TipoTarjeta tipoTarjeta) {
        this.numeroAutorizacion = numeroAutorizacion;
        this.ultimos4Digitos = ultimos4Digitos;
        this.banco = banco;
        this.tipoTarjeta = tipoTarjeta;
    }

    /**
     * Obtiene el numero de autorizacion de la transaccion.
     *
     * @return numero de autorizacion
     */
    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    /**
     * Establece el numero de autorizacion de la transaccion.
     *
     * @param numeroAutorizacion nuevo numero de autorizacion
     */
    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    /**
     * Obtiene los ultimos cuatro digitos de la tarjeta.
     *
     * @return ultimos cuatro digitos
     */
    public String getUltimos4Digitos() {
        return ultimos4Digitos;
    }

    /**
     * Establece los ultimos cuatro digitos de la tarjeta.
     *
     * @param ultimos4Digitos nuevos ultimos cuatro digitos
     */
    public void setUltimos4Digitos(String ultimos4Digitos) {
        this.ultimos4Digitos = ultimos4Digitos;
    }

    /**
     * Obtiene el banco emisor de la tarjeta.
     *
     * @return banco asociado al pago
     */
    public Banco getBanco() {
        return banco;
    }

    /**
     * Establece el banco emisor de la tarjeta.
     *
     * @param banco nuevo banco asociado
     */
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    /**
     * Obtiene el tipo de tarjeta utilizada en el pago.
     *
     * @return tipo de tarjeta
     */
    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Establece el tipo de tarjeta utilizada en el pago.
     *
     * @param tipoTarjeta nuevo tipo de tarjeta
     */
    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

}