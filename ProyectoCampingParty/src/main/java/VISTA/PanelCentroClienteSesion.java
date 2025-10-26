package VISTA;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class PanelCentroClienteSesion extends JPanel {
    private JLabel lab1, lab2;
    private JTextField un;
    private JPasswordField c;
    private JButton i, va;

    public PanelCentroClienteSesion() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        Font fuente = new Font("Helvetica", Font.PLAIN, 14);

        lab1 = new JLabel("Username:");
        lab1.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(lab1, gbc);

        un = new JTextField();
        un.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(un, gbc);

        lab2 = new JLabel("Password:");
        lab2.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(lab2, gbc);

        c = new JPasswordField();
        c.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(c, gbc);

        i = new JButton("Iniciar");
        i.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(i, gbc);

        va = new JButton("Volver Atrás");
        va.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(va, gbc);
    }
}
