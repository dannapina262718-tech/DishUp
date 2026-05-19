package enums;

/**
 * RolEmpleadoDTO.
 * Representa los roles que puede tener un empleado dentro del sistema.
 *
 * Este enum se utiliza para definir las funciones que puede desempeñar
 * cada empleado dentro del restaurante.
 *
 * @author Dishup
 */
public enum RolEmpleadoDTO {

    /**
     * Empleado con rol de gerente, encargado de la administracion general.
     */
    GERENTE,

    /**
     * Empleado encargado de atender mesas y tomar pedidos.
     */
    MESERO,

    /**
     * Empleado encargado de la preparacion de los alimentos.
     */
    COCINERO
}
