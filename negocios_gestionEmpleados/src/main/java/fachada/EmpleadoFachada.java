package fachada;

import control.EmpleadoControl;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import excepciones.EmpleadosException;
import interfaz.IGestionEmpleados;
import java.util.List;
/**
 * 
 * @author Home
 */
public class EmpleadoFachada implements IGestionEmpleados{

    private final EmpleadoControl empleadoControl;

    public EmpleadoFachada() {
        this.empleadoControl = new EmpleadoControl();
    }

    @Override
    public EmpleadoDTO obtenerEmpleadoPorMesa(MesaDTO mesa) throws EmpleadosException {
        return empleadoControl.obtenerEmpleadoPorMesa(mesa);
    }

    @Override
    public EmpleadoDTO login(EmpleadoDTO empleado) throws EmpleadosException {
        return empleadoControl.login(empleado);
    }

    @Override
    public void activarEmpleado(EmpleadoDTO empleado) throws EmpleadosException {
        empleadoControl.activarEmpleado(empleado);
    }

    @Override
    public void desactivarEmpleado(EmpleadoDTO empleado) throws EmpleadosException {
        empleadoControl.desactivarEmpleado(empleado);
    }

    @Override
    public List<EmpleadoDTO> obtenerMeserosActivos() throws EmpleadosException {
        return empleadoControl.obtenerMeserosActivos();
    }

    @Override
    public List<EmpleadoDTO> buscarMeserosNombreUser(String filtro) throws EmpleadosException {
        return empleadoControl.buscarMeserosPorUserNombre(filtro);
    }
}