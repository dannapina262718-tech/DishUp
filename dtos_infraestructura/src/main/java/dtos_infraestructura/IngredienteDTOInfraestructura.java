package dtos_infraestructura;

/**
 * IngredienteDTOInfraestructura.
 * Representa la informacion de un ingrediente proveniente de un sistema externo.
 *
 * Este DTO se utiliza como puente para recibir y enviar informacion
 * de ingredientes entre el sistema principal y la infraestructura externa.
 *
 * Contiene datos basicos como el nombre del ingrediente,
 * la cantidad y si puede ser removido.
 *
 * @author DishUp
 */
public class IngredienteDTOInfraestructura {

    private String nombre;
    private Integer cantidad;
    private boolean removible;

    /**
     * Constructor por defecto.
     */
    public IngredienteDTOInfraestructura() {
    }

    /**
     * Constructor que inicializa los datos del ingrediente externo.
     *
     * @param nombre Nombre del ingrediente.
     * @param cantidad Cantidad del ingrediente.
     * @param removible Indica si el ingrediente puede ser removido.
     */
    public IngredienteDTOInfraestructura(String nombre, Integer cantidad, boolean removible) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.removible = removible;
    }

    /**
     * Obtiene el nombre del ingrediente.
     *
     * @return nombre del ingrediente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del ingrediente.
     *
     * @param nombre nuevo nombre del ingrediente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad del ingrediente.
     *
     * @return cantidad disponible.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del ingrediente.
     *
     * @param cantidad nueva cantidad.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Indica si el ingrediente puede ser removido.
     *
     * @return true si es removible, false si no.
     */
    public boolean isRemovible() {
        return removible;
    }

    /**
     * Establece si el ingrediente puede ser removido.
     *
     * @param removible nuevo estado de removibilidad.
     */
    public void setRemovible(boolean removible) {
        this.removible = removible;
    }
}
