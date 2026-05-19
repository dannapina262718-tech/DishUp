package dtos;

/**
 * ProductoIngredienteDTO.
 * Representa la relacion entre un producto y un ingrediente.
 *
 * Este DTO se utiliza para transportar la informacion de los
 * ingredientes requeridos para la preparacion de un producto.
 *
 * Contiene informacion como los identificadores del producto
 * y del ingrediente, la cantidad requerida y si el ingrediente
 * puede ser removido.
 *
 * @author valeria
 */
public class ProductoIngredienteDTO {
    
    private String id;
    private String idProducto;
    private String idIngrediente;
    private int cantidadRequerida;
    private boolean removible;

    /**
     * Constructor por defecto.
     */
    public ProductoIngredienteDTO() {
    }

    /**
     * Constructor que permite inicializar todos los atributos
     * de la relacion producto-ingrediente.
     *
     * @param id Identificador de la relacion.
     * @param idProducto Identificador del producto.
     * @param idIngrediente Identificador del ingrediente.
     * @param cantidadRequerida Cantidad requerida del ingrediente.
     * @param removible Indica si el ingrediente puede removerse.
     */
    public ProductoIngredienteDTO(String id, String idProducto, String idIngrediente, int cantidadRequerida, boolean removible) {
        this.id = id;
        this.idProducto = idProducto;
        this.idIngrediente = idIngrediente;
        this.cantidadRequerida = cantidadRequerida;
        this.removible = removible;
    }

    /**
     * Obtiene el identificador de la relacion.
     *
     * @return Identificador de la relacion.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador de la relacion.
     *
     * @param id Nuevo identificador de la relacion.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del producto.
     *
     * @return Identificador del producto.
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto.
     *
     * @param idProducto Nuevo identificador del producto.
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el identificador del ingrediente.
     *
     * @return Identificador del ingrediente.
     */
    public String getIdIngrediente() {
        return idIngrediente;
    }

    /**
     * Establece el identificador del ingrediente.
     *
     * @param idIngrediente Nuevo identificador del ingrediente.
     */
    public void setIdIngrediente(String idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    /**
     * Obtiene la cantidad requerida del ingrediente.
     *
     * @return Cantidad requerida.
     */
    public int getCantidadRequerida() {
        return cantidadRequerida;
    }

    /**
     * Establece la cantidad requerida del ingrediente.
     *
     * @param cantidadRequerida Nueva cantidad requerida.
     */
    public void setCantidadRequerida(int cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    /**
     * Indica si el ingrediente puede removerse.
     *
     * @return true si el ingrediente es removible, false en caso contrario.
     */
    public boolean isRemovible() {
        return removible;
    }

    /**
     * Establece si el ingrediente puede removerse.
     *
     * @param removible Nuevo valor de removible.
     */
    public void setRemovible(boolean removible) {
        this.removible = removible;
    }
    
}