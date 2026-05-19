package adaptadores;

import entidades.Pedido;
import entidadesMongo.PedidoEntidadMongo;

/**
 * Adaptador encargado de convertir objetos Pedido entre la capa de dominio
 * y su representación en MongoDB.
 * 
 * Permite mapear la entidad de negocio Pedido a su equivalente persistido
 * y viceversa, manteniendo separadas las capas del sistema.
 * 
 * @author DishUp
 * 
 */
public class PedidoPersistenciaAdapter {

    /**
     * Convierte un objeto Pedido del dominio a su representación en MongoDB.
     *
     * @param pedido objeto de dominio
     * @return entidad Mongo correspondiente o null si el pedido es null
     */
    public PedidoEntidadMongo aMongo(Pedido pedido) {

        if (pedido == null) {
            return null;
        }

        PedidoEntidadMongo mongo = new PedidoEntidadMongo();

        mongo.setId(pedido.getId());
        mongo.setIdProducto(pedido.getIdProducto());
        mongo.setNombreProducto(pedido.getNombreProducto());
        mongo.setCantidad(pedido.getCantidad());
        mongo.setDescripcion(pedido.getDescripcion());
        mongo.setPrecioProducto(pedido.getPrecioProducto());
        mongo.setEstado(pedido.getEstado());
        mongo.setFechaPedido(pedido.getFechaPedido());
        mongo.setIngredientesRemovidos(pedido.getIngredientesRemovidos());

        return mongo;
    }

    /**
     * Convierte una entidad MongoDB a un objeto de dominio Pedido.
     *
     * @param mongo entidad persistida en MongoDB
     * @return objeto de dominio o null si la entidad es null
     */
    public Pedido aDominio(PedidoEntidadMongo mongo) {

        if (mongo == null) {
            return null;
        }

        Pedido pedido = new Pedido();

        pedido.setId(mongo.getId());
        pedido.setIdProducto(mongo.getIdProducto());
        pedido.setNombreProducto(mongo.getNombreProducto());
        pedido.setCantidad(mongo.getCantidad());
        pedido.setDescripcion(mongo.getDescripcion());
        pedido.setPrecioProducto(mongo.getPrecioProducto());
        pedido.setEstado(mongo.getEstado());
        pedido.setFechaPedido(mongo.getFechaPedido());
        pedido.setIngredientesRemovidos(mongo.getIngredientesRemovidos());

        return pedido;
    }
}