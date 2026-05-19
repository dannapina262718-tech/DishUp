package dtos_infraestructura;

import enums.TipoProductoDTOInfraestructura;
import java.util.List;

/**
 * ProductoDTOInfraestructura.
 * Representa la informacion de un producto proveniente de un sistema externo.
 *
 * Este DTO se utiliza como puente para intercambiar informacion
 * de productos entre el sistema principal y la infraestructura externa.
 *
 * Incluye datos como nombre, precio, disponibilidad, tiempo de preparacion,
 * tipo de producto, imagen e ingredientes asociados.
 *
 * @author DishUp
 */
public class ProductoDTOInfraestructura {

    private String id;
    private String nombre;
    private float precio;
    private boolean disponible;
    private Integer tiempoPreparacion;
    private TipoProductoDTOInfraestructura tipo;
    private String urlImagen;
    private List<IngredienteDTOInfraestructura> ingredientes;

    /**
     * Constructor por defecto.
     */
    public ProductoDTOInfraestructura() {
    }

    /**
     * Constructor que inicializa los datos del producto externo.
     *
     * @param id Identificador del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param disponible Indica si el producto esta disponible.
     * @param tiempoPreparacion Tiempo estimado de preparacion.
     * @param tipo Tipo de producto.
     * @param urlImagen URL de la imagen del producto.
     * @param ingredientes Lista de ingredientes del producto.
     */
    public ProductoDTOInfraestructura(String id, String nombre, float precio,
                                     boolean disponible, Integer tiempoPreparacion,
                                     TipoProductoDTOInfraestructura tipo,
                                     String urlImagen,
                                     List<IngredienteDTOInfraestructura> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tipo = tipo;
        this.urlImagen = urlImagen;
        this.ingredientes = ingredientes;
    }

    /**
     * Obtiene el identificador del producto.
     *
     * @return id del producto.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del producto.
     *
     * @param id nuevo id del producto.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return precio del producto.
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio nuevo precio del producto.
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Indica si el producto esta disponible.
     *
     * @return true si esta disponible, false si no.
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Establece la disponibilidad del producto.
     *
     * @param disponible nuevo estado de disponibilidad.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene el tiempo estimado de preparacion.
     *
     * @return tiempo de preparacion.
     */
    public Integer getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    /**
     * Establece el tiempo estimado de preparacion.
     *
     * @param tiempoPreparacion nuevo tiempo de preparacion.
     */
    public void setTiempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    /**
     * Obtiene el tipo de producto.
     *
     * @return tipo de producto.
     */
    public TipoProductoDTOInfraestructura getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de producto.
     *
     * @param tipo nuevo tipo de producto.
     */
    public void setTipo(TipoProductoDTOInfraestructura tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la URL de la imagen del producto.
     *
     * @return url de la imagen.
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * Establece la URL de la imagen del producto.
     *
     * @param urlImagen nueva url de imagen.
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    /**
     * Obtiene la lista de ingredientes del producto.
     *
     * @return lista de ingredientes.
     */
    public List<IngredienteDTOInfraestructura> getIngredientes() {
        return ingredientes;
    }

    /**
     * Establece la lista de ingredientes del producto.
     *
     * @param ingredientes nueva lista de ingredientes.
     */
    public void setIngredientes(List<IngredienteDTOInfraestructura> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
