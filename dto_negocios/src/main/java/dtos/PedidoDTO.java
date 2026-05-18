/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import enums.EstadoPedidoDTO;
import java.time.LocalDateTime;

/**
 * PedidoDTO. Representa la informacion de un pedido ya registrado en el
 * sistema.
 *
 * Este DTO incluye los datos del producto solicitado, la cantidad, el estado
 * del pedido y las especificaciones del cliente, permitiendo dar seguimiento a
 * su preparacion dentro del flujo de cocina.
 *
 * @author DishUp
 */
public class PedidoDTO {

    private String id;
    private String idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private EstadoPedidoDTO estado;
    private String descripcion;
    private float precioProducto;
    private LocalDateTime fechaPedido;

    public PedidoDTO(String id, String idProducto, String nombreProducto, Integer cantidad, EstadoPedidoDTO estado, String descripcion, float precioProducto, LocalDateTime fechaPedido) {
        this.id = id;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.estado = estado;
        this.descripcion = descripcion;
        this.precioProducto = precioProducto;
        this.fechaPedido = fechaPedido;
    }

    public PedidoDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public EstadoPedidoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedidoDTO estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    @Override
    public String toString() {

        if (descripcion == null || descripcion.isBlank()) {
            return nombreProducto + " $" + precioProducto;
        }

        return nombreProducto + " (" + descripcion + ") $" + precioProducto;
    }
}