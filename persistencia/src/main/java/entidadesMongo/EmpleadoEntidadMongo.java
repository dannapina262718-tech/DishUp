package entidadesMongo;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 * Entidad de persistencia Empleado para MongoDB.
 * 
 * Esta clase representa cómo se almacena un empleado dentro de la base de datos.
 * Contiene información básica del empleado como nombres, apellidos, usuario,
 * rol y estado.
 * 
 * Se utiliza únicamente en la capa de persistencia y no debe contener lógica de negocio.
 */
public class EmpleadoEntidadMongo {

    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;

    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String user;
    private String rol;
    private String estado;

    /**
     * Constructor vacío requerido por MongoDB.
     */
    public EmpleadoEntidadMongo() {
    }

    /**
     * Constructor completo de EmpleadoEntidadMongo.
     *
     * @param id identificador único del empleado
     * @param nombres nombres del empleado
     * @param apellidoPaterno apellido paterno
     * @param apellidoMaterno apellido materno
     * @param user nombre de usuario del sistema
     * @param rol rol del empleado (ej. MESERO, ADMIN, etc.)
     * @param estado estado del empleado (activo, inactivo, etc.)
     */
    public EmpleadoEntidadMongo(String id, String nombres, String apellidoPaterno,
                               String apellidoMaterno, String user, String rol,
                               String estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.user = user;
        this.rol = rol;
        this.estado = estado;
    }

    /**
     * Obtiene el identificador del empleado.
     *
     * @return id del empleado
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del empleado.
     *
     * @param id identificador del empleado
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene los nombres del empleado.
     *
     * @return nombres del empleado
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del empleado.
     *
     * @param nombres nombres del empleado
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene el apellido paterno del empleado.
     *
     * @return apellido paterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del empleado.
     *
     * @param apellidoPaterno apellido paterno
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del empleado.
     *
     * @return apellido materno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del empleado.
     *
     * @param apellidoMaterno apellido materno
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el usuario del sistema del empleado.
     *
     * @return usuario del empleado
     */
    public String getUser() {
        return user;
    }

    /**
     * Establece el usuario del sistema del empleado.
     *
     * @param user usuario del empleado
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Obtiene el rol del empleado.
     *
     * @return rol del empleado
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del empleado.
     *
     * @param rol rol del empleado
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Obtiene el estado del empleado.
     *
     * @return estado del empleado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del empleado.
     *
     * @param estado estado del empleado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}