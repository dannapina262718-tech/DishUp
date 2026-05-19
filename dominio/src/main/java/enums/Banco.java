package enums;

/**
 * Enumerador que define el catálogo de entidades bancarias soportadas por el sistema.
 * * Este enum se utiliza principalmente en la capa de persistencia y de negocio para 
 * registrar, clasificar y auditar el origen financiero de las transacciones electrónicas, 
 * tales como pagos con tarjeta de crédito/débito (Terminal Punto de Venta) o transferencias 
 * interbancarias, facilitando los procesos posteriores de conciliación de caja.
 * * @author valeria
 */
public enum Banco {

    /**
     * Banco Bilbao Vizcaya Argentaria.
     */
    BBVA,

    /**
     * Banco Nacional de México.
     */
    BANAMEX,

    /**
     * Banco Santander.
     */
    SANTANDER,

    /**
     * Hongkong and Shanghai Banking Corporation.
     */
    HSBC,

    /**
     * Banco Mercantil del Norte.
     */
    BANORTE
}