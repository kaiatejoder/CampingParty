package Vista;

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
    private IdentPanel ip;
    
    public ClienteLogin(){
        ip = new IdentPanel();
        this.setLayout(new BorderLayout());
        add(ip, BorderLayout.CENTER);
    }
}

class IdentPanel extends JPanel {
    private final JLabel ident;
    private final JButton reg, inises, volvat;

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
        
        add(centroPanel, BorderLayout.CENTER);
    }
}