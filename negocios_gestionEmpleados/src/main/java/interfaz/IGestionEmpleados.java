/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package interfaz;

import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import excepciones.EmpleadosException;
import java.util.List;

/**
 *
 * @author DishUp
 */
public interface IGestionEmpleados {
    
    public EmpleadoDTO obtenerEmpleadoPorMesa(MesaDTO mesa) throws EmpleadosException;

    EmpleadoDTO login(EmpleadoDTO empleado) throws EmpleadosException;

    void activarEmpleado(EmpleadoDTO empleado) throws EmpleadosException;

    void desactivarEmpleado(EmpleadoDTO empleado) throws EmpleadosException;

    List<EmpleadoDTO> obtenerMeserosActivos() throws EmpleadosException;

    public List<EmpleadoDTO> buscarMeserosNombreUser(String filtro) throws EmpleadosException;
}
