package enums;

/**
 * Enumerador que define los estados operativos por los que atraviesa un Pedido.
 * * Este enum es fundamental para controlar el ciclo de vida y el flujo de trabajo
 * de cada platillo o bebida individual desde que es solicitado por el comensal 
 * hasta que se sirve en la mesa, sirviendo de puente de comunicación entre la 
 * interfaz de los meseros y el monitor de cocina.
 * * @author DishUp
 */
public enum EstadoPedido {

    /**
     * El pedido ha sido registrado en el sistema por el mesero y se encuentra en la 
     * cola de espera, pero la cocina aún no ha comenzado a elaborarlo.
     */
    PENDIENTE,

    /**
     * El personal de cocina (o barra) ha tomado el pedido y se encuentra actualmente 
     * en el proceso de preparación o cocinado.
     */
    EN_PREPARACION,

    /**
     * El platillo o bebida ha terminado su preparación con éxito, se encuentra en la 
     * zona de despacho (pass de cocina) y está listo para que el mesero lo recoja.
     */
    LISTA,

    /**
     * El pedido ha sido llevado y servido físicamente al comensal en su mesa correspondiente, 
     * concluyendo su ciclo activo de producción.
     */
    ENTREGADO
}