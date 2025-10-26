package VISTA;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class PanelCentroLogin extends JPanel {
    private JLabel label;
    private JButton b1, b2;
    
    public PanelCentroLogin() {
        this.setLayout(new BorderLayout()); 
        JPanel panelContenido = new JPanel(new GridLayout(3, 1, 0, 15)); 
        
        label = new JLabel("¡BIENVENIDO/A!", SwingConstants.CENTER);
        label.setFont(new Font("Helvetica", Font.BOLD, 18));
        b1 = new JButton("Soy Cliente");
        b1.setFont(new Font("Helvetica", Font.BOLD, 15));
        b2 = new JButton("Soy Trabajador");
        b2.setFont(new Font("Helvetica", Font.BOLD, 15));
        
        panelContenido.add(label);
        panelContenido.add(b1);
        panelContenido.add(b2);
        
        JPanel panelCentrado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        panelCentrado.add(panelContenido);
        
        this.add(panelCentrado, BorderLayout.CENTER); 
    }
}
