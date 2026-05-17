/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package pantallas.AdministrarMesas;

import coordinador.CoordinadorInterfaces;
import dtos.EmpleadoDTO;
import enums.EstadoEmpleadoDTO;
import enums.RolEmpleadoDTO;
import excepciones.EmpleadosException;

/**
 *
 * @author Alejandra Leal Armenta, 262719
 */
public class mainPrueba_cuALE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EmpleadosException {
        coordinador.CoordinadorInterfaces coordinador = new CoordinadorInterfaces();
        EmpleadoDTO gerente = new EmpleadoDTO(
            "6a0a17cace6b3d935f8e25ec",
            "Juan",
            "Pérez",
            "Hernández",
            "GE-001",
            RolEmpleadoDTO.GERENTE,
            EstadoEmpleadoDTO.ACTIVO
        );
        
        EmpleadoDTO g = coordinador.validarExistenciaUsuario(gerente);
        FrmPantallaMesas frm = new FrmPantallaMesas(coordinador, g);
        frm.setVisible(true);
    }

}
