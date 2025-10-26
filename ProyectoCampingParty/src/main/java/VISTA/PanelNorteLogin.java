package VISTA;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Sergio Gimenez Gomez
 */
public class PanelNorteLogin extends JPanel{
    private JLabel label;
    
    public PanelNorteLogin(){
        label = new JLabel("CAMPING RIURAU");
        label.setFont(new Font("Helvetica", Font.BOLD, 20));
        add(label);
    }
}