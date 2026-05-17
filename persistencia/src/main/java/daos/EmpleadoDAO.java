
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

public class EmpleadoDAO implements IEmpleadoDAO {

    private final MongoCollection<EmpleadoEntidadMongo> coleccion;
    private final EmpleadoPersistenciaAdapter empleadoAdapter;

    public EmpleadoDAO() {
        this.coleccion = ConexionMongo.obtenerBaseDatos().getCollection("empleados", EmpleadoEntidadMongo.class);
        this.empleadoAdapter = new EmpleadoPersistenciaAdapter();
    }

    @Override
    public Empleado obtenerEmpleadoPorId(String id) throws PersistenciaException {
        if (id == null || id.isBlank()) {
            throw new PersistenciaException("El ID del empleado es inválido");
        }

        try {
            EmpleadoEntidadMongo empleadoMongo = this.coleccion.find(eq("_id", new ObjectId(id)) ).first();

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
    
    @Override
    public Empleado obtenerEmpleadoPorUser(String user) throws PersistenciaException {
        if (user == null || user.isBlank()) {
            throw new PersistenciaException("El user del empleado es inválido");
        }

        try {
            EmpleadoEntidadMongo empleadoMongo = this.coleccion.find(eq("user", user)).first();

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


    @Override
    public void actualizarEstadoEmpleado(Empleado empleado, EstadoEmpleado estado) throws PersistenciaException {
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
            throw new PersistenciaException("No fue posible actualizar el estado del empleado.", ex);
        }
    }

    //INICIO metodos de cu administrar mesas
    @Override
    public List<Empleado> obtenerMeserosActivos() throws PersistenciaException {
        try{
            List<EmpleadoEntidadMongo> activosMongo = this.coleccion.find(and(eq("rol", RolEmpleado.MESERO),(eq("estado", EstadoEmpleado.ACTIVO)))).into(new ArrayList<>());
            
            List<Empleado> activosDominio = new ArrayList<>();
            
            for(EmpleadoEntidadMongo e: activosMongo){
                activosDominio.add(empleadoAdapter.aDominio(e));
            }
            
            return activosDominio;
        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible obtener a los meseros activos", ex);
        }
    }

    @Override
    public List<Empleado> buscarMeserosPorUserNombre(String filtro) throws PersistenciaException {
        try{
            
            List<Bson> filtros = new ArrayList<>();
            
            filtros.add(eq("rol", RolEmpleado.MESERO));
            filtros.add(eq("estado", EstadoEmpleado.ACTIVO));
            
            if (filtro != null && !filtro.isBlank()) {

                filtros.add(or(
                    regex("user", ".*" + filtro + ".*", "i"),
                    regex("nombres", ".*" + filtro + ".*", "i")
                ));
            }

            List<EmpleadoEntidadMongo> activosMongo = coleccion.find(and(filtros)).into(new ArrayList<>());
            
            List<Empleado> dominios = new ArrayList<>();
            
            for(EmpleadoEntidadMongo e: activosMongo){
                dominios.add(empleadoAdapter.aDominio(e));
            }
            
            return dominios;
        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible obtener a los meseros activos", ex);
        }
    }
    
    //FIN metodos de cu administrar mesas

}