/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package interfaz;

import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import excepciones.MesasException;
import java.util.List;

/**
 *
 * @author DishUp
 */
public interface IGestionMesas {
    List<MesaDTO> obtenerMesasPorMesero(EmpleadoDTO mesero) throws MesasException;

    MesaDTO obtenerMesa(MesaDTO mesa) throws MesasException;

    void eliminarMesa(MesaDTO mesa) throws MesasException;

    void agregarMesa(MesaDTO mesa) throws MesasException;

    void actualizarMesasDeMesero(List<MesaDTO> mesasAsignadas, List<MesaDTO> mesasQuitar, EmpleadoDTO mesero) throws MesasException;

    List<MesaDTO> obtenerMesasDisponibles() throws MesasException;
    
    List<MesaDTO> obtenerMesas() throws MesasException;
}
