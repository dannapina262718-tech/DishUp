package daos;

import adaptadores.ComandaPersistenciaAdapter;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import conexion.ConexionMongo;
import entidades.Comanda;
import entidades.Pedido;
import entidadesMongo.ComandaEntidadMongo;
import entidadesMongo.PedidoEntidadMongo;
import excepciones.PersistenciaException;
import interfaces.IComandaDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class ComandaDAO implements IComandaDAO {

    private final MongoCollection<ComandaEntidadMongo> coleccion;
    private final MongoCollection<Document> coleccionraw;
    private final ComandaPersistenciaAdapter adapter;
    private MongoCollection<Document> coleccionRaw;

    public ComandaDAO() {
        this.coleccion = ConexionMongo.obtenerBaseDatos().getCollection("comandas", ComandaEntidadMongo.class);
        this.coleccionraw = ConexionMongo.obtenerBaseDatos().getCollection("comandas");

        this.adapter = new ComandaPersistenciaAdapter();
    }

    @Override
    public Comanda insertarComanda(Comanda comanda) throws PersistenciaException {

        if (comanda == null) {
            throw new PersistenciaException("Comanda nula");
        }

        try {
            ComandaEntidadMongo mongo = adapter.aMongo(comanda);
            

            InsertOneResult result = coleccion.insertOne(mongo);

            if (result.getInsertedId() == null) {
                throw new PersistenciaException("Error al insertar comanda");
            }

            String id = result.getInsertedId()
                    .asObjectId()
                    .getValue()
                    .toHexString();

            mongo.setId(id);

            return adapter.aDominio(mongo);

        } catch (MongoException e) {
            throw new PersistenciaException("Error Mongo insertar comanda", e);
        }
    }

    @Override
    public List<Comanda> obtenerTodas() throws PersistenciaException {

        try {
            List<ComandaEntidadMongo> listaMongo = coleccion.find().into(new ArrayList<>());

            List<Comanda> lista = new ArrayList<>();

            for (ComandaEntidadMongo m : listaMongo) {
                lista.add(adapter.aDominio(m));
            }

            return lista;

        } catch (MongoException e) {
            throw new PersistenciaException("Error al obtener comandas", e);
        }
    }

    @Override
    public List<Comanda> obtenerComandasPorMesa(int numeroMesa) throws PersistenciaException {

        try {
            List<ComandaEntidadMongo> listaMongo = coleccion.find(eq("numeroMesa", numeroMesa)).into(new ArrayList<>());

            List<Comanda> lista = new ArrayList<>();

            for (ComandaEntidadMongo m : listaMongo) {
                lista.add(adapter.aDominio(m));
            }

            return lista;

        } catch (MongoException e) {
            throw new PersistenciaException("Error al consultar comandas", e);
        }
    }

    @Override
    public Comanda obtenerPorId(String id) throws PersistenciaException {
        if(id == null){
            throw new PersistenciaException("El id es nulo");
        }

        try {
            ComandaEntidadMongo mongo = coleccion.find(eq("_id", new ObjectId(id))).first();
            
            return adapter.aDominio(mongo);

        } catch (MongoException e) {
            throw new PersistenciaException("Error al buscar comanda", e);
        }
    }

    @Override
    public boolean actualizarEstado(String idComanda, String nuevoEstado) throws PersistenciaException {
        if (idComanda == null || idComanda.isBlank()) {
            throw new PersistenciaException("El id de la comanda es inválido");
        }

        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            throw new PersistenciaException("El estado es inválido");
        }
        try {
            UpdateResult result = coleccion.updateOne(
                    eq("_id", new ObjectId(idComanda)),
                    set("estado", nuevoEstado)
            );

            return result.getModifiedCount() > 0;

        } catch (MongoException e) {
            throw new PersistenciaException("Error al actualizar estado", e);
        }
    }

    @Override
    public boolean agregarPedidoAComanda(String idComanda, Pedido nuevoPedido) throws PersistenciaException {
        try {
            
            UpdateResult result = coleccion.updateOne(eq("_id", new ObjectId(idComanda)),
                    com.mongodb.client.model.Updates.push(
                            "pedidos",
                            nuevoPedido
                    )
            );

            return result.getModifiedCount() > 0;

        } catch (MongoException e) {
            throw new PersistenciaException("Error al agregar pedido a comanda", e);
        }
    }

    @Override
    public boolean eliminarComanda(String idComanda) throws PersistenciaException {
        if (idComanda == null || idComanda.isBlank()) {
            throw new PersistenciaException("El id de la comanda es inválido");
        }

        try {
            DeleteResult result = coleccion.deleteOne(
                    eq("_id", new ObjectId(idComanda))
            );

            return result.getDeletedCount() > 0;

        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Id de comanda inválido", e);

        } catch (MongoException e) {
            throw new PersistenciaException("Error al eliminar comanda", e);
        }
    }

    @Override
    public List<Comanda> obtenerComandasListas() throws PersistenciaException {
        try {
            List<ComandaEntidadMongo> listaMongo = coleccion.find().into(new ArrayList<>());
            List<Comanda> lista = new ArrayList<>();

            for (ComandaEntidadMongo mongo : listaMongo) {

                boolean tienePedidosListos = false;

                for (PedidoEntidadMongo pedido : mongo.getPedidos()) {
                    if (pedido.getEstado().name().equals("LISTA")) {
                        tienePedidosListos = true;
                        break;
                    }
                }

                if (tienePedidosListos) {
                    lista.add(adapter.aDominio(mongo));
                }
            }

            return lista;

        } catch (MongoException e) {
            throw new PersistenciaException("Error al obtener comandas con pedidos listos", e);
        }
    }

    @Override
    public boolean actualizarComanda(String idComanda, List<Pedido> pedidos) throws PersistenciaException {
        if (idComanda == null || idComanda.isBlank()) {
            throw new PersistenciaException("El id de la comanda es inválido");
        }

        if (pedidos == null) {
            throw new PersistenciaException("La lista de los pedidos es nula");
        }
        try {
            UpdateResult result = coleccion.updateOne(
                    eq("_id", new ObjectId(idComanda)),
                    set("pedidos", pedidos)
            );
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new PersistenciaException("Error al actualizar comanda", e);
        }
    }
    @Override
    public float calcularMontoComanda(String idComanda) throws PersistenciaException{
        if(idComanda == null){
            throw new PersistenciaException("El id es nulo");
        }
        try{
            List<Bson> pipeline = Arrays.asList(
                    
                    Aggregates.match(eq("_id", new ObjectId(idComanda))),
                    
                    Aggregates.unwind("$pedidos"),
                    
                    Aggregates.project(
                            Projections.fields(
                                    Projections.computed("subtotal", new Document("$multiply", Arrays.asList("$pedidos.cantidad","$pedidos.precioProducto")))
                            )
                    ),
                    Aggregates.group(null, Accumulators.sum("montoTotal", "$subtotal"))  
            );

            List<Document> resultado = coleccionraw.aggregate(pipeline).into(new ArrayList<>());

            
            if (resultado.isEmpty()) {
                return 0f;
            }
            
            return ((Number) resultado.get(0).get("montoTotal")).floatValue();

        } catch (Exception e) {
            throw new PersistenciaException("Error al calcular monto de comanda", e);
        }
    }

    @Override
    public void recalcularMonto(String idComanda) throws PersistenciaException {
        float total = calcularMontoComanda(idComanda);

        coleccion.updateOne(
            eq("_id", new ObjectId(idComanda)),
            set("montoTotal", total)
        );
    }
}
