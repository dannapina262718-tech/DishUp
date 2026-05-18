package dtos;

/**
 * IngredienteEnProductoDTO.
 * Representa un ingrediente asociado a un producto dentro del sistema.
 *
 * Este DTO se utiliza para transportar la informacion de los ingredientes
 * que forman parte de un producto.
 *
 * Contiene informacion como el identificador del ingrediente,
 * su nombre, la cantidad utilizada y si el ingrediente puede
 * ser removido del producto.
 *
 * @author DishUp
 */
public class IngredienteEnProductoDTO {
    
    private String id;
    private String nombre;
    private int cantidad;
    private boolean removible;

    /**
     * Constructor que permite inicializar todos los atributos
     * del ingrediente en producto.
     *
     * @param id Identificador del ingrediente.
     * @param nombre Nombre del ingrediente.
     * @param cantidad Cantidad utilizada del ingrediente.
     * @param removible Indica si el ingrediente puede removerse.
     */
    public IngredienteEnProductoDTO(String id, String nombre, int cantidad, boolean removible) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.removible = removible;
    }

    /**
     * Constructor por defecto.
     */
    public IngredienteEnProductoDTO() {
    }

    /**
     * Obtiene el identificador del ingrediente.
     *
     * @return Identificador del ingrediente.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del ingrediente.
     *
     * @param id Nuevo identificador del ingrediente.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del ingrediente.
     *
     * @return Nombre del ingrediente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del ingrediente.
     *
     * @param nombre Nuevo nombre del ingrediente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad utilizada del ingrediente.
     *
     * @return Cantidad del ingrediente.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad utilizada del ingrediente.
     *
     * @param cantidad Nueva cantidad del ingrediente.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Indica si el ingrediente puede removerse del producto.
     *
     * @return true si el ingrediente es removible, false en caso contrario.
     */
    public boolean isRemovible() {
        return removible;
    }

    /**
     * Establece si el ingrediente puede removerse del producto.
     *
     * @param removible Nuevo valor de removible.
     */
    public void setRemovible(boolean removible) {
        this.removible = removible;
    }
    
}
