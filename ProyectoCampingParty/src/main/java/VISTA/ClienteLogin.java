package VISTA;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ClienteLogin extends JFrame{
    private PanelCentroClienteLogin pc;
    
    public ClienteLogin(){
        this.setLayout(new BorderLayout());
        
        setSize(300, 300);
        this.setTitle("Cliente Login");
        this.setFont(new Font("Helvetica", Font.BOLD, 20));
        this.setResizable(true);
        
        pc = new PanelCentroClienteLogin();
        add(pc,BorderLayout.CENTER);
        //this.setVisible(true);
    }
}