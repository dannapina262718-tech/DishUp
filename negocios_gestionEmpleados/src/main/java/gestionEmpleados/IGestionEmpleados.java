package gestionEmpleados;

import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import excepciones.EmpleadosException;
import java.util.List;

/**
 * IGestionEmpleados.
 * Interfaz que define las operaciones principales para la gestion de empleados
 * dentro del sistema.
 *
 * Esta interfaz establece los metodos que deben ser implementados por la
 * capa de fachada para permitir la consulta, autenticacion y gestion de
 * estados de los empleados.
 *
 * @author DishUp
 */
public interface IGestionEmpleados {

    /**
     * Obtiene el empleado asignado a una mesa.
     *
     * @param mesa Mesa desde la cual se busca el empleado.
     * @return Empleado asociado a la mesa.
     * @throws EmpleadosException Si ocurre un error en la consulta.
     */
    public EmpleadoDTO obtenerEmpleadoPorMesa(MesaDTO mesa) throws EmpleadosException;

    /**
     * Realiza el login de un empleado en el sistema.
     *
     * @param empleado Empleado con credenciales.
     * @return Empleado autenticado.
     * @throws EmpleadosException Si ocurre un error en la autenticacion.
     */
    EmpleadoDTO login(EmpleadoDTO empleado) throws EmpleadosException;

    /**
     * Activa un empleado dentro del sistema.
     *
     * @param empleado Empleado a activar.
     * @throws EmpleadosException Si ocurre un error en la operacion.
     */
    void activarEmpleado(EmpleadoDTO empleado) throws EmpleadosException;

    /**
     * Desactiva un empleado dentro del sistema.
     *
     * @param empleado Empleado a desactivar.
     * @throws EmpleadosException Si ocurre un error en la operacion.
     */
    void desactivarEmpleado(EmpleadoDTO empleado) throws EmpleadosException;

    /**
     * Obtiene la lista de meseros activos.
     *
     * @return Lista de empleados con rol mesero activos.
     * @throws EmpleadosException Si ocurre un error en la consulta.
     */
    List<EmpleadoDTO> obtenerMeserosActivos() throws EmpleadosException;

    /**
     * Busca meseros por nombre o usuario.
     *
     * @param filtro Texto de busqueda.
     * @return Lista de meseros que coinciden con el filtro.
     * @throws EmpleadosException Si ocurre un error en la busqueda.
     */
    public List<EmpleadoDTO> buscarMeserosNombreUser(String filtro) throws EmpleadosException;
}
