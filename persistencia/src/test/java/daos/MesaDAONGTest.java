/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
/*
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import conexion.ConexionMongo;
import entidades.Empleado;
import entidades.Mesa;
import entidadesMongo.EmpleadoEntidadMongo;
import enums.EstadoEmpleado;
import enums.EstadoMesa;
import enums.RolEmpleado;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author DishUp
 */

/*
public class MesaDAONGTest {

    @AfterEach
    public void limpiar() {
        ConexionMongo.obtenerBaseDatos()
                .getCollection("mesas")
                .deleteMany(new Document());

        ConexionMongo.obtenerBaseDatos()
                .getCollection("empleados")
                .deleteMany(new Document());
    }
    @Test
    public void testObtenerMesasPorMesero_correcto() throws PersistenciaException {

        MesaDAO mdao = new MesaDAO();

        MongoCollection<EmpleadoEntidadMongo> coleccion
                = ConexionMongo.obtenerBaseDatos()
                        .getCollection("empleados", EmpleadoEntidadMongo.class);

        EmpleadoEntidadMongo empleadoMongo = new EmpleadoEntidadMongo();

        empleadoMongo.setNombres("Alejandra");
        empleadoMongo.setApellidoPaterno("Leal");
        empleadoMongo.setApellidoMaterno("Armenta");
        empleadoMongo.setUser("ME-001");
        empleadoMongo.setRol(RolEmpleado.MESERO.name());
        empleadoMongo.setEstado(EstadoEmpleado.ACTIVO.name());

        InsertOneResult resultado = coleccion.insertOne(empleadoMongo);

        String idMesero = resultado.getInsertedId()
                .asObjectId()
                .getValue()
                .toHexString();

        Mesa mesa1 = new Mesa(1, EstadoMesa.LIBRE);
        mesa1.setIdMesero(idMesero);

        Mesa mesa2 = new Mesa(2, EstadoMesa.LIBRE);
        mesa2.setIdMesero(idMesero);

        mdao.insertarMesa(mesa1);
        mdao.insertarMesa(mesa2);

        List<Mesa> mesas = mdao.obtenerMesasPorMesero(idMesero);

        assertEquals(mesas.get(0).getIdMesero(), idMesero);
        assertEquals(mesas.get(1).getIdMesero(), idMesero);

        assertEquals(mesas.size(), 2);
    }
    
    @Test
    public void testObtenerMesasPorMesero_sinMesas() throws PersistenciaException {

        MesaDAO mdao = new MesaDAO();

        MongoCollection<EmpleadoEntidadMongo> coleccion
                = ConexionMongo.obtenerBaseDatos()
                        .getCollection("empleados", EmpleadoEntidadMongo.class);

        EmpleadoEntidadMongo empleadoMongo = new EmpleadoEntidadMongo();

        empleadoMongo.setNombres("Alejandra");
        empleadoMongo.setApellidoPaterno("Leal");
        empleadoMongo.setApellidoMaterno("Armenta");
        empleadoMongo.setUser("ME-001");
        empleadoMongo.setRol(RolEmpleado.MESERO.name());
        empleadoMongo.setEstado(EstadoEmpleado.ACTIVO.name());

        InsertOneResult resultado = coleccion.insertOne(empleadoMongo);

        String idMesero = resultado.getInsertedId()
                .asObjectId()
                .getValue()
                .toHexString();

        List<Mesa> mesas = mdao.obtenerMesasPorMesero(idMesero);
        assertEquals(0, mesas.size());
    } 
    
    @Test
    public void insertarMesa_correcto() throws PersistenciaException {
        MesaDAO mdao = new MesaDAO();

        Mesa mesa1 = new Mesa(1, EstadoMesa.LIBRE);
        Mesa mesa2 = new Mesa(2, EstadoMesa.LIBRE);

        mdao.insertarMesa(mesa1);
        mdao.insertarMesa(mesa2);

        Mesa mesa1Obtenida = mdao.obtenerMesaPorId(mesa1.getId());
        Mesa mesa2Obtenida = mdao.obtenerMesaPorId(mesa2.getId());

        assertNotNull(mesa1Obtenida);
        assertNotNull(mesa2Obtenida);
    }

    @Test
    public void insertarMesa_mesaNull() throws PersistenciaException {
        MesaDAO mdao = new MesaDAO();

        assertThrows(PersistenciaException.class, () -> {
            mdao.insertarMesa(null);
        });
    }

    @Test
    public void testEliminarMesa_correcto() throws PersistenciaException {

        MesaDAO mdao = new MesaDAO();

        Mesa mesa = new Mesa(1, EstadoMesa.LIBRE);

        mdao.insertarMesa(mesa);

        Mesa mesaObtenida = mdao.obtenerMesaPorId(mesa.getId());

        assertNotNull(mesaObtenida);

        mdao.eliminarMesa(mesa);

        assertThrows(PersistenciaException.class, () -> {
            mdao.obtenerMesaPorId(mesa.getId());
        });
    }

    @Test
    public void testEliminarMesa_mesaNull() throws PersistenciaException {

        MesaDAO mdao = new MesaDAO();

        assertThrows(PersistenciaException.class, () -> {
            mdao.eliminarMesa(null);
        });
    }

    @Test
    public void testEliminarMesa_mesaNoEncontrada() throws PersistenciaException {

        MesaDAO mdao = new MesaDAO();

        Mesa mesa = new Mesa(1, EstadoMesa.LIBRE);

        assertThrows(PersistenciaException.class, () -> {
            mdao.eliminarMesa(mesa);
        });
    }

    @Test
    public void testObtenerMesaPorId_correcto() throws PersistenciaException {

        MesaDAO mdao = new MesaDAO();

        Mesa mesa = new Mesa(1, EstadoMesa.LIBRE);

        mdao.insertarMesa(mesa);

        Mesa mesaObtenida = mdao.obtenerMesaPorId(mesa.getId());

        assertNotNull(mesaObtenida);
        assertEquals(mesa.getId(), mesaObtenida.getId());
    }

    @Test
    public void testObtenerMesaPorId_mesaNull() throws PersistenciaException {

        MesaDAO mdao = new MesaDAO();

        assertThrows(PersistenciaException.class, () -> {
            mdao.obtenerMesaPorId(null);
        });
    }

    @Test
    public void testObtenerMesaPorId_mesaNoEncontrada() throws PersistenciaException {

        MesaDAO mdao = new MesaDAO();

        Mesa mesa = new Mesa(1, EstadoMesa.LIBRE);

        assertThrows(PersistenciaException.class, () -> {
            mdao.obtenerMesaPorId(mesa.getId());
        });
    }
    
    @Test
    public void testObtenerMesaPorNumero_noExiste() throws PersistenciaException {
        MesaDAO mdao = new MesaDAO();
        Mesa mesa = mdao.obtenerMesaPorNumero(999);

        assertNull(mesa);
    }
    
    @Test
    public void testObtenerMesaPorNumero_null() {
        MesaDAO mdao = new MesaDAO();
        assertThrows(PersistenciaException.class, () -> {
            mdao.obtenerMesaPorNumero(null);
        });
    }
    
    @Test
    public void testAsignarMessaAMesero_correcto() throws PersistenciaException {

        MesaDAO mdao = new MesaDAO();

        MongoCollection<EmpleadoEntidadMongo> coleccion
                = ConexionMongo.obtenerBaseDatos()
                        .getCollection("empleados", EmpleadoEntidadMongo.class);

        EmpleadoEntidadMongo empleadoMongo = new EmpleadoEntidadMongo();

        empleadoMongo.setNombres("Alejandra");
        empleadoMongo.setApellidoPaterno("Leal");
        empleadoMongo.setApellidoMaterno("Armenta");
        empleadoMongo.setUser("ME-001");
        empleadoMongo.setRol(RolEmpleado.MESERO.name());
        empleadoMongo.setEstado(EstadoEmpleado.ACTIVO.name());

        InsertOneResult resultado = coleccion.insertOne(empleadoMongo);

        String idMesero = resultado.getInsertedId()
                .asObjectId()
                .getValue()
                .toHexString();
        
        Empleado empleado = new Empleado(
                idMesero,
                "Alejandra",
                "Leal",
                "Armenta",
                "ME-001",
                RolEmpleado.MESERO,
                EstadoEmpleado.ACTIVO
        );

        Mesa mesa = new Mesa(1, EstadoMesa.LIBRE);
        mdao.insertarMesa(mesa);
        
        List<Mesa> mesas = new ArrayList<>();
        mesas.add(mesa);
        
        mdao.asignarMesasAMesero(mesas, empleado);
        Mesa mesaObtenida = mdao.obtenerMesaPorId(mesa.getId());

        assertEquals(idMesero, mesaObtenida.getIdMesero());
        assertEquals(1, mesas.size());
    }
    
    @Test
    public void testAsignarMesasAMesero_mesasNull() throws PersistenciaException {

        MesaDAO mdao = new MesaDAO();

        MongoCollection<EmpleadoEntidadMongo> coleccion
                = ConexionMongo.obtenerBaseDatos()
                        .getCollection("empleados", EmpleadoEntidadMongo.class);

        EmpleadoEntidadMongo empleadoMongo = new EmpleadoEntidadMongo();

        empleadoMongo.setNombres("Alejandra");
        empleadoMongo.setApellidoPaterno("Leal");
        empleadoMongo.setApellidoMaterno("Armenta");
        empleadoMongo.setUser("ME-001");
        empleadoMongo.setRol(RolEmpleado.MESERO.name());
        empleadoMongo.setEstado(EstadoEmpleado.ACTIVO.name());

        InsertOneResult resultado = coleccion.insertOne(empleadoMongo);

        String idMesero = resultado.getInsertedId()
                .asObjectId()
                .getValue()
                .toHexString();
        
        Empleado empleado = new Empleado(
                idMesero,
                "Alejandra",
                "Leal",
                "Armenta",
                "ME-001",
                RolEmpleado.MESERO,
                EstadoEmpleado.ACTIVO
        );
        assertThrows(PersistenciaException.class, () ->{
            mdao.asignarMesasAMesero(null, empleado);
        });
    }
    
    @Test
    public void testAsignarMesasAMesero_empleadoNull() throws PersistenciaException {

        MesaDAO mdao = new MesaDAO();

        MongoCollection<EmpleadoEntidadMongo> coleccion
                = ConexionMongo.obtenerBaseDatos()
                        .getCollection("empleados", EmpleadoEntidadMongo.class);


        Mesa mesa = new Mesa(1, EstadoMesa.LIBRE);
        mdao.insertarMesa(mesa);
        
        List<Mesa> mesas = new ArrayList<>();
        mesas.add(mesa);
        
        assertThrows(PersistenciaException.class, () ->{
            mdao.asignarMesasAMesero(mesas, null);
        });
    }
    
    @Test
    public void testObtenerMesasDisponibles_correcto() throws PersistenciaException{
        MesaDAO mdao = new MesaDAO();
        
        MongoCollection<EmpleadoEntidadMongo> coleccion
                = ConexionMongo.obtenerBaseDatos()
                        .getCollection("empleados", EmpleadoEntidadMongo.class);

        EmpleadoEntidadMongo empleadoMongo = new EmpleadoEntidadMongo();

        empleadoMongo.setNombres("Alejandra");
        empleadoMongo.setApellidoPaterno("Leal");
        empleadoMongo.setApellidoMaterno("Armenta");
        empleadoMongo.setUser("ME-001");
        empleadoMongo.setRol(RolEmpleado.MESERO.name());
        empleadoMongo.setEstado(EstadoEmpleado.ACTIVO.name());

        InsertOneResult resultado = coleccion.insertOne(empleadoMongo);

        String idMesero = resultado.getInsertedId()
                .asObjectId()
                .getValue()
                .toHexString();
        

        Mesa mesa1 = new Mesa(1, EstadoMesa.LIBRE);
        Mesa mesa2 = new Mesa(2, EstadoMesa.OCUPADA);
        Mesa mesa3 = new Mesa(3, EstadoMesa.LIBRE);
        Mesa mesa4 = new Mesa(4, EstadoMesa.OCUPADA);
        Mesa mesa5 = new Mesa(5, EstadoMesa.LIBRE);
        
        mesa1.setIdMesero(idMesero);
        mesa3.setIdMesero(idMesero);
        
        mdao.insertarMesa(mesa1);
        mdao.insertarMesa(mesa2);
        mdao.insertarMesa(mesa3);
        mdao.insertarMesa(mesa4);
        mdao.insertarMesa(mesa5);
        
        List<Mesa> disponibles = mdao.obtenerMesasDisponibles();
        
        assertEquals(3, disponibles.size());
    }
    
    @Test
    public void testObtenerMesasDisponibles_sinMesas() throws PersistenciaException{
        MesaDAO mdao = new MesaDAO();
        
        MongoCollection<EmpleadoEntidadMongo> coleccion
                = ConexionMongo.obtenerBaseDatos()
                        .getCollection("empleados", EmpleadoEntidadMongo.class);
        
        List<Mesa> disponibles = mdao.obtenerMesas();
        
        assertEquals(0, disponibles.size());
    }
    
    @Test
    public void testObtenerMesasDisponibles_sinMesasDisponibles() throws PersistenciaException{
        MesaDAO mdao = new MesaDAO();
        
        MongoCollection<EmpleadoEntidadMongo> coleccion
                = ConexionMongo.obtenerBaseDatos()
                        .getCollection("empleados", EmpleadoEntidadMongo.class);

        EmpleadoEntidadMongo empleadoMongo = new EmpleadoEntidadMongo();

        empleadoMongo.setNombres("Alejandra");
        empleadoMongo.setApellidoPaterno("Leal");
        empleadoMongo.setApellidoMaterno("Armenta");
        empleadoMongo.setUser("ME-001");
        empleadoMongo.setRol(RolEmpleado.MESERO.name());
        empleadoMongo.setEstado(EstadoEmpleado.ACTIVO.name());

        InsertOneResult resultado = coleccion.insertOne(empleadoMongo);

        String idMesero = resultado.getInsertedId()
                .asObjectId()
                .getValue()
                .toHexString();
        

        Mesa mesa1 = new Mesa(1, EstadoMesa.LIBRE);
        Mesa mesa2 = new Mesa(2, EstadoMesa.OCUPADA);
        Mesa mesa3 = new Mesa(3, EstadoMesa.LIBRE);
        Mesa mesa4 = new Mesa(4, EstadoMesa.OCUPADA);
        Mesa mesa5 = new Mesa(5, EstadoMesa.LIBRE);
        
        mesa1.setIdMesero(idMesero);
        mesa2.setIdMesero(idMesero);
        mesa3.setIdMesero(idMesero);
        mesa4.setIdMesero(idMesero);
        mesa5.setIdMesero(idMesero);
        
        mdao.insertarMesa(mesa1);
        mdao.insertarMesa(mesa2);
        mdao.insertarMesa(mesa3);
        mdao.insertarMesa(mesa4);
        mdao.insertarMesa(mesa5);
        
        List<Mesa> disponibles = mdao.obtenerMesasDisponibles();
        
        assertEquals(0, disponibles.size());
    }
    
    @Test
    public void testDesasignarMesasAMesero_correcto() throws PersistenciaException{
        MesaDAO mdao = new MesaDAO();
        EmpleadoDAO edao = new EmpleadoDAO();
        
        MongoCollection<EmpleadoEntidadMongo> coleccion
                = ConexionMongo.obtenerBaseDatos()
                        .getCollection("empleados", EmpleadoEntidadMongo.class);

        EmpleadoEntidadMongo empleadoMongo = new EmpleadoEntidadMongo();

        empleadoMongo.setNombres("Alejandra");
        empleadoMongo.setApellidoPaterno("Leal");
        empleadoMongo.setApellidoMaterno("Armenta");
        empleadoMongo.setUser("ME-001");
        empleadoMongo.setRol(RolEmpleado.MESERO.name());
        empleadoMongo.setEstado(EstadoEmpleado.ACTIVO.name());

        InsertOneResult resultado = coleccion.insertOne(empleadoMongo);

        String idMesero = resultado.getInsertedId()
                .asObjectId()
                .getValue()
                .toHexString();
        
        Mesa mesa1 = new Mesa(1, EstadoMesa.LIBRE);
        Mesa mesa2 = new Mesa(2, EstadoMesa.OCUPADA);
        Mesa mesa3 = new Mesa(3, EstadoMesa.LIBRE);
        Mesa mesa4 = new Mesa(4, EstadoMesa.LIBRE);
        Mesa mesa5 = new Mesa(5, EstadoMesa.LIBRE);
        
        mesa1.setIdMesero(idMesero);
        mesa2.setIdMesero(idMesero);
        mesa3.setIdMesero(idMesero);
        mesa5.setIdMesero(idMesero);
        
        mdao.insertarMesa(mesa1);
        mdao.insertarMesa(mesa2);
        mdao.insertarMesa(mesa3);
        mdao.insertarMesa(mesa4);
        mdao.insertarMesa(mesa5);
        
        List<Mesa> disponibles = mdao.obtenerMesasPorMesero(idMesero);
        Empleado mesero = edao.obtenerEmpleadoPorId(idMesero);
        
        assertEquals(4, disponibles.size());
        
        mdao.desasignarMesasAMesero(disponibles, mesero);
        
        List<Mesa> sinMesero = mdao.obtenerMesasPorMesero(idMesero);
        
        assertEquals(0, sinMesero.size()); 
    }
    
    @Test
    public void testDesasignarMesasAMesero_mesasNull() throws PersistenciaException{
        MesaDAO mdao = new MesaDAO();
        EmpleadoDAO edao = new EmpleadoDAO();
        
        MongoCollection<EmpleadoEntidadMongo> coleccion
                = ConexionMongo.obtenerBaseDatos()
                        .getCollection("empleados", EmpleadoEntidadMongo.class);

        EmpleadoEntidadMongo empleadoMongo = new EmpleadoEntidadMongo();

        empleadoMongo.setNombres("Alejandra");
        empleadoMongo.setApellidoPaterno("Leal");
        empleadoMongo.setApellidoMaterno("Armenta");
        empleadoMongo.setUser("ME-001");
        empleadoMongo.setRol(RolEmpleado.MESERO.name());
        empleadoMongo.setEstado(EstadoEmpleado.ACTIVO.name());

        InsertOneResult resultado = coleccion.insertOne(empleadoMongo);

        String idMesero = resultado.getInsertedId()
                .asObjectId()
                .getValue()
                .toHexString();
        
        Empleado mesero = edao.obtenerEmpleadoPorId(idMesero);
        
        assertThrows(PersistenciaException.class, () -> {
            mdao.desasignarMesasAMesero(null, mesero);
        });
    }

    @Test
    public void testDesasignarMesasAMesero_meseroNull() throws PersistenciaException{
        MesaDAO mdao = new MesaDAO();
        
        MongoCollection<EmpleadoEntidadMongo> coleccion
                = ConexionMongo.obtenerBaseDatos()
                        .getCollection("empleados", EmpleadoEntidadMongo.class);

        EmpleadoEntidadMongo empleadoMongo = new EmpleadoEntidadMongo();

        empleadoMongo.setNombres("Alejandra");
        empleadoMongo.setApellidoPaterno("Leal");
        empleadoMongo.setApellidoMaterno("Armenta");
        empleadoMongo.setUser("ME-001");
        empleadoMongo.setRol(RolEmpleado.MESERO.name());
        empleadoMongo.setEstado(EstadoEmpleado.ACTIVO.name());

        InsertOneResult resultado = coleccion.insertOne(empleadoMongo);

        String idMesero = resultado.getInsertedId()
                .asObjectId()
                .getValue()
                .toHexString();
        
        Mesa mesa1 = new Mesa(1, EstadoMesa.LIBRE);
        Mesa mesa2 = new Mesa(2, EstadoMesa.OCUPADA);
        Mesa mesa3 = new Mesa(3, EstadoMesa.LIBRE);
        Mesa mesa4 = new Mesa(4, EstadoMesa.LIBRE);
        Mesa mesa5 = new Mesa(5, EstadoMesa.LIBRE);
        
        mesa1.setIdMesero(idMesero);
        mesa2.setIdMesero(idMesero);
        mesa3.setIdMesero(idMesero);
        mesa5.setIdMesero(idMesero);
        
        mdao.insertarMesa(mesa1);
        mdao.insertarMesa(mesa2);
        mdao.insertarMesa(mesa3);
        mdao.insertarMesa(mesa4);
        mdao.insertarMesa(mesa5);
        
        List<Mesa> disponibles = mdao.obtenerMesasPorMesero(idMesero);
        
        assertThrows(PersistenciaException.class, () -> {
            mdao.desasignarMesasAMesero(disponibles, null);
        });
    }

}
*/
