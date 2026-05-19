package enums;

/**
 * Enumerador que define los tipos de tarjeta bancaria aceptados por el sistema de pagos del restaurante.
 * Este catálogo permite clasificar las transacciones electrónicas realizadas con tarjeta,
 * facilitando el control y validación de los pagos procesados en terminales bancarias.
 *
 * @author valeria
 */
public enum TipoTarjeta {

    /**
     * Tarjeta de crédito emitida por una institución bancaria.
     * Permite realizar pagos con cargo a una línea de crédito autorizada por el banco.
     */
    CREDITO,

    /**
     * Tarjeta de débito vinculada a una cuenta bancaria.
     * El pago se descuenta directamente del saldo disponible del usuario.
     */
    DEBITO
}