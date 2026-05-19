/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import entidades.Pago;
import entidadesMongo.PagoEntidadMongo;
import org.bson.types.ObjectId;

/**
 *
 * @author valeria
 */
public class PagoPersistenciaAdapter {
    private final DetallePagoPersistenciaAdapter detalleAdapter =
        new DetallePagoPersistenciaAdapter();
    public PagoPersistenciaAdapter() {
    }

    public PagoEntidadMongo aMongo(Pago pago) {

        if (pago == null) {
            return null;
        }

        PagoEntidadMongo mongo = new PagoEntidadMongo();

        // Mongo NO genera ids automáticos para documentos embebidos
        mongo.setId(new ObjectId().toHexString());

        mongo.setMetodoPago(pago.getMetodoPago());
        mongo.setMonto(pago.getMonto());
        mongo.setEstadoPago(pago.getEstadoPago());
        mongo.setFechaPago(pago.getFechaPago());
        mongo.setDetalles(detalleAdapter.aMongo(pago.getDetalles()));

        return mongo;
    }

    public Pago aDominio(PagoEntidadMongo mongo) {

        if (mongo == null) {
            return null;
        }

        Pago pago = new Pago();

        pago.setId(mongo.getId());
        pago.setMetodoPago(mongo.getMetodoPago());
        pago.setMonto(mongo.getMonto());
        pago.setEstadoPago(mongo.getEstadoPago());
        pago.setFechaPago(mongo.getFechaPago());
        mongo.setDetalles(detalleAdapter.aMongo(pago.getDetalles()));

        return pago;
    }
}
