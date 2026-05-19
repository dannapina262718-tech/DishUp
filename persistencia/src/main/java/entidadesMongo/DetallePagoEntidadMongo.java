package entidadesMongo;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 * Clase abstracta base de persistencia para los detalles de pago en MongoDB.
 * * Esta clase define la estructura base y habilita el soporte polimórfico mediante 
 * la anotación {@link BsonDiscriminator}. Permite que las diferentes formas de pago 
 * (como CoDi o Efectivo) sean almacenadas y mapeadas de manera correcta dentro de 
 * las comandas en la base de datos.
 * * Se utiliza únicamente en la capa de persistencia y no debe contener lógica de negocio.
 * * @author valeria
 */
@BsonDiscriminator
public abstract class DetallePagoEntidadMongo {

    /**
     * Constructor vacío requerido por MongoDB para inicializar las subclases mediante reflexión.
     */
    public DetallePagoEntidadMongo() {
    }
}