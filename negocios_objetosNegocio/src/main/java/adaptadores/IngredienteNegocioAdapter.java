package adaptadores;

import dtos.IngredienteEnProductoDTO;
import dtos.ProductoIngredienteDTO;
import entidades.IngredienteEnProducto;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter encargado de convertir ingredientes y modificadores de productos.
 *
 * @author DishUp
 */
public class IngredienteNegocioAdapter {

    public IngredienteNegocioAdapter() {
    }

    public List<IngredienteEnProductoDTO> convertirIngredientesRemovibles(List<IngredienteEnProducto> ingredientes) {

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

    public List<String> convertirIngredientesAModificadores(List<IngredienteEnProductoDTO> ingredientes) {

        List<String> modificadores = new ArrayList<>();

        if (ingredientes == null) {
            return modificadores;
        }

        for (IngredienteEnProductoDTO ingrediente : ingredientes) {

            if (ingrediente != null) {
                modificadores.add("Sin " + ingrediente.getNombre().toLowerCase());
            }
        }

        return modificadores;
    }

    public List<ProductoIngredienteDTO> convertirAProductoIngredienteDTO(String idProducto,List<IngredienteEnProducto> ingredientes) {

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
