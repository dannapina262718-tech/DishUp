package procesadores;

import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import excepcion.NegocioException;

/**
 * Interfaz encargada de definir el contrato para los procesadores de pago.
 *
 * Cada implementación representa una forma distinta de procesar un pago
 * (por ejemplo: tarjeta, CoDi, etc.).
 *
 * Permite aplicar el patrón Strategy para encapsular la lógica de pago.
 */
public interface IProcesadorPago {

    /**
     * Procesa un pago a partir de una solicitud.
     *
     * @param solicitud información necesaria para realizar el pago
     * @return resultado del procesamiento del pago
     * @throws NegocioException si la solicitud es inválida o el pago no puede procesarse
     */
    ResultadoPagoDTO procesarPago(SolicitudPagoDTO solicitud) throws NegocioException;
}