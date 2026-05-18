package interfaces;

import entidades.Empleado;
import enums.EstadoEmpleado;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz de acceso a datos para la entidad Empleado.
 * 
 * Define las operaciones de persistencia relacionadas con empleados,
 * incluyendo consultas por identificador, usuario, actualización de estado
 * y búsquedas específicas de meseros.
 * 
 * Esta interfaz pertenece a la capa DAO y permite desacoplar la lógica
 * de persistencia de su implementación concreta.
 */
public interface IEmpleadoDAO {

    /**
     * Obtiene un empleado por su identificador.
     *
     * @param id identificador del empleado
     * @return empleado encontrado
     * @throws PersistenciaException si ocurre un error en la consulta o no se encuentra
     */
    public Empleado obtenerEmpleadoPorId(String id) throws PersistenciaException;

    /**
     * Obtiene un empleado por su nombre de usuario.
     *
     * @param id nombre de usuario del empleado
     * @return empleado encontrado
     * @throws PersistenciaException si ocurre un error en la consulta o no se encuentra
     */
    public Empleado obtenerEmpleadoPorUser(String id) throws PersistenciaException;

    /**
     * Actualiza el estado de un empleado.
     *
     * @param empleado empleado a actualizar
     * @param estado nuevo estado del empleado
     * @throws PersistenciaException si ocurre un error en la actualización
     */
    public void actualizarEstadoEmpleado(Empleado empleado, EstadoEmpleado estado)
            throws PersistenciaException;

    /**
     * Obtiene todos los meseros que se encuentran activos.
     *
     * @return lista de meseros activos
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    public List<Empleado> obtenerMeserosActivos() throws PersistenciaException;

    /**
     * Busca meseros filtrando por usuario o nombre.
     *
     * @param filtro texto de búsqueda
     * @return lista de meseros que coinciden con el filtro
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    public List<Empleado> buscarMeserosPorUserNombre(String filtro)
            throws PersistenciaException;
}