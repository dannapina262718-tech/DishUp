package adaptadores;

import dtos.IngredienteEnProductoDTO;
import dtos.ProductoIngredienteDTO;
import entidades.IngredienteEnProducto;
import java.util.ArrayList;
import java.util.List;

/**
 * IngredienteNegocioAdapter.
 * 
 * Clase encargada de convertir y adaptar informacion relacionada
 * con ingredientes de productos entre la capa de negocio y los DTOs.
 * 
 * Su funcion es transformar entidades de ingredientes en estructuras
 * de transferencia de datos, asi como generar representaciones
 * utilizadas para modificaciones o relaciones producto-ingrediente.
 * 
 * Permite manejar ingredientes removibles, generar modificadores
 * de productos y construir relaciones entre productos e ingredientes.
 * 
 * @author DishUp
 */
public class IngredienteNegocioAdapter {

    /**
     * Convierte una lista de ingredientes de entidad a DTO,
     * filtrando unicamente aquellos que son removibles.
     *
     * @param ingredientes lista de ingredientes en entidad
     * @return lista de ingredientes removibles en formato DTO
     */
    public List<IngredienteEnProductoDTO> convertirIngredientesRemovibles(
            List<IngredienteEnProducto> ingredientes
    ) {

        List<IngredienteEnProductoDTO> removibles = new ArrayList<>();

        if (ingredientes == null) {
            return removibles;
        }

        for (IngredienteEnProducto ingrediente : ingredientes) {

            if (ingrediente != null && ingrediente.isRemovible()) {

                IngredienteEnProductoDTO dto = new IngredienteEnProductoDTO();

                dto.setNombre(ingrediente.getNombre());
                dto.setCantidad(ingrediente.getCantidad());
                dto.setRemovible(ingrediente.isRemovible());

                removibles.add(dto);
            }
        }

        return removibles;
    }

    /**
     * Convierte ingredientes en una lista de modificadores de producto.
     *
     * @param ingredientes lista de ingredientes en formato DTO
     * @return lista de textos modificadores del producto
     */
    public List<String> convertirIngredientesAModificadores(
            List<IngredienteEnProductoDTO> ingredientes
    ) {

        List<String> modificadores = new ArrayList<>();

        if (ingredientes == null) {
            return modificadores;
        }

        for (IngredienteEnProductoDTO ingrediente : ingredientes) {

            if (ingrediente != null) {
                modificadores.add(
                        "Sin " + ingrediente.getNombre().toLowerCase()
                );
            }
        }

        return modificadores;
    }

    /**
     * Convierte ingredientes de entidad a DTO de relacion
     * producto-ingrediente.
     *
     * @param idProducto identificador del producto
     * @param ingredientes lista de ingredientes en entidad
     * @return lista de relaciones producto-ingrediente en DTO
     */
    public List<ProductoIngredienteDTO> convertirAProductoIngredienteDTO(
            String idProducto,
            List<IngredienteEnProducto> ingredientes
    ) {

        List<ProductoIngredienteDTO> lista = new ArrayList<>();

        if (ingredientes == null) {
            return lista;
        }

        for (IngredienteEnProducto ingrediente : ingredientes) {

            if (ingrediente != null) {

                ProductoIngredienteDTO dto = new ProductoIngredienteDTO();

                dto.setIdProducto(idProducto);
                dto.setIdIngrediente(ingrediente.getId());
                dto.setCantidadRequerida(ingrediente.getCantidad());
                dto.setRemovible(ingrediente.isRemovible());

                lista.add(dto);
            }
        }

        return lista;
    }
}