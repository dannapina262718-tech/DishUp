/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dtos;

import enums.EstadoMesaDTO;


/**
 *
 * @author DishUp
 */

public class MesaDTO {
    private String idMesa;
    private int numeroMesa;
    private EstadoMesaDTO estado;
    private String idMesero;

    public MesaDTO(String idMesa, int numeroMesa, EstadoMesaDTO estado, String idMesero) {
        this.idMesa = idMesa;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.idMesero = idMesero;
    }

    public MesaDTO(int numeroMesa, EstadoMesaDTO estado) {
        this.numeroMesa = numeroMesa;
        this.estado = estado;
    }
        
    public MesaDTO() {
    }

        
    public String getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(String idMesa) {
        this.idMesa = idMesa;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public EstadoMesaDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoMesaDTO estado) {
        this.estado = estado;
    }

    public String getIdMesero() {
        return idMesero;
    }

    public void setIdMesero(String idMesero) {
        this.idMesero = idMesero;
    }
    
    
}
