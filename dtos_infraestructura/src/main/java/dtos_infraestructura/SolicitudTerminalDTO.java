package dtos_infraestructura;

/**
 * SolicitudTerminalDTO.
 * 
 * Representa la solicitud enviada al sistema externo de terminal
 * de pago dentro de la capa de infraestructura.
 * 
 * Este DTO contiene la informacion necesaria para iniciar una
 * operacion de pago en terminal, en este caso el monto a cobrar
 * al cliente.
 * 
 * Su funcion es transportar datos desde el sistema interno hacia
 * el sistema externo sin exponer la logica de negocio.
 * 
 * @author valeria
 */
public class SolicitudTerminalDTO {

    private float monto;

    /**
     * Constructor por defecto.
     */
    public SolicitudTerminalDTO() {
    }

    /**
     * Constructor que inicializa la solicitud de pago en terminal.
     *
     * @param monto monto a cobrar en la transaccion
     */
    public SolicitudTerminalDTO(float monto) {
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