package dtos_infraestructura;

/**
 * InventarioResponseDTO.
 * Representa la respuesta recibida desde el sistema externo de inventario.
 *
 * Este DTO se utiliza para recibir informacion de la infraestructura externa
 * relacionada con el resultado de operaciones de inventario.
 *
 * Indica si la operacion fue exitosa, un mensaje descriptivo
 * y el stock actual del producto.
 *
 * @author DishUp
 */
public class InventarioResponseDTO {

    private boolean exito;
    private String mensaje;
    private Integer stockActual;

    /**
     * Constructor por defecto.
     */
    public InventarioResponseDTO() {
    }

    /**
     * Constructor que inicializa la respuesta del inventario.
     *
     * @param exito Indica si la operacion fue exitosa.
     * @param mensaje Mensaje descriptivo del resultado.
     * @param stockActual Cantidad actual disponible en inventario.
     */
    public InventarioResponseDTO(boolean exito, String mensaje, Integer stockActual) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.stockActual = stockActual;
    }

    /**
     * Indica si la operacion fue exitosa.
     *
     * @return true si fue exitosa, false si no.
     */
    public boolean isExito() {
        return exito;
    }

    /**
     * Establece si la operacion fue exitosa.
     *
     * @param exito nuevo estado de la operacion.
     */
    public void setExito(boolean exito) {
        this.exito = exito;
    }

    /**
     * Obtiene el mensaje del resultado de la operacion.
     *
     * @return mensaje descriptivo.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje del resultado de la operacion.
     *
     * @param mensaje nuevo mensaje descriptivo.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el stock actual del producto.
     *
     * @return cantidad disponible en inventario.
     */
    public Integer getStockActual() {
        return stockActual;
    }

    /**
     * Establece el stock actual del producto.
     *
     * @param stockActual nueva cantidad disponible.
     */
    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }
}