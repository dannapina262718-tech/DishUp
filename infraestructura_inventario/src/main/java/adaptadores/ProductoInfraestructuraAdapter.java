package adaptadores;

import dtos_infraestructura.IngredienteDTOInfraestructura;
import dtos_infraestructura.ProductoDTOInfraestructura;
import enums.TipoProductoDTOInfraestructura;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * ProductoInfraestructuraAdapter.
 * Clase encargada de convertir informacion en formato JSON
 * proveniente de un sistema externo a objetos DTO internos.
 *
 * Este adaptador permite transformar la respuesta de la infraestructura
 * en objetos ProductoDTOInfraestructura utilizables dentro del sistema,
 * incluyendo la conversion de ingredientes asociados.
 *
 * @author DishUp
 */
public class ProductoInfraestructuraAdapter {

    /**
     * Constructor por defecto.
     */
    public ProductoInfraestructuraAdapter() {
    }

    /**
     * Convierte un objeto JSON en un ProductoDTOInfraestructura.
     *
     * @param obj Objeto JSON con la informacion del producto.
     * @param baseURL URL base para construir la ruta de la imagen.
     * @return ProductoDTOInfraestructura convertido o null si el JSON es nulo.
     */
    public ProductoDTOInfraestructura convertirJSONADTO(JSONObject obj, String baseURL) {

        if (obj == null) {
            return null;
        }

        ProductoDTOInfraestructura dto = new ProductoDTOInfraestructura();

        dto.setId(String.valueOf(obj.getInt("id")));
        dto.setNombre(obj.getString("nombre"));
        dto.setTipo(TipoProductoDTOInfraestructura.valueOf(obj.getString("tipo")));
        dto.setDisponible(obj.getBoolean("disponible"));
        dto.setPrecio((float) obj.optDouble("precio", 0.0));
        dto.setTiempoPreparacion(obj.optInt("tiempoPreparacion", 0));
        dto.setUrlImagen(baseURL + obj.optString("imagen", ""));

        List<IngredienteDTOInfraestructura> ingredientes = new ArrayList<>();

        if (obj.has("ingredientes")) {

            JSONArray ingredientesJSON = obj.getJSONArray("ingredientes");

            for (int i = 0; i < ingredientesJSON.length(); i++) {
                JSONObject ing = ingredientesJSON.getJSONObject(i);

                IngredienteDTOInfraestructura dtoIng = new IngredienteDTOInfraestructura();
                dtoIng.setNombre(ing.getString("nombre"));
                dtoIng.setCantidad(ing.getInt("cantidad"));
                dtoIng.setRemovible(ing.optBoolean("removible", true));

                ingredientes.add(dtoIng);
            }
        }

        dto.setIngredientes(ingredientes);

        return dto;
    }
}
