package interfaces;

import entidades.Empleado;
import entidades.Mesa;
import enums.EstadoMesa;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz de acceso a datos para la entidad Mesa.
 * 
 * Define las operaciones de persistencia relacionadas con mesas,
 * incluyendo consultas, inserción, eliminación y asignación de mesas
 * a meseros.
 * 
 * Esta interfaz forma parte de la capa DAO y permite desacoplar la lógica
 * de persistencia de su implementación concreta.
 */
public interface IMesaDAO {

    /**
     * Obtiene las mesas asignadas a un mesero específico.
     *
     * @param idMesero identificador del mesero
     * @return lista de mesas asignadas al mesero
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    public List<Mesa> obtenerMesasPorMesero(String idMesero) throws PersistenciaException;

    /**
     * Inserta una nueva mesa en la base de datos.
     *
     * @param mesa objeto Mesa a insertar
     * @throws PersistenciaException si ocurre un error en la inserción
     */
    public void insertarMesa(Mesa mesa) throws PersistenciaException;

    /**
     * Elimina una mesa de la base de datos.
     *
     * @param mesa mesa a eliminar
     * @throws PersistenciaException si ocurre un error en la eliminación
     */
    public void eliminarMesa(Mesa mesa) throws PersistenciaException;

    /**
     * Obtiene una mesa por su identificador.
     *
     * @param id identificador de la mesa
     * @return mesa encontrada
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    public Mesa obtenerMesaPorId(String id) throws PersistenciaException;

    /**
     * Obtiene una mesa por su número.
     *
     * @param numero número de la mesa
     * @return mesa encontrada
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    public Mesa obtenerMesaPorNumero(Integer numero) throws PersistenciaException;

    /**
     * Asigna una lista de mesas a un mesero.
     *
     * @param mesas lista de mesas a asignar
     * @param mesero empleado mesero
     * @throws PersistenciaException si ocurre un error en la asignación
     */
    public void asignarMesasAMesero(List<Mesa> mesas, Empleado mesero) throws PersistenciaException;
            

    /**
     * Desasigna una lista de mesas de un mesero.
     *
     * @param mesas lista de mesas a desasignar
     * @param mesero empleado mesero
     * @throws PersistenciaException si ocurre un error en la operación
     */
    void desasignarMesasAMesero(List<Mesa> mesas, Empleado mesero) throws PersistenciaException;
            

    /**
     * Obtiene todas las mesas disponibles (sin mesero asignado).
     *
     * @return lista de mesas disponibles
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    public List<Mesa> obtenerMesasDisponibles() throws PersistenciaException;

    /**
     * Obtiene todas las mesas registradas en el sistema.
     *
     * @return lista de todas las mesas
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    public List<Mesa> obtenerMesas() throws PersistenciaException;
    
    /**
     * Cambia el estado de la mesa segun si la mesa tiene comandas o no
     * @param numero de la mesa a la que se le quire hacer el cambio.
     * @param estado al que se quiere cambiar la mesa
     * @throws PersistenciaException si ocurre un error en la actualizacion
     */
    public void cambiarEstadoMesaPorNumero(int numero, EstadoMesa estado) throws PersistenciaException;
}