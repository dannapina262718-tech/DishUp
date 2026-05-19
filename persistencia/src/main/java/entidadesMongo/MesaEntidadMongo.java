package entidadesMongo;

import enums.EstadoMesa;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 * Entidad de persistencia Mesa para MongoDB.
 * 
 * Esta clase representa cómo se almacena una mesa dentro de la base de datos.
 * Contiene información básica como número de mesa, estado actual y el mesero asignado.
 * 
 * Se utiliza únicamente en la capa de persistencia y no debe contener lógica de negocio.
 */
public class MesaEntidadMongo {

    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;

    private Integer numero;
    private EstadoMesa estado;
    private String idMesero;

    /**
     * Constructor con id, número y estado.
     *
     * @param id identificador de la mesa
     * @param numero número de la mesa
     * @param estado estado actual de la mesa
     */
    public MesaEntidadMongo(String id, Integer numero, EstadoMesa estado) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
    }

    /**
     * Constructor con número, estado y mesero asignado.
     *
     * @param numero número de la mesa
     * @param estado estado de la mesa
     * @param idMesero identificador del mesero asignado
     */
    public MesaEntidadMongo(Integer numero, EstadoMesa estado, String idMesero) {
        this.numero = numero;
        this.estado = estado;
        this.idMesero = idMesero;
    }

    /**
     * Constructor con número y estado.
     *
     * @param numero número de la mesa
     * @param estado estado de la mesa
     */
    public MesaEntidadMongo(Integer numero, EstadoMesa estado) {
        this.numero = numero;
        this.estado = estado;
    }

    /**
     * Constructor vacío requerido por MongoDB.
     */
    public MesaEntidadMongo() {
    }

    /**
     * Obtiene el identificador de la mesa.
     *
     * @return id de la mesa
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador de la mesa.
     *
     * @param id identificador de la mesa
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el número de la mesa.
     *
     * @return número de mesa
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Establece el número de la mesa.
     *
     * @param numero número de mesa
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el estado actual de la mesa.
     *
     * @return estado de la mesa
     */
    public EstadoMesa getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la mesa.
     *
     * @param estado estado de la mesa
     */
    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el identificador del mesero asignado a la mesa.
     *
     * @return id del mesero
     */
    public String getIdMesero() {
        return idMesero;
    }

    /**
     * Establece el identificador del mesero asignado a la mesa.
     *
     * @param idMesero id del mesero
     */
    public void setIdMesero(String idMesero) {
        this.idMesero = idMesero;
    }

    /**
     * Representación en texto de la mesa.
     *
     * @return cadena con información de la mesa
     */
    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", numero=" + numero +
                ", estado=" + estado +
                ", idMesero=" + idMesero +
                '}';
    }
}