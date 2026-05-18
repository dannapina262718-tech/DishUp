/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ComandaDTO;
import dtos.EmpleadoDTO;
import dtos.PedidoDTO;
import excepciones.ComandasException;
import java.util.List;

/**
 *
 * @author DishUp
 */
public interface IGestionComandas {

    public void crearComanda(String nombreCliente, int numeroMesa, List<PedidoDTO> pedidos, EmpleadoDTO empleadoActual) throws ComandasException;

    public List<ComandaDTO> obtenerComandasPorMesa(int numeroMesa) throws ComandasException;

    public void agregarPedidosAComanda(String idComanda, List<PedidoDTO> pedidos) throws ComandasException;

    public boolean eliminarComanda(String idComanda) throws ComandasException;

    public List<ComandaDTO> obtenerComandasListas() throws ComandasException;

    public void entregarComanda(String idComanda) throws ComandasException;
    
    public void actualizarComanda(ComandaDTO comanda) throws ComandasException;
    
}
