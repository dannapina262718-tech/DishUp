/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import enums.Banco;
import enums.TipoTarjeta;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 *
 * @author valeria
 */
@BsonDiscriminator(value = "tarjeta")
public class DetallePagoTarjetaEntidadMongo extends DetallePagoEntidadMongo {
    private String numeroAutorizacion;
    private String ultimos4Digitos;
    private Banco banco;
    private TipoTarjeta tipoTarjeta;

    public DetallePagoTarjetaEntidadMongo() {
    }

    public DetallePagoTarjetaEntidadMongo(String numeroAutorizacion, String ultimos4Digitos, Banco banco, TipoTarjeta tipoTarjeta) {
        this.numeroAutorizacion = numeroAutorizacion;
        this.ultimos4Digitos = ultimos4Digitos;
        this.banco = banco;
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getUltimos4Digitos() {
        return ultimos4Digitos;
    }

    public void setUltimos4Digitos(String ultimos4Digitos) {
        this.ultimos4Digitos = ultimos4Digitos;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }
}
