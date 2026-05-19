package enums;

/**
 * Enumerador que define los estados de seguimiento globales por los que atraviesa una Comanda.
 * * Este enum consolida el progreso macro de una mesa en el restaurante. Su valor se determina 
 * de forma dinámica basándose jerárquicamente en los estados individuales de los pedidos que la 
 * integran, abarcando desde la recepción de la orden en el salón, el flujo interno dentro de la cocina, 
 * hasta la entrega del servicio y el posterior cierre financiero en caja.
 * @author DishUp
 * @author valeria
 */
public enum EstadoComanda {

    /**
     * La comanda ha sido abierta recientemente y cuenta con al menos un pedido en espera 
     * en la cola de la cocina que aún no ha comenzado a ser procesado.
     */
    PENDIENTE,

    /**
     * No quedan pedidos rezagados en estado pendiente y el personal de cocina se encuentra 
     * elaborando activamente al menos uno de los platillos o bebidas de la mesa.
     */
    EN_PREPARACION,

    /**
     * Todos los pedidos pertenecientes a la comanda han finalizado su preparación y se encuentran 
     * listos en la zona de despacho (pass) esperando a ser llevados a la mesa por el mesero.
     */
    LISTA,

    /**
     * Todos los platillos y bebidas solicitados han sido servidos físicamente a los comensales, 
     * significando que el servicio de alimentos ha concluido y la mesa está consumiendo.
     */
    ENTREGADA,

    /**
     * La cuenta asociada a la comanda ha sido liquidada en su totalidad en caja. Este estado 
     * representa el cierre operativo y financiero definitivo del folio antes de liberar la mesa.
     */
    PAGADA
}