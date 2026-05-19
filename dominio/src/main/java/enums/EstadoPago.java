package enums;

/**
 * Enumerador que define los estados financieros y de cobranza globales aplicables a una cuenta o comanda.
 * * Este enum permite controlar el ciclo de vida transaccional del restaurante, facilitando el 
 * seguimiento del flujo de caja, la detección de cuentas morosas, la gestión de cierres de caja 
 * y la administración de cobros divididos o cancelaciones de folios.
 * * @author valeria
 */
public enum EstadoPago {

    /**
     * La cuenta ha sido totalizada pero aún no se recibe ningún importe monetario. 
     * Se encuentra en espera de que el cliente liquide el saldo.
     */
    PENDIENTE,

    /**
     * Transacción concluida con éxito. El importe total de la comanda ha sido cubierto 
     * en su totalidad mediante uno o más métodos de pago válidos.
     */
    PAGADO,

    /**
     * Se ha registrado un abono o pago parcial a la cuenta (común en cuentas divididas o 
     * pagos compartidos), pero aún resta un saldo pendiente por liquidar para cerrar el folio.
     */
    PAGO_PARCIAL,

    /**
     * El proceso de cobro ha sido anulado de forma explícita. Este estado se utiliza para 
     * auditorías ante errores de captura, devoluciones completas o cancelaciones autorizadas.
     */
    CANCELADO      
}