package objetosNegocio;

import adaptadores.EmpleadoNegocioAdapter;
import adaptadores.MesaNegocioAdapter;
import daos.MesaDAO;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import entidades.Mesa;
import enums.EstadoEmpleadoDTO;
import enums.EstadoMesa;
import excepcion.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IMesaDAO;
import java.util.List;

/**
 * BO de mesas.
 *
 * Trabaja hacia afuera con DTOs. Internamente convierte entidades y maneja
 * excepciones de persistencia hacia negocio.
 *
 * @author DishUp
 */
public class MesaBO {

    private final IMesaDAO mesaDAO;
    private final MesaNegocioAdapter mesaAdapter;
    private final EmpleadoNegocioAdapter empleadoAdapter;

    public MesaBO() {
        this.mesaDAO = new MesaDAO();
        this.mesaAdapter = new MesaNegocioAdapter();
        this.empleadoAdapter = new EmpleadoNegocioAdapter();
    }

    public MesaBO(IMesaDAO mesaDAO) {
        this.mesaDAO = mesaDAO;
        this.mesaAdapter = new MesaNegocioAdapter();
        this.empleadoAdapter = new EmpleadoNegocioAdapter();
    }

    public List<MesaDTO> obtenerMesasPorMesero(EmpleadoDTO empleado) throws NegocioException {
        try {
            List<Mesa> mesas = mesaDAO.obtenerMesasPorMesero(empleado.getId());

            return mesaAdapter.listaEntidadADTO(mesas);

        } catch (PersistenciaException ex) {

            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    public MesaDTO obtenerMesa(MesaDTO mesa) throws NegocioException{
        try {
            Mesa obtenida = mesaDAO.obtenerMesaPorId(mesaAdapter.aEntidad(mesa).getId());
            
            return mesaAdapter.aDTO(obtenida);
            
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    public void eliminarMesa(MesaDTO mesa) throws NegocioException{            
        try {
            
            Mesa entidad = mesaAdapter.aEntidad(mesa);

            Mesa actual = mesaDAO.obtenerMesaPorId(entidad.getId());
            
            if (actual.getIdMesero() != null) {
                throw new NegocioException("No se puede eliminar una mesa asignada a un mesero.");
            }
            
            mesaDAO.eliminarMesa(entidad);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    public void agregarMesa(MesaDTO mesa) throws NegocioException{
        if (mesa == null) {
            throw new NegocioException("Los datos de la mesa no pueden ser nulos.");
        }

        try {
            Mesa entidad = mesaAdapter.aEntidad(mesa);
            
            if (entidad.getNumero() == null) {
                throw new NegocioException("El número de mesa es obligatorio.");
            }
            
            Mesa obtenida = mesaDAO.obtenerMesaPorNumero(entidad.getNumero());
            
            if(obtenida != null){
                throw new NegocioException("Ya existe la mesa "+entidad.getNumero());
            }
            
            entidad.setIdMesero(null);
            entidad.setEstado(EstadoMesa.LIBRE);

            mesaDAO.insertarMesa(entidad);

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }  
    
    public void actualizarMesasDeMesero(List<MesaDTO> mesasAsignar, List<MesaDTO> mesasQuitar, EmpleadoDTO mesero) throws NegocioException{
        try {
            if (mesero.getEstado() != EstadoEmpleadoDTO.ACTIVO) {
                throw new NegocioException("El mesero no está activo.");
            }
            
            List<Mesa> listaEntidadAsignar = mesaAdapter.listaDTOAEntidad(mesasAsignar);
            List<Mesa> listaEntidadQuitar = mesaAdapter.listaDTOAEntidad(mesasQuitar);
            
            for (Mesa mesa : listaEntidadAsignar) {

                Mesa actual = mesaDAO.obtenerMesaPorId(mesa.getId());

                if (actual.getIdMesero() != null) {
                    throw new NegocioException("La mesa ya está asignada a un mesero.");
                }
            }

            mesaDAO.desasignarMesasAMesero(listaEntidadQuitar, empleadoAdapter.aEntidad(mesero));
            mesaDAO.asignarMesasAMesero(listaEntidadAsignar, empleadoAdapter.aEntidad(mesero)); 
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    public List<MesaDTO> obtenerMesasDisponibles() throws NegocioException  {
        try {

            List<Mesa> mesas = mesaDAO.obtenerMesasDisponibles();

            return mesaAdapter.listaEntidadADTO(mesas);

        } catch (PersistenciaException ex) {

            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    public List<MesaDTO> obtenerMesas() throws NegocioException  {
        try {

            List<Mesa> mesas = mesaDAO.obtenerMesas();

            return mesaAdapter.listaEntidadADTO(mesas);

        } catch (PersistenciaException ex) {

            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}