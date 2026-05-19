/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package gestionMesas;

import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import excepciones.MesasException;
import java.util.List;

/**
 * IGestionMesas.
 * Interfaz que define las operaciones principales para la gestion de mesas
 * dentro del sistema.
 *
 * Esta interfaz establece los metodos que deben ser implementados por la
 * capa de fachada para permitir la administracion, consulta y asignacion
 * de mesas a los meseros.
 *
 * @author DishUp
 */
public interface IGestionMesas {

    /**
     * Obtiene las mesas asignadas a un mesero.
     *
     * @param mesero Empleado con rol de mesero.
     * @return Lista de mesas asignadas al mesero.
     * @throws MesasException Si ocurre un error en la consulta.
     */
    List<MesaDTO> obtenerMesasPorMesero(EmpleadoDTO mesero) throws MesasException;

    /**
     * Obtiene una mesa especifica.
     *
     * @param mesa Mesa con el identificador a buscar.
     * @return Mesa encontrada.
     * @throws MesasException Si ocurre un error en la busqueda.
     */
    MesaDTO obtenerMesa(MesaDTO mesa) throws MesasException;

    /**
     * Elimina una mesa del sistema.
     *
     * @param mesa Mesa a eliminar.
     * @throws MesasException Si ocurre un error en la eliminacion.
     */
    void eliminarMesa(MesaDTO mesa) throws MesasException;

    /**
     * Agrega una nueva mesa al sistema.
     *
     * @param mesa Mesa a registrar.
     * @throws MesasException Si ocurre un error en el registro.
     */
    void agregarMesa(MesaDTO mesa) throws MesasException;

    /**
     * Actualiza las mesas asignadas y desasignadas de un mesero.
     *
     * @param mesasAsignadas Lista de mesas a asignar.
     * @param mesasQuitar Lista de mesas a quitar.
     * @param mesero Empleado mesero.
     * @throws MesasException Si ocurre un error en la operacion.
     */
    void actualizarMesasDeMesero(List<MesaDTO> mesasAsignadas, List<MesaDTO> mesasQuitar, EmpleadoDTO mesero) throws MesasException;

    /**
     * Obtiene todas las mesas disponibles.
     *
     * @return Lista de mesas libres.
     * @throws MesasException Si ocurre un error en la consulta.
     */
    List<MesaDTO> obtenerMesasDisponibles() throws MesasException;

    /**
     * Obtiene todas las mesas registradas en el sistema.
     *
     * @return Lista de mesas.
     * @throws MesasException Si ocurre un error en la consulta.
     */
    List<MesaDTO> obtenerMesas() throws MesasException;
}
