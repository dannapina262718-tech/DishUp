package objetosNegocio;

import adaptadores.EmpleadoNegocioAdapter;
import daos.EmpleadoDAO;
import daos.MesaDAO;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import entidades.Empleado;
import entidades.Mesa;
import enums.EstadoEmpleado;
import enums.EstadoMesa;
import excepcion.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IEmpleadoDAO;
import interfaces.IMesaDAO;
import java.util.List;

public class EmpleadoBO {

    private final IEmpleadoDAO empleadoDAO;
    private final IMesaDAO mesaDAO;
    private final EmpleadoNegocioAdapter empleadoAdapter;

    public EmpleadoBO() {
        this.empleadoDAO = new EmpleadoDAO();
        this.mesaDAO = new MesaDAO();
        this.empleadoAdapter = new EmpleadoNegocioAdapter();
    }

    public EmpleadoBO(IEmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
        this.mesaDAO = new MesaDAO();
        this.empleadoAdapter = new EmpleadoNegocioAdapter();
    }

    public EmpleadoDTO obtenerEmpleadoPorMesa(MesaDTO mesa) throws NegocioException {
        if (mesa == null || mesa.getIdMesero() == null) {
            return null;
        }
        try {

            Empleado consultado = empleadoDAO.obtenerEmpleadoPorId(mesa.getIdMesero());

            if (consultado == null) {
                throw new NegocioException("Empleado no encontrado.");
            }

            return empleadoAdapter.aDTO(consultado);

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    public EmpleadoDTO login(EmpleadoDTO empleadoDTO) throws NegocioException {

        try {

            Empleado consultado = empleadoDAO.obtenerEmpleadoPorUser(empleadoDTO.getUser());

            if (consultado == null) {
                throw new NegocioException("Usuario incorrecto.");
            }

            return empleadoAdapter.aDTO(consultado);

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    public void activarEmpleado(EmpleadoDTO empleadoDTO) throws NegocioException {

        try {
            Empleado empleado = empleadoAdapter.aEntidad(empleadoDTO);

            Empleado consultado = empleadoDAO.obtenerEmpleadoPorId(empleadoDTO.getId());

            if (consultado == null) {
                throw new NegocioException("Empleado no encontrado.");
            }

            empleadoDAO.actualizarEstadoEmpleado(consultado, EstadoEmpleado.ACTIVO);

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    public void desactivarEmpleado(EmpleadoDTO empleadoDTO) throws NegocioException {

        try {
            Empleado consultado = empleadoDAO.obtenerEmpleadoPorId(empleadoDTO.getId());

            if (consultado == null) {
                throw new NegocioException("Empleado no encontrado.");
            }

            List<Mesa> mesasAsignadas = mesaDAO.obtenerMesasPorMesero(consultado.getId());
            
            for (Mesa mesa : mesasAsignadas) {
                if (mesa.getEstado() == EstadoMesa.OCUPADA) {
                    throw new NegocioException("No se puede desactivar el empleado porque tiene mesas ocupadas.");
                }
            } 

            empleadoDAO.actualizarEstadoEmpleado(consultado, EstadoEmpleado.INACTIVO);
               
            mesaDAO.desasignarMesasAMesero(mesasAsignadas, consultado);

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    public List<EmpleadoDTO> obtenerMeserosActivos() throws NegocioException {

        try {
            List<Empleado> empleados = empleadoDAO.obtenerMeserosActivos();
            return empleadoAdapter.listaEntidadADTO(empleados);

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    public List<EmpleadoDTO> buscarMeserosNombreUser(String filtro) throws NegocioException {
        try {
            List<Empleado> empleados;

            if (filtro == null || filtro.isBlank()) {
                empleados = empleadoDAO.obtenerMeserosActivos();
            } else {
                empleados = empleadoDAO.buscarMeserosPorUserNombre(filtro);
            }

            return empleadoAdapter.listaEntidadADTO(empleados);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error en la búsqueda de meseros.", ex);
        }
    }
}