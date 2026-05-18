package dtos;

import enums.TipoProductoDTO;
import java.util.List;

/**
 * ProductoDTO.
 * Representa la informacion de un producto dentro del sistema.
 *
 * Este DTO se utiliza para transportar los datos de un producto
 * entre las distintas capas de la aplicacion.
 *
 * Contiene informacion como el identificador del producto,
 * nombre, precio, disponibilidad, tipo de producto e imagen.
 *
 * Tambien almacena la lista de ingredientes asociados al producto.
 *
 * @author DishUp
 */
public class ProductoDTO {

    private String id;
    private String nombre;
    private float precio;
    private boolean disponible;
    private TipoProductoDTO tipo;
    private String urlImagen;

    private List<IngredienteEnProductoDTO> ingredientes;

    /**
     * Constructor que permite inicializar los atributos principales
     * del producto.
     *
     * @param id Identificador del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param disponible Indica si el producto esta disponible.
     * @param tipo Tipo de producto.
     * @param urlImagen URL de la imagen del producto.
     */
    public ProductoDTO(String id, String nombre, float precio, boolean disponible, TipoProductoDTO tipo, String urlImagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.tipo = tipo;
        this.urlImagen = urlImagen;
    }

    /**
     * Constructor por defecto.
     */
    public ProductoDTO() {
    }

    /**
     * Obtiene el identificador del producto.
     *
     * @return Identificador del producto.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del producto.
     *
     * @param id Nuevo identificador del producto.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre Nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return Precio del producto.
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio Nuevo precio del producto.
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Indica si el producto esta disponible.
     *
     * @return true si el producto esta disponible, false en caso contrario.
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Establece la disponibilidad del producto.
     *
     * @param disponible Nuevo valor de disponibilidad.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene el tipo de producto.
     *
     * @return Tipo del producto.
     */
    public TipoProductoDTO getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de producto.
     *
     * @param tipo Nuevo tipo de producto.
     */
    public void setTipo(TipoProductoDTO tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la URL de la imagen del producto.
     *
     * @return URL de la imagen.
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * Establece la URL de la imagen del producto.
     *
     * @param urlImagen Nueva URL de la imagen.
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    /**
     * Obtiene la lista de ingredientes asociados al producto.
     *
     * @return Lista de ingredientes.
     */
    public List<IngredienteEnProductoDTO> getIngredientes() {
        return ingredientes;
    }

    /**
     * Establece la lista de ingredientes asociados al producto.
     *
     * @param ingredientes Nueva lista de ingredientes.
     */
    public void setIngredientes(List<IngredienteEnProductoDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

    /**
     * Devuelve una representacion en texto del producto.
     *
     * @return Cadena con la informacion del producto.
     */
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
