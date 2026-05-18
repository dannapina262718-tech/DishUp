/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dtos;

import enums.EstadoEmpleadoDTO;
import enums.RolEmpleadoDTO;

/**
 *
 * @author DishUp
 */

public class EmpleadoDTO {
    private String id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;

    private String user;
    private RolEmpleadoDTO rol;
    private EstadoEmpleadoDTO estado;

    public EmpleadoDTO(String id, String nombres, String apellidoPaterno, String apellidoMaterno, String user, RolEmpleadoDTO rol, EstadoEmpleadoDTO estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.user = user;
        this.rol = rol;
        this.estado = estado;
    }

    public EmpleadoDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String user, RolEmpleadoDTO rol, EstadoEmpleadoDTO estado) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.user = user;
        this.rol = rol;
        this.estado = estado;
    }

    public EmpleadoDTO() {
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public RolEmpleadoDTO getRol() {
        return rol;
    }

    public void setRol(RolEmpleadoDTO rol) {
        this.rol = rol;
    }

    public EstadoEmpleadoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoEmpleadoDTO estado) {
        this.estado = estado;
    }
    
    
}
