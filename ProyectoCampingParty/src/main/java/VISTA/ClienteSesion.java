package VISTA;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;

/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ClienteSesion extends JFrame{
    private PanelCentroClienteSesion pcs;
    
    public ClienteSesion(){
        this.setLayout(new BorderLayout());
        
        setSize(300, 300);
        this.setTitle("Cliente Sesion");
        this.setFont(new Font("Helvetica", Font.BOLD, 20));
        this.setResizable(true);
        
        pcs = new PanelCentroClienteSesion();
        add(pcs);
        
        this.setVisible(true);
    }
}