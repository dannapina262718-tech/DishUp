/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import entidades.DetallePago;
import enums.EstadoPago;
import enums.EstadoPagoIndividual;
import enums.MetodoPago;
import java.time.LocalDateTime;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author valeria
 */
public class PagoEntidadMongo {
    
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;

    private MetodoPago metodoPago;
    private float monto;
    private EstadoPagoIndividual estadoPago;
    private LocalDateTime fechaPago;

    private DetallePagoEntidadMongo detalles;

    public PagoEntidadMongo() {
    }

    public PagoEntidadMongo(String id, MetodoPago metodoPago, float monto, EstadoPagoIndividual estadoPago, LocalDateTime fechaPago, DetallePagoEntidadMongo detalles) {
        this.id = id;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.estadoPago = estadoPago;
        this.fechaPago = fechaPago;
        this.detalles = detalles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public EstadoPagoIndividual getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPagoIndividual estadoPago) {
        this.estadoPago = estadoPago;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public DetallePagoEntidadMongo getDetalles() {
        return detalles;
    }

    public void setDetalles(DetallePagoEntidadMongo detalles) {
        this.detalles = detalles;
    }
}
