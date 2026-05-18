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

public class SistemaInventario {

    private final String BASE_URL = "http://localhost:5000";
    // private final String BASE_URL = "https://sistema-inventario-3m6j.onrender.com";
    private final ProductoInfraestructuraAdapter productoAdapter;

    public SistemaInventario() {
        this.productoAdapter = new ProductoInfraestructuraAdapter();
    }

    // OBTENER PRODUCTOS
    public List<ProductoDTOInfraestructura> obtenerProductos() throws InfraestructuraException {

        List<ProductoDTOInfraestructura> lista = new ArrayList<>();

        try {
            URL url = new URL(BASE_URL + "/productos");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();

            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            in.close();

            JSONArray array = new JSONArray(response.toString());

            for (int i = 0; i < array.length(); i++) {

                JSONObject obj = array.getJSONObject(i);

                ProductoDTOInfraestructura dto = productoAdapter.convertirJSONADTO(obj, BASE_URL);

                lista.add(dto);
            }

            return lista;

        } catch (Exception e) {
            throw new InfraestructuraException("No fue posible obtener los productos.", e);
        }
    }

    // OBTENER PRODUCTO POR ID
    public ProductoDTOInfraestructura obtenerProductoPorId(String idProducto) throws InfraestructuraException {

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

    // DESCONTAR STOCK 
    public boolean descontarStock(List<InventarioRequestDTO> pedidos) throws InfraestructuraException {

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

                obj.put("productoId", pedido.getIdProducto());

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

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            JSONObject json = new JSONObject(response.toString());

            return json.getBoolean("exito");

        } catch (Exception e) {
            throw new InfraestructuraException("No fue posible descontar el stock.", e);
        }

    }

    public boolean agregarStock(String ingrediente, int cantidad) throws InfraestructuraException {
        try {
            URL url = new URL(BASE_URL + "/agregarStock");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject body = new JSONObject();
            body.put("ingrediente", ingrediente);
            body.put("cantidad", cantidad);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.toString().getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new InfraestructuraException("Error al agregar stock: " + responseCode);
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
            throw new InfraestructuraException("No fue posible agregar stock.", e);
        }
    }

}
