package adaptadores;

import dtos.EmpleadoDTO;
import entidades.Empleado;
import enums.EstadoEmpleado;
import enums.EstadoEmpleadoDTO;
import enums.RolEmpleado;
import enums.RolEmpleadoDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador encargado de convertir objetos entre la capa de dominio (Entidad)
 * y la capa de transferencia de datos (DTO) para la entidad Empleado.
 *
 * Este componente permite desacoplar la lógica de negocio de la estructura
 * utilizada para transporte de datos, facilitando la separación de capas
 * dentro del sistema.
 *
 * Incluye conversiones individuales y de listas para Empleado ↔ EmpleadoDTO.
 *
 * @author DishUp
 */
public class EmpleadoNegocioAdapter {

    /**
     * Constructor por defecto del adaptador.
     */
    public EmpleadoNegocioAdapter() {
    }

    /**
     * Convierte una entidad Empleado a su representación DTO.
     *
     * @param empleado entidad de dominio a convertir
     * @return objeto EmpleadoDTO equivalente, o null si la entidad es null
     */
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

    /**
     * Convierte un DTO Empleado a su representación de entidad de dominio.
     *
     * @param dto objeto DTO a convertir
     * @return entidad Empleado equivalente, o null si el DTO es null
     */
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

    /**
     * Convierte una lista de entidades Empleado a una lista de EmpleadoDTO.
     *
     * @param empleados lista de entidades a convertir
     * @return lista de DTOs convertidos (lista vacía si la entrada está vacía)
     */
    public List<EmpleadoDTO> listaEntidadADTO(List<Empleado> empleados) {

        List<EmpleadoDTO> listaDTO = new ArrayList<>();

        if (empleados == null) {
            return listaDTO;
        }

        for (Empleado empleado : empleados) {
            listaDTO.add(aDTO(empleado));
        }

        return listaDTO;
    }
}