package enums;

/**
 * Enumerador que define el estatus laboral y operativo de un Empleado dentro del sistema.
 * * Este enum actúa como un mecanismo de control de acceso y auditoría de personal.
 * Permite determinar de manera inmediata los privilegios de autenticación de un usuario,
 * la asignación activa de responsabilidades en el piso de venta (como comandas o mesas) 
 * y la conservación del histórico de transacciones sin necesidad de eliminar físicamente 
 * los registros de la base de datos.
 * @author DishUp
 */
public enum EstadoEmpleado {

    /**
     * El empleado se encuentra laborando activamente en la empresa. 
     * Posee permisos vigentes para autenticarse en el sistema, abrir turnos, 
     * levantar comandas, procesar pagos o acceder a los módulos según su rol asignado.
     */
    ACTIVO,

    /**
     * El empleado ha sido suspendido, dado de baja laboral o se encuentra inactivo. 
     * El sistema le deniega de forma automática cualquier intento de inicio de sesión 
     * y lo excluye de las listas de asignación operativa vigentes, protegiendo la 
     * integridad de la información del restaurante.
     */
    INACTIVO
}