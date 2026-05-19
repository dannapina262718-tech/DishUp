package dtos;

import entidades.DetallePago;
import enums.MetodoPago;

/**
 * SolicitudPagoDTO.
 * 
 * Representa la solicitud de pago dentro del sistema.
 * 
 * Este DTO se utiliza para transportar la informacion necesaria
 * para procesar un pago asociado a una comanda, incluyendo el
 * identificador de la comanda, el metodo de pago seleccionado,
 * el monto a pagar y el detalle especifico del pago.
 * 
 * Su funcion es servir como objeto de transferencia de datos entre
 * las capas del sistema, especialmente hacia la capa de servicios
 * o infraestructura de pagos.
 * 
 * @author valeria
 */
public class SolicitudPagoDTO {

    private String idComanda;
    private MetodoPago metodoPago;
    private float monto;
    private DetallePago detallePago;

    /**
     * Constructor por defecto.
     */
    public SolicitudPagoDTO() {
    }

    /**
     * Constructor que inicializa todos los datos de la solicitud
     * de pago.
     *
     * @param idComanda identificador de la comanda
     * @param metodoPago metodo de pago seleccionado
     * @param monto monto total a pagar
     * @param detallePago detalle especifico del pago
     */
    public SolicitudPagoDTO(String idComanda, MetodoPago metodoPago,
                             float monto, DetallePago detallePago) {
        this.idComanda = idComanda;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.detallePago = detallePago;
    }

    /**
     * Obtiene el identificador de la comanda.
     *
     * @return id de la comanda
     */
    public String getIdComanda() {
        return idComanda;
    }

    /**
     * Establece el identificador de la comanda.
     *
     * @param idComanda nuevo identificador
     */
    public void setIdComanda(String idComanda) {
        this.idComanda = idComanda;
    }

    /**
     * Obtiene el metodo de pago seleccionado.
     *
     * @return metodo de pago
     */
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    /**
     * Establece el metodo de pago seleccionado.
     *
     * @param metodoPago nuevo metodo de pago
     */
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene el monto a pagar.
     *
     * @return monto del pago
     */
    public float getMonto() {
        return monto;
    }

    /**
     * Establece el monto a pagar.
     *
     * @param monto nuevo monto
     */
    public void setMonto(float monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el detalle especifico del pago.
     *
     * @return detalle del pago
     */
    public DetallePago getDetallePago() {
        return detallePago;
    }

    /**
     * Establece el detalle especifico del pago.
     *
     * @param detallePago nuevo detalle de pago
     */
    public void setDetallePago(DetallePago detallePago) {
        this.detallePago = detallePago;
    }
}