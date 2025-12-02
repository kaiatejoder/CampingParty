package Vista;

import RiuRauLaf.RiuRauLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que representa la interfaz de la ventana de gestión de Reservas (VistaReservas).
 * Contiene una tabla de reservas y botones de acción.
 */
public class VistaReservas extends JFrame {

    // Componentes de la interfaz
    private JLabel labelTitulo;
    private JTable tablaReservas;
    private JScrollPane scrollTabla;
    private JButton btnCheckIn;
    private JButton btnVolverAtras;

    /**
     * Constructor para inicializar la interfaz.
     * Configura el JFrame y añade los componentes.
     */
    public VistaReservas() {
        RiuRauLaf.setup();
        // Configuración básica del JFrame
        setTitle("Reservas");
        setSize(550, 400); // Tamaño adecuado
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setResizable(true);

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
        labelTitulo = new JLabel("Reservas");
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));

        // Tabla de Reservas
        String[] columnas = {"IDReserva", "Cliente"};
        // Datos de ejemplo (el controlador luego podrá cambiarlos)
        Object[][] datos = {
            {"R001", "Ana García"},
            {"R002", "Javier López"},
            {"R003", "Marta Pérez"}
        };

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // que no se pueda editar desde la tabla
            }
        };

        tablaReservas = new JTable(model);
        tablaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollTabla = new JScrollPane(tablaReservas);

        // Botones
        btnCheckIn = new JButton("Hacer Check-in");
        btnVolverAtras = new JButton("Volver atrás");
    }

    /**
     * Configura el layout del JFrame y añade los componentes.
     */
    private void configurarLayout() {
        // Usamos un BorderLayout para el marco principal.
        setLayout(new BorderLayout(15, 15));

        // 1. Título (Norte)
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelNorte.add(labelTitulo);
        add(panelNorte, BorderLayout.NORTH);

        // 2. Panel Central: Tabla y Botones Laterales
        JPanel panelCentral = new JPanel(new BorderLayout(15, 15));

        // Panel de la Tabla (Centro)
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        // Panel de Botones (Derecha)
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        // Ajustar el tamaño de los botones
        Dimension buttonSize = new Dimension(150, 30);

        btnCheckIn.setMaximumSize(buttonSize);
        btnVolverAtras.setMaximumSize(buttonSize);

        panelBotones.add(Box.createVerticalGlue());
        btnCheckIn.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBotones.add(btnCheckIn);

        panelBotones.add(Box.createVerticalStrut(10)); // Espacio entre botones

        btnVolverAtras.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBotones.add(btnVolverAtras);

        panelBotones.add(Box.createVerticalGlue());

        JPanel panelEste = new JPanel(new BorderLayout());
        panelEste.add(panelBotones, BorderLayout.NORTH);
        panelEste.add(Box.createVerticalGlue(), BorderLayout.CENTER);
        panelEste.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        panelCentral.add(panelEste, BorderLayout.EAST);

        // Añadir margen al panel central
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));

        add(panelCentral, BorderLayout.CENTER);
    }

    // ================== GETTERS PARA EL CONTROLADOR ==================

    public JTable getTablaReservas() {
        return tablaReservas;
    }

    public JButton getBtnCheckIn() {
        return btnCheckIn;
    }

    public JButton getBtnVolverAtras() {
        return btnVolverAtras;
    }

    /** Permite al controlador actualizar el modelo de la tabla con reservas reales del Modelo. */
    public void setModeloTabla(DefaultTableModel modelo) {
        tablaReservas.setModel(modelo);
    }
}
