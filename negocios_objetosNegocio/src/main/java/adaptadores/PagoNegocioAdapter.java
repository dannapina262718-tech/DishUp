package adaptadores;

import dtos.ResultadoPagoDTO;
import entidades.Pago;
import enums.EstadoPagoIndividual;
import java.time.LocalDateTime;

/**
 * PagoNegocioAdapter.
 * 
 * Clase encargada de convertir el resultado de un pago (DTO)
 * en una entidad de negocio Pago.
 * 
 * Su funcion es adaptar la respuesta obtenida despues de procesar
 * un pago en el sistema, transformandola en una entidad persistente
 * que pueda ser utilizada dentro de la logica del dominio.
 * 
 * En este proceso se asigna automaticamente el estado del pago
 * como PAGADO y la fecha actual como fecha de registro.
 * 
 * @author valeria
 */
public class PagoNegocioAdapter {

    /**
     * Convierte un ResultadoPagoDTO a una entidad Pago.
     *
     * @param resultado resultado del procesamiento del pago
     * @return entidad Pago construida a partir del resultado
     */
    public Pago aEntidad(ResultadoPagoDTO resultado) {

        if (resultado == null) {
            return null;
        }

        Pago pago = new Pago();

        pago.setMetodoPago(resultado.getMetodoPago());
        pago.setMonto(resultado.getMontoPagado());
        pago.setEstadoPago(EstadoPagoIndividual.PAGADO);
        pago.setFechaPago(LocalDateTime.now());
        pago.setDetalles(resultado.getDetallePago());

        return pago;
    }
}