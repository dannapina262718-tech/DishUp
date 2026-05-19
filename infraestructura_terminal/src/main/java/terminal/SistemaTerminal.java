/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author valeria
 */
public class SistemaTerminal {
    private final String BASE_URL = "http://localhost:5002";
    private final TerminalInfraestructuraAdapter adapter;

    public SistemaTerminal() {
        this.adapter = new TerminalInfraestructuraAdapter();
    }

    public RespuestaTerminalDTO cobrarTarjeta(SolicitudTerminalDTO solicitud) throws InfraestructuraTerminalException {

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
    
    public RespuestaCodiDTO cobrarCodi(SolicitudCodiDTO solicitud) throws InfraestructuraTerminalException {

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
