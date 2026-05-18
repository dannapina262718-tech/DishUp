package entidades;

/**
 * IngredienteEnProducto.
 * Representa un ingrediente que forma parte de un producto
 * dentro del sistema en la capa de dominio.
 *
 * Esta clase almacena informacion basica del ingrediente,
 * como su identificador, nombre, cantidad requerida en el producto
 * y si puede ser removido por el cliente.
 *
 * @author DishUp
 */
public class IngredienteEnProducto {

    private String id;
    private String nombre;
    private Integer cantidad;
    private boolean removible;

    /**
     * Constructor por defecto.
     */
    public IngredienteEnProducto() {
    }

    /**
     * Constructor que inicializa todos los atributos del ingrediente en producto.
     *
     * @param id identificador del ingrediente
     * @param nombre nombre del ingrediente
     * @param cantidad cantidad utilizada en el producto
     * @param removible indica si el ingrediente puede ser removido
     */
    public IngredienteEnProducto(String id, String nombre, Integer cantidad, boolean removible) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.removible = removible;
    }

    /**
     * Obtiene el identificador del ingrediente.
     *
     * @return id del ingrediente
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del ingrediente.
     *
     * @param id nuevo id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del ingrediente.
     *
     * @return nombre del ingrediente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del ingrediente.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad del ingrediente dentro del producto.
     *
     * @return cantidad utilizada
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del ingrediente dentro del producto.
     *
     * @param cantidad nueva cantidad
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Indica si el ingrediente puede ser removido por el cliente.
     *
     * @return true si es removible, false si no
     */
    public boolean isRemovible() {
        return removible;
    }

    /**
     * Establece si el ingrediente puede ser removido.
     *
     * @param removible nuevo estado de removibilidad
     */
    public void setRemovible(boolean removible) {
        this.removible = removible;
    }
}
