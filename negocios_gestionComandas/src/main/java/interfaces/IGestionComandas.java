package interfaces;

import dtos.ComandaDTO;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import dtos.PedidoDTO;
import excepciones.ComandasException;
import java.util.List;

/**
 * IGestionComandas. Interfaz que define las operaciones principales para la
 * gestion de comandas dentro del sistema.
 *
 * Esta interfaz establece los metodos que deben ser implementados por la capa
 * de fachada para permitir la creacion, consulta, actualizacion, eliminacion y
 * entrega de comandas.
 *
 * @author DishUp
 */
public interface IGestionComandas {

    /**
     * Crea una nueva comanda en el sistema.
     *
     * @param nombreCliente Nombre del cliente asociado a la comanda.
     * @param numeroMesa Numero de la mesa asignada.
     * @param pedidos Lista de pedidos que forman la comanda.
     * @param empleadoActual Empleado que registra la comanda.
     * @throws ComandasException Si ocurre un error durante la creacion.
     */
    public void crearComanda(String nombreCliente, int numeroMesa, List<PedidoDTO> pedidos, EmpleadoDTO empleadoActual) throws ComandasException;

    /**
     * Obtiene todas las comandas asociadas a una mesa especifica.
     *
     * @param numeroMesa Numero de la mesa a consultar.
     * @return Lista de comandas encontradas.
     * @throws ComandasException Si ocurre un error en la consulta.
     */
    public List<ComandaDTO> obtenerComandasPorMesa(int numeroMesa) throws ComandasException;

    /**
     * Agrega pedidos a una comanda existente.
     *
     * @param idComanda Identificador de la comanda.
     * @param pedidos Lista de pedidos a agregar.
     * @throws ComandasException Si ocurre un error en la operacion.
     */
    public void agregarPedidosAComanda(String idComanda, List<PedidoDTO> pedidos) throws ComandasException;

    /**
     * Elimina una comanda del sistema.
     *
     * @param idComanda Identificador de la comanda.
     * @param mesa a la que se le puede hacer el cambio de estado
     * @return true si la eliminacion fue exitosa.
     * @throws ComandasException Si ocurre un error en la eliminacion.
     */
    public boolean eliminarComanda(String idComanda, MesaDTO mesa) throws ComandasException;

    /**
     * Obtiene todas las comandas que se encuentran en estado LISTA.
     *
     * @return Lista de comandas listas.
     * @throws ComandasException Si ocurre un error en la consulta.
     */
    public List<ComandaDTO> obtenerComandasListas() throws ComandasException;

    /**
     * Marca una comanda como entregada al cliente.
     *
     * @param idComanda Identificador de la comanda.
     * @throws ComandasException Si ocurre un error en la operacion.
     */
    public void entregarComanda(String idComanda) throws ComandasException;

    /**
     * Actualiza la informacion de una comanda existente.
     *
     * @param comanda Objeto comanda con los datos actualizados.
     * @throws ComandasException Si ocurre un error en la actualizacion.
     */
    public void actualizarComanda(ComandaDTO comanda) throws ComandasException;

    
    ComandaDTO obtenerComandaPorId(String idComanda) throws ComandasException;

    public void cancelarPedido(String idComanda, String idPedido) throws ComandasException;

    public void editarPedido(String idComanda, PedidoDTO pedidoEditado) throws ComandasException;

}
