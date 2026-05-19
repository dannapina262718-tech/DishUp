package gestionEmpleados;

import gestionEmpleados.EmpleadoControl;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import excepciones.EmpleadosException;
import java.util.List;

/**
 * EmpleadoFachada.
 * Clase fachada que expone las operaciones relacionadas con empleados
 * hacia la capa de presentacion.
 *
 * Esta clase implementa la interfaz IGestionEmpleados y delega la logica
 * de negocio al EmpleadoControl, funcionando como punto unico de acceso
 * para la gestion de empleados.
 *
 * @author DishUp
 */
public class EmpleadoFachada implements IGestionEmpleados {

    private final EmpleadoControl empleadoControl;

    /**
     * Constructor que inicializa la fachada y su controlador de empleados.
     */
    public EmpleadoFachada() {
        this.empleadoControl = new EmpleadoControl();
    }

    /**
     * Obtiene el empleado asignado a una mesa.
     *
     * @param mesa Mesa desde la cual se busca el empleado.
     * @return Empleado asociado a la mesa.
     * @throws EmpleadosException Si ocurre un error en la operacion.
     */
    @Override
    public EmpleadoDTO obtenerEmpleadoPorMesa(MesaDTO mesa) throws EmpleadosException {
        return empleadoControl.obtenerEmpleadoPorMesa(mesa);
    }

    /**
     * Realiza el inicio de sesion de un empleado.
     *
     * @param empleado Empleado con credenciales.
     * @return Empleado autenticado.
     * @throws EmpleadosException Si ocurre un error en el login.
     */
    @Override
    public EmpleadoDTO login(EmpleadoDTO empleado) throws EmpleadosException {
        return empleadoControl.login(empleado);
    }

    /**
     * Activa un empleado en el sistema.
     *
     * @param empleado Empleado a activar.
     * @throws EmpleadosException Si ocurre un error en la operacion.
     */
    @Override
    public void activarEmpleado(EmpleadoDTO empleado) throws EmpleadosException {
        empleadoControl.activarEmpleado(empleado);
    }

    /**
     * Desactiva un empleado en el sistema.
     *
     * @param empleado Empleado a desactivar.
     * @throws EmpleadosException Si ocurre un error en la operacion.
     */
    @Override
    public void desactivarEmpleado(EmpleadoDTO empleado) throws EmpleadosException {
        empleadoControl.desactivarEmpleado(empleado);
    }

    /**
     * Obtiene la lista de meseros activos.
     *
     * @return Lista de empleados con rol mesero activos.
     * @throws EmpleadosException Si ocurre un error en la consulta.
     */
    @Override
    public List<EmpleadoDTO> obtenerMeserosActivos() throws EmpleadosException {
        return empleadoControl.obtenerMeserosActivos();
    }

    /**
     * Busca meseros por nombre o usuario.
     *
     * @param filtro Texto de busqueda.
     * @return Lista de meseros que coinciden con el filtro.
     * @throws EmpleadosException Si ocurre un error en la busqueda.
     */
    @Override
    public List<EmpleadoDTO> buscarMeserosNombreUser(String filtro) throws EmpleadosException {
        return empleadoControl.buscarMeserosPorUserNombre(filtro);
    }
}