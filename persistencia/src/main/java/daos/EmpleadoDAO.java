package daos;

import adaptadores.EmpleadoPersistenciaAdapter;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;
import conexion.ConexionMongo;
import entidades.Empleado;
import entidadesMongo.EmpleadoEntidadMongo;
import enums.EstadoEmpleado;
import enums.RolEmpleado;
import excepciones.PersistenciaException;
import interfaces.IEmpleadoDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * EmpleadoDAO.
 * Se encarga del acceso a datos de los empleados en la base de datos MongoDB.
 *
 * Esta clase realiza operaciones de consulta y actualizacion sobre la coleccion
 * de empleados, y convierte los datos entre MongoDB y el modelo de dominio.
 */
public class EmpleadoDAO implements IEmpleadoDAO {

    private final MongoCollection<EmpleadoEntidadMongo> coleccion;
    private final EmpleadoPersistenciaAdapter empleadoAdapter;

    /**
     * Constructor.
     * Inicializa la conexion con la coleccion de empleados y el adaptador.
     */
    public EmpleadoDAO() {
        this.coleccion = ConexionMongo.obtenerBaseDatos()
                .getCollection("empleados", EmpleadoEntidadMongo.class);
        this.empleadoAdapter = new EmpleadoPersistenciaAdapter();
    }

    /**
     * Obtiene un empleado por su ID.
     *
     * @param id identificador del empleado
     * @return empleado en modelo de dominio
     * @throws PersistenciaException si el ID es invalido, no existe o falla la consulta
     */
    @Override
    public Empleado obtenerEmpleadoPorId(String id) throws PersistenciaException {
        if (id == null || id.isBlank()) {
            throw new PersistenciaException("El ID del empleado es inválido");
        }

        try {
            EmpleadoEntidadMongo empleadoMongo =
                    this.coleccion.find(eq("_id", new ObjectId(id))).first();

            if (empleadoMongo == null) {
                throw new PersistenciaException("Empleado no encontrado");
            }

            return empleadoAdapter.aDominio(empleadoMongo);

        } catch (IllegalArgumentException ex) {
            throw new PersistenciaException("El formato del ID es inválido.", ex);

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible obtener el empleado.", ex);
        }
    }

    /**
     * Obtiene un empleado por su usuario.
     *
     * @param user nombre de usuario del empleado
     * @return empleado en modelo de dominio
     * @throws PersistenciaException si el usuario es invalido o ocurre un error
     */
    @Override
    public Empleado obtenerEmpleadoPorUser(String user) throws PersistenciaException {
        if (user == null || user.isBlank()) {
            throw new PersistenciaException("El user del empleado es inválido");
        }

        try {
            EmpleadoEntidadMongo empleadoMongo =
                    this.coleccion.find(eq("user", user)).first();

            if (empleadoMongo == null) {
                throw new PersistenciaException("Empleado no encontrado");
            }

            return empleadoAdapter.aDominio(empleadoMongo);

        } catch (IllegalArgumentException ex) {
            throw new PersistenciaException("El formato del user es inválido.", ex);

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible obtener el empleado.", ex);
        }
    }

    /**
     * Actualiza el estado de un empleado.
     *
     * @param empleado empleado a actualizar
     * @param estado nuevo estado del empleado
     * @throws PersistenciaException si los datos son invalidos o falla la operacion
     */
    @Override
    public void actualizarEstadoEmpleado(Empleado empleado, EstadoEmpleado estado)
            throws PersistenciaException {

        if (empleado == null) {
            throw new PersistenciaException("empleado nulo");
        }

        if (empleado.getId() == null || empleado.getId().isBlank()) {
            throw new PersistenciaException("ID inválido");
        }

        if (estado == null) {
            throw new PersistenciaException("Estado inválido");
        }

        try {
            coleccion.updateOne(
                    eq("_id", new ObjectId(empleado.getId())),
                    set("estado", estado)
            );

        } catch (MongoException ex) {
            throw new PersistenciaException(
                    "No fue posible actualizar el estado del empleado.", ex);
        }
    }
    
    // INICIO CASO DE USO administrarMesas

    /**
     * Obtiene todos los meseros activos.
     *
     * @return lista de empleados con rol MESERO y estado ACTIVO
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public List<Empleado> obtenerMeserosActivos() throws PersistenciaException {
        try {
            List<EmpleadoEntidadMongo> activosMongo =
                    this.coleccion.find(and(
                            eq("rol", RolEmpleado.MESERO),
                            eq("estado", EstadoEmpleado.ACTIVO)
                    )).into(new ArrayList<>());

            List<Empleado> activosDominio = new ArrayList<>();

            for (EmpleadoEntidadMongo e : activosMongo) {
                activosDominio.add(empleadoAdapter.aDominio(e));
            }

            return activosDominio;

        } catch (MongoException ex) {
            throw new PersistenciaException(
                    "No fue posible obtener a los meseros activos", ex);
        }
    }

    /**
     * Busca meseros activos por usuario o nombre.
     *
     * @param filtro texto de busqueda (usuario o nombre)
     * @return lista de meseros que coinciden con el filtro
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public List<Empleado> buscarMeserosPorUserNombre(String filtro)
            throws PersistenciaException {

        try {
            List<Bson> filtros = new ArrayList<>();

            filtros.add(eq("rol", RolEmpleado.MESERO));
            filtros.add(eq("estado", EstadoEmpleado.ACTIVO));

            if (filtro != null && !filtro.isBlank()) {
                filtros.add(or(
                        regex("user", filtro, "i"),
                        regex("nombres", filtro, "i"),
                        regex("apellidoPaterno", filtro, "i"),
                        regex("apellidoMaterno", filtro, "i")
                ));
            }

            List<EmpleadoEntidadMongo> activosMongo =
                    coleccion.find(and(filtros)).into(new ArrayList<>());

            List<Empleado> dominios = new ArrayList<>();

            for (EmpleadoEntidadMongo e : activosMongo) {
                dominios.add(empleadoAdapter.aDominio(e));
            }

            return dominios;

        } catch (MongoException ex) {
            throw new PersistenciaException(
                    "No fue posible obtener a los meseros activos", ex);
        }
    }

    // FIN CASO DE USO administrarMesas
}