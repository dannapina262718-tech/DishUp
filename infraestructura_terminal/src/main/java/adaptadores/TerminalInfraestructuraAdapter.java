package adaptadores;

import dtos_infraestructura.RespuestaCodiDTO;
import dtos_infraestructura.RespuestaTerminalDTO;
import enums.Banco;
import enums.TipoTarjeta;
import org.json.JSONObject;

/**
 * TerminalInfraestructuraAdapter.
 * 
 * Clase adaptadora encargada de convertir respuestas en formato JSON
 * provenientes de sistemas externos (como terminal de pago y CoDi)
 * a objetos DTO internos del sistema.
 * 
 * Su funcion es desacoplar la capa de infraestructura del sistema
 * interno, transformando estructuras JSON en objetos tipados que
 * pueden ser utilizados por las capas superiores.
 * 
 * Facilita la integracion con servicios externos y mejora la
 * mantenibilidad del sistema.
 * 
 * @author valeria
 */
public class TerminalInfraestructuraAdapter {

    /**
     * Convierte una respuesta JSON de la terminal de pago
     * a un DTO interno {@link RespuestaTerminalDTO}.
     *
     * @param obj objeto JSON recibido desde la terminal
     * @return DTO con los datos estructurados de la respuesta
     */
    public RespuestaTerminalDTO convertirJSONADTO(JSONObject obj) {

        RespuestaTerminalDTO dto = new RespuestaTerminalDTO();

        dto.setAprobado(obj.optBoolean("aprobado", false));
        dto.setMensaje(obj.optString("mensaje", ""));
        dto.setMonto((float) obj.optDouble("monto", 0));
        dto.setNumeroAutorizacion(obj.optString("numeroAutorizacion", ""));
        dto.setUltimos4Digitos(obj.optString("ultimos4Digitos", ""));

        dto.setBanco(
                Banco.valueOf(obj.getString("banco"))
        );

        dto.setTipoTarjeta(
                TipoTarjeta.valueOf(obj.getString("tipoTarjeta"))
        );

        return dto;
    }

    /**
     * Convierte una respuesta JSON del servicio CoDi
     * a un DTO interno {@link RespuestaCodiDTO}.
     *
     * @param obj objeto JSON recibido desde CoDi
     * @return DTO con los datos estructurados de la respuesta
     */
    public RespuestaCodiDTO convertirJSONACodiDTO(JSONObject obj) {

        RespuestaCodiDTO dto = new RespuestaCodiDTO();

        dto.setAprobado(obj.getBoolean("aprobado"));
        dto.setMensaje(obj.getString("mensaje"));
        dto.setFolio(obj.getString("folio"));
        dto.setQrBase64(obj.getString("qrBase64"));

        if (obj.has("monto")) {
            dto.setMonto((float) obj.optDouble("monto", 0));
        }

        return dto;
    }
}