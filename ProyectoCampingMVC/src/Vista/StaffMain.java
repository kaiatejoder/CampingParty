package Vista;

/**
 *
 * @author Sergio Gimenez Gomez
 */
import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la interfaz principal del Staff (StaffMain).
 * Contiene dos pestañas: Reservas y Actividades.
 */
public class StaffMain extends JFrame {

    private JLabel labelSaludo;
    private JTabbedPane tabbedPane;

    // Componentes de la Pestaña "Reservas"
    private JLabel labelReservas;
    private JButton btnRegistrarEntrada;
    private JButton btnAdministrarReservas;
    private JButton btnCambiarDescuento;
    private JLabel labelEstadoActual;
    private JPanel panelCuadriculaEstado;

    // Componentes de la Pestaña "Actividades"
    private JLabel labelActividades;
    private JLabel labelSeleccionaFecha;
    //private JCalendar calendar; // Usaremos un JCalendar simulado o un panel para el calendario
    private JButton btnAgregarActividad;
    private JLabel labelMiDia;
    private JTable tablaMiDia;
    private JScrollPane scrollTablaMiDia;

    /**
     * Constructor para inicializar la interfaz principal del Staff.
     */
    public StaffMain() {
        // Configuración básica del JFrame
        setTitle("Sistema Staff");
        setSize(800, 600); // Tamaño adecuado para contener ambas vistas
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Inicializar y configurar la estructura principal
        inicializarComponentes();
        configurarLayoutPrincipal();
    }

    /**
     * Inicializa todos los componentes de Swing.
     */
    private void inicializarComponentes() {
        // --- Saludo ---
        labelSaludo = new JLabel("¡Hola, Staff!");
        labelSaludo.setFont(new Font("SansSerif", Font.BOLD, 36));

        // --- Pestañas ---
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Reservas", crearPanelReservas());
        tabbedPane.addTab("Actividades", crearPanelActividades());
    }

    /**
     * Configura el layout principal del JFrame (solo Saludo y Pestañas).
     */
    private void configurarLayoutPrincipal() {
        setLayout(new BorderLayout(10, 10));

        // Panel para el Saludo (Arriba)
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorte.add(labelSaludo);

        add(panelNorte, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Crea y configura la Pestaña "Reservas".
     * @return El JPanel configurado para la pestaña Reservas.
     */
    private JPanel crearPanelReservas() {
        JPanel panelReservas = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // --- Componentes de Reservas ---
        labelReservas = new JLabel("Reservas");
        labelReservas.setFont(new Font("SansSerif", Font.BOLD, 24));
        btnRegistrarEntrada = new JButton("Registrar Entrada");
        btnAdministrarReservas = new JButton("Administrar Reservas");
        btnCambiarDescuento = new JButton("Cambiar descuento");
        labelEstadoActual = new JLabel("Estado actual");
        labelEstadoActual.setFont(new Font("SansSerif", Font.PLAIN, 18));
        
        // Cuadrícula de Estado (Simulación de 4x4)
        panelCuadriculaEstado = new JPanel(new GridLayout(4, 4, 5, 5));
        for (int i = 0; i < 16; i++) {
            // Se añaden JPanels vacíos con borde para simular las celdas
            JPanel celda = new JPanel();
            celda.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panelCuadriculaEstado.add(celda);
        }

        // --- Posicionamiento con GridBagLayout ---
        
        // 1. Título "Reservas"
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        panelReservas.add(labelReservas, gbc);
        
        // 2. Título "Estado actual"
        gbc.gridx = 1; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panelReservas.add(labelEstadoActual, gbc);
        
        // 3. Botones (Columna 0)
        gbc.gridx = 0; gbc.gridy = 1; gbc.weighty = 0; // No se estira verticalmente
        panelReservas.add(btnRegistrarEntrada, gbc);
        
        gbc.gridy = 2;
        panelReservas.add(btnAdministrarReservas, gbc);
        
        gbc.gridy = 3;
        panelReservas.add(btnCambiarDescuento, gbc);
        
        // 4. Cuadrícula de Estado (Columna 1)
        gbc.gridx = 1; gbc.gridy = 1; 
        gbc.gridwidth = 1; // Ocupa 1 columna
        gbc.gridheight = 4; // Ocupa las filas de los botones
        gbc.weightx = 1.0; // Se expande horizontalmente
        gbc.weighty = 1.0; // Se expande verticalmente
        gbc.fill = GridBagConstraints.BOTH;
        panelReservas.add(panelCuadriculaEstado, gbc);

        return panelReservas;
    }

    /**
     * Crea y configura la Pestaña "Actividades".
     * @return El JPanel configurado para la pestaña Actividades.
     */
    private JPanel crearPanelActividades() {
        JPanel panelActividades = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // --- Componentes de Actividades ---
        labelActividades = new JLabel("Actividades");
        labelActividades.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        labelSeleccionaFecha = new JLabel("Selecciona fecha");
        btnAgregarActividad = new JButton("Agregar actividad");
        
        labelMiDia = new JLabel("Mi día");
        labelMiDia.setFont(new Font("SansSerif", Font.PLAIN, 18));
        
        // Tabla de Actividades (Mi día)
        String[] columnas = {"Hora", "Actividad"};
        Object[][] datos = {}; // Inicialmente vacía
        tablaMiDia = new JTable(new MyTableModel(datos, columnas)); // Usamos un TableModel simple
        tablaMiDia.setFillsViewportHeight(true);
        scrollTablaMiDia = new JScrollPane(tablaMiDia);

        // Calendario (Simulación con un JPanel con borde para el contexto)
        // Requeriría una librería externa (como JCalendar) para la funcionalidad completa,
        // pero para la IU, creamos un panel simulado.
        JPanel panelCalendarioSimulado = crearPanelCalendario(); 

        // --- Posicionamiento con GridBagLayout ---
        
        // 1. Título "Actividades"
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1; gbc.weightx = 0;
        panelActividades.add(labelActividades, gbc);
        
        // 2. Título "Mi día"
        gbc.gridx = 1; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        panelActividades.add(labelMiDia, gbc);
        
        // 3. Etiqueta "Selecciona fecha"
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0;
        panelActividades.add(labelSeleccionaFecha, gbc);

        // 4. Panel Calendario Simulado
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridheight = 1;
        gbc.weightx = 0; gbc.weighty = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelActividades.add(panelCalendarioSimulado, gbc);
        
        // 5. Botón "Agregar actividad"
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets.top = 20; // Más espacio arriba
        panelActividades.add(btnAgregarActividad, gbc);
        
        // 6. Tabla (Mi día)
        gbc.gridx = 1; gbc.gridy = 1; 
        gbc.gridwidth = 1; 
        gbc.gridheight = 4; // Ocupa desde la fila del título hasta abajo
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        panelActividades.add(scrollTablaMiDia, gbc);

        return panelActividades;
    }
    
    /**
     * Crea un panel que simula la vista del calendario con los botones.
     * En una aplicación real, se usaría un componente de calendario.
     */
    private JPanel crearPanelCalendario() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Simulación de la barra de mes/año
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(new JComboBox<>(new String[]{"noviembre"}));
        topPanel.add(new JSpinner(new SpinnerNumberModel(2025, 2000, 2100, 1)));
        panel.add(topPanel, BorderLayout.NORTH);

        // Simulación de la cuadrícula de días
        JPanel gridPanel = new JPanel(new GridLayout(6, 7, 1, 1));
        String[] diasSemana = {"lun", "mar", "mié", "jue", "vie", "sáb", "dom"};
        for (String dia : diasSemana) {
            JLabel label = new JLabel(dia, SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.BOLD, 10));
            gridPanel.add(label);
        }
        
        // Añadir números de días (solo para la estructura visual)
        for (int i = 1; i <= 30; i++) {
            JButton dayButton = new JButton(String.valueOf(i));
            if (i == 15) { // Simular el día seleccionado
                dayButton.setBackground(Color.GRAY);
                dayButton.setForeground(Color.WHITE);
            }
            dayButton.setMargin(new Insets(2, 2, 2, 2)); // Botones más pequeños
            dayButton.setPreferredSize(new Dimension(30, 20)); // Tamaño fijo
            gridPanel.add(dayButton);
        }
        
        panel.add(gridPanel, BorderLayout.CENTER);
        
        // Ajustar el tamaño del panel simulado
        panel.setPreferredSize(new Dimension(300, 200)); 
        return panel;
    }
    
    // Clase simple para el TableModel (necesaria para JTable)
    class MyTableModel extends javax.swing.table.AbstractTableModel {
        private String[] columnNames;
        private Object[][] data;

        public MyTableModel(Object[][] data, String[] columnNames) {
            this.data = data;
            this.columnNames = columnNames;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
    }
}