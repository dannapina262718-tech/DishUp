package enums;

/**
 * Enumerador que define los roles de los empleados dentro del sistema del restaurante.
 * Este catálogo permite clasificar a cada empleado según sus responsabilidades operativas,
 * facilitando el control de accesos, asignación de funciones y gestión de permisos dentro
 * del sistema.
 *
 * @author DishUp
 */
public enum RolEmpleado {

    /**
     * Empleado con nivel de administración general del sistema y del restaurante.
     * Se encarga de la supervisión de operaciones, gestión de personal y toma de decisiones
     * importantes dentro del negocio.
     */
    GERENTE,

    /**
     * Empleado encargado de la atención directa al cliente, toma de pedidos,
     * entrega de comandas y apoyo en el servicio dentro del área de mesas.
     */
    MESERO,

    /**
     * Empleado responsable de la preparación de alimentos en cocina,
     * siguiendo las órdenes registradas en las comandas del sistema.
     */
    COCINERO
}