package dtos;

/**
 * Objeto de transferencia de datos (DTO) que representa un ingrediente dentro del sistema.
 * <p>
 * Esta clase se utiliza para transportar información de ingredientes entre las distintas capas
 * del sistema (presentación, lógica de negocio y persistencia) sin exponer directamente la entidad
 * de dominio.
 * <p>
 * Permite manejar datos básicos como identificación, nombre y niveles de inventario,
 * facilitando el control de stock dentro del restaurante.
 *
 * @author DishUp
 */
public class IngredienteDTO {

    private String id;
    private String nombre;
    private int stockActual;
    private int stockMinimo;

    /**
     * Constructor vacío requerido para frameworks de serialización o mapeo de datos.
     */
    public IngredienteDTO() {
    }

    /**
     * Constructor que inicializa un ingrediente con todos sus atributos principales.
     *
     * @param id identificador único del ingrediente
     * @param nombre nombre del ingrediente
     * @param stockActual cantidad actual disponible en inventario
     * @param stockMinimo cantidad mínima requerida antes de generar alerta de reposición
     */
    public IngredienteDTO(String id, String nombre, int stockActual, int stockMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
    }

    /**
     * Obtiene el identificador único del ingrediente.
     *
     * @return id del ingrediente
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único del ingrediente.
     *
     * @param id identificador del ingrediente
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
     * @param nombre nombre del ingrediente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad actual disponible en inventario.
     *
     * @return stock actual del ingrediente
     */
    public int getStockActual() {
        return stockActual;
    }

    /**
     * Establece la cantidad actual disponible en inventario.
     *
     * @param stockActual cantidad actual del ingrediente
     */
    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    /**
     * Obtiene el nivel mínimo de stock permitido antes de generar alerta.
     *
     * @return stock mínimo del ingrediente
     */
    public int getStockMinimo() {
        return stockMinimo;
    }

    /**
     * Establece el nivel mínimo de stock permitido antes de generar alerta.
     *
     * @param stockMinimo stock mínimo del ingrediente
     */
    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}