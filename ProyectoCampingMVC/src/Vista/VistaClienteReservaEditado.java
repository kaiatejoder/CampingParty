package Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la interfaz para el proceso de Modificación/Nueva Reserva (VistaClienteReservaEditado).
 * Utiliza un JTabbedPane para gestionar los pasos: Fechas, Parcelas, Miembros y Confirmación.
 */
public class VistaClienteReservaEditado extends JFrame {

    private JLabel labelTitulo;
    private JTabbedPane tabbedPane;

    /**
     * Constructor para inicializar la interfaz.
     */
    public VistaClienteReservaEditado() {
        // Configuración básica del JFrame
        setTitle("Nueva reserva");
        setSize(800, 600); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        setResizable(true);

        // Inicializar componentes
        inicializarComponentes();

        // Configurar el layout y añadir paneles
        configurarLayout();
    }

    /**
     * Inicializa todos los componentes de Swing.
     */
    private void inicializarComponentes() {
        labelTitulo = new JLabel("Nueva reserva");
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        tabbedPane = new JTabbedPane();
        
        // Crear el contenido de cada pestaña
        tabbedPane.addTab("Fecha de la reserva", crearPanelFechas());
        tabbedPane.addTab("Parcelas", crearPanelParcelas());
        tabbedPane.addTab("Miembros", crearPanelMiembros());
        tabbedPane.addTab("Confirmación", crearPanelConfirmacion());
    }

    /**
     * Configura el layout principal del JFrame.
     */
    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        // Título "Nueva reserva" fuera del JTabbedPane
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelNorte.add(labelTitulo);
        add(panelNorte, BorderLayout.NORTH);
        
        // Pestañas
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    // =========================================================================
    // Métodos para crear cada Pestaña (Implementación del asistente de reserva)
    // =========================================================================

    /** Crea el contenido de la pestaña "Fecha de la reserva" (image_2f4e83.png). */
    private JPanel crearPanelFechas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel panelContenido = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel labelFechas = new JLabel("Fechas");
        labelFechas.setFont(new Font("SansSerif", Font.BOLD, 20));
        
        JLabel labelSePuede = new JLabel("¡Se puede");
        labelSePuede.setFont(new Font("SansSerif", Font.ITALIC, 14));
        
        // Panel que simula el calendario (más detallado que la versión anterior)
        JPanel panelCalendarioSimulado = crearPanelCalendarioFechas();
        
        // Posicionamiento con GridBagLayout
        
        // Título "Fechas"
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(labelFechas, gbc);
        
        // Panel Calendario
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridheight = 2; gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 0.0; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(panelCalendarioSimulado, gbc);
        
        // ¡Se puede
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridheight = 1; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.NORTHWEST;
        panelContenido.add(labelSePuede, gbc);
        
        panel.add(panelContenido, BorderLayout.CENTER);
        
        // Botones Inferiores
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnSiguiente = new JButton("Siguiente");
        panelBotones.add(btnCancelar);
        panelBotones.add(btnSiguiente);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    /** Crea el panel que simula el calendario con las entradas de fecha y salida. */
    private JPanel crearPanelCalendarioFechas() {
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // 1. Cabecera Mes/Año
        JPanel panelCabecera = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCabecera.add(new JLabel("<"));
        panelCabecera.add(new JLabel("noviembre"));
        panelCabecera.add(new JLabel("2025"));
        panelCabecera.add(new JLabel(">"));
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panelPrincipal.add(panelCabecera, gbc);

        // 2. Simulacion de la Cuadricula de Días
        JPanel gridPanel = new JPanel(new GridLayout(6, 7, 2, 2));
        String[] diasSemana = {"dom", "lun", "mar", "mié", "jue", "vie", "sáb"};
        for (String dia : diasSemana) {
            gridPanel.add(new JLabel(dia, SwingConstants.CENTER));
        }
        for (int i = 26; i <= 30; i++) gridPanel.add(new JButton(String.valueOf(i)));
        gridPanel.add(new JButton("1"));
        for (int i = 2; i <= 30; i++) {
            JButton dayButton = new JButton(String.valueOf(i));
            if (i == 15) { // Simular día seleccionado
                dayButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }
            gridPanel.add(dayButton);
        }
        
        gbc.gridy = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(gridPanel, gbc);
        
        // 3. Fecha de entrada
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(new JLabel("Fecha de entrada"), gbc);
        
        gbc.gridy = 3;
        JPanel panelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelEntrada.add(new JTextField(8));
        panelEntrada.add(new JButton("🗓️"));
        panelPrincipal.add(panelEntrada, gbc);

        // 4. Fecha de salida
        gbc.gridx = 1; gbc.gridy = 2;
        panelPrincipal.add(new JLabel("Fecha de salida"), gbc);
        
        gbc.gridy = 3;
        JPanel panelSalida = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSalida.add(new JTextField(8));
        panelSalida.add(new JButton("🗓️"));
        panelPrincipal.add(panelSalida, gbc);

        return panelPrincipal;
    }


    /** Crea el contenido de la pestaña "Parcelas" (image_2f4ea2.png). */
    private JPanel crearPanelParcelas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel panelContenido = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel labelSelecciona = new JLabel("Selecciona las parcelas que quieras reservar");
        JLabel labelPreguntaTienda = new JLabel("¿Quieres agregar una tienda de campaña?");
        JLabel labelNombreTienda = new JLabel("Nombre de la tienda");
        JTextField campoNombreTienda = new JTextField("Nombre...", 10);
        JLabel labelM2 = new JLabel("m2");
        JTextField campoM2 = new JTextField("Metros cuadrados...", 10);
        JButton btnAgregar = new JButton("Agregar");

        // Cuadrícula de Parcelas (Simulación de 4x4)
        JPanel panelCuadriculaParcelas = new JPanel(new GridLayout(4, 4, 5, 5));
        for (int i = 0; i < 16; i++) {
            JPanel celda = new JPanel();
            celda.setBackground(new Color(152, 251, 152)); 
            celda.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            panelCuadriculaParcelas.add(celda);
        }
        
        // Posicionamiento con GridBagLayout
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 0.0;
        panelContenido.add(labelSelecciona, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 4; gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.anchor = GridBagConstraints.NORTH;
        panelContenido.add(panelCuadriculaParcelas, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridheight = 1; gbc.weightx = 0.0; gbc.weighty = 0.0;
        panelContenido.add(labelPreguntaTienda, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JPanel panelCamposTienda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelCamposTiendaVertical = new JPanel(new GridLayout(2, 2, 5, 5));
        
        panelCamposTiendaVertical.add(labelNombreTienda);
        panelCamposTiendaVertical.add(labelM2);
        panelCamposTiendaVertical.add(campoNombreTienda);
        panelCamposTiendaVertical.add(campoM2);
        
        panelCamposTienda.add(panelCamposTiendaVertical);
        panelContenido.add(panelCamposTienda, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(btnAgregar, gbc);

        panel.add(panelContenido, BorderLayout.CENTER);
        
        // Botones Inferiores
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        JButton btnAtras = new JButton("Atrás");
        JButton btnSiguiente = new JButton("Siguiente");
        panelBotones.add(btnAtras);
        panelBotones.add(btnSiguiente);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    /** Crea el contenido de la pestaña "Miembros" (image_2f4ec1.png). */
    private JPanel crearPanelMiembros() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel panelContenido = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel labelAgregarParticipantes = new JLabel("Agregar participantes");
        labelAgregarParticipantes.setFont(new Font("SansSerif", Font.PLAIN, 16));
        
        JLabel labelNombre = new JLabel("Nombre");
        JTextField campoNombre = new JTextField("Nombre...", 10);
        JLabel labelApellidos = new JLabel("Apellidos");
        JTextField campoApellidos = new JTextField("Apellidos...", 10);
        JLabel labelEdad = new JLabel("Edad");
        JTextField campoEdad = new JTextField("18", 4);
        JButton btnAgregar = new JButton("Agregar");

        JLabel labelQuienViene = new JLabel("Quién viene?");
        labelQuienViene.setFont(new Font("SansSerif", Font.PLAIN, 16));
        
        // Tabla de participantes
        String[] columnas = {"Nombre", "Apellidos", "Edad"};
        Object[][] datos = {}; 
        JTable tablaParticipantes = new JTable(new MyTableModel(datos, columnas)); 
        JScrollPane scrollTabla = new JScrollPane(tablaParticipantes);
        
        // Posicionamiento con GridBagLayout
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 0.0;
        panelContenido.add(labelAgregarParticipantes, gbc);
        
        gbc.gridx = 2; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 1.0;
        panelContenido.add(labelQuienViene, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(labelNombre, gbc);
        gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoNombre, gbc);

        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(labelEdad, gbc);
        gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(labelApellidos, gbc);
        gbc.gridy = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoApellidos, gbc);

        gbc.gridx = 1; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.EAST;
        panelContenido.add(btnAgregar, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1; gbc.gridwidth = 2; gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 1.0;
        panelContenido.add(scrollTabla, gbc);

        panel.add(panelContenido, BorderLayout.CENTER);
        
        // Botones Inferiores (Atrás/Siguiente)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        JButton btnAtras = new JButton("Atrás");
        JButton btnSiguiente = new JButton("Siguiente");
        panelBotones.add(btnAtras);
        panelBotones.add(btnSiguiente);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /** Crea el contenido de la pestaña "Confirmación" (image_2f4edd.png). */
    private JPanel crearPanelConfirmacion() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        
        JLabel labelMiReserva = new JLabel("Mi reserva");
        labelMiReserva.setFont(new Font("SansSerif", Font.BOLD, 20));
        
        JLabel labelFechas = new JLabel("Fechas:");
        JLabel labelParcelas = new JLabel("Parcelas:");
        JLabel labelParticipantes = new JLabel("Participantes:");
        
        JLabel labelEstasSeguro = new JLabel("¿Estás seguro?");
        JButton btnAsiPerfecto = new JButton("Sí, así perfecto"); 
        JButton btnNoVolver = new JButton("No, vuelve atrás"); 

        // Posicionamiento con GridBagLayout
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(labelMiReserva, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(labelEstasSeguro, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(labelFechas, gbc);
        
        gbc.gridy = 2;
        panel.add(labelParcelas, gbc);
        
        gbc.gridy = 3;
        panel.add(labelParticipantes, gbc);
        
        // Opciones de confirmación
        gbc.gridx = 1; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panel.add(btnAsiPerfecto, gbc);
        
        gbc.gridy = 2;
        panel.add(btnNoVolver, gbc);
        
        // Rellenar espacio vacío
        gbc.gridy = 4; gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);

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

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
    }
}