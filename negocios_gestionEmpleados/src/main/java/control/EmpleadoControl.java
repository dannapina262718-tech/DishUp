package control;

import daos.EmpleadoDAO;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import excepciones.EmpleadosException;
import excepcion.NegocioException;
import interfaces.IEmpleadoDAO;
import java.util.List;
import objetosNegocio.EmpleadoBO;

/**
 * EmpleadoControl.
 * Clase de control encargada de validar y gestionar las operaciones
 * relacionadas con empleados dentro del sistema.
 *
 * Esta clase actua como intermediario entre la capa de presentacion
 * y la capa de negocio (EmpleadoBO), validando la informacion antes
 * de enviarla y manejando las excepciones del sistema.
 *
 * @author DishUp
 */
public class EmpleadoControl {

    private final EmpleadoBO empleadoBO;

    /**
     * Constructor que inicializa la capa de negocio de empleados
     * junto con su DAO correspondiente.
     */
    public EmpleadoControl() {
        IEmpleadoDAO empleadoDAO = new EmpleadoDAO();
        this.empleadoBO = new EmpleadoBO(empleadoDAO);
    }

    /**
     * Obtiene el empleado asignado a una mesa.
     *
     * @param mesa Mesa desde la cual se busca el empleado.
     * @return Empleado asociado a la mesa.
     * @throws EmpleadosException Si la mesa es invalida o ocurre un error.
     */
    public EmpleadoDTO obtenerEmpleadoPorMesa(MesaDTO mesa) throws EmpleadosException {

        if (mesa == null) {
            throw new EmpleadosException("La mesa no puede ser nula.");
        }

        if (mesa.getIdMesa() == null || mesa.getIdMesa().isBlank()) {
            throw new EmpleadosException("El ID de la mesa es obligatorio.");
        }

        try {
            return empleadoBO.obtenerEmpleadoPorMesa(mesa);
        } catch (NegocioException ex) {
            throw new EmpleadosException(ex.getMessage());
        }
    }

    /**
     * Realiza el login de un empleado en el sistema.
     *
     * @param empleado Empleado con credenciales.
     * @return Empleado autenticado.
     * @throws EmpleadosException Si los datos son invalidos o falla el login.
     */
    public EmpleadoDTO login(EmpleadoDTO empleado) throws EmpleadosException {

        if (empleado == null) {
            throw new EmpleadosException("Empleado nulo.");
        }

        if (empleado.getUser() == null || empleado.getUser().isBlank()) {
            throw new EmpleadosException("Usuario obligatorio.");
        }

        try {
            return empleadoBO.login(empleado);
        } catch (NegocioException ex) {
            throw new EmpleadosException(ex.getMessage());
        }
    }

    /**
     * Activa un empleado en el sistema.
     *
     * @param empleado Empleado a activar.
     * @throws EmpleadosException Si los datos son invalidos o ocurre un error.
     */
    public void activarEmpleado(EmpleadoDTO empleado) throws EmpleadosException {

        if (empleado == null) {
            throw new EmpleadosException("Empleado nulo.");
        }

        if (empleado.getUser() == null || empleado.getUser().isBlank()) {
            throw new EmpleadosException("Usuario obligatorio.");
        }

        try {
            empleadoBO.activarEmpleado(empleado);
        } catch (NegocioException ex) {
            throw new EmpleadosException(ex.getMessage());
        }
    }

    /**
     * Desactiva un empleado en el sistema.
     *
     * @param empleado Empleado a desactivar.
     * @throws EmpleadosException Si los datos son invalidos o ocurre un error.
     */
    public void desactivarEmpleado(EmpleadoDTO empleado) throws EmpleadosException {

        if (empleado == null) {
            throw new EmpleadosException("Empleado nulo.");
        }

        if (empleado.getUser() == null || empleado.getUser().isBlank()) {
            throw new EmpleadosException("Usuario obligatorio.");
        }

        try {
            empleadoBO.desactivarEmpleado(empleado);
        } catch (NegocioException ex) {
            throw new EmpleadosException(ex.getMessage());
        }
    }

    /**
     * Obtiene la lista de meseros activos.
     *
     * @return Lista de empleados con rol mesero activos.
     * @throws EmpleadosException Si ocurre un error en la consulta.
     */
    public List<EmpleadoDTO> obtenerMeserosActivos() throws EmpleadosException {

        try {
            return empleadoBO.obtenerMeserosActivos();
        } catch (NegocioException ex) {
            throw new EmpleadosException(ex.getMessage());
        }
    }

    /**
     * Busca meseros por nombre o usuario.
     *
     * @param filtro Texto de busqueda.
     * @return Lista de meseros que coinciden con el filtro.
     * @throws EmpleadosException Si ocurre un error en la busqueda.
     */
    public List<EmpleadoDTO> buscarMeserosPorUserNombre(String filtro) throws EmpleadosException {
        try {
            return empleadoBO.buscarMeserosNombreUser(filtro);
        } catch (NegocioException ex) {
            throw new EmpleadosException(ex.getMessage());
        }
    }
}