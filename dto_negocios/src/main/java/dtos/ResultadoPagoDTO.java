package dtos;

import entidades.DetallePago;
import enums.MetodoPago;

/**
 * ResultadoPagoDTO.
 * 
 * Representa el resultado de una operacion de pago dentro del sistema.
 * 
 * Este DTO se utiliza para transportar la informacion generada despues
 * de procesar un pago, incluyendo si fue aprobado, el mensaje de respuesta,
 * el monto pagado, el metodo de pago utilizado, el detalle del pago
 * y el saldo restante en caso de pagos parciales o saldo pendiente.
 * 
 * Su funcion es servir como objeto de respuesta entre la capa de servicios
 * y las capas superiores del sistema.
 * 
 * @author valeria
 */
public class ResultadoPagoDTO {

    private boolean aprobado;
    private String mensaje;
    private float montoPagado;
    private MetodoPago metodoPago;
    private DetallePago detallePago;
    private float saldoRestante;

    /**
     * Constructor por defecto.
     */
    public ResultadoPagoDTO() {
    }

    /**
     * Constructor que inicializa los datos principales del resultado
     * del pago.
     *
     * @param aprobado indica si el pago fue aprobado
     * @param mensaje mensaje de resultado de la operacion
     * @param montoPagado monto efectivamente pagado
     * @param metodoPago metodo de pago utilizado
     * @param detallePago detalle especifico del pago realizado
     */
    public ResultadoPagoDTO(boolean aprobado, String mensaje, float montoPagado,
                             MetodoPago metodoPago, DetallePago detallePago) {
        this.aprobado = aprobado;
        this.mensaje = mensaje;
        this.montoPagado = montoPagado;
        this.metodoPago = metodoPago;
        this.detallePago = detallePago;
    }

    /**
     * Indica si el pago fue aprobado.
     *
     * @return true si fue aprobado, false en caso contrario
     */
    public boolean isAprobado() {
        return aprobado;
    }

    /**
     * Establece si el pago fue aprobado.
     *
     * @param aprobado nuevo estado del pago
     */
    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    /**
     * Obtiene el mensaje del resultado del pago.
     *
     * @return mensaje de resultado
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje del resultado del pago.
     *
     * @param mensaje nuevo mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el monto pagado.
     *
     * @return monto pagado
     */
    public float getMontoPagado() {
        return montoPagado;
    }

    /**
     * Establece el monto pagado.
     *
     * @param montoPagado nuevo monto pagado
     */
    public void setMontoPagado(float montoPagado) {
        this.montoPagado = montoPagado;
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
     * Obtiene el detalle del pago realizado.
     *
     * @return detalle del pago
     */
    public DetallePago getDetallePago() {
        return detallePago;
    }

    /**
     * Establece el detalle del pago realizado.
     *
     * @param detallePago nuevo detalle de pago
     */
    public void setDetallePago(DetallePago detallePago) {
        this.detallePago = detallePago;
    }

    /**
     * Obtiene el saldo restante despues del pago.
     *
     * @return saldo restante
     */
    public float getSaldoRestante() {
        return saldoRestante;
    }

    /**
     * Establece el saldo restante despues del pago.
     *
     * @param saldoRestante nuevo saldo restante
     */
    public void setSaldoRestante(float saldoRestante) {
        this.saldoRestante = saldoRestante;
    }
}