/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dtos;


/**
 *
 * @author DishUp
 */

public class IngredienteEnProductoDTO {
    private String id;
    private String nombre;
    private int cantidad;
    private boolean removible;

    public IngredienteEnProductoDTO(String id, String nombre, int cantidad, boolean removible) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.removible = removible;
    }

    public IngredienteEnProductoDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isRemovible() {
        return removible;
    }

    public void setRemovible(boolean removible) {
        this.removible = removible;
    }
    
    
}
