package enums;

/**
 * Enumerador que define los estados aplicables a un Pago Individual o transacción desglosada.
 * A diferencia de los estados globales de una comanda, este enum se utiliza específicamente 
 * cuando una mesa solicita cuentas divididas (pago por separado o por comensal). Permite fiscalizar 
 * de forma independiente la validez de cada aportación económica realizada, asegurando un control 
 * preciso sobre qué partes de la cuenta total ya fueron saldadas o anuladas.
 * 
 * @author valeria
 */
public enum EstadoPagoIndividual {

    /**
     * El pago individual ha sido procesado, autorizado y recibido con éxito por el cajero. 
     * El importe correspondiente a este comensal o fracción ha quedado debidamente cubierto.
     */
    PAGADO,

    /**
     * El registro de este pago individual fue anulado de forma explícita antes o después de su 
     * validación, ya sea por corrección de captura, rechazo de la terminal bancaria o cancelación 
     * del ticket por parte de un administrador.
     */
    CANCELADO
}