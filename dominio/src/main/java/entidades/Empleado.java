/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.EstadoEmpleado;
import enums.RolEmpleado;

/**
 *
 * @author DishUp
 */
public class Empleado {

    private String id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String user;
    private RolEmpleado rol;
    private EstadoEmpleado estado;

    public Empleado() {
    }

    public Empleado(String id, String nombres, String apellidoPaterno, String apellidoMaterno, String user, RolEmpleado rol, EstadoEmpleado estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.user = user;
        this.rol = rol;
        this.estado = estado;
    }

    public Empleado(String nombres, String apellidoPaterno, String apellidoMaterno, String user, RolEmpleado rol, EstadoEmpleado estado) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.user = user;
        this.rol = rol;
        this.estado = estado;
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

    public RolEmpleado getRol() {
        return rol;
    }

    public void setRol(RolEmpleado rol) {
        this.rol = rol;
    }

    public EstadoEmpleado getEstado() {
        return estado;
    }

    public void setEstado(EstadoEmpleado estado) {
        this.estado = estado;
    }
}
