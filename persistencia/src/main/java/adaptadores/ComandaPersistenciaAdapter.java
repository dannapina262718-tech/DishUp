package adaptadores;

import entidades.Comanda;
import entidades.Mesa;
import entidades.Pedido;
import entidadesMongo.ComandaEntidadMongo;
import entidadesMongo.MesaEntidadMongo;
import entidadesMongo.PedidoEntidadMongo;
import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador de persistencia para la entidad Comanda.
 * 
 * Esta clase se encarga de convertir objetos del modelo de dominio (Comanda)
 * a entidades de MongoDB (ComandaEntidadMongo) y viceversa.
 * 
 * Su función principal es aislar la lógica de conversión entre la capa de
 * negocio y la capa de persistencia, evitando acoplamiento directo.
 */
public class ComandaPersistenciaAdapter {

    /**
     * Constructor por defecto del adaptador.
     */
    public ComandaPersistenciaAdapter() {
    }

    /**
     * Convierte una Comanda del modelo de dominio a una entidad MongoDB.
     *
     * @param comanda objeto del dominio a convertir
     * @return entidad ComandaEntidadMongo o null si la comanda es null
     */
    public ComandaEntidadMongo aMongo(Comanda comanda) {

        if (comanda == null) {
            return null;
        }

        ComandaEntidadMongo mongo = new ComandaEntidadMongo();

        mongo.setId(comanda.getId());
        mongo.setNombreCliente(comanda.getNombreCliente());
        mongo.setFecha(comanda.getFecha());
        mongo.setEstado(comanda.getEstado());
        mongo.setMontoTotal(comanda.getMontoTotal());
        mongo.setNumeroMesa(comanda.getMesa().getNumero());
        mongo.setNombreEmpleado(comanda.getEmpleado().getNombres());

        MesaEntidadMongo mesaMongo = new MesaEntidadMongo();
        mesaMongo.setNumero(comanda.getMesa().getNumero());

        List<PedidoEntidadMongo> pedidosMongo = new ArrayList<>();

        if (comanda.getPedidos() != null) {

            for (Pedido pedido : comanda.getPedidos()) {

                PedidoEntidadMongo pedidoMongo = new PedidoEntidadMongo();

                pedidoMongo.setId(pedido.getId());
                pedidoMongo.setIdProducto(pedido.getIdProducto());
                pedidoMongo.setNombreProducto(pedido.getNombreProducto());
                pedidoMongo.setCantidad(pedido.getCantidad());
                pedidoMongo.setDescripcion(pedido.getDescripcion());
                pedidoMongo.setPrecioProducto(pedido.getPrecioProducto());
                pedidoMongo.setEstado(pedido.getEstado());
                pedidoMongo.setFechaPedido(pedido.getFechaPedido());

                pedidosMongo.add(pedidoMongo);
            }
        }

        mongo.setPedidos(pedidosMongo);

        return mongo;
    }

    /**
     * Convierte una entidad MongoDB a una Comanda del modelo de dominio.
     *
     * @param mongo entidad ComandaEntidadMongo a convertir
     * @return objeto Comanda del dominio o null si la entidad es null
     */
    public Comanda aDominio(ComandaEntidadMongo mongo) {

        if (mongo == null) {
            return null;
        }

        Comanda comanda = new Comanda();

        comanda.setId(mongo.getId());
        comanda.setNombreCliente(mongo.getNombreCliente());
        comanda.setFecha(mongo.getFecha());
        comanda.setEstado(mongo.getEstado());
        comanda.setMontoTotal(mongo.getMontoTotal());

        entidades.Empleado empleado = new entidades.Empleado();
        empleado.setNombres(mongo.getNombreEmpleado());

        comanda.setEmpleado(empleado);

        Mesa mesa = new Mesa();
        mesa.setNumero(mongo.getNumeroMesa());

        comanda.setMesa(mesa);

        List<Pedido> pedidos = new ArrayList<>();

        if (mongo.getPedidos() != null) {

            for (PedidoEntidadMongo pedidoMongo : mongo.getPedidos()) {

                Pedido pedido = new Pedido();

                pedido.setId(pedidoMongo.getId());
                pedido.setIdProducto(pedidoMongo.getIdProducto());
                pedido.setNombreProducto(pedidoMongo.getNombreProducto());
                pedido.setCantidad(pedidoMongo.getCantidad());
                pedido.setDescripcion(pedidoMongo.getDescripcion());
                pedido.setPrecioProducto(pedidoMongo.getPrecioProducto());
                pedido.setEstado(pedidoMongo.getEstado());
                pedido.setFechaPedido(pedidoMongo.getFechaPedido());

                pedidos.add(pedido);
            }
        }

        comanda.setPedidos(pedidos);

        return comanda;
    }
}