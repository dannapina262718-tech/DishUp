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
import enums.TipoProductoDTO;
import excepciones.ComandasException;
import excepciones.EmpleadosException;
import excepciones.MesasException;
import excepciones.ProductosException;
import fachada.ComandaFachada;
import fachada.EmpleadoFachada;
import fachada.MesaFachada;
import fachada.ProductoFachada;
import interfaces.IGestionComandas;
import interfaz.IGestionEmpleados;
import interfaz.IGestionMesas;
import interfaz.IGestionProductos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import pantallas.AdministrarMesas.FrmAsignarMesas;
import pantallas.AdministrarMesas.FrmPantallaMesas;
import pantallas.AdministrarMesas.panInfoMesa;
import pantallas.DlgModificarProducto;
import pantallas.DlgResumenComanda;
import pantallas.FrmCliente;
import pantallas.FrmInicioSesión;
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
    private panInfoMesa panInfoMesa;
    private FrmAsignarMesas frmAsignarMesas;
    private FrmPantallaMesas frmMesas;
    private FrmInicioSesión frmSesion;

    private IGestionProductos productoFachada;
    private IGestionComandas comandaFachada;
    private IGestionEmpleados empleadoFachada;
    private IGestionMesas mesaFachada;

    public CoordinadorInterfaces() {
        this.productoFachada = new ProductoFachada();
        this.comandaFachada = new ComandaFachada();
        this.empleadoFachada = new EmpleadoFachada();
        this.mesaFachada = new MesaFachada();
    }

    public void setFrmProductos(FrmProductos frmProductos) {
        this.frmProductos = frmProductos;
    }

    public void setFrmComandas(FrmPantallaComandas frmComandas) {
        this.frmComandas = frmComandas;
    }

    private List<PedidoDTO> comandaTemporal = new ArrayList<>();

    public void cerrarSesion() {
        frmSesion = new FrmInicioSesión();
        frmSesion.setVisible(true);
    }

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
        frmProductos.setModoNuevo();
        frmProductos.setVisible(true);
    }

    public void frmProductosAFrmCliente(String nombreCliente, Integer numMesa) {
        frmCliente = new FrmCliente(this);
        frmCliente.setNombreCliente(nombreCliente);
        frmCliente.setNumeroMesa(numMesa);
        frmCliente.setVisible(true);
    }

    public void abrirPersonalizacionProducto(FrmProductos frm, ProductoDTO producto, List<IngredienteEnProductoDTO> removibles) {
        DlgModificarProducto dlg = new DlgModificarProducto(frm, producto, removibles, null);
        dlg.setVisible(true);
        PedidoDTO pedido = dlg.getResultado();
        if (pedido != null) {
            pedido.setIdProducto(producto.getId());
            comandaTemporal.add(pedido);
            frm.agregarPedidoVisual(pedido, null); // null = modo nuevo
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
            frmComandas.cargarMesas();

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

    public void abrirFrmComandasMesero(EmpleadoDTO mesero, String nombre) {
        // FrmPantallaComandas frm = new FrmPantallaComandas(this);
        frmComandas = new FrmPantallaComandas(this);
        frmComandas.setMesero(mesero, nombre);
        frmComandas.cargarMesas();
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
        if (comandaActual != null) {
            comandaActual.getPedidos().remove(pedido);
        }
        if (frmProductos != null) {
            frmProductos.refrescarPedidos();
        }
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

    public void agregarPedidosAComanda(ComandaDTO comanda, List<PedidoDTO> nuevosPedidos) {
        try {
            comanda.getPedidos().addAll(nuevosPedidos);

            comandaFachada.agregarPedidosAComanda(comanda.getId(), nuevosPedidos);

            if (frmComandas != null) {
                frmComandas.setVisible(true);
                frmComandas.actualizarPantalla();
            }
            if (frmProductos != null) {
                frmProductos.dispose();
                frmProductos = null;
            }

            comandaTemporal.clear();

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

    public boolean eliminarComanda(String idComanda, MesaDTO mesa) throws ComandasException {
        return comandaFachada.eliminarComanda(idComanda, mesa);
    }

    public List<Integer> obtenerMesasConComandasListas() {
        try {
            List<ComandaDTO> comandas = comandaFachada.obtenerComandasListas();

            List<Integer> mesas = new ArrayList<>();

            for (ComandaDTO c : comandas) {
                if (!mesas.contains(c.getNumMesa())) {
                    mesas.add(c.getNumMesa());
                }
            }

            return mesas;

        } catch (ComandasException e) {
            return new ArrayList<>();
        }
    }

    public void entregarComanda(String idComanda) throws ComandasException {
        comandaFachada.entregarComanda(idComanda);

        if (frmComandas != null) {
            frmComandas.actualizarPantalla();
        }
    }

    public void abrirEditarComanda(ComandaDTO comanda) {
        comandaTemporal = new ArrayList<>(); // Limpiar temporal
        FrmProductos frm = new FrmProductos(this);
        this.frmProductos = frm; // Guardar referencia
        frm.setMesaAndCliente(comanda.getNumMesa(), comanda.getNombreCliente());
        frm.setModoEdicion(comanda);
        frm.setVisible(true);
    }

    public void abrirResumenEditarComanda(ComandaDTO comandaEdicion) {
        DlgResumenComanda dlg = new DlgResumenComanda(
                this,
                frmProductos,
                comandaEdicion,
                comandaTemporal
        );
        dlg.setVisible(true);
    }

    public List<PedidoDTO> getComandaTemporal() {
        return new ArrayList<>(comandaTemporal);
    }

    public void abrirModificacionPedidoExistente(
            FrmProductos frm,
            PedidoDTO pedidoOriginal,
            ComandaDTO comanda,
            JPanel itemPanel,
            JTextArea txtLista) {

        // Necesitamos los ingredientes removibles del producto
        List<IngredienteEnProductoDTO> removibles = obtenerIngredientesRemovibles(pedidoOriginal.getIdProducto());

        // Creamos un ProductoDTO mínimo para pasarle al diálogo
        ProductoDTO productoTemp = new ProductoDTO();
        productoTemp.setId(pedidoOriginal.getIdProducto());
        productoTemp.setNombre(pedidoOriginal.getNombreProducto());
        productoTemp.setPrecio(pedidoOriginal.getPrecioProducto());

        DlgModificarProducto dlg = new DlgModificarProducto(frm, productoTemp, removibles, pedidoOriginal);
        dlg.setVisible(true);

        PedidoDTO modificado = dlg.getResultado();
        if (modificado != null) {

            pedidoOriginal.setDescripcion(modificado.getDescripcion());
            pedidoOriginal.setCantidad(modificado.getCantidad());
            pedidoOriginal.setPrecioProducto(modificado.getPrecioProducto());

            String desc = (pedidoOriginal.getDescripcion() != null
                    && !pedidoOriginal.getDescripcion().isEmpty())
                    ? pedidoOriginal.getDescripcion().replace(", ", "\n• ")
                    : "";

            txtLista.setText("• " + desc);

            itemPanel.revalidate();
            itemPanel.repaint();
        }
    }

    public List<PedidoDTO> getComandaTemporalReal() {
        return comandaTemporal;
    }

    public void limpiarComandaTemporal() {
        comandaTemporal = new ArrayList<>();
    }

    public void actualizarComanda(ComandaDTO comanda, List<PedidoDTO> nuevosPedidos) {
        try {
            if (nuevosPedidos != null && !nuevosPedidos.isEmpty()) {
                comanda.getPedidos().addAll(nuevosPedidos);
            }

            comandaFachada.actualizarComanda(comanda);
            if (frmComandas != null) {
                frmComandas.setVisible(true);
                frmComandas.actualizarPantalla();
            }
            if (frmProductos != null) {
                frmProductos.dispose();
                frmProductos = null;
            }

            comandaTemporal.clear();

        } catch (ComandasException e) {
            JOptionPane.showMessageDialog(
                    frmComandas,
                    "Error al actualizar la comanda: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    //-----------------------------CU ADMINISTRAR MESAS-------------------------------
    public List<EmpleadoDTO> obtenerMeserosActivos() throws EmpleadosException {
        return empleadoFachada.obtenerMeserosActivos();
    }

    public List<MesaDTO> obtenerMesas() throws MesasException {
        return mesaFachada.obtenerMesas();
    }

    public MesaDTO obtenerMesa(MesaDTO mesa) throws MesasException {
        return mesaFachada.obtenerMesa(mesa);
    }

    public EmpleadoDTO obtenerEmpleadoPorMesa(MesaDTO mesa) throws EmpleadosException {
        return empleadoFachada.obtenerEmpleadoPorMesa(mesa);
    }

    public void agregarMesa(MesaDTO mesa) throws MesasException {
        mesaFachada.agregarMesa(mesa);
    }

    public void setMeseroMesa(EmpleadoDTO mesero, MesaDTO mesa) {
        panInfoMesa = new panInfoMesa();
        panInfoMesa.setInfoMesa(mesero, mesa);
        panInfoMesa.setVisible(true);
    }

    public void eliminarMesa(MesaDTO mesa) throws MesasException {
        mesaFachada.eliminarMesa(mesa);
    }

    public List<EmpleadoDTO> buscar(String filtro) throws EmpleadosException {
        return empleadoFachada.buscarMeserosNombreUser(filtro);
    }

    public void desactivarMesero(EmpleadoDTO mesero) throws EmpleadosException {
        empleadoFachada.desactivarEmpleado(mesero);
    }

    public void pantallaMesas(EmpleadoDTO gerente) {
        frmMesas = new FrmPantallaMesas(this, gerente);
        frmMesas.setVisible(true);
    }

    public void pantallaAsignarMesas(EmpleadoDTO mesero, EmpleadoDTO gerente) {
        frmAsignarMesas = new FrmAsignarMesas(mesero, gerente, this);
        frmAsignarMesas.setVisible(true);
    }

    public List<MesaDTO> obtenerMesasDelMesero(EmpleadoDTO mesero) throws MesasException {
        return mesaFachada.obtenerMesasPorMesero(mesero);
    }

    public List<MesaDTO> obtenerMesasDisponibles() throws MesasException {
        return mesaFachada.obtenerMesasDisponibles();
    }

    public void actualizarMesasDeMesero(List<MesaDTO> mesasAgregar, List<MesaDTO> mesasQuitar, EmpleadoDTO mesero) throws MesasException {
        mesaFachada.actualizarMesasDeMesero(mesasAgregar, mesasQuitar, mesero);
    }

    //-----------------------------CU ADMINISTRAR MESAS-------------------------------
    public void entregarPedido(PedidoDTO pedido) throws ComandasException {

        // comandaFachada.entregarPedido(pedido);
        if (frmComandas != null) {
            frmComandas.actualizarPantalla();
        }
    }

}
