package Vista;

/**
 *
 * @author Sergio Gimenez Gomez
 */
import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la interfaz de la ventana de inicio de sesión para el personal (TrabajadorLogin).
 * Contiene un título y dos botones.
 */
public class TrabajadorLogin extends JFrame {

    // Componentes de la interfaz
    private JLabel labelTitulo;
    private JButton btnIniciarSesion;
    private JButton btnVolverAtras;

    /**
     * Constructor para inicializar la interfaz.
     * Configura el JFrame y añade los componentes.
     */
    public TrabajadorLogin() {
        // Configuración básica del JFrame
        setTitle("Identificación");
        setSize(350, 250); // Tamaño ajustado para una ventana simple
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setResizable(false);

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
        labelTitulo = new JLabel("Identifícate", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));

        // Botones
        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnVolverAtras = new JButton("Volver Atrás");

        // Ajustar el tamaño preferido de los botones para que se vean grandes y centrados
        Dimension buttonSize = new Dimension(150, 35);
        btnIniciarSesion.setPreferredSize(buttonSize);
        btnIniciarSesion.setMaximumSize(buttonSize);
        btnVolverAtras.setPreferredSize(buttonSize);
        btnVolverAtras.setMaximumSize(buttonSize);
    }

    /**
     * Configura el layout del JFrame y añade los componentes.
     */
    private void configurarLayout() {
        // Usamos un BorderLayout para el JFrame principal.
        setLayout(new BorderLayout());

        // El panel principal usará BoxLayout para apilar los elementos verticalmente
        // y centrar el conjunto.
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        // Añadir espacio, título y más espacio
        panelContenido.add(Box.createVerticalStrut(20));
        
        // Centrar el título horizontalmente
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(labelTitulo);
        panelContenido.add(panelTitulo);
        
        panelContenido.add(Box.createVerticalStrut(30)); // Espacio entre título y botones

        // Los botones se centran porque tienen su tamaño máximo/preferido definido.
        btnIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(btnIniciarSesion);
        
        panelContenido.add(Box.createVerticalStrut(20)); // Espacio entre botones
        
        btnVolverAtras.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(btnVolverAtras);
        
        panelContenido.add(Box.createVerticalGlue()); // Espacio elástico al final

        // Añadir el panel de contenido al centro del JFrame
        add(panelContenido, BorderLayout.CENTER);
    }
}