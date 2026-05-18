/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Empleado;
import entidades.Mesa;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author DishUp
 */
public interface IMesaDAO {

    public List<Mesa> obtenerMesasPorMesero(String idMesero) throws PersistenciaException;

    public void insertarMesa(Mesa mesa) throws PersistenciaException;
    
    public void eliminarMesa(Mesa mesa) throws PersistenciaException;
    
    public Mesa obtenerMesaPorId(String id) throws PersistenciaException;
    
    public Mesa obtenerMesaPorNumero(Integer numero) throws PersistenciaException;
    
    public void asignarMesasAMesero(List<Mesa> mesas, Empleado mesero) throws PersistenciaException; 
    
    void desasignarMesasAMesero(List<Mesa> mesas, Empleado mesero) throws PersistenciaException;
    
    public List<Mesa> obtenerMesasDisponibles() throws PersistenciaException;
    
    public List<Mesa> obtenerMesas() throws PersistenciaException;
}
