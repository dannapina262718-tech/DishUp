package adaptadores;

import dtos.MesaDTO;
import entidades.Mesa;
import enums.EstadoMesa;
import enums.EstadoMesaDTO;
import java.util.ArrayList;
import java.util.List;

public class MesaNegocioAdapter {

    public MesaNegocioAdapter() {
    }

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

    public List<MesaDTO> listaEntidadADTO(List<Mesa> mesas) {

        List<MesaDTO> listaDTO = new ArrayList<>();

        for (Mesa m : mesas) {
            listaDTO.add(aDTO(m));
        }

        return listaDTO;
    }
    
    public List<Mesa> listaDTOAEntidad(List<MesaDTO> mesas) {

        List<Mesa> listaEntidad = new ArrayList<>();

        for (MesaDTO m : mesas) {
            listaEntidad.add(aEntidad(m));
        }
        return listaEntidad;
    }
}