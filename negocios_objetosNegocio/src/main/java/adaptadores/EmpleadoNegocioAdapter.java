package adaptadores;

import dtos.EmpleadoDTO;
import entidades.Empleado;
import enums.EstadoEmpleado;
import enums.EstadoEmpleadoDTO;
import enums.RolEmpleado;
import enums.RolEmpleadoDTO;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoNegocioAdapter {

    public EmpleadoNegocioAdapter() {
    }

    public EmpleadoDTO aDTO(Empleado empleado) {
        if (empleado == null) {
            return null;
        }

        EmpleadoDTO dto = new EmpleadoDTO();

        dto.setId(empleado.getId());
        dto.setNombres(empleado.getNombres());
        dto.setApellidoPaterno(empleado.getApellidoPaterno());
        dto.setApellidoMaterno(empleado.getApellidoMaterno());
        dto.setUser(empleado.getUser());
        dto.setRol(RolEmpleadoDTO.valueOf(empleado.getRol().name()));
        dto.setEstado(EstadoEmpleadoDTO.valueOf(empleado.getEstado().name()));

        return dto;
    }

    public Empleado aEntidad(EmpleadoDTO dto) {
        if (dto == null) {
            return null;
        }

        Empleado empleado = new Empleado();

        empleado.setId(dto.getId());
        empleado.setNombres(dto.getNombres());
        empleado.setApellidoPaterno(dto.getApellidoPaterno());
        empleado.setApellidoMaterno(dto.getApellidoMaterno());
        empleado.setUser(dto.getUser());
        empleado.setRol(RolEmpleado.valueOf(dto.getRol().name()));
        empleado.setEstado(EstadoEmpleado.valueOf(dto.getEstado().name()));

        return empleado;
    }

    public List<EmpleadoDTO> listaEntidadADTO(List<Empleado> empleados) {

        List<EmpleadoDTO> listaDTO = new ArrayList<>();

        for (Empleado empleado : empleados) {
            listaDTO.add(aDTO(empleado));
        }

        return listaDTO;
    }
}