package control;

import daos.MesaDAO;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import enums.EstadoEmpleadoDTO;
import enums.EstadoMesaDTO;
import enums.RolEmpleadoDTO;
import excepcion.NegocioException;
import excepciones.MesasException;
import interfaces.IMesaDAO;
import java.util.List;
import objetosNegocio.MesaBO;

/**
 * MesaControl.
 * Clase de control encargada de validar y gestionar las operaciones
 * relacionadas con las mesas dentro del sistema.
 *
 * Esta clase actua como intermediario entre la capa de presentacion
 * y la capa de negocio (MesaBO), validando la informacion antes
 * de enviarla y manejando las excepciones del sistema.
 *
 * @author DishUp
 */
public class MesaControl {

    private final MesaBO mesaBO;

    /**
     * Constructor que inicializa la capa de negocio de mesas
     * junto con su DAO correspondiente.
     */
    public MesaControl() {
        IMesaDAO mesaDAO = new MesaDAO();
        this.mesaBO = new MesaBO(mesaDAO);
    }

    /**
     * Valida que una mesa no sea nula y que tenga un ID valido.
     *
     * @param mesa Objeto MesaDTO a validar.
     * @throws MesasException Si la mesa es nula o no tiene ID.
     */
    private void validarMesa(MesaDTO mesa) throws MesasException {
        if (mesa == null) {
            throw new MesasException("La mesa no puede ser nula");
        }
        if (mesa.getIdMesa() == null || mesa.getIdMesa().isBlank()) {
            throw new MesasException("La mesa debe tener un ID");
        }
    }

    /**
     * Valida que un empleado no sea nulo y tenga un ID valido.
     *
     * @param empleado Objeto EmpleadoDTO a validar.
     * @throws MesasException Si el empleado es nulo o no tiene ID.
     */
    private void validarEmpleado(EmpleadoDTO empleado) throws MesasException {
        if (empleado == null) {
            throw new MesasException("El empleado no puede ser nulo");
        }
        if (empleado.getId() == null || empleado.getId().isBlank()) {
            throw new MesasException("El empleado debe tener un ID");
        }
    }

    /**
     * Obtiene las mesas asignadas a un mesero.
     *
     * @param empleado Empleado mesero a consultar.
     * @return Lista de mesas asignadas.
     * @throws MesasException Si el empleado es invalido o ocurre un error.
     */
    public List<MesaDTO> obtenerMesasPorMesero(EmpleadoDTO empleado) throws MesasException {
        validarEmpleado(empleado);

        try {
            return mesaBO.obtenerMesasPorMesero(empleado);
        } catch (NegocioException ex) {
            throw new MesasException(ex.getMessage());
        }
    }

    /**
     * Obtiene una mesa por su identificador.
     *
     * @param mesa Objeto MesaDTO con el ID de la mesa.
     * @return Mesa encontrada.
     * @throws MesasException Si la mesa es invalida o ocurre un error.
     */
    public MesaDTO obtenerMesaPorId(MesaDTO mesa) throws MesasException {
        validarMesa(mesa);

        try {
            return mesaBO.obtenerMesa(mesa);
        } catch (NegocioException ex) {
            throw new MesasException(ex.getMessage());
        }
    }

    /**
     * Elimina una mesa del sistema.
     *
     * @param mesa Mesa a eliminar.
     * @throws MesasException Si la mesa es invalida o ocurre un error.
     */
    public void eliminarMesa(MesaDTO mesa) throws MesasException {
        validarMesa(mesa);

        try {
            mesaBO.eliminarMesa(mesa);
        } catch (NegocioException ex) {
            throw new MesasException(ex.getMessage());
        }
    }

    /**
     * Agrega una nueva mesa al sistema.
     *
     * La mesa se registra automaticamente con estado LIBRE.
     *
     * @param mesa Mesa a registrar.
     * @throws MesasException Si los datos son invalidos o ocurre un error.
     */
    public void agregarMesa(MesaDTO mesa) throws MesasException {

        if (mesa == null) {
            throw new MesasException("La mesa no puede ser nula");
        }

        if (mesa.getNumeroMesa() <= 0) {
            throw new MesasException("El número de mesa debe ser mayor a 0");
        }

        mesa.setEstado(EstadoMesaDTO.LIBRE);

        try {
            mesaBO.agregarMesa(mesa);
        } catch (NegocioException ex) {
            throw new MesasException(ex.getMessage());
        }
    }

    /**
     * Actualiza las mesas asignadas y desasignadas de un mesero.
     *
     * Valida que el empleado sea un mesero activo antes de realizar
     * la operacion.
     *
     * @param mesasAsignar Lista de mesas a asignar.
     * @param mesasQuitar Lista de mesas a desasignar.
     * @param mesero Empleado mesero.
     * @throws MesasException Si los datos son invalidos o el empleado no cumple requisitos.
     */
    public void actualizarMesasDeMesero(List<MesaDTO> mesasAsignar, List<MesaDTO> mesasQuitar, EmpleadoDTO mesero) throws MesasException {

        for (MesaDTO mesa : mesasAsignar) {
            validarMesa(mesa);
        }

        for (MesaDTO mesa : mesasQuitar) {
            validarMesa(mesa);
        }

        validarEmpleado(mesero);

        if (mesero.getRol() == null) {
            throw new MesasException("El rol del empleado es obligatorio");
        }

        if (mesero.getRol() != RolEmpleadoDTO.MESERO) {
            throw new MesasException("El empleado no es mesero");
        }

        if (mesero.getEstado() == null) {
            throw new MesasException("El estado del empleado es obligatorio");
        }

        if (mesero.getEstado() != EstadoEmpleadoDTO.ACTIVO) {
            throw new MesasException("El mesero no está activo");
        }

        try {
            mesaBO.actualizarMesasDeMesero(mesasAsignar, mesasQuitar, mesero);
        } catch (NegocioException ex) {
            throw new MesasException(ex.getMessage());
        }
    }

    /**
     * Obtiene todas las mesas disponibles.
     *
     * @return Lista de mesas libres.
     * @throws MesasException Si ocurre un error en la consulta.
     */
    public List<MesaDTO> obtenerMesasDisponibles() throws MesasException {
        try {
            return mesaBO.obtenerMesasDisponibles();
        } catch (NegocioException ex) {
            throw new MesasException(ex.getMessage());
        }
    }

    /**
     * Obtiene todas las mesas registradas en el sistema.
     *
     * @return Lista de mesas.
     * @throws MesasException Si ocurre un error en la consulta.
     */
    public List<MesaDTO> obtenerMesas() throws MesasException {
        try {
            return mesaBO.obtenerMesas();
        } catch (NegocioException ex) {
            throw new MesasException(ex.getMessage());
        }
    }
}