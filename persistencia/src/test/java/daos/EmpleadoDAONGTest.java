/*
package daos;

import adaptadores.EmpleadoPersistenciaAdapter;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import conexion.ConexionMongo;
import entidades.Empleado;
import entidadesMongo.EmpleadoEntidadMongo;
import enums.EstadoEmpleado;
import enums.RolEmpleado;
import excepciones.PersistenciaException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class EmpleadoDAONGTest {

    private MongoCollection<EmpleadoEntidadMongo> coleccion =
            ConexionMongo.obtenerBaseDatos()
                    .getCollection("empleados", EmpleadoEntidadMongo.class);

    private EmpleadoDAO emDAO = new EmpleadoDAO();
    
    private EmpleadoPersistenciaAdapter adapter = new EmpleadoPersistenciaAdapter();

    @AfterEach
    public void limpiar() {
        coleccion.deleteMany(new Document());
    }

    private EmpleadoEntidadMongo insertarEmpleadoMongo(EmpleadoEntidadMongo emp) {

        InsertOneResult result = coleccion.insertOne(emp);

        String id = result.getInsertedId()
                .asObjectId()
                .getValue()
                .toHexString();

        emp.setId(id);

        return emp;
    }

    @Test
    public void testObtenerEmpleadoPorId_correcto() throws PersistenciaException {
        EmpleadoEntidadMongo emp1 = new EmpleadoEntidadMongo( null, "Alejandra", "Leal", "Armenta", "ME-001", RolEmpleado.MESERO.name(), EstadoEmpleado.ACTIVO.name() );
        EmpleadoEntidadMongo resultado = insertarEmpleadoMongo(emp1);

        Empleado consultado = emDAO.obtenerEmpleadoPorId(adapter.aDominio(resultado).getId());

        assertNotNull(consultado);
        assertEquals(emp1.getUser(), consultado.getUser());
        assertEquals(RolEmpleado.MESERO, consultado.getRol());
    }

    @Test
    public void testObtenerEmpleadoPorId_noEncontrado() {
        assertThrows(PersistenciaException.class, () -> {
            emDAO.obtenerEmpleadoPorId(new ObjectId().toHexString());
        });
    }
    
    @Test
    public void testObtenerEmpleadoPorId_idInvalido() {
        assertThrows(PersistenciaException.class, () -> {
            emDAO.obtenerEmpleadoPorId("123");
        });
    }
    
    @Test
    public void testObtenerEmpleadoPorUser_correcto() throws PersistenciaException {
        EmpleadoEntidadMongo emp = new EmpleadoEntidadMongo(
                null, "Alejandra", "Leal", "Armenta",
                "ME-001", RolEmpleado.MESERO.name(), EstadoEmpleado.ACTIVO.name()
        );

        insertarEmpleadoMongo(emp);

        Empleado consultado = emDAO.obtenerEmpleadoPorUser("ME-001");

        assertNotNull(consultado);
        assertEquals("ME-001", consultado.getUser());
    }
    
    @Test
    public void testObtenerEmpleadoPorUser_noEncontrado() {
        assertThrows(PersistenciaException.class, () -> {
            emDAO.obtenerEmpleadoPorUser("NO_EXISTE");
        });
    }
    
    @Test
    public void testObtenerEmpleadoPorUser_invalido() {
        assertThrows(PersistenciaException.class, () -> {
            emDAO.obtenerEmpleadoPorUser("");
        });
    }
    
    @Test
    public void testActualizarEstadoEmpleado_correcto() throws PersistenciaException {
        EmpleadoEntidadMongo emp1 = new EmpleadoEntidadMongo( null, "Alejandra", "Leal", "Armenta", "ME-001", RolEmpleado.MESERO.name(), EstadoEmpleado.ACTIVO.name() );
        EmpleadoEntidadMongo resultado = insertarEmpleadoMongo(emp1);
     
        emDAO.actualizarEstadoEmpleado(adapter.aDominio(resultado), EstadoEmpleado.INACTIVO);

        Empleado actualizado = emDAO.obtenerEmpleadoPorId(adapter.aDominio(resultado).getId());

        assertEquals(EstadoEmpleado.INACTIVO, actualizado.getEstado());
    }
    
    @Test
    public void testActualizarEstadoEmpleado_estadoNull() throws PersistenciaException {
        EmpleadoEntidadMongo emp1 = new EmpleadoEntidadMongo( null, "Alejandra", "Leal", "Armenta", "ME-001", RolEmpleado.MESERO.name(), EstadoEmpleado.ACTIVO.name() );
        EmpleadoEntidadMongo resultado = insertarEmpleadoMongo(emp1);
        assertThrows(PersistenciaException.class, () -> {
            emDAO.actualizarEstadoEmpleado(adapter.aDominio(resultado), null);
        });
    }
    
    @Test
    public void testActualizarEstadoEmpleado_empleadoNull() throws PersistenciaException {
        assertThrows(PersistenciaException.class, () -> {
            emDAO.actualizarEstadoEmpleado(null, EstadoEmpleado.INACTIVO);
        });
    }
    
    @Test
    public void testObtenerMeserosActivos_correcto() throws PersistenciaException{
        EmpleadoEntidadMongo emp1 = new EmpleadoEntidadMongo(null, "Alejandra", "Leal", "Armenta", "ME-001", RolEmpleado.MESERO.name(), EstadoEmpleado.INACTIVO.name());
        EmpleadoEntidadMongo emp2 = new EmpleadoEntidadMongo(null, "Carlos", "Ramírez", "López", "ME-002", RolEmpleado.COCINERO.name(), EstadoEmpleado.ACTIVO.name());
        EmpleadoEntidadMongo emp3 = new EmpleadoEntidadMongo(null, "Fernanda", "García", "Soto", "ME-003", RolEmpleado.MESERO.name(), EstadoEmpleado.INACTIVO.name());
        EmpleadoEntidadMongo emp4 = new EmpleadoEntidadMongo(null, "Luis", "Hernández", "Morales", "ME-004", RolEmpleado.MESERO.name(), EstadoEmpleado.ACTIVO.name());
        EmpleadoEntidadMongo emp5 = new EmpleadoEntidadMongo(null, "Sofía", "Vázquez", "Torres", "ME-005", RolEmpleado.MESERO.name(), EstadoEmpleado.ACTIVO.name());
        
        insertarEmpleadoMongo(emp1);
        insertarEmpleadoMongo(emp2);
        insertarEmpleadoMongo(emp3);
        insertarEmpleadoMongo(emp4);
        insertarEmpleadoMongo(emp5);
        
        List<Empleado> activos = emDAO.obtenerMeserosActivos();
        
        assertNotNull(activos);
        assertEquals(2, activos.size()); 
    }
    
    @Test
    public void testObtenerMeserosActivos_sinActivos() throws PersistenciaException{
        EmpleadoEntidadMongo emp1 = new EmpleadoEntidadMongo(null, "Alejandra", "Leal", "Armenta", "ME-001", RolEmpleado.MESERO.name(), EstadoEmpleado.INACTIVO.name());
        EmpleadoEntidadMongo emp2 = new EmpleadoEntidadMongo(null, "Carlos", "Ramírez", "López", "ME-002", RolEmpleado.MESERO.name(), EstadoEmpleado.INACTIVO.name());
        EmpleadoEntidadMongo emp3 = new EmpleadoEntidadMongo(null, "Fernanda", "García", "Soto", "ME-003", RolEmpleado.MESERO.name(), EstadoEmpleado.INACTIVO.name());
        EmpleadoEntidadMongo emp4 = new EmpleadoEntidadMongo(null, "Luis", "Hernández", "Morales", "ME-004", RolEmpleado.MESERO.name(), EstadoEmpleado.INACTIVO.name());
        EmpleadoEntidadMongo emp5 = new EmpleadoEntidadMongo(null, "Sofía", "Vázquez", "Torres", "ME-005", RolEmpleado.MESERO.name(), EstadoEmpleado.INACTIVO.name());
        
        insertarEmpleadoMongo(emp1);
        insertarEmpleadoMongo(emp2);
        insertarEmpleadoMongo(emp3);
        insertarEmpleadoMongo(emp4);
        insertarEmpleadoMongo(emp5);
        
        List<Empleado> activos = emDAO.obtenerMeserosActivos();
        
        assertNotNull(activos);
        assertEquals(0, activos.size()); 
    }

    @Test
    public void testBuscarMeserosPorUserNombre_correcto() throws PersistenciaException {

        EmpleadoEntidadMongo emp1 = new EmpleadoEntidadMongo( null, "Alejandra", "Leal", "Armenta", "ale123", RolEmpleado.MESERO.name(), EstadoEmpleado.ACTIVO.name() );

        EmpleadoEntidadMongo emp2 = new EmpleadoEntidadMongo( null, "Carlos", "Ramirez", "Lopez", "carlos99", RolEmpleado.MESERO.name(), EstadoEmpleado.ACTIVO.name() );

        insertarEmpleadoMongo(emp1);
        insertarEmpleadoMongo(emp2);

        List<Empleado> resultado = emDAO.buscarMeserosPorUserNombre("ale");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("ale123", resultado.get(0).getUser());
    }

    @Test
    public void testBuscarMeserosPorUserNombre_sinCoincidencias() throws PersistenciaException {

        EmpleadoEntidadMongo emp1 = new EmpleadoEntidadMongo(
                null,
                "Luis",
                "Hernandez",
                "Morales",
                "luis01",
                RolEmpleado.MESERO.name(),
                EstadoEmpleado.ACTIVO.name()
        );

        insertarEmpleadoMongo(emp1);

        List<Empleado> resultado =
                emDAO.buscarMeserosPorUserNombre("zzz");

        assertNotNull(resultado);
        assertEquals(0, resultado.size());
    }
}
*/
