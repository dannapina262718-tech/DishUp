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
 * Encargado de la lógica de negocio relacionada con las mesas.
 * Trabaja con DTOs hacia la capa superior y convierte a entidades
 * para interactuar con la capa de persistencia.
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

    /**
     * Obtiene todas las mesas asignadas a un mesero.
     *
     * @param empleado DTO del empleado (mesero)
     * @return lista de mesas asignadas al mesero
     * @throws NegocioException si ocurre un error en la consulta o persistencia
     */
    public List<MesaDTO> obtenerMesasPorMesero(EmpleadoDTO empleado) throws NegocioException {
        try {
            List<Mesa> mesas = mesaDAO.obtenerMesasPorMesero(empleado.getId());
            return mesaAdapter.listaEntidadADTO(mesas);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    /**
     * Obtiene una mesa por su identificador.
     *
     * @param mesa DTO con el ID de la mesa
     * @return mesa encontrada en formato DTO
     * @throws NegocioException si ocurre un error o no existe la mesa
     */
    public MesaDTO obtenerMesa(MesaDTO mesa) throws NegocioException {
        try {
            Mesa obtenida = mesaDAO.obtenerMesaPorId(
                    mesaAdapter.aEntidad(mesa).getId()
            );

            return mesaAdapter.aDTO(obtenida);

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    /**
     * Elimina una mesa del sistema.
     *
     * No permite eliminar mesas asignadas a un mesero.
     *
     * @param mesa DTO de la mesa a eliminar
     * @throws NegocioException si la mesa está asignada o ocurre un error de persistencia
     */
    public void eliminarMesa(MesaDTO mesa) throws NegocioException {
        try {
            Mesa entidad = mesaAdapter.aEntidad(mesa);
            Mesa actual = mesaDAO.obtenerMesaPorId(entidad.getId());

            if (actual.getIdMesero() != null) {
                throw new NegocioException(
                        "No se puede eliminar una mesa asignada a un mesero."
                );
            }

            mesaDAO.eliminarMesa(entidad);

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    /**
     * Agrega una nueva mesa al sistema.
     *
     * Valida que no exista previamente y que tenga número válido.
     *
     * @param mesa DTO con la información de la mesa
     * @throws NegocioException si la mesa es inválida o ya existe
     */
    public void agregarMesa(MesaDTO mesa) throws NegocioException {
        if (mesa == null) {
            throw new NegocioException("Los datos de la mesa no pueden ser nulos.");
        }

        try {
            Mesa entidad = mesaAdapter.aEntidad(mesa);

            if (entidad.getNumero() == null) {
                throw new NegocioException("El número de mesa es obligatorio.");
            }

            Mesa obtenida = mesaDAO.obtenerMesaPorNumero(entidad.getNumero());

            if (obtenida != null) {
                throw new NegocioException("Ya existe la mesa " + entidad.getNumero());
            }

            entidad.setIdMesero(null);
            entidad.setEstado(EstadoMesa.LIBRE);

            mesaDAO.insertarMesa(entidad);

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    /**
     * Actualiza la asignación de mesas de un mesero.
     *
     * @param mesasAsignar lista de mesas a asignar
     * @param mesasQuitar lista de mesas a desasignar
     * @param mesero empleado responsable
     * @throws NegocioException si el mesero no está activo o hay conflicto de asignación
     */
    public void actualizarMesasDeMesero(
            List<MesaDTO> mesasAsignar,
            List<MesaDTO> mesasQuitar,
            EmpleadoDTO mesero
    ) throws NegocioException {
        try {
            if (mesero.getEstado() != EstadoEmpleadoDTO.ACTIVO) {
                throw new NegocioException("El mesero no está activo.");
            }

            List<Mesa> listaAsignar = mesaAdapter.listaDTOAEntidad(mesasAsignar);
            List<Mesa> listaQuitar = mesaAdapter.listaDTOAEntidad(mesasQuitar);

            for (Mesa mesa : listaAsignar) {
                Mesa actual = mesaDAO.obtenerMesaPorId(mesa.getId());

                if (actual.getIdMesero() != null) {
                    throw new NegocioException("La mesa ya está asignada a un mesero.");
                }
            }

            mesaDAO.desasignarMesasAMesero(
                    listaQuitar,
                    empleadoAdapter.aEntidad(mesero)
            );

            mesaDAO.asignarMesasAMesero(
                    listaAsignar,
                    empleadoAdapter.aEntidad(mesero)
            );

        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    /**
     * Obtiene todas las mesas disponibles.
     *
     * @return lista de mesas libres
     * @throws NegocioException si ocurre un error en la consulta
     */
    public List<MesaDTO> obtenerMesasDisponibles() throws NegocioException {
        try {
            List<Mesa> mesas = mesaDAO.obtenerMesasDisponibles();
            return mesaAdapter.listaEntidadADTO(mesas);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    /**
     * Obtiene todas las mesas del sistema.
     *
     * @return lista de mesas registradas
     * @throws NegocioException si ocurre un error en la consulta
     */
    public List<MesaDTO> obtenerMesas() throws NegocioException {
        try {
            List<Mesa> mesas = mesaDAO.obtenerMesas();
            return mesaAdapter.listaEntidadADTO(mesas);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}