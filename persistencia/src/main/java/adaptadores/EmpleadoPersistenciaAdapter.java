package adaptadores;

import entidades.Empleado;
import entidadesMongo.EmpleadoEntidadMongo;
import enums.EstadoEmpleado;
import enums.RolEmpleado;

/**
 * Adaptador de persistencia para la entidad Empleado.
 * 
 * Esta clase se encarga de convertir objetos del modelo de dominio (Empleado)
 * a entidades de MongoDB (EmpleadoEntidadMongo) y viceversa.
 * 
 * Su propósito es desacoplar la capa de negocio de la capa de persistencia,
 * permitiendo una conversión clara entre ambos modelos.
 * 
 * @author DishUp
 */
public class EmpleadoPersistenciaAdapter {

    /**
     * Constructor por defecto del adaptador.
     */
    public EmpleadoPersistenciaAdapter() {
    }

    /**
     * Convierte un Empleado del modelo de dominio a una entidad MongoDB.
     *
     * @param empleado objeto del dominio a convertir
     * @return entidad EmpleadoEntidadMongo o null si el empleado es null
     */
    public EmpleadoEntidadMongo aMongo(Empleado empleado) {

        if (empleado == null) {
            return null;
        }

        EmpleadoEntidadMongo mongo = new EmpleadoEntidadMongo();

        mongo.setId(empleado.getId());
        mongo.setNombres(empleado.getNombres());
        mongo.setApellidoPaterno(empleado.getApellidoPaterno());
        mongo.setApellidoMaterno(empleado.getApellidoMaterno());
        mongo.setUser(empleado.getUser());
        mongo.setRol(empleado.getRol().name());
        mongo.setEstado(empleado.getEstado().name());

        return mongo;
    }

    /**
     * Convierte una entidad MongoDB a un Empleado del modelo de dominio.
     *
     * @param mongo entidad EmpleadoEntidadMongo a convertir
     * @return objeto Empleado del dominio o null si la entidad es null
     */
    public Empleado aDominio(EmpleadoEntidadMongo mongo) {

        if (mongo == null) {
            return null;
        }

        Empleado empleado = new Empleado();

        empleado.setId(mongo.getId());
        empleado.setNombres(mongo.getNombres());
        empleado.setApellidoPaterno(mongo.getApellidoPaterno());
        empleado.setApellidoMaterno(mongo.getApellidoMaterno());
        empleado.setUser(mongo.getUser());
        empleado.setRol(RolEmpleado.valueOf(mongo.getRol().toUpperCase()));
        empleado.setEstado(EstadoEmpleado.valueOf(mongo.getEstado().toUpperCase()));

        return empleado;
    }
}