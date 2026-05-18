package pantallas;

import coordinador.CoordinadorInterfaces;
import dtos.PedidoDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author DishUp
 */
public class DlgDetallePedido extends JDialog {

    private PedidoDTO pedido;

    private CoordinadorInterfaces coordinador;

    public DlgDetallePedido(java.awt.Frame parent, boolean modal, PedidoDTO pedido, CoordinadorInterfaces coordinador) {
        super(parent, modal);
        this.pedido = pedido;
        this.coordinador = coordinador;

        setSize(450, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);

        add(panelPrincipal);

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.decode("#FFDA92"));
        header.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Detalle del pedido");
        titulo.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(titulo, BorderLayout.CENTER);

        panelPrincipal.add(header, BorderLayout.NORTH);

        // BODY
        JPanel body = new JPanel();
        body.setBackground(Color.WHITE);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(BorderFactory.createEmptyBorder(25, 25, 20, 25));

        JLabel lblNombre = new JLabel(pedido.getNombreProducto());
        lblNombre.setForeground(Color.decode("#F56464"));
        lblNombre.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

        LocalDateTime fecha = LocalDateTime.parse(pedido.getFechaPedido().toString());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        String fechaFormateada = fecha.format(formato);
        JLabel lblHora = new JLabel("Hora de solicitud: " + fechaFormateada);
        lblHora.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));

        JTextArea txtEspecificaciones = new JTextArea();

        String descripcion = pedido.getDescripcion();

        if (descripcion == null || descripcion.trim().isEmpty()) {
            txtEspecificaciones.setText("• Sin especificaciones");
        } else {

            String[] especificaciones = descripcion.split(",");

            String texto = "";

            for (String esp : especificaciones) {
                texto += "• " + esp.trim() + "\n";
            }

            txtEspecificaciones.setText(texto);
        }

        txtEspecificaciones.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        txtEspecificaciones.setEditable(false);
        txtEspecificaciones.setLineWrap(true);
        txtEspecificaciones.setWrapStyleWord(true);
        txtEspecificaciones.setBackground(Color.decode("#F5F5F5"));
        txtEspecificaciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(txtEspecificaciones);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(190, 50));

        JLabel lblPrecio = new JLabel("Precio: $" + pedido.getPrecioProducto());
        lblPrecio.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));

        String estadoTexto = pedido.getEstado().toString();

        if (estadoTexto.equals("EN_PREPARACION")) {
            estadoTexto = "EN PREPARACIÓN";
        } else if (estadoTexto.equals("LISTA")) {
            estadoTexto = "LISTO";
        }

        JLabel lblEstado = new JLabel("Estado: " + estadoTexto);
        lblEstado.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        lblEstado.setForeground(Color.decode("#000000"));

        body.add(lblNombre);
        body.add(Box.createVerticalStrut(10));

        body.add(scroll);
        body.add(Box.createVerticalStrut(20));

        body.add(lblPrecio);
        body.add(Box.createVerticalStrut(10));

        body.add(lblHora);
        body.add(Box.createVerticalStrut(10));

        body.add(lblEstado);

        panelPrincipal.add(body, BorderLayout.CENTER);

        // FOOTER
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        footer.setBackground(Color.WHITE);

        panelPrincipal.add(footer, BorderLayout.SOUTH);
    }
}
