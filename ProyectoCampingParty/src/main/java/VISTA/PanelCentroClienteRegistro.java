package VISTA;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class PanelCentroClienteRegistro extends JPanel {

    private JLabel labUsername, labPass1, labPass2, labDni;
    private JTextField tfUsername, tfDni;
    private JPasswordField pfPass1, pfPass2;
    private JButton bRegistrar, bVolver;

    public PanelCentroClienteRegistro() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        Font fuente = new Font("Helvetica", Font.PLAIN, 14);

        labUsername = new JLabel("Username:");
        labUsername.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(labUsername, gbc);

        tfUsername = new JTextField();
        tfUsername.setFont(fuente);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(tfUsername, gbc);

        labPass1 = new JLabel("Crear Contraseña:");
        labPass1.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labPass1, gbc);

        pfPass1 = new JPasswordField();
        pfPass1.setFont(fuente);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(pfPass1, gbc);

        labPass2 = new JLabel("Confirmar Contraseña:");
        labPass2.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labPass2, gbc);

        pfPass2 = new JPasswordField();
        pfPass2.setFont(fuente);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(pfPass2, gbc);

        labDni = new JLabel("DNI:");
        labDni.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labDni, gbc);

        tfDni = new JTextField();
        tfDni.setFont(fuente);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(tfDni, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        bRegistrar = new JButton("Registrar");
        bVolver = new JButton("Volver Atrás");
        bRegistrar.setFont(fuente);
        bVolver.setFont(fuente);
        panelBotones.add(bRegistrar);
        panelBotones.add(bVolver);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(panelBotones, gbc);
    }
}