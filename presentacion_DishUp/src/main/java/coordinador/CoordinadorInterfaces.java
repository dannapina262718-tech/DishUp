/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinador;

import dtos.ComandaDTO;
import dtos.EmpleadoDTO;
import dtos.IngredienteEnProductoDTO;
import dtos.MesaDTO;
import dtos.PedidoDTO;
import dtos.ProductoDTO;
import dtos.ResultadoPagoDTO;
import dtos.SolicitudPagoDTO;
import enums.TipoProductoDTO;
import excepciones.ComandasException;
import excepciones.EmpleadosException;
import excepciones.PagosException;
import excepciones.ProductosException;
import fachada.ComandaFachada;
import fachada.EmpleadoFachada;
import fachada.MesaFachada;
import fachada.PagoFachada;
import fachada.ProductoFachada;
import interfaces.IGestionComandas;
import interfaz.IGestionEmpleados;
import interfaz.IGestionMesas;
import interfaz.IGestionPagos;
import interfaz.IGestionProductos;
import java.util.ArrayList;
import java.util.List;
import pantallas.DlgModificarProducto;
import pantallas.DlgPagoComanda;
import pantallas.DlgPagoEfectivo;
import pantallas.DlgPagoTarjeta;
import pantallas.DlgResumenComanda;
import pantallas.FrmCliente;
import pantallas.FrmPantallaComandas;
import pantallas.FrmProductos;

/**
 *
 * @author DishUp
 */
public class CoordinadorInterfaces {

    private EmpleadoDTO empleadoActual;
    private ComandaDTO comandaActual;

    private FrmPantallaComandas frmComandas;
    private FrmCliente frmCliente;
    private FrmProductos frmProductos;
    
    private DlgPagoComanda dlgPagoComanda;
    private DlgPagoEfectivo dlgPagoEfectivo;
    private DlgPagoTarjeta dlgPagoTarjeta;

    private IGestionProductos productoFachada;
    private IGestionComandas comandaFachada;
    private IGestionEmpleados empleadoFachada;
    private IGestionMesas mesaFachada;
    private IGestionPagos pagoFachada;

    public CoordinadorInterfaces() {
        this.productoFachada = new ProductoFachada();
        this.comandaFachada = new ComandaFachada();
        this.empleadoFachada = new EmpleadoFachada();
        this.mesaFachada = new MesaFachada();
        this.pagoFachada = new PagoFachada();
    }

    public void setFrmProductos(FrmProductos frmProductos) {
        this.frmProductos = frmProductos;
    }

    public void setFrmComandas(FrmPantallaComandas frmComandas) {
        this.frmComandas = frmComandas;
    }

    private List<PedidoDTO> comandaTemporal = new ArrayList<>();

    public void mostrarRegistrarCliente(MesaDTO mesa) {
        frmCliente = new FrmCliente(this);
        comandaTemporal = new ArrayList<>();
        frmCliente.setNumeroMesa(mesa.getNumeroMesa());
        frmCliente.setVisible(true);
    }

    public void regresarFrmComandas() {
        if (this.frmComandas != null) {
            this.frmComandas.setVisible(true);
        }
    }

    public void frmClienteAFrmProductos(Integer mesa, String nombreCliente) {
        frmProductos = new FrmProductos(this);
        frmProductos.setMesaAndCliente(mesa, nombreCliente);
        frmProductos.setVisible(true);
    }

    public void frmProductosAFrmCliente(String nombreCliente, Integer numMesa) {
        frmCliente = new FrmCliente(this);
        frmCliente.setNombreCliente(nombreCliente);
        frmCliente.setNumeroMesa(numMesa);
        frmCliente.setVisible(true);
    }

    public void abrirPersonalizacionProducto(FrmProductos frm, ProductoDTO producto, List<IngredienteEnProductoDTO> removibles) {

        DlgModificarProducto dlg = new DlgModificarProducto(frm, producto, removibles);
        dlg.setVisible(true);

        PedidoDTO pedido = dlg.getResultado();

        if (pedido != null) {
            comandaTemporal.add(pedido);
            frm.agregarPedidoVisual(pedido);
        }
    }

    public void abrirResumenComanda(FrmProductos frm, int mesa, String nombreCliente) {
        DlgResumenComanda dlg = new DlgResumenComanda(this, frm, comandaTemporal, mesa, nombreCliente);
        dlg.setMesaAndCliente(mesa, nombreCliente);
        dlg.setVisible(true);

    }

    public void enviarComandaAFinal(String nombreCliente, int numeroMesa, List<PedidoDTO> pedidos) {
        try {
            comandaFachada.crearComanda(nombreCliente, numeroMesa, pedidos, empleadoActual);

            this.frmComandas.setVisible(true);
            this.frmComandas.refrescarMesaActual();

            if (this.frmProductos != null) {
                this.frmProductos.dispose();
                this.frmProductos = null;
            }

            comandaTemporal.clear();

        } catch (ComandasException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<ComandaDTO> getComandasDeMesa(int numeroMesa) {
        try {
            return comandaFachada.obtenerComandasPorMesa(numeroMesa);
        } catch (ComandasException e) {
            System.out.println("Error al obtener comandas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<ProductoDTO> obtenerProductosParaUI(TipoProductoDTO tipo) {
        try {
            return productoFachada.obtenerProductosPorTipo(tipo);
        } catch (ProductosException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public EmpleadoDTO validarExistenciaUsuario(EmpleadoDTO e) throws EmpleadosException {
        empleadoActual = empleadoFachada.login(e);
        return empleadoActual;
    }

    public void abrirFrmComandasMesero(String id, String nombre) {
        // FrmPantallaComandas frm = new FrmPantallaComandas(this);
        frmComandas = new FrmPantallaComandas(this);
        frmComandas.setMesero(id, nombre);
        frmComandas.cargarMesas();
        frmComandas.setVisible(true);
        frmComandas.setMesero(id, nombre);
        frmComandas.setVisible(true);
    }

    public void activarEmpleado(EmpleadoDTO e) throws EmpleadosException {
        empleadoFachada.activarEmpleado(e);
    }

    public List<IngredienteEnProductoDTO> obtenerIngredientesRemovibles(String idProducto) {
        try {
            return productoFachada.obtenerIngredientesRemovibles(idProducto);
        } catch (ProductosException e) {
            System.out.println("Error al obtener ingredientes removibles: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void eliminarPedidoTemporal(PedidoDTO pedido) {
        comandaTemporal.remove(pedido);
    }

    public void abrirAgregarPedido(ComandaDTO comanda) {

        this.comandaActual = comanda;

        FrmProductos frm = new FrmProductos(this);

        frm.setMesaAndCliente(
                comanda.getNumMesa(),
                comanda.getNombreCliente()
        );

        frm.cargarPedidosExistentes(comanda);

        frm.setVisible(true);
    }

    public void agregarPedidosAComanda(ComandaDTO comanda, int numeroMesa, String nombreCliente) {
        try {

            for (PedidoDTO pedido : comandaTemporal) {
                comanda.getPedidos().add(pedido);
            }

            comandaFachada.agregarPedidosAComanda(comanda.getId(), comandaTemporal);

            this.frmComandas.refrescarMesaActual();

            if (this.frmProductos != null) {
                this.frmProductos.dispose();
                this.frmProductos = null;
            }

            comandaTemporal.clear();
            this.frmComandas.setVisible(true);

        } catch (ComandasException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void abrirResumenAgregarPedido(ComandaDTO comanda) {

        List<PedidoDTO> pedidosExistentes = comanda.getPedidos();

        DlgResumenComanda dlg = new DlgResumenComanda(
                this,
                frmProductos,
                pedidosExistentes,
                comandaTemporal,
                comanda.getNumMesa(),
                comanda.getNombreCliente(),
                true,
                comanda
        );

        dlg.setVisible(true);
    }

    public boolean eliminarComanda(String idComanda) throws ComandasException {
        return comandaFachada.eliminarComanda(idComanda);
    }

    public boolean puedePagarComanda(String id) throws PagosException {
        return pagoFachada.puedePagarComanda(id);
    }

    public boolean puedePagarMesa(int numeroMesa) throws PagosException {
        return pagoFachada.puedePagarMesa(numeroMesa);
    }

    public void mostrarPagoComanda(ComandaDTO comanda) {
        DlgPagoComanda dlg = new DlgPagoComanda(frmComandas, true, comanda, frmComandas, this);

        dlg.setLocationRelativeTo(frmComandas);

        dlg.setVisible(true);
    }

    public void mostrarPagoEfectivo(ComandaDTO comanda, float restante, DlgPagoComanda dlgPadre) {
        DlgPagoEfectivo dlg = new DlgPagoEfectivo(frmComandas, true, comanda, restante, dlgPadre, this);
        dlg.setLocationRelativeTo(dlgPadre);

        dlg.setVisible(true);
    }
    
    public void mostrarPagoTarjeta(ComandaDTO comanda, float restante, DlgPagoComanda dlgPadre) {
        DlgPagoTarjeta dlg = new DlgPagoTarjeta(frmComandas, true, comanda, restante, dlgPadre, this);
        dlg.setLocationRelativeTo(dlgPadre);

        dlg.setVisible(true);
    }

    public ResultadoPagoDTO registrarPago(SolicitudPagoDTO solicitud) throws PagosException {
        return pagoFachada.registrarPago(solicitud);
    }
    
}
