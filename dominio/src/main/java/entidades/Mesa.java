/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.EstadoMesa;

/**
 *
 * @author DishUp
 */
public class Mesa {
    private String id;
    private Integer numero;
    private EstadoMesa estado;
    private String idMesero;

    public Mesa() {
    }

    public Mesa(String id, Integer numero, EstadoMesa estado, String idMesero) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
        this.idMesero = idMesero;
    }

    public Mesa(Integer numero, EstadoMesa estado) {
        this.numero = numero;
        this.estado = estado;
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
}