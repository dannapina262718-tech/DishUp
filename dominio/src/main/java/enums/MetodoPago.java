package enums;

/**
 * Enumerador que define los métodos de pago disponibles dentro del sistema de restaurante.
 * <p>
 * Este catálogo permite estandarizar la forma en la que se registran los pagos realizados por los clientes,
 * asegurando consistencia en la persistencia y en la lógica de negocio relacionada con la facturación,
 * el cierre de cuentas y el control de ingresos.
 * <p>
 * Cada valor representa un canal o mecanismo de pago aceptado por el sistema, y puede ser utilizado
 * tanto en pagos individuales como en pagos asociados a una comanda completa.
 *
 * @author valeria
 */
public enum MetodoPago {

    /**
     * Pago realizado mediante tarjeta bancaria (débito o crédito).
     * Incluye transacciones procesadas por terminal bancaria u otro sistema electrónico de cobro.
     */
    TARJETA,

    /**
     * Pago realizado mediante CODI (Cobro Digital), un sistema de transferencias electrónicas
     * instantáneas basado en códigos QR o referencias bancarias.
     */
    CODI,

    /**
     * Pago realizado en efectivo (dinero físico).
     * Este método no depende de sistemas electrónicos y requiere validación manual del monto recibido.
     */
    EFECTIVO
}