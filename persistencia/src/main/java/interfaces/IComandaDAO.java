package interfaces;

import entidades.Comanda;
import entidades.Pedido;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz de acceso a datos para la entidad Comanda.
 * 
 * Define las operaciones principales de persistencia relacionadas con comandas,
 * incluyendo creación, consulta, actualización, eliminación y manejo de pedidos
 * asociados a una comanda.
 * 
 * Esta interfaz forma parte de la capa DAO y permite desacoplar la lógica de
 * persistencia de la implementación concreta (por ejemplo MongoDB).
 */
public interface IComandaDAO {

    /**
     * Inserta una nueva comanda en la base de datos.
     *
     * @param comanda objeto Comanda a insertar
     * @return comanda insertada con su identificador generado
     * @throws PersistenciaException si ocurre un error en la persistencia
     */
    Comanda insertarComanda(Comanda comanda) throws PersistenciaException;

    /**
     * Obtiene todas las comandas registradas.
     *
     * @return lista de comandas
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    List<Comanda> obtenerTodas() throws PersistenciaException;

    /**
     * Obtiene las comandas asociadas a una mesa específica.
     *
     * @param numeroMesa número de la mesa
     * @return lista de comandas de la mesa
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    List<Comanda> obtenerComandasPorMesa(int numeroMesa) throws PersistenciaException;

    /**
     * Obtiene una comanda por su identificador.
     *
     * @param id identificador de la comanda
     * @return comanda encontrada
     * @throws PersistenciaException si no se encuentra o ocurre un error
     */
    Comanda obtenerPorId(String id) throws PersistenciaException;

    /**
     * Actualiza el estado de una comanda.
     *
     * @param idComanda identificador de la comanda
     * @param nuevoEstado nuevo estado a asignar
     * @return true si la actualización fue exitosa
     * @throws PersistenciaException si ocurre un error en la operación
     */
    boolean actualizarEstado(String idComanda, String nuevoEstado) throws PersistenciaException;

    /**
     * Agrega un pedido a una comanda existente.
     *
     * @param idComanda identificador de la comanda
     * @param nuevoPedido pedido a agregar
     * @return true si el pedido fue agregado correctamente
     * @throws PersistenciaException si ocurre un error en la operación
     */
    boolean agregarPedidoAComanda(String idComanda, Pedido nuevoPedido) throws PersistenciaException;

    /**
     * Elimina una comanda por su identificador.
     *
     * @param idComanda identificador de la comanda
     * @return true si la eliminación fue exitosa
     * @throws PersistenciaException si ocurre un error en la operación
     */
    boolean eliminarComanda(String idComanda) throws PersistenciaException;

    /**
     * Obtiene las comandas que están listas.
     *
     * @return lista de comandas en estado listo
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    List<Comanda> obtenerComandasListas() throws PersistenciaException;

    /**
     * Actualiza los pedidos de una comanda.
     *
     * @param idComanda identificador de la comanda
     * @param pedidos lista de pedidos actualizada
     * @return true si la actualización fue exitosa
     * @throws PersistenciaException si ocurre un error en la operación
     */
    public boolean actualizarComanda(String idComanda, List<Pedido> pedidos) throws PersistenciaException;
 

    /**
     * Calcula el monto total de una comanda.
     *
     * @param idComanda identificador de la comanda
     * @return monto total calculado
     * @throws PersistenciaException si ocurre un error en el cálculo
     */
    float calcularMontoComanda(String idComanda) throws PersistenciaException;

    /**
     * Recalcula y actualiza el monto total de una comanda en la base de datos.
     *
     * @param idComanda identificador de la comanda
     * @throws PersistenciaException si ocurre un error en la operación
     */
    void recalcularMonto(String idComanda) throws PersistenciaException;

}
