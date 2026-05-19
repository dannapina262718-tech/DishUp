package gestionMesas;

import gestionMesas.MesaControl;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import excepciones.MesasException;
import gestionMesas.IGestionMesas;
import java.util.List;

/**
 * MesaFachada.
 * Clase fachada que expone las operaciones relacionadas con mesas
 * hacia la capa de presentacion.
 *
 * Esta clase implementa la interfaz IGestionMesas y delega la logica
 * de negocio al MesaControl, funcionando como punto unico de acceso
 * para la gestion de mesas.
 *
 * @author DishUp
 */
public class MesaFachada implements IGestionMesas {

    private final MesaControl mesaControl;

    /**
     * Constructor que inicializa la fachada y su controlador de mesas.
     */
    public MesaFachada() {
        this.mesaControl = new MesaControl();
    }

    /**
     * Obtiene las mesas asignadas a un mesero.
     *
     * @param mesero Empleado con rol de mesero.
     * @return Lista de mesas asignadas.
     * @throws MesasException Si ocurre un error en la operacion.
     */
    @Override
    public List<MesaDTO> obtenerMesasPorMesero(EmpleadoDTO mesero) throws MesasException {
        return mesaControl.obtenerMesasPorMesero(mesero);
    }

    /**
     * Obtiene una mesa por su identificador.
     *
     * @param mesa Objeto MesaDTO con el ID de la mesa.
     * @return Mesa encontrada.
     * @throws MesasException Si ocurre un error en la consulta.
     */
    @Override
    public MesaDTO obtenerMesa(MesaDTO mesa) throws MesasException {
        return mesaControl.obtenerMesaPorId(mesa);
    }

    /**
     * Elimina una mesa del sistema.
     *
     * @param mesa Mesa a eliminar.
     * @throws MesasException Si ocurre un error en la eliminacion.
     */
    @Override
    public void eliminarMesa(MesaDTO mesa) throws MesasException {
        mesaControl.eliminarMesa(mesa);
    }

    /**
     * Agrega una nueva mesa al sistema.
     *
     * @param mesa Mesa a registrar.
     * @throws MesasException Si ocurre un error en la creacion.
     */
    @Override
    public void agregarMesa(MesaDTO mesa) throws MesasException {
        mesaControl.agregarMesa(mesa);
    }

    /**
     * Actualiza las mesas asignadas y desasignadas de un mesero.
     *
     * @param mesasAsignadas Lista de mesas a asignar.
     * @param mesasQuitar Lista de mesas a quitar.
     * @param mesero Empleado mesero.
     * @throws MesasException Si ocurre un error en la operacion.
     */
    @Override
    public void actualizarMesasDeMesero(List<MesaDTO> mesasAsignadas, List<MesaDTO> mesasQuitar, EmpleadoDTO mesero) throws MesasException {
        mesaControl.actualizarMesasDeMesero(mesasAsignadas, mesasQuitar, mesero);
    }

    /**
     * Obtiene todas las mesas disponibles.
     *
     * @return Lista de mesas libres.
     * @throws MesasException Si ocurre un error en la consulta.
     */
    @Override
    public List<MesaDTO> obtenerMesasDisponibles() throws MesasException {
        return mesaControl.obtenerMesasDisponibles();
    }

    /**
     * Obtiene todas las mesas registradas en el sistema.
     *
     * @return Lista de mesas.
     * @throws MesasException Si ocurre un error en la consulta.
     */
    @Override
    public List<MesaDTO> obtenerMesas() throws MesasException {
        return mesaControl.obtenerMesas();
    }
}