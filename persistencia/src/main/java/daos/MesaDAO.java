package daos;

import adaptadores.MesaPersistenciaAdapter;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import conexion.ConexionMongo;
import entidades.Empleado;
import entidades.Mesa;
import entidadesMongo.EmpleadoEntidadMongo;
import entidadesMongo.MesaEntidadMongo;
import enums.RolEmpleado;
import excepciones.PersistenciaException;
import interfaces.IMesaDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

public class MesaDAO implements IMesaDAO {

    private final MongoCollection<MesaEntidadMongo> coleccion;
    private final MongoCollection<EmpleadoEntidadMongo> coleccionEmpleados;
    private final MesaPersistenciaAdapter mesaAdapter;

    public MesaDAO() {
        this.coleccion = ConexionMongo.obtenerBaseDatos().getCollection("mesas", MesaEntidadMongo.class);
        this.coleccionEmpleados = ConexionMongo.obtenerBaseDatos().getCollection("empleados", EmpleadoEntidadMongo.class);
        this.mesaAdapter = new MesaPersistenciaAdapter();
    }

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

    @Override
    public void insertarMesa(Mesa mesa) throws PersistenciaException {
        if (mesa == null) {
            throw new PersistenciaException("La mesa es nula");
        }

        try {
            MesaEntidadMongo mesaMongo = mesaAdapter.aMongo(mesa);

            InsertOneResult resultado = this.coleccion.insertOne(mesaMongo);

            if (resultado.getInsertedId() == null) {
                throw new PersistenciaException("Error al guardar");
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

    @Override
    public void eliminarMesa(Mesa mesa) throws PersistenciaException {
        if (mesa == null) {
            throw new PersistenciaException("La mesa es nula");
        }
        
        if (mesa.getId() == null || mesa.getId().isBlank()) {
            throw new PersistenciaException("La mesa no tiene id");
        }
        
        try{        
            DeleteResult resultado = this.coleccion.deleteOne(eq("_id", new ObjectId(mesa.getId())));
            
            if (resultado.getDeletedCount() == 0) {
                throw new PersistenciaException("No se encontró la mesa para eliminar.");
            }
            
        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible eliminar la mesa.", ex);
        }
    }

    @Override
    public Mesa obtenerMesaPorId(String id) throws PersistenciaException {
        if (id == null) {
            throw new PersistenciaException("El id de la mesa es nulo");
        }
        
        try{
            MesaEntidadMongo mesaMongo = this.coleccion.find(eq("_id", new ObjectId(id))).first();
            
            if (mesaMongo == null) {
                throw new PersistenciaException("No se encontró la mesa");
            }
            
            return mesaAdapter.aDominio(mesaMongo);
        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible buscar la mesa.", ex);
        }
    }
    
    @Override
    public Mesa obtenerMesaPorNumero(Integer numero) throws PersistenciaException{
        if (numero == null) {
            throw new PersistenciaException("El número de mesa es nulo");
        }

        try {
            MesaEntidadMongo mesaMongo = this.coleccion.find(eq("numero", numero)).first();
            if (mesaMongo == null) {
                return null;
            }

            return mesaAdapter.aDominio(mesaMongo);

        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible buscar la mesa.", ex);
        }
    }

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
        try{
            EmpleadoEntidadMongo meseroMongo = this.coleccionEmpleados.find(and(eq("_id", new ObjectId(mesero.getId())), eq("rol", RolEmpleado.MESERO.name()))).first();

            if (meseroMongo == null) {
                throw new PersistenciaException("No se encontró el mesero");
            }
            
            for(Mesa mesa: mesas){
                MesaEntidadMongo mesaMongo = this.coleccion.find(eq("_id", new ObjectId(mesa.getId()))).first();
                
                if (mesaMongo == null) {
                    throw new PersistenciaException("No se encontró la mesa");
                }
                
                this.coleccion.updateOne(eq("_id", new ObjectId(mesa.getId())), set("idMesero", mesero.getId()));
                mesaMongo.setIdMesero(mesero.getId());
            }  
        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible asignar el mesero a la mesa.", ex);
        }  
    }
    
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
        
        try{
            EmpleadoEntidadMongo meseroMongo = this.coleccionEmpleados.find(and(eq("_id", new ObjectId(mesero.getId())), eq("rol", RolEmpleado.MESERO.name()))).first();

            if (meseroMongo == null) {
                throw new PersistenciaException("No se encontró el mesero");
            }
            
            for(Mesa mesa: mesas){
                MesaEntidadMongo mesaMongo = this.coleccion.find(eq("_id", new ObjectId(mesa.getId()))).first();
                
                if (mesaMongo == null) {
                    throw new PersistenciaException("No se encontró la mesa");
                }
                
                this.coleccion.updateOne(eq("_id", new ObjectId(mesa.getId())), unset("idMesero"));
            }  
        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible asignar el mesero a la mesa.", ex);
        }  
    }

    @Override
    public List<Mesa> obtenerMesasDisponibles() throws PersistenciaException {
        try{
            List<MesaEntidadMongo> mesasMongo = this.coleccion.find(exists("idMesero", false)).sort(ascending("numero")).into(new ArrayList<>());
            
            List<Mesa> mesasDominio = new ArrayList<>();
            
            for(MesaEntidadMongo m: mesasMongo){
                mesasDominio.add(mesaAdapter.aDominio(m));
            }
            
            return mesasDominio;
        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible consultar las mesas disponibles", ex);
        }
    }
    
    @Override
    public List<Mesa> obtenerMesas() throws PersistenciaException {
        try{
            List<MesaEntidadMongo> mesasMongo = this.coleccion.find().sort(ascending("numero")).into(new ArrayList<>());
            
            List<Mesa> mesasDominio = new ArrayList<>();
            
            for(MesaEntidadMongo m: mesasMongo){
                mesasDominio.add(mesaAdapter.aDominio(m));
            }
            
            return mesasDominio;
        } catch (MongoException ex) {
            throw new PersistenciaException("No fue posible consultar las mesas disponibles", ex);
        }
    }
}