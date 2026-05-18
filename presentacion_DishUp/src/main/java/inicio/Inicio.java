package inicio;

import coordinador.CoordinadorInterfaces;
import pantallas.FrmInicioSesión;

/**
 *
 * @author DishUp
 */
public class Inicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        coordinador.CoordinadorInterfaces coordinador = new CoordinadorInterfaces();
        FrmInicioSesión frm = new FrmInicioSesión();
        frm.setVisible(true);
        
        System.out.println("prueba de las branches");
    }

}