package adaptadores;

import entidades.DetallePago;
import entidades.DetallePagoCodi;
import entidades.DetallePagoEfectivo;
import entidades.DetallePagoTarjeta;
import entidadesMongo.DetallePagoCodiEntidadMongo;
import entidadesMongo.DetallePagoEfectivoEntidadMongo;
import entidadesMongo.DetallePagoEntidadMongo;
import entidadesMongo.DetallePagoTarjetaEntidadMongo;

/**
 * Adaptador encargado de convertir los distintos tipos de detalles de pago
 * del dominio hacia entidades de persistencia MongoDB.
 * <p>
 * Permite mapear la jerarquía de DetallePago a sus equivalentes en la base de datos,
 * separando la lógica de dominio de la capa de persistencia.
 * </p>
 * 
 * @author DishUp
 */
public class DetallePagoPersistenciaAdapter {

    /**
     * Convierte un detalle de pago del dominio a su representación en MongoDB.
     *
     * @param detalle objeto de dominio que representa el detalle del pago
     * @return entidad Mongo correspondiente al tipo de detalle recibido,
     *         o null si el tipo no es reconocido
     */
    public DetallePagoEntidadMongo aMongo(DetallePago detalle) {

        if (detalle == null) {
            return null;
        }

        if (detalle instanceof DetallePagoEfectivo efectivo) {

            DetallePagoEfectivoEntidadMongo mongo =
                    new DetallePagoEfectivoEntidadMongo();

            mongo.setMontoRecibido(efectivo.getMontoRecibido());
            mongo.setCambio(efectivo.getCambio());

            return mongo;
        }

        if (detalle instanceof DetallePagoTarjeta tarjeta) {

            DetallePagoTarjetaEntidadMongo mongo =
                    new DetallePagoTarjetaEntidadMongo();

            mongo.setNumeroAutorizacion(tarjeta.getNumeroAutorizacion());
            mongo.setUltimos4Digitos(tarjeta.getUltimos4Digitos());
            mongo.setBanco(tarjeta.getBanco());
            mongo.setTipoTarjeta(tarjeta.getTipoTarjeta());

            return mongo;
        }

        if (detalle instanceof DetallePagoCodi codi) {

            DetallePagoCodiEntidadMongo mongo =
                    new DetallePagoCodiEntidadMongo();

            mongo.setReferencia(codi.getReferencia());
            mongo.setFolio(codi.getFolio());

            return mongo;
        }

        return null;
    }
}