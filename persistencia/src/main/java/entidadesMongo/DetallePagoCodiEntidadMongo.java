/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 *
 * @author valeria
 */
@BsonDiscriminator(value = "codi")
public class DetallePagoCodiEntidadMongo extends DetallePagoEntidadMongo {
    private String referencia;
    private String folio;

    public DetallePagoCodiEntidadMongo() {
    }

    public DetallePagoCodiEntidadMongo(String referencia, String folio) {
        this.referencia = referencia;
        this.folio = folio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
}
