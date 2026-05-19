package daos;

import adaptadores.ComandaPersistenciaAdapter;
import adaptadores.PagoPersistenciaAdapter;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.pull;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import conexion.ConexionMongo;
import entidades.Comanda;
import entidades.Pago;
import entidades.Pedido;
import entidadesMongo.ComandaEntidadMongo;
import entidadesMongo.PagoEntidadMongo;
import entidadesMongo.PedidoEntidadMongo;
import excepciones.PersistenciaException;
import interfaces.IComandaDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Clase de acceso a datos (DAO) para la gestión de Comandas en MongoDB.
 * * Implementa la interfaz IComandaDAO para realizar operaciones CRUD y consultas
 * avanzadas sobre la colección de comandas, abstrayendo la lógica de persistencia
 * y transformando los datos entre los modelos de persistencia y de dominio.
 * * Se utiliza únicamente en la capa de persistencia y no debe contener lógica de negocio.
 */
public class ComandaDAO implements IComandaDAO {

    private final MongoCollection<ComandaEntidadMongo> coleccion;
    private final MongoCollection<Document> coleccionraw;
    private final ComandaPersistenciaAdapter adapter;
    private final PagoPersistenciaAdapter adapterPago;

    /**
     * Constructor por defecto que inicializa las colecciones de MongoDB y los adaptadores.
     */
    public ComandaDAO() {
        this.coleccion = ConexionMongo.obtenerBaseDatos().getCollection("comandas", ComandaEntidadMongo.class);
        this.coleccionraw = ConexionMongo.obtenerBaseDatos().getCollection("comandas");
        this.adapter = new ComandaPersistenciaAdapter();
        this.adapterPago = new PagoPersistenciaAdapter();
    }

    /**
     * Inserta una nueva comanda en la base de datos de MongoDB.
     *
     * @param comanda la comanda perteneciente al modelo de dominio a insertar
     * @return la comanda insertada con su identificador único generado por la base de datos
     * @throws PersistenciaException si la comanda es nula o si ocurre un error en MongoDB
     */
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

    /**
     * Obtiene todas las comandas almacenadas en la base de datos.
     *
     * @return una lista con todas las comandas convertidas al modelo de dominio
     * @throws PersistenciaException si ocurre un error durante la consulta en MongoDB
     */
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

    /**
     * Obtiene las comandas asociadas a un número de mesa específico.
     *
     * @param numeroMesa el número de la mesa a consultar
     * @return una lista de comandas correspondientes a la mesa indicada
     * @throws PersistenciaException si ocurre un error durante la consulta en MongoDB
     */
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

    /**
     * Busca y obtiene una comanda por su identificador único.
     *
     * @param id el identificador hexadecimal de la comanda
     * @return la comanda correspondiente al identificador, o null si no se encuentra
     * @throws PersistenciaException si el id proporcionado es nulo o si ocurre un error en MongoDB
     */
    @Override
    public Comanda obtenerPorId(String id) throws PersistenciaException {
        if (id == null) {
            throw new PersistenciaException("El id es nulo");
        }

        try {
            ComandaEntidadMongo mongo = coleccion.find(eq("_id", new ObjectId(id))).first();
            return adapter.aDominio(mongo);

        } catch (MongoException e) {
            throw new PersistenciaException("Error al buscar comanda", e);
        }
    }

    /**
     * Actualiza el estado actual de una comanda específica.
     *
     * @param idComanda el identificador de la comanda a modificar
     * @param nuevoEstado el nuevo estado que se le asignará a la comanda
     * @return true si la comanda fue modificada exitosamente, false de lo contrario
     * @throws PersistenciaException si alguno de los parámetros es inválido o si ocurre un error en MongoDB
     */
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

    /**
     * Agrega un nuevo pedido a la lista de pedidos de una comanda.
     *
     * @param idComanda el identificador de la comanda destino
     * @param nuevoPedido el objeto pedido a añadir
     * @return true si el pedido fue añadido con éxito, false de lo contrario
     * @throws PersistenciaException si ocurre un error en la operación de actualización en MongoDB
     */
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

    /**
     * Elimina de forma permanente una comanda de la base de datos.
     *
     * @param idComanda el identificador de la comanda a eliminar
     * @return true si la comanda fue eliminada de forma efectiva, false de lo contrario
     * @throws PersistenciaException si el id es inválido o si se genera un error en el motor de MongoDB
     */
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

    /**
     * Registra un nuevo pago dentro del arreglo de pagos de una comanda específica.
     *
     * @param idComanda el identificador de la comanda
     * @param pago el objeto pago del modelo de dominio a registrar
     * @return true si el pago se agregó exitosamente a la comanda, false de lo contrario
     * @throws PersistenciaException si el id es inválido, el pago es nulo o por fallas de base de datos
     */
    @Override
    public boolean insertarPagoAComanda(String idComanda, Pago pago) throws PersistenciaException {
        if (idComanda == null || idComanda.isBlank()) {
            throw new PersistenciaException("El id de la comanda es inválido");
        }

        if (pago == null) {
            throw new PersistenciaException("Pago nulo");
        }

        try {
            PagoEntidadMongo pagoMongo = adapterPago.aMongo(pago);

            UpdateResult resultado = coleccion.updateOne(
                    eq("_id", new ObjectId(idComanda)),
                    Updates.push("pagos", pagoMongo)
            );

            return resultado.getModifiedCount() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new PersistenciaException(
                    "No fue posible agregar el pago a la comanda.",
                    ex
            );
        }
    }

    /**
     * Obtiene una lista de comandas que contengan al menos un pedido en estado "LISTA".
     *
     * @return lista de comandas con pedidos listos para entrega
     * @throws PersistenciaException si ocurre un error en la consulta de MongoDB
     */
    @Override
    public List<Comanda> obtenerComandasListas() throws PersistenciaException {
        try {
            List<ComandaEntidadMongo> listaMongo = coleccion.find().into(new ArrayList<>());
            List<Comanda> lista = new ArrayList<>();

            for (ComandaEntidadMongo mongo : listaMongo) {
                boolean tienePedidosListos = false;

                for (PedidoEntidadMongo pedido : mongo.getPedidos()) {
                    if (pedido.getEstado() != null && "LISTA".equals(pedido.getEstado().name())) {
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

    /**
     * Reemplaza por completo el listado de pedidos de una comanda específica.
     *
     * @param idComanda el identificador de la comanda
     * @param pedidos la nueva lista de pedidos a establecer
     * @return true si la lista de pedidos de la comanda fue actualizada, false de lo contrario
     * @throws PersistenciaException si los parámetros son nulos/vacíos o por errores del Driver de Mongo
     */
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

    /**
     * Calcula el monto acumulado total de una comanda mediante un pipeline de agregación.
     * * Multiplica la cantidad por el precio unitario de cada producto dentro del arreglo de pedidos.
     *
     * @param idComanda el identificador de la comanda a calcular
     * @return el monto total flotante calculado de los pedidos; 0f si no hay pedidos o no se encuentra la comanda
     * @throws PersistenciaException si el id es nulo o si falla el proceso de agregación
     */
    @Override
    public float calcularMontoComanda(String idComanda) throws PersistenciaException {
        if (idComanda == null) {
            throw new PersistenciaException("El id es nulo");
        }
        try {
            List<Bson> pipeline = Arrays.asList(
                    Aggregates.match(eq("_id", new ObjectId(idComanda))),
                    Aggregates.unwind("$pedidos"),
                    Aggregates.project(
                            Projections.fields(
                                    Projections.computed("subtotal", new Document("$multiply", Arrays.asList("$pedidos.cantidad", "$pedidos.precioProducto")))
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

    /**
     * Calcula de nuevo el importe total de la comanda y actualiza su campo 'montoTotal' en la base de datos.
     *
     * @param idComanda el identificador de la comanda a recalcular
     * @throws PersistenciaException si ocurre un error al calcular o persistir el nuevo monto total
     */
    @Override
    public void recalcularMonto(String idComanda) throws PersistenciaException {
        float total = calcularMontoComanda(idComanda);

        coleccion.updateOne(
                eq("_id", new ObjectId(idComanda)),
                set("montoTotal", total)
        );
    }

    /**
     * Edita los campos internos de un pedido específico anidado dentro de una comanda usando arrayFilters.
     *
     * @param idComanda el identificador de la comanda contenedora
     * @param pedidoEditado el pedido con los nuevos valores modificados
     * @return true si se localizó la comanda y aplicaron los filtros de actualización, false de lo contrario
     * @throws PersistenciaException si las referencias son nulas o si ocurre un fallo en MongoDB
     */
    @Override
    public boolean editarPedidoDeComanda(String idComanda, Pedido pedidoEditado) throws PersistenciaException {
        if (idComanda == null || idComanda.isBlank()) {
            throw new PersistenciaException("El id de la comanda es inválido");
        }
        if (pedidoEditado == null || pedidoEditado.getId() == null) {
            throw new PersistenciaException("El pedido o su id es nulo");
        }

        try {
            Bson filtro = eq("_id", new ObjectId(idComanda));

            Bson updates = Updates.combine(
                    set("pedidos.$[p].descripcion", pedidoEditado.getDescripcion()),
                    set("pedidos.$[p].ingredientesRemovidos", pedidoEditado.getIngredientesRemovidos()),
                    set("pedidos.$[p].cantidad", pedidoEditado.getCantidad()),
                    set("pedidos.$[p].precioProducto", pedidoEditado.getPrecioProducto())
            );

            UpdateOptions options = new UpdateOptions().arrayFilters(
                    List.of(eq("p._id", pedidoEditado.getId()))
            );

            UpdateResult result = coleccion.updateOne(filtro, updates, options);

            System.out.println("MATCHED: " + result.getMatchedCount());
            System.out.println("MODIFIED: " + result.getModifiedCount());

            return result.getMatchedCount() > 0;

        } catch (MongoException e) {
            throw new PersistenciaException("Error al editar pedido", e);
        }
    }

    /**
     * Cancela y remueve un pedido específico del arreglo de pedidos de una comanda (Pull).
     *
     * @param idComanda el identificador de la comanda
     * @param idPedido el identificador del pedido que se desea remover
     * @return true si se removió el elemento de los pedidos de la comanda, false de lo contrario
     * @throws PersistenciaException si cualquiera de los ids suministrados es nulo o vacío
     */
    @Override
    public boolean cancelarPedidoDeComanda(String idComanda, String idPedido) throws PersistenciaException {
        if (idComanda == null || idComanda.isBlank()) {
            throw new PersistenciaException("El id de la comanda es inválido");
        }

        if (idPedido == null || idPedido.isBlank()) {
            throw new PersistenciaException("El id del pedido es inválido");
        }

        try {
            UpdateResult result = coleccion.updateOne(
                    eq("_id", new ObjectId(idComanda)),
                    pull("pedidos", new Document("_id", idPedido))
            );

            return result.getModifiedCount() > 0;

        } catch (MongoException e) {
            throw new PersistenciaException("Error al cancelar pedido", e);
        }
    }
}