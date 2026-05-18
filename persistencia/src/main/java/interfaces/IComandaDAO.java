package interfaces;

import entidades.Comanda;
import entidades.Pago;
import entidades.Pedido;
import excepciones.PersistenciaException;
import java.util.List;

public interface IComandaDAO {

    Comanda insertarComanda(Comanda comanda) throws PersistenciaException;

    List<Comanda> obtenerTodas() throws PersistenciaException;

    List<Comanda> obtenerComandasPorMesa(int numeroMesa) throws PersistenciaException;

    Comanda obtenerPorId(String id) throws PersistenciaException;

    boolean actualizarEstado(String idComanda, String nuevoEstado) throws PersistenciaException;

    boolean agregarPedidoAComanda(String idComanda, entidades.Pedido nuevoPedido) throws PersistenciaException;

    public boolean eliminarComanda(String idComanda) throws PersistenciaException;

    public boolean insertarPagoAComanda(String idComanda, Pago pago) throws PersistenciaException;

    public List<Comanda> obtenerComandasListas() throws PersistenciaException;

    public boolean actualizarComanda(String idComanda, List<Pedido> pedidos) throws PersistenciaException;
    
}
