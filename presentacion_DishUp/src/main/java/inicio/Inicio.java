package inicio;

import coordinador.CoordinadorInterfaces;
import javax.swing.UIManager;
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
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        coordinador.CoordinadorInterfaces coordinador = new CoordinadorInterfaces();
        FrmInicioSesión frm = new FrmInicioSesión();
        frm.setVisible(true);
        
        System.out.println("prueba de las branches");
    }

}