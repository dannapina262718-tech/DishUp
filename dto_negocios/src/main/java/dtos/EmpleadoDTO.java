package dtos;

import enums.EstadoEmpleadoDTO;
import enums.RolEmpleadoDTO;

/**
 * EmpleadoDTO.
 * Representa la informacion de un empleado dentro del sistema.
 *
 * Este DTO se utiliza para transportar los datos de un empleado
 * entre las distintas capas de la aplicacion.
 *
 * Contiene informacion personal del empleado como nombres,
 * apellidos, usuario, rol y estado actual dentro del sistema.
 *
 * @author DishUp
 */
public class EmpleadoDTO {
    
    private String id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;

    private String user;
    private RolEmpleadoDTO rol;
    private EstadoEmpleadoDTO estado;

    /**
     * Constructor que permite inicializar todos los atributos del empleado.
     *
     * @param id Identificador del empleado.
     * @param nombres Nombres del empleado.
     * @param apellidoPaterno Apellido paterno del empleado.
     * @param apellidoMaterno Apellido materno del empleado.
     * @param user Nombre de usuario del empleado.
     * @param rol Rol asignado al empleado.
     * @param estado Estado actual del empleado.
     */
    public EmpleadoDTO(String id, String nombres, String apellidoPaterno, String apellidoMaterno, String user, RolEmpleadoDTO rol, EstadoEmpleadoDTO estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.user = user;
        this.rol = rol;
        this.estado = estado;
    }

    /**
     * Constructor que permite inicializar los atributos principales
     * del empleado sin incluir el identificador.
     *
     * @param nombres Nombres del empleado.
     * @param apellidoPaterno Apellido paterno del empleado.
     * @param apellidoMaterno Apellido materno del empleado.
     * @param user Nombre de usuario del empleado.
     * @param rol Rol asignado al empleado.
     * @param estado Estado actual del empleado.
     */
    public EmpleadoDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String user, RolEmpleadoDTO rol, EstadoEmpleadoDTO estado) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.user = user;
        this.rol = rol;
        this.estado = estado;
    }

    /**
     * Constructor por defecto.
     */
    public EmpleadoDTO() {
    }
    
    /**
     * Obtiene el identificador del empleado.
     *
     * @return Identificador del empleado.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del empleado.
     *
     * @param id Nuevo identificador del empleado.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene los nombres del empleado.
     *
     * @return Nombres del empleado.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del empleado.
     *
     * @param nombres Nuevos nombres del empleado.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene el apellido paterno del empleado.
     *
     * @return Apellido paterno del empleado.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del empleado.
     *
     * @param apellidoPaterno Nuevo apellido paterno del empleado.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del empleado.
     *
     * @return Apellido materno del empleado.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del empleado.
     *
     * @param apellidoMaterno Nuevo apellido materno del empleado.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el nombre de usuario del empleado.
     *
     * @return Nombre de usuario.
     */
    public String getUser() {
        return user;
    }

    /**
     * Establece el nombre de usuario del empleado.
     *
     * @param user Nuevo nombre de usuario.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Obtiene el rol asignado al empleado.
     *
     * @return Rol del empleado.
     */
    public RolEmpleadoDTO getRol() {
        return rol;
    }

    /**
     * Establece el rol del empleado.
     *
     * @param rol Nuevo rol del empleado.
     */
    public void setRol(RolEmpleadoDTO rol) {
        this.rol = rol;
    }

    /**
     * Obtiene el estado actual del empleado.
     *
     * @return Estado del empleado.
     */
    public EstadoEmpleadoDTO getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual del empleado.
     *
     * @param estado Nuevo estado del empleado.
     */
    public void setEstado(EstadoEmpleadoDTO estado) {
        this.estado = estado;
    }
    
}
