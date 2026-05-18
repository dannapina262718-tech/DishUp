/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entidadesMongo;

import enums.EstadoMesa;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;


/**
 *
 * @author DishUp
 */

public class MesaEntidadMongo {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;

    private Integer numero;
    private EstadoMesa estado;
    private String idMesero;

    public MesaEntidadMongo(String id, Integer numero, EstadoMesa estado) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
    }

    public MesaEntidadMongo(Integer numero, EstadoMesa estado, String idMesero) {
        this.numero = numero;
        this.estado = estado;
        this.idMesero = idMesero;
    }

    public MesaEntidadMongo(Integer numero, EstadoMesa estado) {
        this.numero = numero;
        this.estado = estado;
    }
    
    
    public MesaEntidadMongo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public EstadoMesa getEstado() {
        return estado;
    }

    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }

    public String getIdMesero() {
        return idMesero;
    }

    public void setIdMesero(String idMesero) {
        this.idMesero = idMesero;
    }
    
    

    @Override
    public String toString() {
        return "Mesa{" + "id=" + id + ", numero=" + numero + ", estado=" + estado + ", idMesero=" + idMesero + '}';
    }

    
}
