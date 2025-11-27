package Vista;

import RiuRauLaf.RiuRauLaf;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ClienteLogin extends JFrame {

    private final IdentPanel ip;

    public ClienteLogin() {
        
        super("Acceso Cliente");
        RiuRauLaf.setup();
        ip = new IdentPanel();
        this.setLayout(new BorderLayout());
        this.add(ip, BorderLayout.CENTER);

        // Ajustes básicos de ventana
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 250);
        this.setLocationRelativeTo(null);
    }

    // === GETTERS para que el controlador pueda enganchar listeners ===

    public JButton getBotonRegistrarse() {
        return ip.getBotonRegistrarse();
    }

    public JButton getBotonIniciarSesion() {
        return ip.getBotonIniciarSesion();
    }

    public JButton getBotonVolverAtras() {
        return ip.getBotonVolverAtras();
    }
}

class IdentPanel extends JPanel {

    private final JLabel ident;
    private final JButton reg;
    private final JButton inises;
    private final JButton volvat;

    public IdentPanel() {
        this.setLayout(new BorderLayout(0, 20));

        JPanel centroPanel = new JPanel(new GridLayout(3, 1, 0, 10));

        ident = new JLabel("IDENTIFÍQUESE O REGÍSTRESE");
        ident.setHorizontalAlignment(SwingConstants.CENTER);
        centroPanel.add(ident);

        JPanel botonesPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        reg = new JButton("Registrarse");
        inises = new JButton("Iniciar Sesión");
        botonesPanel.add(reg);
        botonesPanel.add(inises);
        centroPanel.add(botonesPanel);

        volvat = new JButton("Volver Atrás");
        centroPanel.add(volvat);

        this.add(centroPanel, BorderLayout.CENTER);
    }

    // Getters para que ClienteLogin los exponga al controlador

    public JButton getBotonRegistrarse() {
        return reg;
    }

    public JButton getBotonIniciarSesion() {
        return inises;
    }

    public JButton getBotonVolverAtras() {
        return volvat;
    }
}
