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

/**
 * EmpleadoBO.
 *
 * Clase de la capa de negocio encargada de gestionar la logica
 * relacionada con los empleados dentro del sistema, principalmente
 * meseros.
 *
 * Contiene las operaciones necesarias para autenticacion, consulta,
 * activacion y desactivacion de empleados, asi como validaciones
 * relacionadas con el estado de las mesas asignadas.
 *
 * Esta clase coordina la comunicacion entre la capa de persistencia
 * (DAO) y los adaptadores de conversion a DTO.
 *
 * @author DishUp
 */
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

    /**
     * Obtiene el empleado asignado a una mesa.
     *
     * @param mesa mesa que contiene el id del mesero asignado
     * @return EmpleadoDTO correspondiente al mesero asignado
     * @throws NegocioException si no se encuentra el empleado
     */
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

    /**
     * Realiza el login de un empleado mediante su usuario.
     *
     * @param empleadoDTO datos del empleado
     * @return EmpleadoDTO autenticado
     * @throws NegocioException si el usuario no es valido
     */
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

    /**
     * Activa un empleado en el sistema.
     *
     * @param empleadoDTO empleado a activar
     * @throws NegocioException si el empleado no existe
     */
    public void activarEmpleado(EmpleadoDTO empleadoDTO) throws NegocioException {

        try {

            Empleado consultado = empleadoDAO.obtenerEmpleadoPorId(empleadoDTO.getId());

            if (consultado == null) {
                throw new NegocioException("Empleado no encontrado.");
            }

            empleadoDAO.actualizarEstadoEmpleado(consultado, EstadoEmpleado.ACTIVO);

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    /**
     * Desactiva un empleado, validando que no tenga mesas ocupadas.
     *
     * @param empleadoDTO empleado a desactivar
     * @throws NegocioException si tiene mesas ocupadas o no existe
     */
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

    /**
     * Obtiene la lista de meseros activos.
     *
     * @return lista de EmpleadoDTO activos
     * @throws NegocioException si ocurre un error de persistencia
     */
    public List<EmpleadoDTO> obtenerMeserosActivos() throws NegocioException {

        try {
            List<Empleado> empleados = empleadoDAO.obtenerMeserosActivos();
            return empleadoAdapter.listaEntidadADTO(empleados);

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    /**
     * Busca meseros por usuario o nombre.
     *
     * @param filtro texto de busqueda
     * @return lista de empleados encontrados
     * @throws NegocioException si ocurre un error en la busqueda
     */
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