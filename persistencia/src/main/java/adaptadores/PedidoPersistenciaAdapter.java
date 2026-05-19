package adaptadores;

import entidades.Pedido;
import entidadesMongo.PedidoEntidadMongo;

/**
 * Adaptador de persistencia para Pedido.
 *
 * Convierte objetos Pedido del dominio a PedidoEntidadMongo y viceversa.
 *
 * @author DishUp
 */
public class PedidoPersistenciaAdapter {

    /**
     * Convierte Pedido -> PedidoEntidadMongo
     *
     * @param pedido pedido del dominio
     * @return entidad mongo
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

        return mongo;
    }

    /**
     * Convierte PedidoEntidadMongo -> Pedido
     *
     * @param mongo entidad mongo
     * @return pedido dominio
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

        return pedido;
    }
}
