package dtos;

import enums.EstadoMesaDTO;

/*
 * MesaDTO.
 * Representa la informacion de una mesa dentro del sistema.
 *
 * Este DTO se utiliza para transportar los datos de una mesa
 * entre las distintas capas de la aplicacion.
 *
 * Contiene informacion como el identificador de la mesa,
 * el numero asignado, el estado actual y el mesero responsable.
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
     * @param idMesa Identificador de la mesa.
     * @param numeroMesa Numero asignado a la mesa.
     * @param estado Estado actual de la mesa.
     * @param idMesero Identificador del mesero asignado.
     */
    public MesaDTO(String idMesa, int numeroMesa, EstadoMesaDTO estado, String idMesero) {
        this.idMesa = idMesa;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.idMesero = idMesero;
    }

    /**
     * Constructor que permite inicializar los atributos principales
     * de la mesa sin incluir identificadores.
     *
     * @param numeroMesa Numero asignado a la mesa.
     * @param estado Estado actual de la mesa.
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
     * Obtiene el identificador de la mesa.
     *
     * @return Identificador de la mesa.
     */
    public String getIdMesa() {
        return idMesa;
    }

    /**
     * Establece el identificador de la mesa.
     *
     * @param idMesa Nuevo identificador de la mesa.
     */
    public void setIdMesa(String idMesa) {
        this.idMesa = idMesa;
    }

    /**
     * Obtiene el numero asignado a la mesa.
     *
     * @return Numero de la mesa.
     */
    public int getNumeroMesa() {
        return numeroMesa;
    }

    /**
     * Establece el numero asignado a la mesa.
     *
     * @param numeroMesa Nuevo numero de la mesa.
     */
    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    /**
     * Obtiene el estado actual de la mesa.
     *
     * @return Estado de la mesa.
     */
    public EstadoMesaDTO getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la mesa.
     *
     * @param estado Nuevo estado de la mesa.
     */
    public void setEstado(EstadoMesaDTO estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el identificador del mesero asignado.
     *
     * @return Identificador del mesero.
     */
    public String getIdMesero() {
        return idMesero;
    }

    /**
     * Establece el identificador del mesero asignado.
     *
     * @param idMesero Nuevo identificador del mesero.
     */
    public void setIdMesero(String idMesero) {
        this.idMesero = idMesero;
    }
}
