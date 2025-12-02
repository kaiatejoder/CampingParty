package Vista;

import RiuRauLaf.RiuRauLaf;
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
        RiuRauLaf.setup();
        // Configuración básica del JFrame
        setTitle("Identificación");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
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

        // Tamaño de botones
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
        setLayout(new BorderLayout());

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        panelContenido.add(Box.createVerticalStrut(20));

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(labelTitulo);
        panelContenido.add(panelTitulo);

        panelContenido.add(Box.createVerticalStrut(30));

        btnIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(btnIniciarSesion);

        panelContenido.add(Box.createVerticalStrut(20));

        btnVolverAtras.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(btnVolverAtras);

        panelContenido.add(Box.createVerticalGlue());

        add(panelContenido, BorderLayout.CENTER);
    }

    // ================== GETTERS PARA EL CONTROLADOR ==================

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public JButton getBtnVolverAtras() {
        return btnVolverAtras;
    }
}
