package adaptadores;

import dtos.MesaDTO;
import entidades.Mesa;
import enums.EstadoMesa;
import enums.EstadoMesaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * MesaNegocioAdapter.
 * 
 * Clase encargada de convertir objetos relacionados con mesas
 * entre la capa de negocio (entidades) y la capa de transferencia
 * de datos (DTOs).
 * 
 * Su funcion es mapear la entidad Mesa a MesaDTO y viceversa,
 * incluyendo la conversion de estados entre enums de dominio y DTO.
 * 
 * Tambien proporciona utilidades para convertir listas completas
 * de mesas en ambas direcciones.
 * 
 * @author DishUp
 */
public class MesaNegocioAdapter {

    /**
     * Convierte una entidad Mesa a su representacion DTO.
     *
     * @param mesa entidad de mesa
     * @return objeto MesaDTO con la informacion de la mesa
     */
    public MesaDTO aDTO(Mesa mesa) {

        if (mesa == null) {
            return null;
        }

        return new MesaDTO(
                mesa.getId(),
                mesa.getNumero(),
                EstadoMesaDTO.valueOf(
                        mesa.getEstado().name()
                ),
                mesa.getIdMesero()
        );
    }

    /**
     * Convierte un MesaDTO a una entidad Mesa.
     *
     * @param dto objeto DTO de mesa
     * @return entidad Mesa construida
     */
    public Mesa aEntidad(MesaDTO dto) {

        if (dto == null) {
            return null;
        }

        Mesa mesa = new Mesa();

        mesa.setId(dto.getIdMesa());
        mesa.setNumero(dto.getNumeroMesa());
        mesa.setEstado(
                EstadoMesa.valueOf(
                        dto.getEstado().name()
                )
        );
        mesa.setIdMesero(dto.getIdMesero());

        return mesa;
    }

    /**
     * Convierte una lista de entidades Mesa a una lista de MesaDTO.
     *
     * @param mesas lista de entidades Mesa
     * @return lista de DTOs de mesa
     */
    public List<MesaDTO> listaEntidadADTO(List<Mesa> mesas) {

        List<MesaDTO> listaDTO = new ArrayList<>();

        if (mesas == null) {
            return listaDTO;
        }

        for (Mesa m : mesas) {
            listaDTO.add(aDTO(m));
        }

        return listaDTO;
    }

    /**
     * Convierte una lista de MesaDTO a una lista de entidades Mesa.
     *
     * @param mesas lista de DTOs de mesa
     * @return lista de entidades Mesa
     */
    public List<Mesa> listaDTOAEntidad(List<MesaDTO> mesas) {

        List<Mesa> listaEntidad = new ArrayList<>();

        if (mesas == null) {
            return listaEntidad;
        }

        for (MesaDTO m : mesas) {
            listaEntidad.add(aEntidad(m));
        }

        return listaEntidad;
    }
}