package daos;

import adaptadores.MesaPersistenciaAdapter;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import conexion.ConexionMongo;
import entidades.Empleado;
import entidades.Mesa;
import entidadesMongo.EmpleadoEntidadMongo;
import entidadesMongo.MesaEntidadMongo;
import enums.EstadoMesa;
import enums.RolEmpleado;
import excepciones.PersistenciaException;
import interfaces.IMesaDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * DAO de Mesa.
 * 
 * Esta clase se encarga del acceso a datos de la entidad Mesa en MongoDB.
 * Su responsabilidad principal es realizar operaciones CRUD sobre la colección "mesas",
 * así como la asignación y desasignación de mesas a meseros.
 * 
 * También maneja la conversión entre entidades de dominio (Mesa)
 * y entidades de persistencia (MesaEntidadMongo) mediante el adaptador MesaPersistenciaAdapter.
 */
public class MesaDAO implements IMesaDAO {

    private final MongoCollection<MesaEntidadMongo> coleccion;
    private final MongoCollection<EmpleadoEntidadMongo> coleccionEmpleados;
    private final MesaPersistenciaAdapter mesaAdapter;

    /**
     * Constructor de MesaDAO.
     * Inicializa las colecciones de MongoDB y el adaptador de persistencia.
     */
    public MesaDAO() {
        this.coleccion = ConexionMongo.obtenerBaseDatos().getCollection("mesas", MesaEntidadMongo.class);
        this.coleccionEmpleados = ConexionMongo.obtenerBaseDatos().getCollection("empleados", EmpleadoEntidadMongo.class);
        this.mesaAdapter = new MesaPersistenciaAdapter();
    }

    /**
     * Obtiene las mesas asignadas a un mesero específico.
     *
     * @param idMesero identificador del mesero
     * @return lista de mesas asignadas al mesero
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public List<Mesa> obtenerMesasPorMesero(String idMesero) throws PersistenciaException {

        if (idMesero == null || idMesero.isBlank()) {
            return new ArrayList<>();
        }

        try {

            List<MesaEntidadMongo> mesasMongo = coleccion
                    .find(eq("idMesero", idMesero))
                    .sort(ascending("numero"))
                    .into(new ArrayList<>());

            List<Mesa> mesasDominio = new ArrayList<>();

            for (MesaEntidadMongo mesaMongo : mesasMongo) {
                mesasDominio.add(mesaAdapter.aDominio(mesaMongo));
            }

            return mesasDominio;

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible obtener las mesas del mesero.", ex);
        }
    }

    /**
     * Inserta una nueva mesa en la base de datos.
     *
     * @param mesa objeto Mesa a insertar
     * @throws PersistenciaException si la mesa es nula o no se puede insertar
     */
    @Override
    public void insertarMesa(Mesa mesa) throws PersistenciaException {

        if (mesa == null) {
            throw new PersistenciaException("La mesa es nula");
        }

        try {

            MesaEntidadMongo mesaMongo = mesaAdapter.aMongo(mesa);

            InsertOneResult resultado = coleccion.insertOne(mesaMongo);

            if (resultado.getInsertedId() == null) {
                throw new PersistenciaException("Error al guardar la mesa");
            }

            String idGenerado = resultado.getInsertedId()
                    .asObjectId()
                    .getValue()
                    .toHexString();

            mesaMongo.setId(idGenerado);
            mesa.setId(idGenerado);

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible insertar la mesa.", ex);
        }
    }

    /**
     * Elimina una mesa de la base de datos.
     *
     * @param mesa mesa a eliminar
     * @throws PersistenciaException si la mesa es nula, no tiene id o no se puede eliminar
     */
    @Override
    public void eliminarMesa(Mesa mesa) throws PersistenciaException {

        if (mesa == null) {
            throw new PersistenciaException("La mesa es nula");
        }

        if (mesa.getId() == null || mesa.getId().isBlank()) {
            throw new PersistenciaException("La mesa no tiene id");
        }

        try {

            DeleteResult resultado = coleccion.deleteOne(
                    eq("_id", new ObjectId(mesa.getId()))
            );

            if (resultado.getDeletedCount() == 0) {
                throw new PersistenciaException("No se encontró la mesa para eliminar");
            }

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible eliminar la mesa.", ex);
        }
    }

    /**
     * Obtiene una mesa por su identificador.
     *
     * @param id identificador de la mesa
     * @return mesa encontrada
     * @throws PersistenciaException si no existe o ocurre un error
     */
    @Override
    public Mesa obtenerMesaPorId(String id) throws PersistenciaException {

        if (id == null) {
            throw new PersistenciaException("El id de la mesa es nulo");
        }

        try {

            MesaEntidadMongo mesaMongo = coleccion
                    .find(eq("_id", new ObjectId(id)))
                    .first();

            if (mesaMongo == null) {
                throw new PersistenciaException("No se encontró la mesa");
            }

            return mesaAdapter.aDominio(mesaMongo);

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible buscar la mesa.", ex);
        }
    }

    /**
     * Obtiene una mesa por su número.
     *
     * @param numero número de la mesa
     * @return mesa encontrada o null si no existe
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public Mesa obtenerMesaPorNumero(Integer numero) throws PersistenciaException {

        if (numero == null) {
            throw new PersistenciaException("El número de mesa es nulo");
        }

        try {

            MesaEntidadMongo mesaMongo = coleccion
                    .find(eq("numero", numero))
                    .first();

            if (mesaMongo == null) {
                return null;
            }

            return mesaAdapter.aDominio(mesaMongo);

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible buscar la mesa.", ex);
        }
    }

    /**
     * Asigna una lista de mesas a un mesero.
     *
     * @param mesas lista de mesas
     * @param mesero empleado mesero
     * @throws PersistenciaException si ocurre un error en la asignación
     */
    @Override
    public void asignarMesasAMesero(List<Mesa> mesas, Empleado mesero) throws PersistenciaException {

        if (mesas == null) {
            throw new PersistenciaException("La lista es nula");
        }

        if (mesero == null) {
            throw new PersistenciaException("El mesero es nulo");
        }

        if (mesas.isEmpty()) {
            return;
        }

        try {

            EmpleadoEntidadMongo meseroMongo = coleccionEmpleados.find(
                    and(eq("_id", new ObjectId(mesero.getId())),
                        eq("rol", RolEmpleado.MESERO.name()))
            ).first();

            if (meseroMongo == null) {
                throw new PersistenciaException("No se encontró el mesero");
            }

            for (Mesa mesa : mesas) {

                MesaEntidadMongo mesaMongo = coleccion
                        .find(eq("_id", new ObjectId(mesa.getId())))
                        .first();

                if (mesaMongo == null) {
                    throw new PersistenciaException("No se encontró la mesa");
                }

                coleccion.updateOne(
                        eq("_id", new ObjectId(mesa.getId())),
                        set("idMesero", mesero.getId())
                );
            }

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible asignar el mesero a las mesas.", ex);
        }
    }

    /**
     * Desasigna una lista de mesas de un mesero.
     *
     * @param mesas lista de mesas
     * @param mesero empleado mesero
     * @throws PersistenciaException si ocurre un error en la operación
     */
    @Override
    public void desasignarMesasAMesero(List<Mesa> mesas, Empleado mesero) throws PersistenciaException {

        if (mesas == null) {
            throw new PersistenciaException("La lista es nula");
        }

        if (mesero == null) {
            throw new PersistenciaException("El mesero es nulo");
        }

        if (mesas.isEmpty()) {
            return;
        }

        try {

            EmpleadoEntidadMongo meseroMongo = coleccionEmpleados.find(
                    and(eq("_id", new ObjectId(mesero.getId())),
                        eq("rol", RolEmpleado.MESERO.name()))
            ).first();

            if (meseroMongo == null) {
                throw new PersistenciaException("No se encontró el mesero");
            }

            for (Mesa mesa : mesas) {

                MesaEntidadMongo mesaMongo = coleccion
                        .find(eq("_id", new ObjectId(mesa.getId())))
                        .first();

                if (mesaMongo == null) {
                    throw new PersistenciaException("No se encontró la mesa");
                }

                coleccion.updateOne(
                        eq("_id", new ObjectId(mesa.getId())),
                        unset("idMesero")
                );
            }

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible desasignar las mesas del mesero.", ex);
        }
    }

    /**
     * Obtiene todas las mesas sin mesero asignado.
     *
     * @return lista de mesas disponibles
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public List<Mesa> obtenerMesasDisponibles() throws PersistenciaException {

        try {

            List<MesaEntidadMongo> mesasMongo = coleccion
                    .find(exists("idMesero", false))
                    .sort(ascending("numero"))
                    .into(new ArrayList<>());

            List<Mesa> mesasDominio = new ArrayList<>();

            for (MesaEntidadMongo m : mesasMongo) {
                mesasDominio.add(mesaAdapter.aDominio(m));
            }

            return mesasDominio;

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible consultar las mesas disponibles", ex);
        }
    }

    /**
     * Obtiene todas las mesas registradas.
     *
     * @return lista de mesas
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public List<Mesa> obtenerMesas() throws PersistenciaException {

        try {

            List<MesaEntidadMongo> mesasMongo = coleccion
                    .find()
                    .sort(ascending("numero"))
                    .into(new ArrayList<>());

            List<Mesa> mesasDominio = new ArrayList<>();

            for (MesaEntidadMongo m : mesasMongo) {
                mesasDominio.add(mesaAdapter.aDominio(m));
            }

            return mesasDominio;

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible consultar las mesas.", ex);
        }
    }
    
    /**
     * Cambia el estado de la mesa segun si la mesa tiene comandas o no.
     * @param numero de la mesa a la que se le quire hacer el cambio.
     * @param estado al que se quiere cambiar la mesa
     * @throws PersistenciaException si ocurre un error en la actualizacion
     */
    @Override
    public void cambiarEstadoMesaPorNumero(int numero, EstadoMesa estado) throws PersistenciaException {
        try {

            UpdateResult result = coleccion.updateOne(eq("numero", numero), set("estado", estado));

            if (result.getMatchedCount() == 0) {
                throw new PersistenciaException("No existe la mesa con numero: "+ numero);
            }

        } catch (MongoException ex) {
            throw new PersistenciaException("Error al cambiar estado de la mesa", ex);
        }
    }
}