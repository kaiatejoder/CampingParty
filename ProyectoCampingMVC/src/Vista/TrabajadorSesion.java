package Vista;

import RiuRauLaf.RiuRauLaf;
import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la interfaz de la ventana de inicio de sesión del trabajador (TrabajadorSesion).
 * Contiene campos para Nombre de Usuario y Contraseña, y botones de acción.
 */
public class TrabajadorSesion extends JFrame {

    // Componentes de la interfaz
    private JLabel labelTitulo;
    private JLabel labelUsuario;
    private JTextField campoUsuario;
    private JLabel labelContrasena;
    private JPasswordField campoContrasena;
    private JButton btnIniciar;
    private JButton btnVolverAtras;

    /**
     * Constructor para inicializar la interfaz.
     * Configura el JFrame y añade los componentes.
     */
    public TrabajadorSesion() {
        RiuRauLaf.setup();
        setTitle("Iniciar Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        inicializarComponentes();
        configurarLayout();
    }

    /**
     * Inicializa todas las instancias de los componentes de Swing.
     */
    private void inicializarComponentes() {
        labelTitulo = new JLabel("Inicia Sesión", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));

        labelUsuario = new JLabel("Nombre de Usuario :");
        campoUsuario = new JTextField("username", 15);

        labelContrasena = new JLabel("Contraseña :");
        campoContrasena = new JPasswordField("password", 15);

        btnIniciar = new JButton("INICIAR");
        btnVolverAtras = new JButton("Volver Atrás");
    }

    /**
     * Configura el layout del JFrame y añade los componentes.
     */
    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(Box.createVerticalStrut(20));
        panelTitulo.add(labelTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        JPanel panelContenido = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panelContenido.add(labelUsuario, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(labelContrasena, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoContrasena, gbc);

        add(panelContenido, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.add(btnIniciar);
        panelBotones.add(btnVolverAtras);

        add(panelBotones, BorderLayout.SOUTH);
    }

    // ============================
    //      GETTERS PARA MVC
    // ============================

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public JButton getBtnVolverAtras() {
        return btnVolverAtras;
    }

    public JTextField getCampoUsuario() {
        return campoUsuario;
    }

    public JPasswordField getCampoContrasena() {
        return campoContrasena;
    }

    /** Devuelve la contraseña como String ya lista para validar */
    public String getPassword() {
        return new String(campoContrasena.getPassword());
    }

    /** Devuelve el nombre de usuario */
    public String getUsuario() {
        return campoUsuario.getText().trim();
    }
}
