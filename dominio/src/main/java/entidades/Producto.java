package entidades;

import enums.TipoProducto;
import java.util.List;

/**
 * Producto.
 * Representa un producto dentro del sistema.
 *
 * Esta clase contiene la informacion de un producto que se ofrece en el menu,
 * incluyendo su nombre, precio, tipo, imagen y los ingredientes que lo componen.
 *
 * Se utiliza para la gestion de productos dentro del sistema de restaurante.
 *
 * @author DishUp
 */
public class Producto {

    private String id;
    private String nombre;
    private float precio;
    private boolean disponible;
    private TipoProducto tipo;
    private String urlImagen;
    private List<IngredienteEnProducto> ingredientes;

    /**
     * Constructor vacio.
     */
    public Producto() {
    }

    /**
     * Constructor con todos los atributos del producto.
     *
     * @param id Identificador del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param disponible Indica si el producto esta disponible.
     * @param tipo Tipo de producto (comida, bebida, etc).
     * @param urlImagen Ruta o enlace de la imagen del producto.
     * @param ingredientes Lista de ingredientes del producto.
     */
    public Producto(String id, String nombre, float precio, boolean disponible,
                    TipoProducto tipo, String urlImagen,
                    List<IngredienteEnProducto> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.tipo = tipo;
        this.urlImagen = urlImagen;
        this.ingredientes = ingredientes;
    }
    /**
     * Obtiene el id del producto.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el id del producto.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del producto.
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Indica si el producto esta disponible.
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Cambia la disponibilidad del producto.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene el tipo de producto.
     */
    public TipoProducto getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de producto.
     */
    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la url de la imagen del producto.
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * Establece la url de la imagen del producto.
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    /**
     * Obtiene la lista de ingredientes del producto.
     */
    public List<IngredienteEnProducto> getIngredientes() {
        return ingredientes;
    }

    /**
     * Establece la lista de ingredientes del producto.
     */
    public void setIngredientes(List<IngredienteEnProducto> ingredientes) {
        this.ingredientes = ingredientes;
    }
}