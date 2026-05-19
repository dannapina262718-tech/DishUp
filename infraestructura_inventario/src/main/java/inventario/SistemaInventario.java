package inventario;

import adaptadores.ProductoInfraestructuraAdapter;
import dtos_infraestructura.InventarioRequestDTO;
import dtos_infraestructura.ProductoDTOInfraestructura;
import excepciones.InfraestructuraException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * SistemaInventario. Clase encargada de comunicarse con el sistema externo de
 * inventario.
 *
 * Esta clase realiza peticiones HTTP para obtener productos, consultar
 * informacion y actualizar el stock en el sistema externo.
 *
 * Funciona como puente entre el sistema principal y la infraestructura de
 * inventario.
 *
 * @author DishUp
 */
public class SistemaInventario {

    private final String BASE_URL = "http://localhost:5000";
    // private final String BASE_URL = "https://sistema-inventario-3m6j.onrender.com";

    private final ProductoInfraestructuraAdapter productoAdapter;

    /**
     * Constructor que inicializa el adaptador de productos.
     */
    public SistemaInventario() {
        this.productoAdapter = new ProductoInfraestructuraAdapter();
    }

    /**
     * Obtiene la lista de productos desde el sistema de inventario.
     *
     * @return lista de productos en formato infraestructura.
     * @throws InfraestructuraException si ocurre un error en la consulta.
     */
    public List<ProductoDTOInfraestructura> obtenerProductos() throws InfraestructuraException {

        List<ProductoDTOInfraestructura> lista = new ArrayList<>();

        try {
            URL url = new URL(BASE_URL + "/productos");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            in.close();

            JSONArray array = new JSONArray(response.toString());

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                ProductoDTOInfraestructura dto
                        = productoAdapter.convertirJSONADTO(obj, BASE_URL);

                lista.add(dto);
            }

            return lista;

        } catch (Exception e) {
            throw new InfraestructuraException(
                    "No fue posible obtener los productos.",
                    e
            );
        }
    }

    /**
     * Obtiene un producto por su identificador.
     *
     * @param idProducto identificador del producto.
     * @return producto encontrado o null si no existe.
     * @throws InfraestructuraException si ocurre un error en la operacion.
     */
    public ProductoDTOInfraestructura obtenerProductoPorId(String idProducto)
            throws InfraestructuraException {

        if (idProducto == null || idProducto.isBlank()) {
            return null;
        }

        List<ProductoDTOInfraestructura> productos = obtenerProductos();

        for (ProductoDTOInfraestructura producto : productos) {
            if (producto.getId().equals(idProducto)) {
                return producto;
            }
        }

        return null;
    }

    /**
     * Descuenta stock de una lista de productos.
     *
     * @param pedidos lista de productos a descontar.
     * @return true si la operacion fue exitosa.
     * @throws InfraestructuraException si ocurre un error en la operacion.
     */
    public boolean descontarStock(List<InventarioRequestDTO> pedidos)
            throws InfraestructuraException {

        if (pedidos == null || pedidos.isEmpty()) {
            throw new InfraestructuraException("Lista de pedidos vacía");
        }

        try {
            URL url = new URL(BASE_URL + "/descontarStock");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject body = new JSONObject();
            JSONArray productosArray = new JSONArray();

            for (InventarioRequestDTO pedido : pedidos) {
                JSONObject obj = new JSONObject();

                obj.put("productoId", Integer.parseInt(pedido.getIdProducto()));
                obj.put("cantidad", pedido.getCantidad());

                productosArray.put(obj);
            }

            body.put("productos", productosArray);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.toString().getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {

                BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream())
                );

                StringBuilder errorResponse = new StringBuilder();
                String line;

                while ((line = errorReader.readLine()) != null) {
                    errorResponse.append(line);
                }

                JSONObject errorJson = new JSONObject(errorResponse.toString());

                throw new InfraestructuraException(
                        errorJson.getString("mensaje")
                );
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            JSONObject json = new JSONObject(response.toString());

            return json.getBoolean("exito");

        } catch (Exception e) {
            throw new InfraestructuraException(
                    "No fue posible descontar el stock.",
                    e
            );
        }
    }

    /**
     * Regresa stock de una lista de productos (al cancelar pedidos).
     *
     * @param pedidos lista de productos a regresar.
     * @param ingredientesRemovidos ingredientes que NO se regresan por haber
     * sido removidos.
     * @return true si la operacion fue exitosa.
     * @throws InfraestructuraException si ocurre un error en la operacion.
     */
    public boolean regresarStock(List<InventarioRequestDTO> pedidos, List<String> ingredientesRemovidos)
            throws InfraestructuraException {
        if (pedidos == null || pedidos.isEmpty()) {
            throw new InfraestructuraException("Lista de pedidos vacía");
        }
        try {
            URL url = new URL(BASE_URL + "/regresarStock");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject body = new JSONObject();
            JSONArray productosArray = new JSONArray();

            for (InventarioRequestDTO pedido : pedidos) {
                JSONObject obj = new JSONObject();
                obj.put("productoId", Integer.parseInt(pedido.getIdProducto()));
                obj.put("cantidad", pedido.getCantidad());
                if (ingredientesRemovidos != null && !ingredientesRemovidos.isEmpty()) {
                    obj.put("ingredientesRemovidos", new JSONArray(ingredientesRemovidos));
                }
                productosArray.put(obj);
            }
            body.put("productos", productosArray);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.toString().getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new InfraestructuraException("Error al regresar stock: " + responseCode);
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            JSONObject json = new JSONObject(response.toString());
            return json.getBoolean("exito");

        } catch (Exception e) {
            throw new InfraestructuraException("No fue posible regresar el stock.", e);
        }
    }
}
