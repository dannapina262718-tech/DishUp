package dtos;

import enums.TipoProductoDTO;
import java.util.List;

public class ProductoDTO {

    private String id;
    private String nombre;
    private float precio;
    private boolean disponible;
    private TipoProductoDTO tipo;
    private String urlImagen;

    private List<IngredienteEnProductoDTO> ingredientes;

    public ProductoDTO(String id, String nombre, float precio, boolean disponible, TipoProductoDTO tipo, String urlImagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.tipo = tipo;
        this.urlImagen = urlImagen;
    }

    public ProductoDTO() {
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public TipoProductoDTO getTipo() {
        return tipo;
    }

    public void setTipo(TipoProductoDTO tipo) {
        this.tipo = tipo;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public List<IngredienteEnProductoDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteEnProductoDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "ProductoDTO{"
                + "id=" + id
                + ", nombre=" + nombre
                + ", precio=" + precio
                + ", disponible=" + disponible
                + ", tipo=" + tipo
                + ", urlImagen=" + urlImagen
                + ", ingredientes=" + ingredientes
                + '}';
    }
}
