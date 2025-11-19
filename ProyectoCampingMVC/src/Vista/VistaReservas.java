package Vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que representa la interfaz de la ventana de gestión de Reservas para el Staff (VistaReservas).
 * Contiene una tabla de reservas y botones de acción.
 */
public class VistaReservas extends JFrame {

    // Componentes de la interfaz
    private JLabel labelTitulo;
    public JTable tablaReservas;
    private JScrollPane scrollTabla;
    public JButton btnCheckIn;
    private JButton btnVolverAtras;

    /**
     * Constructor para inicializar la interfaz.
     * Configura el JFrame y añade los componentes.
     */
    public VistaReservas() {
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
        // Datos de ejemplo
        Object[][] datos = {
            {"R001", "Ana García"},
            {"R002", "Javier López"},
            {"R003", "Marta Pérez"}
        };
        
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
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
        
        // Panel de la Tabla (Izquierda/Centro)
        panelCentral.add(scrollTabla, BorderLayout.CENTER);
        
        // Panel de Botones (Derecha)
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        
        // Ajustar el tamaño de los botones
        Dimension buttonSize = new Dimension(150, 30);
        
        btnCheckIn.setMaximumSize(buttonSize);
        btnVolverAtras.setMaximumSize(buttonSize);

        // Añadir espacio y centrar los botones verticalmente
        panelBotones.add(Box.createVerticalGlue());
        btnCheckIn.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBotones.add(btnCheckIn);
        
        panelBotones.add(Box.createVerticalStrut(10)); // Espacio entre botones
        
        btnVolverAtras.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBotones.add(btnVolverAtras);
        
        panelBotones.add(Box.createVerticalGlue());

        // Añadir el panel de botones a la derecha del panel central, con margen
        JPanel panelEste = new JPanel(new BorderLayout());
        panelEste.add(panelBotones, BorderLayout.NORTH); // Usar NORTH para anclar los botones arriba
        panelEste.add(Box.createVerticalGlue(), BorderLayout.CENTER); // Rellenar el resto del espacio
        panelEste.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20)); // Margen derecho
        
        panelCentral.add(panelEste, BorderLayout.EAST);
        
        // Añadir margen al panel central
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0)); 

        add(panelCentral, BorderLayout.CENTER);
    }

}