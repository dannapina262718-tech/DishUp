package adaptadores;

import entidades.Pago;
import entidadesMongo.PagoEntidadMongo;
import org.bson.types.ObjectId;

/**
 * Adaptador encargado de convertir la entidad Pago entre el dominio
 * y su representación en MongoDB.
 * 
 * Incluye la conversión de los datos principales del pago y su detalle asociado,
 * delegando la transformación del detalle al DetallePagoPersistenciaAdapter.
 * 
 * @author valeria
 */
public class PagoPersistenciaAdapter {

    private final DetallePagoPersistenciaAdapter detalleAdapter =
            new DetallePagoPersistenciaAdapter();

    public PagoPersistenciaAdapter() {
    }

    /**
     * Convierte un objeto de dominio Pago a su representación en MongoDB.
     *
     * @param pago objeto de dominio
     * @return entidad Mongo correspondiente o null si el pago es null
     */
    public PagoEntidadMongo aMongo(Pago pago) {

        if (pago == null) {
            return null;
        }

        PagoEntidadMongo mongo = new PagoEntidadMongo();

        // Mongo no genera automáticamente IDs para subdocumentos
        mongo.setId(new ObjectId().toHexString());

        mongo.setMetodoPago(pago.getMetodoPago());
        mongo.setMonto(pago.getMonto());
        mongo.setEstadoPago(pago.getEstadoPago());
        mongo.setFechaPago(pago.getFechaPago());

        mongo.setDetalles(detalleAdapter.aMongo(pago.getDetalles()));

        return mongo;
    }

    /**
     * Convierte una entidad MongoDB a un objeto de dominio Pago.
     *
     * @param mongo entidad persistida en MongoDB
     * @return objeto de dominio o null si la entidad es null
     */
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
        return pago;
    }
}