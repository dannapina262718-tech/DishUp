/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import adaptadores.PagoNegocioAdapter;
import daos.ComandaDAO;
import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import entidades.Comanda;
import entidades.Pago;
import enums.EstadoComanda;
import excepcion.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IComandaDAO;
import java.util.List;
import procesadores.IProcesadorPago;
import procesadores.ProcesadorPagoFactory;


/**
 *
 * @author valeria
 */
public class PagoBO {
    
    private final IComandaDAO comandaDAO;
    private final PagoNegocioAdapter adapter;

    public PagoBO() {
        this.comandaDAO = new ComandaDAO();
        this.adapter = new PagoNegocioAdapter();
    }


    public boolean puedePagarMesa(int numeroMesa) throws NegocioException {
        if (numeroMesa <= 0) {
            throw new NegocioException("El número de mesa es inválido.");
        }

        try {
            List<Comanda> comandas = comandaDAO.obtenerComandasPorMesa(numeroMesa);

            if (comandas == null || comandas.isEmpty()) {
                return false;
            }

            boolean comandasPendientesPago = false;
            
            for (Comanda comanda : comandas) {
                EstadoComanda estado = comanda.getEstado();
                
                // Si hay alguna comanda que no esté ni LISTA ni PAGADA (ej. EN_PREPARACION), no se puede pagar la mesa
                if (estado != EstadoComanda.LISTA && estado != EstadoComanda.PAGADA) {
                    return false;
                }
                
                if (estado == EstadoComanda.LISTA) {
                    comandasPendientesPago = true;
                }
            }

            return comandasPendientesPago;

        } catch (PersistenciaException ex) {
            throw new NegocioException(
                    "No fue posible validar si la mesa puede pagarse.",
                    ex
            );
        }
    }

    public boolean puedePagarComanda(String idComanda) throws NegocioException {
        if (idComanda == null || idComanda.isBlank()) {
            throw new NegocioException("El id de la comanda es inválido.");
        }

        try {
            Comanda comanda = comandaDAO.obtenerPorId(idComanda);

            if (comanda == null) {
                throw new NegocioException("La comanda no existe.");
            }

            return comanda.getEstado() == EstadoComanda.ENTREGADA;

        } catch (PersistenciaException ex) {
            throw new NegocioException(
                    "No fue posible validar si la comanda puede pagarse.",
                    ex
            );
        }
    }
    
    public ResultadoPagoDTO registrarPago(SolicitudPagoDTO solicitud) throws NegocioException {
        if (solicitud == null) {
            throw new NegocioException("Solicitud vacía");
        }

        if (solicitud.getIdComanda() == null || solicitud.getIdComanda().isBlank()) {
            throw new NegocioException("Id de comanda inválido");
        }

        if (solicitud.getMonto() <= 0) {
            throw new NegocioException("El monto a pagar debe ser mayor a cero.");
        }

        float restante;

        try {
            Comanda comanda = comandaDAO.obtenerPorId(solicitud.getIdComanda());

            if (comanda == null) {
                throw new NegocioException("La comanda no existe.");
            }

            if (comanda.getEstado() != EstadoComanda.ENTREGADA) {
                throw new NegocioException("Solo se pueden pagar comandas entregadas.");
            }

            float totalPagado = 0;

            if (comanda.getPagos() != null) {
                for (Pago pago : comanda.getPagos()) {
                    totalPagado += pago.getMonto();
                }
            }

            restante = comanda.getMontoTotal() - totalPagado;

            if (restante <= 0) {
                throw new NegocioException("La comanda ya está liquidada.");
            }

            float monto = Math.round(solicitud.getMonto() * 100f) / 100f;
            float restanteRedondeado = Math.round(restante * 100f) / 100f;
            if (monto > restanteRedondeado) {
                throw new NegocioException(
                        "El monto a pagar no puede ser mayor al restante."
                );
            }
            
            solicitud.setMonto(monto);
            restante = restanteRedondeado;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al validar la comanda.", e);
        }

        IProcesadorPago procesador = ProcesadorPagoFactory.crearProcesador(solicitud.getMetodoPago());

        ResultadoPagoDTO resultado = procesador.procesarPago(solicitud);

        if (!resultado.isAprobado()) {
            throw new NegocioException(resultado.getMensaje());
        }

        Pago pago = adapter.aEntidad(resultado);

        try {
            boolean guardado = comandaDAO.insertarPagoAComanda(solicitud.getIdComanda(), pago);

            if (!guardado) {
                throw new NegocioException("No se pudo guardar el pago en la comanda.");
            }

            float nuevoRestante = Math.round((restante - resultado.getMontoPagado()) * 100f) / 100f;
            resultado.setSaldoRestante(nuevoRestante);

            if (Math.abs(nuevoRestante) < 0.01f) {
                comandaDAO.actualizarEstado(
                        solicitud.getIdComanda(),
                        EstadoComanda.PAGADA.toString()
                );
            }

        } catch (PersistenciaException e) {
            e.printStackTrace();
            throw new NegocioException("Error al guardar el pago: " + e.getMessage(), e);
        }

        return resultado;
    }
}
