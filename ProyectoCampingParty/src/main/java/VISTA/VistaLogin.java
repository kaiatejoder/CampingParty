package VISTA;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class VistaLogin extends JFrame {
    private PanelNorteLogin pnl;
    private PanelCentroLogin pcl;
    
    public VistaLogin(){
        this.setLayout(new BorderLayout(20, 20));
        
        setSize(300, 300);
        setTitle("Ventana de Login");
        setFont(new Font("Helvetica", Font.BOLD, 20));
        setResizable(true);
        
        pnl = new PanelNorteLogin();
        pcl = new PanelCentroLogin();
        
        add(pnl, BorderLayout.NORTH);
        add(pcl, BorderLayout.CENTER); 
        
        //this.setVisible(true);
    }
}