package procesadores;

import enums.MetodoPago;
import excepcion.NegocioException;

/**
 * Fábrica encargada de instanciar el procesador de pago correspondiente.
 * <p>
 * Centraliza la creación de estrategias de pago (EFECTIVO, TARJETA, CODI),
 * evitando que la lógica de negocio dependa directamente de las implementaciones
 * concretas de cada método de pago.
 * </p>
 */
public class ProcesadorPagoFactory {

    /**
     * Crea una instancia del procesador de pago según el método indicado.
     *
     * @param metodo método de pago seleccionado
     * @return instancia del procesador correspondiente
     * @throws NegocioException si el método de pago no está soportado
     */
    public static IProcesadorPago crearProcesador(MetodoPago metodo) throws NegocioException {

        switch (metodo) {

            case EFECTIVO:
                return new ProcesadorPagoEfectivo();

            case TARJETA:
                return new ProcesadorPagoTarjeta();

            case CODI:
                return new ProcesadorPagoCodi();

            default:
                throw new NegocioException("Método no soportado");
        }
    }
}