package VISTA;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class PanelCentroClienteLogin extends JPanel {
    private JButton b1, b2, b3;

    public PanelCentroClienteLogin() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(30, 30, 30, 30)); 
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 0, 15)); 

        b1 = new JButton("Iniciar Sesión");
        b2 = new JButton("Registrarse");
        b3 = new JButton("Volver Atrás");

        Font fuente = new Font("Helvetica", Font.PLAIN, 16);
        b1.setFont(fuente);
        b2.setFont(fuente);
        b3.setFont(fuente);

        panelBotones.add(b1);
        panelBotones.add(b2);
        panelBotones.add(b3);

        this.add(panelBotones, BorderLayout.CENTER);
    }
}