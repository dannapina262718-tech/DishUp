package dtos;

import enums.EstadoMesaDTO;

/**
 * Objeto de transferencia de datos (DTO) que representa una mesa dentro del sistema del restaurante.
 *
 * Este DTO se utiliza para transportar la información de una mesa entre las distintas capas de la aplicación,
 * permitiendo gestionar su estado, número asignado e identificación del mesero responsable.
 *
 * Incluye información clave para el control operativo de mesas dentro del sistema de atención.
 *
 * @author DishUp
 */
public class MesaDTO {

    private String idMesa;
    private int numeroMesa;
    private EstadoMesaDTO estado;
    private String idMesero;

    /**
     * Constructor que permite inicializar todos los atributos de la mesa.
     *
     * @param idMesa identificador único de la mesa
     * @param numeroMesa número asignado a la mesa
     * @param estado estado actual de la mesa
     * @param idMesero identificador del mesero asignado a la mesa
     */
    public MesaDTO(String idMesa, int numeroMesa, EstadoMesaDTO estado, String idMesero) {
        this.idMesa = idMesa;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.idMesero = idMesero;
    }

    /**
     * Constructor que permite inicializar los atributos principales de la mesa sin identificadores.
     *
     * @param numeroMesa número asignado a la mesa
     * @param estado estado actual de la mesa
     */
    public MesaDTO(int numeroMesa, EstadoMesaDTO estado) {
        this.numeroMesa = numeroMesa;
        this.estado = estado;
    }

    /**
     * Constructor por defecto.
     */
    public MesaDTO() {
    }

    /**
     * Obtiene el identificador único de la mesa.
     *
     * @return id de la mesa
     */
    public String getIdMesa() {
        return idMesa;
    }

    /**
     * Establece el identificador único de la mesa.
     *
     * @param idMesa identificador de la mesa
     */
    public void setIdMesa(String idMesa) {
        this.idMesa = idMesa;
    }

    /**
     * Obtiene el número asignado a la mesa.
     *
     * @return número de la mesa
     */
    public int getNumeroMesa() {
        return numeroMesa;
    }

    /**
     * Establece el número asignado a la mesa.
     *
     * @param numeroMesa número de la mesa
     */
    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    /**
     * Obtiene el estado actual de la mesa.
     *
     * @return estado de la mesa
     */
    public EstadoMesaDTO getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la mesa.
     *
     * @param estado estado de la mesa
     */
    public void setEstado(EstadoMesaDTO estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el identificador del mesero asignado.
     *
     * @return id del mesero
     */
    public String getIdMesero() {
        return idMesero;
    }

    /**
     * Establece el identificador del mesero asignado.
     *
     * @param idMesero id del mesero
     */
    public void setIdMesero(String idMesero) {
        this.idMesero = idMesero;
    }
}