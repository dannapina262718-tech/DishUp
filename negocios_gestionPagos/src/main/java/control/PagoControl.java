package control;

import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import excepcion.NegocioException;
import excepciones.PagosException;
import objetosNegocio.PagoBO;

/**
 * PagoControl.
 * 
 * Representa la capa de control encargada de gestionar las operaciones
 * relacionadas con el proceso de pagos dentro del sistema.
 * 
 * Esta clase actua como intermediario entre las capas superiores
 * (UI o servicios) y la logica de negocio encapsulada en {@link PagoBO}.
 * 
 * Su responsabilidad es coordinar las operaciones de validacion,
 * consulta y registro de pagos, manejando las excepciones del negocio
 * y transformandolas en excepciones propias de la capa de control.
 * 
 * @author valeria
 */
public class PagoControl {

    private final PagoBO pagoBO;

    /**
     * Constructor que inicializa la logica de negocio de pagos.
     */
    public PagoControl() {
        this.pagoBO = new PagoBO();
    }

    /**
     * Verifica si una mesa puede realizar pagos.
     *
     * @param numeroMesa numero de la mesa a evaluar
     * @return true si la mesa puede pagar, false en caso contrario
     * @throws PagosException si ocurre un error en la logica de negocio
     */
    public boolean puedePagarMesa(int numeroMesa) throws PagosException {
        try {
            return pagoBO.puedePagarMesa(numeroMesa);
        } catch (NegocioException ex) {
            throw new PagosException(
                    "Ocurrio un error al evaluar si se puede pagar la mesa: "
                    + ex.getMessage()
            );
        }
    }

    /**
     * Verifica si una comanda puede realizar pagos.
     *
     * @param idComanda identificador de la comanda
     * @return true si la comanda puede pagar, false en caso contrario
     * @throws PagosException si ocurre un error en la logica de negocio
     */
    public boolean puedePagarComanda(String idComanda) throws PagosException {
        try {
            return pagoBO.puedePagarComanda(idComanda);
        } catch (NegocioException ex) {
            throw new PagosException(
                    "Ocurrio un error al evaluar si se puede pagar la comanda: "
                    + ex.getMessage()
            );
        }
    }

    /**
     * Registra un pago en el sistema.
     *
     * @param solicitud datos necesarios para registrar el pago
     * @return resultado del pago realizado
     * @throws PagosException si ocurre un error en el proceso de pago
     */
    public ResultadoPagoDTO registrarPago(SolicitudPagoDTO solicitud)
            throws PagosException {
        try {
            return pagoBO.registrarPago(solicitud);
        } catch (NegocioException ex) {
            throw new PagosException(
                    "Ocurrio un error al intentar registrar el pago: "
                    + ex.getMessage()
            );
        }
    }
}