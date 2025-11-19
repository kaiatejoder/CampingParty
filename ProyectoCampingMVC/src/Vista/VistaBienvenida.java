package Vista;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import com.formdev.flatlaf.FlatLightLaf;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class VistaBienvenida extends JFrame {
    private BienvenidaPanel bp;
    
    public VistaBienvenida(){
        FlatLightLaf.setup();
        bp = new BienvenidaPanel();
        this.setLayout(new BorderLayout());
        this.setSize(300,500);
        add(bp, BorderLayout.CENTER);
    }
}

class BienvenidaPanel extends JPanel {
    private final JButton cliente, trabajador;
    private final JLabel bienvenida;
    
    public BienvenidaPanel(){
        
        this.setLayout(new GridBagLayout());
        
        this.setLayout(new GridLayout(3, 1, 20, 10));
        
        bienvenida = new JLabel("¡Os damos la bienvenida!", SwingConstants.CENTER);
        bienvenida.setFont((new java.awt.Font("HelveticaNowDisplay Medium", 0, 18))); 
        add(bienvenida);
        
        cliente = new JButton("Soy Cliente");
        cliente.setFont((new java.awt.Font("HelveticaNowDisplay Medium", 0, 18))); 
        add(cliente);

        trabajador = new JButton("Soy Staff");
        trabajador.setFont((new java.awt.Font("HelveticaNowDisplay Medium", 0, 18))); 
        add(trabajador);
    }
}
