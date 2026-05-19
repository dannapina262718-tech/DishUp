package adaptadores;

import entidades.Mesa;
import entidadesMongo.MesaEntidadMongo;

/**
 * Adaptador de persistencia para la entidad Mesa.
 * 
 * Esta clase se encarga de convertir objetos del modelo de dominio (Mesa)
 * a entidades de MongoDB (MesaEntidadMongo) y viceversa.
 * 
 * Su función principal es desacoplar la lógica de negocio de la capa de
 * persistencia, permitiendo una conversión clara entre ambos modelos.
 * 
 * @author DishUp
 */
public class MesaPersistenciaAdapter {

    /**
     * Convierte una Mesa del modelo de dominio a una entidad MongoDB.
     *
     * @param mesa objeto del dominio a convertir
     * @return entidad MesaEntidadMongo o null si la mesa es null
     */
    public MesaEntidadMongo aMongo(Mesa mesa) {
        if (mesa == null) {
            return null;
        }

        MesaEntidadMongo mongo = new MesaEntidadMongo();

        mongo.setId(mesa.getId());
        mongo.setNumero(mesa.getNumero());
        mongo.setEstado(mesa.getEstado());
        mongo.setIdMesero(mesa.getIdMesero());

        return mongo;
    }

    /**
     * Convierte una entidad MongoDB a una Mesa del modelo de dominio.
     *
     * @param mongo entidad MesaEntidadMongo a convertir
     * @return objeto Mesa del dominio o null si la entidad es null
     */
    public Mesa aDominio(MesaEntidadMongo mongo) {

        if (mongo == null) {
            return null;
        }

        return new Mesa(
                mongo.getId(),
                mongo.getNumero(),
                mongo.getEstado(),
                mongo.getIdMesero()
        );
    }
}