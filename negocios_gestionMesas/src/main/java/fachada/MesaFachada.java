package fachada;

import control.MesaControl;
import dtos.EmpleadoDTO;
import dtos.MesaDTO;
import excepciones.MesasException;
import interfaz.IGestionMesas;
import java.util.List;

public class MesaFachada implements IGestionMesas{

    private final MesaControl mesaControl;

    public MesaFachada() {
        this.mesaControl = new MesaControl();
    }

    @Override
    public List<MesaDTO> obtenerMesasPorMesero(EmpleadoDTO mesero) throws MesasException {
        return mesaControl.obtenerMesasPorMesero(mesero);
    }

    @Override
    public MesaDTO obtenerMesa(MesaDTO mesa) throws MesasException {
        return mesaControl.obtenerMesaPorId(mesa);
    }

    @Override
    public void eliminarMesa(MesaDTO mesa) throws MesasException {
        mesaControl.eliminarMesa(mesa);
    }

    @Override
    public void agregarMesa(MesaDTO mesa) throws MesasException {
        mesaControl.agregarMesa(mesa);
    }

    @Override
    public void actualizarMesasDeMesero(List<MesaDTO> mesasAsignadas, List<MesaDTO> mesasQuitar, EmpleadoDTO mesero) throws MesasException {
        mesaControl.actualizarMesasDeMesero(mesasAsignadas, mesasQuitar, mesero);
    }

    @Override
    public List<MesaDTO> obtenerMesasDisponibles() throws MesasException {
        return mesaControl.obtenerMesasDisponibles();
    }

    @Override
    public List<MesaDTO> obtenerMesas() throws MesasException {
        return mesaControl.obtenerMesas();
    }
}