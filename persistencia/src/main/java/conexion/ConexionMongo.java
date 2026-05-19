/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import entidadesMongo.ComandaEntidadMongo;
import entidadesMongo.DetallePagoCodiEntidadMongo;
import entidadesMongo.DetallePagoEfectivoEntidadMongo;
import entidadesMongo.DetallePagoEntidadMongo;
import entidadesMongo.DetallePagoTarjetaEntidadMongo;
import entidadesMongo.PagoEntidadMongo;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author DishUp
 */

public class ConexionMongo {
    private static final String url = "mongodb+srv://dishup_user:DishUp2026@dishupcluster.b37ha6e.mongodb.net/dishup_db?appName=DishUpCluster";
    //private static final String url = "mongodb://localhost:27017";
    private static final String BASE_DATOS = "dishup_db";
    
    //cliente (acceso a la bd solo 1 vez)
    private static MongoClient cliente;

    private ConexionMongo() {
    }
    
    public static MongoClient obtenerCliente(){
        if (cliente == null) {
            // proveedor de pojos
            CodecProvider proveedorPojo = PojoCodecProvider.builder()
                    .automatic(true)
                    .register(
                            ComandaEntidadMongo.class,
                            PagoEntidadMongo.class,
                            DetallePagoEntidadMongo.class,
                            DetallePagoEfectivoEntidadMongo.class,
                            DetallePagoTarjetaEntidadMongo.class,
                            DetallePagoCodiEntidadMongo.class
                    )
                    .build();
            // almacen de codecs
            CodecRegistry registroCodecs = CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(proveedorPojo)
            );

            MongoClientSettings configuracionMongo = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(url))
                    .codecRegistry(registroCodecs)
                    .build();
            cliente = MongoClients.create(configuracionMongo);
        }
        return cliente;
    }
    
    public static MongoDatabase obtenerBaseDatos(){
        return obtenerCliente().getDatabase(BASE_DATOS);
    }
}
