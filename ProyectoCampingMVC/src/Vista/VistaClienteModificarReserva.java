package Vista;

import RiuRauLaf.RiuRauLaf;
import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la interfaz para el proceso de Modificación/Nueva Reserva.
 * Utiliza un JTabbedPane para gestionar los pasos: Fechas, Parcelas, Miembros y Confirmación.
 */
public class VistaClienteModificarReserva extends JFrame {

    private JLabel labelTitulo;
    private JTabbedPane tabbedPane;

    // === Pestaña Fechas ===
    private JTextField campoEntrada;
    private JTextField campoSalida;
    private JButton btnCalendarioEntrada;
    private JButton btnCalendarioSalida;
    private JButton btnCancelarFechas;
    private JButton btnSiguienteFechas;

    // === Pestaña Parcelas ===
    private JPanel panelCuadriculaParcelas;
    private JTextField campoNombreTienda;
    private JTextField campoM2;
    private JButton btnAgregarTienda;
    private JButton btnAtrasParcelas;
    private JButton btnSiguienteParcelas;

    // === Pestaña Miembros ===
    private JTextField campoNombreMiembro;
    private JTextField campoApellidosMiembro;
    private JTextField campoEdadMiembro;
    private JButton btnAgregarMiembro;
    private JTable tablaParticipantes;

    // === Pestaña Confirmación ===
    private JLabel labelFechasResumen;
    private JLabel labelParcelasResumen;
    private JLabel labelParticipantesResumen;
    private JButton btnConfirmarReserva;       // "Sí, así perfecto"
    private JButton btnVolverAtrasConfirmacion; // "No, vuelve atrás"

    /**
     * Constructor para inicializar la interfaz.
     */
    public VistaClienteModificarReserva() {
        RiuRauLaf.setup();
        // Configuración básica del JFrame
        setTitle("Nueva reserva");
        setSize(800, 600); // Tamaño adecuado para el asistente
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
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

    /**
     * Crea el contenido de la pestaña "Fecha de la reserva".
     */
    private JPanel crearPanelFechas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel panelContenido = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelFechas = new JLabel("Fechas");
        labelFechas.setFont(new Font("SansSerif", Font.BOLD, 20));

        JLabel labelEntrada = new JLabel("Fecha de entrada");
        campoEntrada = new JTextField(10);
        btnCalendarioEntrada = new JButton("🗓️");

        JLabel labelSalida = new JLabel("Fecha de salida");
        campoSalida = new JTextField(10);
        btnCalendarioSalida = new JButton("🗓️");

        JLabel labelSePuede = new JLabel("¡Se puede");
        labelSePuede.setFont(new Font("SansSerif", Font.ITALIC, 14));

        // --- Posicionamiento con GridBagLayout ---

        // Título "Fechas"
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(labelFechas, gbc);

        // Fecha de entrada
        gbc.gridy = 1; gbc.gridwidth = 1;
        panelContenido.add(labelEntrada, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JPanel panelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelEntrada.add(campoEntrada);
        panelEntrada.add(btnCalendarioEntrada);
        panelContenido.add(panelEntrada, gbc);

        // Fecha de salida
        gbc.gridx = 1; gbc.gridy = 1;
        panelContenido.add(labelSalida, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        JPanel panelSalida = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSalida.add(campoSalida);
        panelSalida.add(btnCalendarioSalida);
        panelContenido.add(panelSalida, gbc);

        // ¡Se puede
        gbc.gridx = 2; gbc.gridy = 2; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(labelSePuede, gbc);

        // Rellenar espacio vacío
        gbc.gridy = 3; gbc.weighty = 1.0;
        panelContenido.add(Box.createVerticalGlue(), gbc);

        panel.add(panelContenido, BorderLayout.CENTER);

        // Botones Inferiores
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        btnCancelarFechas = new JButton("Cancelar");
        btnSiguienteFechas = new JButton("Siguiente");
        panelBotones.add(btnCancelarFechas);
        panelBotones.add(btnSiguienteFechas);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Crea el contenido de la pestaña "Parcelas".
     */
    private JPanel crearPanelParcelas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel panelContenido = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelSelecciona = new JLabel("Selecciona las parcelas que quieras reservar");
        JLabel labelPreguntaTienda = new JLabel("¿Quieres agregar una tienda de campaña?");
        JLabel labelNombreTienda = new JLabel("Nombre de la tienda");
        campoNombreTienda = new JTextField("Nombre...", 10);
        JLabel labelM2 = new JLabel("m2");
        campoM2 = new JTextField("Metros cuadrados...", 10);
        btnAgregarTienda = new JButton("Agregar");

        // Cuadrícula de Parcelas (Simulación de 4x4)
        panelCuadriculaParcelas = new JPanel(new GridLayout(4, 4, 5, 5));
        for (int i = 0; i < 16; i++) {
            JPanel celda = new JPanel();
            celda.setBackground(new Color(152, 251, 152)); // Verde claro
            celda.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            panelCuadriculaParcelas.add(celda);
        }

        // --- Posicionamiento con GridBagLayout ---

        // Título "Selecciona..."
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 0.0;
        panelContenido.add(labelSelecciona, gbc);

        // Cuadrícula de Parcelas (Derecha)
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 4; gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.anchor = GridBagConstraints.NORTH;
        panelContenido.add(panelCuadriculaParcelas, gbc);

        // Pregunta Tienda
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridheight = 1; gbc.weightx = 0.0; gbc.weighty = 0.0;
        panelContenido.add(labelPreguntaTienda, gbc);

        // Campos de Tienda (Nombre y M2)
        gbc.gridx = 0; gbc.gridy = 2;
        JPanel panelCamposTienda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelCamposTiendaVertical = new JPanel(new GridLayout(2, 2, 5, 5));

        panelCamposTiendaVertical.add(labelNombreTienda);
        panelCamposTiendaVertical.add(labelM2);
        panelCamposTiendaVertical.add(campoNombreTienda);
        panelCamposTiendaVertical.add(campoM2);

        panelCamposTienda.add(panelCamposTiendaVertical);
        panelContenido.add(panelCamposTienda, gbc);

        // Botón Agregar
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(btnAgregarTienda, gbc);

        panel.add(panelContenido, BorderLayout.CENTER);

        // Botones Inferiores
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        btnAtrasParcelas = new JButton("Atrás");
        btnSiguienteParcelas = new JButton("Siguiente");
        panelBotones.add(btnAtrasParcelas);
        panelBotones.add(btnSiguienteParcelas);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Crea el contenido de la pestaña "Miembros".
     */
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
        campoNombreMiembro = new JTextField("Nombre...", 10);
        JLabel labelApellidos = new JLabel("Apellidos");
        campoApellidosMiembro = new JTextField("Apellidos...", 10);
        JLabel labelEdad = new JLabel("Edad");
        campoEdadMiembro = new JTextField("18", 4);
        btnAgregarMiembro = new JButton("Agregar");

        JLabel labelQuienViene = new JLabel("Quién viene?");
        labelQuienViene.setFont(new Font("SansSerif", Font.PLAIN, 16));

        // Tabla de participantes
        String[] columnas = {"Nombre", "Apellidos", "Edad"};
        Object[][] datos = {};
        tablaParticipantes = new JTable(new MyTableModel(datos, columnas));
        JScrollPane scrollTabla = new JScrollPane(tablaParticipantes);

        // --- Posicionamiento con GridBagLayout ---

        // Título Agregar participantes
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 0.0;
        panelContenido.add(labelAgregarParticipantes, gbc);

        // Título Quién viene?
        gbc.gridx = 2; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 1.0;
        panelContenido.add(labelQuienViene, gbc);

        // Columna Nombre (Etiqueta y Campo)
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(labelNombre, gbc);
        gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoNombreMiembro, gbc);

        // Columna Edad (Etiqueta y Campo)
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(labelEdad, gbc);
        gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoEdadMiembro, gbc);

        // Columna Apellidos (Etiqueta y Campo)
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(labelApellidos, gbc);
        gbc.gridy = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoApellidosMiembro, gbc);

        // Botón Agregar
        gbc.gridx = 1; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.EAST;
        panelContenido.add(btnAgregarMiembro, gbc);

        // Tabla de Participantes
        gbc.gridx = 2; gbc.gridy = 1; gbc.gridwidth = 2; gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 1.0;
        panelContenido.add(scrollTabla, gbc);

        panel.add(panelContenido, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Crea el contenido de la pestaña "Confirmación".
     */
    private JPanel crearPanelConfirmacion() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;

        JLabel labelMiReserva = new JLabel("Mi reserva");
        labelMiReserva.setFont(new Font("SansSerif", Font.BOLD, 20));

        JLabel labelEstasSeguro = new JLabel("¿Estás seguro?");

        labelFechasResumen = new JLabel("Fechas:");
        labelParcelasResumen = new JLabel("Parcelas:");
        labelParticipantesResumen = new JLabel("Participantes:");

        btnConfirmarReserva = new JButton("Sí, así perfecto");
        btnVolverAtrasConfirmacion = new JButton("No, vuelve atrás");

        // --- Posicionamiento con GridBagLayout ---

        // Título "Mi reserva"
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(labelMiReserva, gbc);

        // Pregunta "¿Estás seguro?"
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(labelEstasSeguro, gbc);

        // Detalles de la reserva
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(labelFechasResumen, gbc);

        gbc.gridy = 2;
        panel.add(labelParcelasResumen, gbc);

        gbc.gridy = 3;
        panel.add(labelParticipantesResumen, gbc);

        // Opciones de confirmación
        gbc.gridx = 1; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panel.add(btnConfirmarReserva, gbc);

        gbc.gridy = 2;
        panel.add(btnVolverAtrasConfirmacion, gbc);

        // Rellenar espacio vacío
        gbc.gridy = 4; gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    // === TableModel simple para la tabla de participantes ===
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

    // ================== GETTERS PARA EL CONTROLADOR ==================

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    // Fechas
    public JTextField getCampoEntrada() {
        return campoEntrada;
    }

    public JTextField getCampoSalida() {
        return campoSalida;
    }

    public JButton getBtnCalendarioEntrada() {
        return btnCalendarioEntrada;
    }

    public JButton getBtnCalendarioSalida() {
        return btnCalendarioSalida;
    }

    public JButton getBtnCancelarFechas() {
        return btnCancelarFechas;
    }

    public JButton getBtnSiguienteFechas() {
        return btnSiguienteFechas;
    }

    // Parcelas
    public JPanel getPanelCuadriculaParcelas() {
        return panelCuadriculaParcelas;
    }

    public JTextField getCampoNombreTienda() {
        return campoNombreTienda;
    }

    public JTextField getCampoM2() {
        return campoM2;
    }

    public JButton getBtnAgregarTienda() {
        return btnAgregarTienda;
    }

    public JButton getBtnAtrasParcelas() {
        return btnAtrasParcelas;
    }

    public JButton getBtnSiguienteParcelas() {
        return btnSiguienteParcelas;
    }

    // Miembros
    public JTextField getCampoNombreMiembro() {
        return campoNombreMiembro;
    }

    public JTextField getCampoApellidosMiembro() {
        return campoApellidosMiembro;
    }

    public JTextField getCampoEdadMiembro() {
        return campoEdadMiembro;
    }

    public JButton getBtnAgregarMiembro() {
        return btnAgregarMiembro;
    }

    public JTable getTablaParticipantes() {
        return tablaParticipantes;
    }

    // Confirmación
    public JLabel getLabelFechasResumen() {
        return labelFechasResumen;
    }

    public JLabel getLabelParcelasResumen() {
        return labelParcelasResumen;
    }

    public JLabel getLabelParticipantesResumen() {
        return labelParticipantesResumen;
    }

    public JButton getBtnConfirmarReserva() {
        return btnConfirmarReserva;
    }

    public JButton getBtnVolverAtrasConfirmacion() {
        return btnVolverAtrasConfirmacion;
    }

    // Utilidad: cambiar título (para "Modificar reserva" en lugar de "Nueva reserva")
    public void setTitulo(String texto) {
        labelTitulo.setText(texto);
    }
}
