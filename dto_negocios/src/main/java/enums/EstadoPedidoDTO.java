package enums;

/**
 * EstadoPedidoDTO.
 * Representa los posibles estados de un pedido dentro del sistema.
 *
 * Este enum se utiliza para indicar en que etapa del proceso
 * se encuentra un pedido desde que es creado hasta que es entregado.
 *
 * @author DishUp
 */
public enum EstadoPedidoDTO {

    /**
     * El pedido ha sido registrado pero aun no se prepara.
     */
    PENDIENTE,

    /**
     * El pedido se encuentra en proceso de preparacion.
     */
    EN_PREPARACION,

    /**
     * El pedido ya esta listo para ser entregado.
     */
    LISTA,

    /**
     * El pedido ya fue entregado al cliente.
     */
    ENTREGADO
}