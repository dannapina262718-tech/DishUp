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
 * Clase de acceso a datos (DAO) para la gestión de Empleados en MongoDB.
 * * Esta clase realiza operaciones de consulta y actualización sobre la colección
 * de empleados, abstrayendo la lógica de persistencia y transformando los datos 
 * entre los modelos de persistencia (MongoDB) y de dominio.
 * * Se utiliza únicamente en la capa de persistencia y no debe contener lógica de negocio.
 */
public class EmpleadoDAO implements IEmpleadoDAO {

    private final MongoCollection<EmpleadoEntidadMongo> coleccion;
    private final EmpleadoPersistenciaAdapter empleadoAdapter;

    /**
     * Constructor por defecto que inicializa la conexión con la colección de empleados y el adaptador.
     */
    public EmpleadoDAO() {
        this.coleccion = ConexionMongo.obtenerBaseDatos()
                .getCollection("empleados", EmpleadoEntidadMongo.class);
        this.empleadoAdapter = new EmpleadoPersistenciaAdapter();
    }

    /**
     * Obtiene un empleado por su identificador único.
     *
     * @param id identificador único del empleado en formato hexadecimal
     * @return el empleado correspondiente al identificador en el modelo de dominio
     * @throws PersistenciaException si el ID es nulo, vacío, tiene formato inválido, 
     * no se encuentra el empleado o falla la consulta en MongoDB
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
     * Obtiene un empleado por su nombre de usuario único.
     *
     * @param user nombre de usuario único del empleado
     * @return el empleado correspondiente al usuario en el modelo de dominio
     * @throws PersistenciaException si el usuario es nulo, vacío, no se encuentra 
     * el registro o si ocurre un error en MongoDB
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
     * Actualiza el estado actual de un empleado específico en la base de datos.
     *
     * @param empleado el empleado perteneciente al modelo de dominio a actualizar
     * @param estado el nuevo estado que se le asignará al empleado
     * @throws PersistenciaException si el empleado es nulo, su ID es inválido, 
     * el nuevo estado es nulo o falla la operación en MongoDB
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
     * Obtiene todos los empleados con rol de mesero que se encuentren en estado activo.
     *
     * @return una lista de empleados con rol MESERO y estado ACTIVO convertidos al modelo de dominio
     * @throws PersistenciaException si ocurre un error durante la consulta en MongoDB
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
     * Busca meseros activos cuyo usuario, nombres o apellidos coincidan de manera parcial 
     * o total con el texto de filtro proporcionado (insensible a mayúsculas y minúsculas).
     *
     * @param filtro texto o cadena de búsqueda para filtrar a los meseros
     * @return una lista de empleados que cumplen con los criterios de búsqueda y rol especificados
     * @throws PersistenciaException si ocurre un error en el proceso de consulta de MongoDB
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