package entidades;

import enums.TipoProducto;
import java.util.List;

/**
 * Entidad de dominio que representa un Producto en el sistema de restaurante.
 * * Esta clase almacena la información de un producto ofrecido en el menú comercial,
 * incluyendo sus especificaciones de precio, estado de disponibilidad para venta, 
 * clasificación por tipo, recursos multimedia asociados y el desglose de los 
 * ingredientes que componen su receta para el control de inventario.
 * * Pertenece al modelo de dominio central de la aplicación.
 * * @author DishUp
 * @author valeria
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
     * Constructor vacío requerido para marcos de trabajo de persistencia o serialización.
     */
    public Producto() {
    }

    /**
     * Constructor completo para inicializar una instancia de Producto con todos sus atributos.
     *
     * @param id identificador único del producto (mapeado típicamente a un ObjectId)
     * @param nombre nombre comercial del producto tal como se muestra en el menú
     * @param precio precio de venta al público en moneda local
     * @param disponible indicador lógico que dicta si el platillo o bebida puede ser ordenado
     * @param tipo clasificación del producto (e.g., COMIDA, BEBIDA, POSTRE)
     * @param urlImagen ruta física, relativa o URL remota de la imagen ilustrativa del producto
     * @param ingredientes listado detallado de ingredientes y porciones requeridas en su preparación
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
     * Obtiene el identificador único del producto.
     *
     * @return identificador del producto
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     *
     * @param id identificador del producto
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre comercial del producto.
     *
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre comercial del producto.
     *
     * @param nombre nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio de venta asignado al producto.
     *
     * @return precio del producto
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de venta del producto.
     *
     * @param precio precio del producto
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Evalúa si el producto se encuentra disponible para su venta en el menú actual.
     *
     * @return true si el producto está disponible; false en caso contrario
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Modifica el estado de disponibilidad del producto para la venta.
     *
     * @param disponible indicador de disponibilidad del producto
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene la categoría o tipo de producto asignado.
     *
     * @return tipo de producto en base al enumerador {@link TipoProducto}
     */
    public TipoProducto getTipo() {
        return tipo;
    }

    /**
     * Establece la categoría o tipo de producto.
     *
     * @param tipo tipo de producto en base al enumerador {@link TipoProducto}
     */
    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la ruta o enlace URL de la imagen del producto.
     *
     * @return cadena con la ubicación del recurso de imagen
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * Establece la ruta o enlace URL de la imagen del producto.
     *
     * @param urlImagen cadena con la ubicación del recurso de imagen
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    /**
     * Obtiene la lista de ingredientes asociados que componen la receta del producto.
     *
     * @return listado de objetos {@link IngredienteEnProducto}
     */
    public List<IngredienteEnProducto> getIngredientes() {
        return ingredientes;
    }

    /**
     * Establece la lista de ingredientes que componen la receta del producto.
     *
     * @param ingredientes listado de objetos {@link IngredienteEnProducto}
     */
    public void setIngredientes(List<IngredienteEnProducto> ingredientes) {
        this.ingredientes = ingredientes;
    }
}