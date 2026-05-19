package entidades;

import enums.EstadoMesa;

/**
 * Mesa.
 * Representa una mesa dentro del sistema en la capa de dominio.
 *
 * Esta entidad almacena la informacion de una mesa del restaurante,
 * incluyendo su numero, estado actual y el mesero asignado.
 *
 * Es utilizada para controlar la disponibilidad y asignacion
 * de mesas dentro del sistema.
 *
 * @author DishUp
 */
public class Mesa {

    private String id;
    private Integer numero;
    private EstadoMesa estado;
    private String idMesero;

    /**
     * Constructor por defecto.
     */
    public Mesa() {
    }

    /**
     * Constructor que inicializa todos los atributos de la mesa.
     *
     * @param id identificador de la mesa
     * @param numero numero de la mesa
     * @param estado estado actual de la mesa
     * @param idMesero identificador del mesero asignado
     */
    public Mesa(String id, Integer numero, EstadoMesa estado, String idMesero) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
        this.idMesero = idMesero;
    }

    /**
     * Constructor que inicializa solo numero y estado de la mesa.
     *
     * @param numero numero de la mesa
     * @param estado estado de la mesa
     */
    public Mesa(Integer numero, EstadoMesa estado) {
        this.numero = numero;
        this.estado = estado;
    }

    /**
     * Obtiene el identificador de la mesa.
     *
     * @return id de la mesa
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador de la mesa.
     *
     * @param id nuevo id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el numero de la mesa.
     *
     * @return numero de mesa
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Establece el numero de la mesa.
     *
     * @param numero nuevo numero
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el estado actual de la mesa.
     *
     * @return estado de la mesa
     */
    public EstadoMesa getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la mesa.
     *
     * @param estado nuevo estado
     */
    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el id del mesero asignado a la mesa.
     *
     * @return id del mesero
     */
    public String getIdMesero() {
        return idMesero;
    }

    /**
     * Establece el mesero asignado a la mesa.
     *
     * @param idMesero nuevo id de mesero
     */
    public void setIdMesero(String idMesero) {
        this.idMesero = idMesero;
    }
}