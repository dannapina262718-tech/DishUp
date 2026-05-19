package enums;

/**
 * Enumerador que define los estados físicos y operativos de una Mesa en el salón del restaurante.
 * Este enum es utilizado por los módulos de asignación de clientes, control de piso de venta
 * y la capa de lógica de negocio para gestionar el mapa de mesas en tiempo real. Permite a los 
 * meseros y recepcionistas identificar de manera visual y lógica qué estaciones requieren 
 * servicio inmediato o están disponibles para recibir nuevos comensales.
 * 
 * @author DishUp
 */
public enum EstadoMesa {

    /**
     * La mesa se encuentra asignada a un grupo de comensales y posee al menos una comanda 
     * activa en el sistema. No puede ser asignada a nuevos clientes hasta que se liquide 
     * o cierre el folio correspondiente.
     */
    OCUPADA,

    /**
     * La mesa se encuentra vacía, limpia y disponible en el piso de venta. Está lista 
     * para que el personal de servicio (hostess o meseros) asigne nuevos comensales e 
     * inicie un nuevo ciclo de consumo.
     */
    LIBRE
}