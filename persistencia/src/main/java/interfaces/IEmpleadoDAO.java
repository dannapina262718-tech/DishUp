/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package interfaces;

import entidades.Empleado;
import enums.EstadoEmpleado;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author DishUp
 */
public interface IEmpleadoDAO {
    
    public Empleado obtenerEmpleadoPorId(String id) throws PersistenciaException;
    
    public Empleado obtenerEmpleadoPorUser(String id) throws PersistenciaException;
    
    public void actualizarEstadoEmpleado(Empleado empleado, EstadoEmpleado estado) throws PersistenciaException;
    
    public List<Empleado> obtenerMeserosActivos() throws PersistenciaException;
    
    public List<Empleado> buscarMeserosPorUserNombre(String filtro) throws PersistenciaException;
}
