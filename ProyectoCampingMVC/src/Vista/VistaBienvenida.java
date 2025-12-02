package Vista;

import RiuRauLaf.RiuRauLaf;
import com.formdev.flatlaf.FlatLightLaf;
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
public class VistaBienvenida extends JFrame {
    private final BienvenidaPanel bp;

    public VistaBienvenida() {
        
        super("Bienvenida");
        RiuRauLaf.setup();
        bp = new BienvenidaPanel();

        this.setLayout(new BorderLayout());
        this.add(bp, BorderLayout.CENTER);

        // Ajustes básicos de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
    }

    // === GETTERS para que el controlador pueda enganchar listeners ===

    public JButton getBotonCliente() {
        return bp.getBotonCliente();
    }

    public JButton getBotonTrabajador() {
        return bp.getBotonTrabajador();
    }
}

class BienvenidaPanel extends JPanel {
    private final JButton cliente;
    private final JButton trabajador;
    private final JLabel bienvenida;

    public BienvenidaPanel() {
        // Un único layout: GridLayout con 3 filas (texto + 2 botones)
        this.setLayout(new GridLayout(3, 1, 0, 10));

        bienvenida = new JLabel("¡Os damos la bienvenida!", SwingConstants.CENTER);
        this.add(bienvenida);

        cliente = new JButton("Soy Cliente");
        this.add(cliente);

        trabajador = new JButton("Soy Staff");
        this.add(trabajador);
    }

    // Getters para que la VistaBienvenida pueda exponerlos al controlador

    public JButton getBotonCliente() {
        return cliente;
    }

    public JButton getBotonTrabajador() {
        return trabajador;
    }
}
