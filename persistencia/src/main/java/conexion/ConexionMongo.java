package conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 * Clase de conexión a MongoDB.
 * 
 * Esta clase se encarga de gestionar la conexión con la base de datos MongoDB
 * utilizando un patrón de cliente único (Singleton) para evitar múltiples conexiones.
 * 
 * Además, configura el registro de codecs para permitir el uso de POJOs
 * en las operaciones de lectura y escritura con MongoDB.
 */
public class ConexionMongo {

    private static final String url = "mongodb+srv://dishup_user:DishUp2026@dishupcluster.b37ha6e.mongodb.net/dishup_db?appName=DishUpCluster";
   // private static final String url = "mongodb://localhost:27017";
    private static final String BASE_DATOS = "dishup_db";

    // Cliente único de MongoDB (Singleton)
    private static MongoClient cliente;

    /**
     * Constructor privado para evitar instanciación.
     */
    private ConexionMongo() {
    }

    /**
     * Obtiene el cliente de MongoDB.
     * Si no existe, lo crea con la configuración de codecs para POJOs.
     *
     * @return cliente de MongoDB
     */
    public static MongoClient obtenerCliente() {
        if (cliente == null) {

            // Proveedor de POJOs para conversión automática
            CodecProvider proveedorPojo = PojoCodecProvider.builder()
                    .automatic(true)
                    .build();

            // Registro de codecs
            CodecRegistry registroCodecs = CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(proveedorPojo)
            );

            // Configuración del cliente MongoDB
            MongoClientSettings configuracionMongo = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(url))
                    .codecRegistry(registroCodecs)
                    .build();

            cliente = MongoClients.create(configuracionMongo);
        }
        return cliente;
    }

    /**
     * Obtiene la base de datos configurada del sistema.
     *
     * @return instancia de MongoDatabase
     */
    public static MongoDatabase obtenerBaseDatos() {
        return obtenerCliente().getDatabase(BASE_DATOS);
    }
}