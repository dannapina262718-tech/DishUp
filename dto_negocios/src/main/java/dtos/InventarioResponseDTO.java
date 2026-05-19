package dtos;

/**
 * InventarioResponseDTO.
 * Representa la respuesta de una operacion relacionada con inventario.
 *
 * Este DTO se utiliza para transportar informacion sobre el resultado
 * de una operacion realizada en el inventario.
 *
 * Contiene informacion sobre si la operacion fue exitosa,
 * un mensaje descriptivo y el stock actual disponible.
 *
 * @author DishUp
 */
public class InventarioResponseDTO {

    private boolean exito;
    private String mensaje;
    private int stockActual;

    /**
     * Constructor por defecto.
     */
    public InventarioResponseDTO() {
    }

    /**
     * Constructor que permite inicializar todos los atributos
     * de la respuesta de inventario.
     *
     * @param exito Indica si la operacion fue exitosa.
     * @param mensaje Mensaje relacionado con la operacion.
     * @param stockActual Stock actual disponible.
     */
    public InventarioResponseDTO(boolean exito, String mensaje, int stockActual) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.stockActual = stockActual;
    }

    /**
     * Indica si la operacion fue exitosa.
     *
     * @return true si la operacion fue exitosa, false en caso contrario.
     */
    public boolean isExito() {
        return exito;
    }

    /**
     * Establece si la operacion fue exitosa.
     *
     * @param exito Nuevo valor de exito.
     */
    public void setExito(boolean exito) {
        this.exito = exito;
    }

    /**
     * Obtiene el mensaje relacionado con la operacion.
     *
     * @return Mensaje de la operacion.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje relacionado con la operacion.
     *
     * @param mensaje Nuevo mensaje de la operacion.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el stock actual disponible.
     *
     * @return Stock actual.
     */
    public int getStockActual() {
        return stockActual;
    }

    /**
     * Establece el stock actual disponible.
     *
     * @param stockActual Nuevo stock actual.
     */
    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

}
