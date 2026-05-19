package enums;

/**
 * EstadoComandaDTO.
 * Representa los posibles estados de una comanda dentro del sistema.
 *
 * Este enum se utiliza para indicar la etapa actual
 * en la que se encuentra una comanda durante su flujo
 * dentro del restaurante.
 *
 * @author DishUp
 */
public enum EstadoComandaDTO {
    
    /**
     * La comanda fue registrada pero aun no inicia su preparacion.
     */
    PENDIENTE,
    
    /**
     * La comanda se encuentra en proceso de preparacion.
     */
    EN_PREPARACION,
    
    /**
     * La comanda ya fue preparada y esta lista para entregarse.
     */
    LISTA,
    
    /**
     * La comanda ya fue entregada al cliente.
     */
    ENTREGADA,
    
    /**
     * La comanda ya fue pagada.
     */
    PAGADA
}