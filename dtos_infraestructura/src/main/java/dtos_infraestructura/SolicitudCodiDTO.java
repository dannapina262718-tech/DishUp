package dtos_infraestructura;

/**
 * SolicitudCodiDTO.
 * 
 * Representa la solicitud enviada al sistema externo de CoDi
 * dentro de la capa de infraestructura.
 * 
 * Este DTO contiene la informacion necesaria para iniciar
 * una operacion de pago mediante CoDi, en este caso el monto
 * a cobrar al cliente.
 * 
 * Su funcion es transportar los datos desde el sistema interno
 * hacia el servicio externo sin exponer la logica de negocio.
 * 
 * @author valeria
 */
public class SolicitudCodiDTO {

    private float monto;

    /**
     * Constructor por defecto.
     */
    public SolicitudCodiDTO() {
    }

    /**
     * Constructor que inicializa la solicitud de pago CoDi.
     *
     * @param monto monto a cobrar en la transaccion
     */
    public SolicitudCodiDTO(float monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el monto de la solicitud.
     *
     * @return monto a cobrar
     */
    public float getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la solicitud.
     *
     * @param monto nuevo monto
     */
    public void setMonto(float monto) {
        this.monto = monto;
    }
}