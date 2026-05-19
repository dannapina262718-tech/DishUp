/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos_infraestructura;

/**
 *
 * @author valeria
 */
public class RespuestaCodiDTO {
    private boolean aprobado;
    private String mensaje;
    private float monto;
    private String folio;
    private String qrBase64;
    private String referencia;

    public RespuestaCodiDTO() {
    }

    public RespuestaCodiDTO(boolean aprobado, String mensaje, float monto, String folio, String qrBase64, String referencia) {
        this.aprobado = aprobado;
        this.mensaje = mensaje;
        this.monto = monto;
        this.folio = folio;
        this.qrBase64 = qrBase64;
        this.referencia = referencia;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getQrBase64() {
        return qrBase64;
    }

    public void setQrBase64(String qrBase64) {
        this.qrBase64 = qrBase64;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
