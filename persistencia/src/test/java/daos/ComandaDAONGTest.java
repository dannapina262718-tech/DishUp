/*
package daos;

import entidades.Comanda;
import entidades.Empleado;
import entidades.Mesa;
import entidades.Pedido;
import enums.EstadoPedido;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ComandaDAONGTest {

    ComandaDAO dao = new ComandaDAO();

    @AfterEach
    public void limpiar() {
    }

    @Test
    public void insertarComanda_correcto() throws PersistenciaException {

        Empleado empleado = new Empleado();
        empleado.setNombres("Juan");
        empleado.setApellidoPaterno("Perez");
        empleado.setApellidoMaterno("Lopez");
        empleado.setUser("EMP-001");

        Mesa mesa = new Mesa();
        mesa.setNumero(1);

        Comanda c = new Comanda();
        c.setEmpleado(empleado);
        c.setMesa(mesa);
        c.setNombreCliente("Juan");
        c.setPedidos(new ArrayList<>());

        Comanda insertada = dao.insertarComanda(c);

        assertNotNull(insertada);
        assertNotNull(insertada.getId());
    }

    @Test
    public void insertarComanda_null() {
        assertThrows(PersistenciaException.class, () -> {
            dao.insertarComanda(null);
        });
    }

    @Test
    public void obtenerTodas_correcto() throws PersistenciaException {

        Empleado empleado = new Empleado();
        empleado.setNombres("Juan");
        empleado.setApellidoPaterno("Perez");
        empleado.setApellidoMaterno("Lopez");
        empleado.setUser("EMP-001");

        Mesa mesa = new Mesa();
        mesa.setNumero(5);

        Comanda c1 = new Comanda();
        c1.setEmpleado(empleado);
        c1.setMesa(mesa);
        c1.setNombreCliente("A");
        c1.setPedidos(new ArrayList<>());

        Comanda c2 = new Comanda();
        c2.setEmpleado(empleado);
        c2.setMesa(mesa);
        c2.setNombreCliente("B");
        c2.setPedidos(new ArrayList<>());

        dao.insertarComanda(c1);
        dao.insertarComanda(c2);

        List<Comanda> lista = dao.obtenerTodas();

        assertNotNull(lista);
        assertEquals(2, lista.size());
    }

    @Test
    public void obtenerComandasPorMesa_correcto() throws PersistenciaException {

        Empleado empleado = new Empleado();
        empleado.setNombres("Juan");
        empleado.setApellidoPaterno("Perez");
        empleado.setApellidoMaterno("Lopez");
        empleado.setUser("EMP-001");

        Mesa mesa = new Mesa();
        mesa.setNumero(5);

        Comanda c1 = new Comanda();
        c1.setEmpleado(empleado);
        c1.setMesa(mesa);
        c1.setNombreCliente("A");
        c1.setPedidos(new ArrayList<>());

        Comanda c2 = new Comanda();
        c2.setEmpleado(empleado);
        c2.setMesa(mesa);
        c2.setNombreCliente("B");
        c2.setPedidos(new ArrayList<>());

        dao.insertarComanda(c1);
        dao.insertarComanda(c2);

        List<Comanda> lista = dao.obtenerComandasPorMesa(5);

        assertEquals(2, lista.size());
    }

    @Test
    public void obtenerComandasPorMesa_sinResultados() throws PersistenciaException {

        List<Comanda> lista = dao.obtenerComandasPorMesa(999);

        assertEquals(0, lista.size());
    }

    @Test
    public void obtenerPorId_correcto() throws PersistenciaException {

        Empleado empleado = new Empleado();
        empleado.setNombres("Juan");
        empleado.setApellidoPaterno("Perez");
        empleado.setApellidoMaterno("Lopez");
        empleado.setUser("EMP-001");

        Mesa mesa = new Mesa();
        mesa.setNumero(1);

        Comanda c = new Comanda();
        c.setEmpleado(empleado);
        c.setMesa(mesa);
        c.setNombreCliente("Test");
        c.setPedidos(new ArrayList<>());

        Comanda insertada = dao.insertarComanda(c);

        Comanda encontrada = dao.obtenerPorId(insertada.getId());

        assertNotNull(encontrada);
    }

    @Test
    public void obtenerPorId_inexistente() throws PersistenciaException {

        Comanda encontrada = dao.obtenerPorId(new ObjectId().toHexString());

        assertNull(encontrada);
    }

    @Test
    public void obtenerPorId_null() {
        assertThrows(PersistenciaException.class, () -> {
            dao.obtenerPorId(null);
        });
    }

    @Test
    public void agregarPedido_correcto() throws PersistenciaException {

        Empleado empleado = new Empleado();
        empleado.setNombres("Juan");
        empleado.setUser("EMP-001");

        Mesa mesa = new Mesa();
        mesa.setNumero(1);

        Comanda c = new Comanda();
        c.setEmpleado(empleado);
        c.setMesa(mesa);
        c.setNombreCliente("Test");
        c.setPedidos(new ArrayList<>());

        Comanda insertada = dao.insertarComanda(c);

        Pedido p = new Pedido();
        p.setCantidad(1);
        p.setPrecioProducto(100);

        boolean ok = dao.agregarPedidoAComanda(insertada.getId(), p);

        assertTrue(ok);
    }

    @Test
    public void eliminarComanda_correcto() throws PersistenciaException {

        Empleado empleado = new Empleado();
        empleado.setNombres("Juan");
        empleado.setUser("EMP-001");

        Mesa mesa = new Mesa();
        mesa.setNumero(1);

        Comanda c = new Comanda();
        c.setEmpleado(empleado);
        c.setMesa(mesa);
        c.setNombreCliente("Test");
        c.setPedidos(new ArrayList<>());

        Comanda insertada = dao.insertarComanda(c);

        boolean eliminado = dao.eliminarComanda(insertada.getId());

        assertTrue(eliminado);
    }

    @Test
    public void eliminarComanda_null() {
        assertThrows(PersistenciaException.class, () -> {
            dao.eliminarComanda(null);
        });
    }


    @Test
    public void calcularMonto_correcto() throws PersistenciaException {

        Pedido p1 = new Pedido();
        p1.setCantidad(2);
        p1.setPrecioProducto(100);

        Pedido p2 = new Pedido();
        p2.setCantidad(1);
        p2.setPrecioProducto(50);

        Empleado empleado = new Empleado();
        empleado.setNombres("Juan");
        empleado.setApellidoPaterno("Perez");
        empleado.setApellidoMaterno("Lopez");
        empleado.setUser("EMP-001");

        Mesa mesa = new Mesa();
        mesa.setNumero(1);

        Comanda c = new Comanda();
        c.setEmpleado(empleado);
        c.setMesa(mesa);
        c.setNombreCliente("Test");
        c.setPedidos(new ArrayList<>());

        c.setPedidos(List.of(p1, p2));

        Comanda insertada = dao.insertarComanda(c);

        float total = dao.calcularMontoComanda(insertada.getId());

        assertEquals(250f, total);
    }

    @Test
    public void calcularMonto_null() {
        assertThrows(PersistenciaException.class, () -> {
            dao.calcularMontoComanda(null);
        });
    }

    @Test
    public void recalcularMonto_correcto() throws PersistenciaException {

        Pedido p = new Pedido();
        p.setCantidad(2);
        p.setPrecioProducto(100);

        Empleado empleado = new Empleado();
        empleado.setNombres("Juan");
        empleado.setApellidoPaterno("Perez");
        empleado.setApellidoMaterno("Lopez");
        empleado.setUser("EMP-001");

        Mesa mesa = new Mesa();
        mesa.setNumero(1);

        Comanda c = new Comanda();
        c.setEmpleado(empleado);
        c.setMesa(mesa);
        c.setNombreCliente("Test");
        c.setPedidos(new ArrayList<>());

        c.setPedidos(List.of(p));

        Comanda insertada = dao.insertarComanda(c);

        dao.recalcularMonto(insertada.getId());

        float total = dao.calcularMontoComanda(insertada.getId());

        assertEquals(200f, total);
    }

    @Test
    public void recalcularMonto_null() {
        assertThrows(PersistenciaException.class, () -> {
            dao.recalcularMonto(null);
        });
    }
}
*/