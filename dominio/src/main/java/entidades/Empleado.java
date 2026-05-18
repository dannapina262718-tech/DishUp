package entidades;

import enums.EstadoEmpleado;
import enums.RolEmpleado;

/**
 * Empleado.
 * Representa un empleado dentro del sistema en la capa de dominio.
 *
 * Esta entidad almacena la informacion personal y laboral de un empleado,
 * incluyendo su nombre completo, usuario, rol dentro del sistema
 * y su estado actual (activo o inactivo).
 *
 * Es una entidad clave para el control de acceso y asignacion de tareas
 * dentro del sistema del restaurante.
 *
 * @author DishUp
 */
public class Empleado {

    private String id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String user;
    private RolEmpleado rol;
    private EstadoEmpleado estado;

    /**
     * Constructor por defecto.
     */
    public Empleado() {
    }

    /**
     * Constructor que inicializa todos los atributos del empleado incluyendo id.
     *
     * @param id identificador del empleado
     * @param nombres nombres del empleado
     * @param apellidoPaterno apellido paterno
     * @param apellidoMaterno apellido materno
     * @param user usuario del sistema
     * @param rol rol del empleado dentro del sistema
     * @param estado estado actual del empleado
     */
    public Empleado(String id, String nombres, String apellidoPaterno,
                    String apellidoMaterno, String user, RolEmpleado rol,
                    EstadoEmpleado estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.user = user;
        this.rol = rol;
        this.estado = estado;
    }

    /**
     * Constructor sin id, usado generalmente para creación de nuevos empleados.
     *
     * @param nombres nombres del empleado
     * @param apellidoPaterno apellido paterno
     * @param apellidoMaterno apellido materno
     * @param user usuario del sistema
     * @param rol rol del empleado
     * @param estado estado del empleado
     */
    public Empleado(String nombres, String apellidoPaterno,
                    String apellidoMaterno, String user,
                    RolEmpleado rol, EstadoEmpleado estado) {
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
     * @param id nuevo id del empleado
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
     * @param nombres nuevos nombres
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
     * @param apellidoPaterno nuevo apellido paterno
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
     * @param apellidoMaterno nuevo apellido materno
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el nombre de usuario del empleado.
     *
     * @return usuario del sistema
     */
    public String getUser() {
        return user;
    }

    /**
     * Establece el usuario del empleado.
     *
     * @param user nuevo usuario
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Obtiene el rol del empleado.
     *
     * @return rol del empleado
     */
    public RolEmpleado getRol() {
        return rol;
    }

    /**
     * Establece el rol del empleado.
     *
     * @param rol nuevo rol
     */
    public void setRol(RolEmpleado rol) {
        this.rol = rol;
    }

    /**
     * Obtiene el estado del empleado.
     *
     * @return estado del empleado
     */
    public EstadoEmpleado getEstado() {
        return estado;
    }

    /**
     * Establece el estado del empleado.
     *
     * @param estado nuevo estado
     */
    public void setEstado(EstadoEmpleado estado) {
        this.estado = estado;
    }
}