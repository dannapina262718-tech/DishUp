/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author valeria
 */
public class DetallePagoPersistenciaAdapter {
    public DetallePagoEntidadMongo aMongo(DetallePago detalle) {

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
