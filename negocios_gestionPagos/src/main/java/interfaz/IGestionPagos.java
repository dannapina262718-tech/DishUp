/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaz;

import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import excepciones.PagosException;

/**
 *
 * @author valeria
 */
public interface IGestionPagos {
    boolean puedePagarComanda(String idComanda) throws PagosException;
    boolean puedePagarMesa(int numeroMesa) throws PagosException;
    
    ResultadoPagoDTO registrarPago(SolicitudPagoDTO solicitud) throws PagosException;
}
