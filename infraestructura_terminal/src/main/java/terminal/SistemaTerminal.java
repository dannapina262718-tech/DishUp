package terminal;

import adaptadores.TerminalInfraestructuraAdapter;
import dtos_infraestructura.RespuestaCodiDTO;
import dtos_infraestructura.RespuestaTerminalDTO;
import dtos_infraestructura.SolicitudCodiDTO;
import dtos_infraestructura.SolicitudTerminalDTO;
import excepciones.InfraestructuraTerminalException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * SistemaTerminal.
 * 
 * Representa la implementacion del sistema de terminal de pagos
 * dentro de la capa de infraestructura.
 * 
 * Esta clase se encarga de comunicarse con un servicio externo
 * mediante HTTP para procesar pagos con tarjeta y CoDi.
 * 
 * Funciona como un puente entre el sistema interno y la API externa,
 * enviando solicitudes en formato JSON y recibiendo respuestas que
 * posteriormente son convertidas a DTOs mediante un adaptador.
 * 
 * Utiliza {@link TerminalInfraestructuraAdapter} para transformar
 * las respuestas JSON en objetos tipados del sistema.
 * 
 * @author valeria
 */
public class SistemaTerminal {

    private final String BASE_URL = "http://localhost:5002";
    private final TerminalInfraestructuraAdapter adapter;

    /**
     * Constructor que inicializa el adaptador de infraestructura.
     */
    public SistemaTerminal() {
        this.adapter = new TerminalInfraestructuraAdapter();
    }

    /**
     * Realiza un cobro con tarjeta contra el servicio externo de terminal.
     *
     * @param solicitud datos necesarios para el cobro con tarjeta
     * @return respuesta del sistema de terminal convertida a DTO
     * @throws InfraestructuraTerminalException si ocurre un error en la comunicacion
     */
    public RespuestaTerminalDTO cobrarTarjeta(SolicitudTerminalDTO solicitud)
            throws InfraestructuraTerminalException {

        if (solicitud == null || solicitud.getMonto() <= 0) {
            throw new InfraestructuraTerminalException("Solicitud de cobro inválida.");
        }

        try {
            URL url = new URL(BASE_URL + "/cobrarTarjeta");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject body = new JSONObject();
            body.put("monto", solicitud.getMonto());

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.toString().getBytes());
                os.flush();
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            conn.getResponseCode() < 300
                                    ? conn.getInputStream()
                                    : conn.getErrorStream()
                    )
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            JSONObject json = new JSONObject(response.toString());

            return adapter.convertirJSONADTO(json);

        } catch (Exception e) {
            throw new InfraestructuraTerminalException(
                    "No fue posible procesar el cobro con terminal.",
                    e
            );
        }
    }

    /**
     * Realiza un cobro mediante CoDi contra el servicio externo.
     *
     * @param solicitud datos necesarios para el cobro CoDi
     * @return respuesta del sistema CoDi convertida a DTO
     * @throws InfraestructuraTerminalException si ocurre un error en la comunicacion
     */
    public RespuestaCodiDTO cobrarCodi(SolicitudCodiDTO solicitud)
            throws InfraestructuraTerminalException {

        if (solicitud == null || solicitud.getMonto() <= 0) {
            throw new InfraestructuraTerminalException("Solicitud CoDi inválida.");
        }

        try {
            URL url = new URL(BASE_URL + "/cobrarCodi");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject body = new JSONObject();
            body.put("monto", solicitud.getMonto());

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.toString().getBytes());
                os.flush();
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            conn.getResponseCode() < 300
                                    ? conn.getInputStream()
                                    : conn.getErrorStream()
                    )
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            JSONObject json = new JSONObject(response.toString());

            return adapter.convertirJSONACodiDTO(json);

        } catch (Exception e) {
            throw new InfraestructuraTerminalException(
                    "No fue posible procesar el cobro con CoDi.",
                    e
            );
        }
    }
}