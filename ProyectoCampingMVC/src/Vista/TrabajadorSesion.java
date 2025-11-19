package Vista;

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
        // Configuración básica del JFrame
        setTitle("Iniciar Sesión");
        setSize(400, 300); // Tamaño adecuado
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setResizable(false); // Mantener el tamaño fijo como en la imagen

        // Inicializar componentes
        inicializarComponentes();

        // Configurar el layout y añadir paneles
        configurarLayout();
    }

    /**
     * Inicializa todas las instancias de los componentes de Swing.
     */
    private void inicializarComponentes() {
        // Título
        labelTitulo = new JLabel("Inicia Sesión", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));

        // Campos de entrada
        labelUsuario = new JLabel("Nombre de Usuario :");
        campoUsuario = new JTextField("username", 15); // Texto de ejemplo
        
        labelContrasena = new JLabel("Contraseña :");
        campoContrasena = new JPasswordField("password", 15); // Texto de ejemplo

        // Botones
        btnIniciar = new JButton("INICIAR");
        btnVolverAtras = new JButton("Volver Atrás");
    }

    /**
     * Configura el layout del JFrame y añade los componentes.
     */
    private void configurarLayout() {
        // Usamos un BorderLayout para el marco principal (título arriba y contenido abajo).
        setLayout(new BorderLayout(10, 10));

        // 1. Título (Norte)
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(Box.createVerticalStrut(20)); // Espacio superior
        panelTitulo.add(labelTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        // 2. Panel de Contenido Central (GridBagLayout para el formulario)
        JPanel panelContenido = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda por defecto

        // --- Fila 1: Nombre de Usuario ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelContenido.add(labelUsuario, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoUsuario, gbc);

        // --- Fila 2: Contraseña ---
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(labelContrasena, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoContrasena, gbc);
        
        // Añadir el panel de contenido al centro del JFrame
        add(panelContenido, BorderLayout.CENTER);

        // 3. Panel de Botones (Sur)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.add(btnIniciar);
        panelBotones.add(btnVolverAtras);

        add(panelBotones, BorderLayout.SOUTH);

        // Nota: La llamada a setVisible(true) debe hacerse desde la clase principal (main).
    }

}