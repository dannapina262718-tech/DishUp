package fachada;

import control.PagoControl;
import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import excepciones.PagosException;
import interfaz.IGestionPagos;

/**
 * PagoFachada.
 * 
 * Implementa el patron de diseño Fachada para simplificar el acceso
 * a las operaciones relacionadas con pagos dentro del sistema.
 * 
 * Esta clase actua como punto unico de entrada hacia la capa de control
 * de pagos, ocultando la complejidad de {@link PagoControl} y exponiendo
 * una interfaz definida por {@link IGestionPagos}.
 * 
 * Permite a las capas superiores interactuar con el sistema de pagos
 * sin conocer la logica interna de negocio.
 * 
 * @author valeria
 */
public class PagoFachada implements IGestionPagos {

    private final PagoControl pagoControl;

    /**
     * Constructor que inicializa la fachada de pagos.
     */
    public PagoFachada() {
        this.pagoControl = new PagoControl();
    }

    /**
     * Verifica si una comanda puede realizar pagos.
     *
     * @param idComanda identificador de la comanda
     * @return true si puede pagar, false en caso contrario
     * @throws PagosException si ocurre un error en el proceso
     */
    @Override
    public boolean puedePagarComanda(String idComanda) throws PagosException {
        return pagoControl.puedePagarComanda(idComanda);
    }

    /**
     * Verifica si una mesa puede realizar pagos.
     *
     * @param numeroMesa numero de la mesa
     * @return true si puede pagar, false en caso contrario
     * @throws PagosException si ocurre un error en el proceso
     */
    @Override
    public boolean puedePagarMesa(int numeroMesa) throws PagosException {
        return pagoControl.puedePagarMesa(numeroMesa);
    }

    /**
     * Registra un pago en el sistema.
     *
     * @param solicitud datos necesarios para registrar el pago
     * @return resultado del pago registrado
     * @throws PagosException si ocurre un error en el proceso de pago
     */
    @Override
    public ResultadoPagoDTO registrarPago(SolicitudPagoDTO solicitud)
            throws PagosException {
        return pagoControl.registrarPago(solicitud);
    }
}