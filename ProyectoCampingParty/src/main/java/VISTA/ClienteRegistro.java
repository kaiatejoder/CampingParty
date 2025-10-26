package VISTA;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ClienteRegistro extends JFrame{
    private PanelCentroClienteRegistro pcr;
    
    public ClienteRegistro(){
        this.setLayout(new BorderLayout());
        
        setSize(400, 400);
        this.setTitle("Cliente Registro");
        this.setFont(new Font("Helvetica", Font.BOLD, 20));
        this.setResizable(true);
        
        pcr = new PanelCentroClienteRegistro();
        add(pcr,BorderLayout.CENTER);
        
        //this.setVisible(true);
    }
}