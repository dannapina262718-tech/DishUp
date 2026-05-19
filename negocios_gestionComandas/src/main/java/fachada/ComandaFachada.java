package fachada;

import control.ComandaControl;
import dtos.ComandaDTO;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import dtos.PedidoDTO;
import excepciones.ComandasException;
import interfaces.IGestionComandas;
import java.util.List;

/**
 * ComandaFachada. Clase fachada que expone las operaciones de gestion de
 * comandas hacia la capa de presentacion.
 *
 * Esta clase implementa la interfaz IGestionComandas y delega la logica de
 * negocio al ComandaControl, funcionando como punto de acceso unico para las
 * operaciones relacionadas con comandas.
 *
 * @author DishUp
 */
public class ComandaFachada implements IGestionComandas {

    private final ComandaControl comandaControl;

    /**
     * Constructor que inicializa la fachada y su controlador de comandas.
     */
    public ComandaFachada() {
        this.comandaControl = new ComandaControl();
    }

    /**
     * Crea una nueva comanda en el sistema.
     *
     * @param nombreCliente Nombre del cliente asociado a la comanda.
     * @param numeroMesa Numero de la mesa asignada.
     * @param pedidos Lista de pedidos que forman la comanda.
     * @param empleadoActual Empleado que registra la comanda.
     * @throws ComandasException Si ocurre un error en la creacion de la
     * comanda.
     */
    @Override
    public void crearComanda(String nombreCliente, int numeroMesa, List<PedidoDTO> pedidos, EmpleadoDTO empleadoActual) throws ComandasException {
        comandaControl.crearComanda(nombreCliente, numeroMesa, pedidos, empleadoActual);
    }

    /**
     * Obtiene las comandas asociadas a una mesa especifica.
     *
     * @param numeroMesa Numero de la mesa.
     * @return Lista de comandas encontradas.
     * @throws ComandasException Si ocurre un error en la consulta.
     */
    @Override
    public List<ComandaDTO> obtenerComandasPorMesa(int numeroMesa) throws ComandasException {
        return comandaControl.obtenerComandasPorMesa(numeroMesa);
    }

    /**
     * Agrega pedidos a una comanda existente.
     *
     * @param idComanda Identificador de la comanda.
     * @param pedidos Lista de pedidos a agregar.
     * @throws ComandasException Si ocurre un error al agregar los pedidos.
     */
    @Override
    public void agregarPedidosAComanda(String idComanda, List<PedidoDTO> pedidos) throws ComandasException {
        comandaControl.agregarPedidoAComanda(idComanda, pedidos);
    }

    /**
     * Elimina una comanda del sistema.
     *
     * @param idComanda Identificador de la comanda.
     * @param mesa a la que se le puede hacer el cambio de estado
     * @return true si la eliminacion fue exitosa.
     * @throws ComandasException Si ocurre un error en la eliminacion.
     */
    @Override
    public boolean eliminarComanda(String idComanda, MesaDTO mesa) throws ComandasException {
        return comandaControl.eliminarComanda(idComanda, mesa);
    }

    /**
     * Obtiene todas las comandas en estado LISTA.
     *
     * @return Lista de comandas listas.
     * @throws ComandasException Si ocurre un error en la consulta.
     */
    @Override
    public List<ComandaDTO> obtenerComandasListas() throws ComandasException {
        return comandaControl.obtenerComandasListas();
    }

    /**
     * Marca una comanda como entregada.
     *
     * @param idComanda Identificador de la comanda.
     * @throws ComandasException Si ocurre un error al entregar la comanda.
     */
    @Override
    public void entregarComanda(String idComanda) throws ComandasException {
        comandaControl.entregarComanda(idComanda);
    }

    /**
     * Actualiza la informacion de una comanda existente.
     *
     * @param comanda Objeto comanda con la informacion actualizada.
     * @throws ComandasException Si ocurre un error en la actualizacion.
     */
    @Override
    public void actualizarComanda(ComandaDTO comanda) throws ComandasException {
        comandaControl.actualizarComanda(comanda);
    }

    @Override
    public void cancelarPedido(String idComanda, String idPedido) throws ComandasException {
        comandaControl.cancelarPedidoDeComanda(idComanda, idPedido);
    }

    @Override
    public void editarPedido(String idComanda, PedidoDTO pedidoEditado) throws ComandasException {
        comandaControl.editarPedidoDeComanda(idComanda, pedidoEditado);
    }
}
