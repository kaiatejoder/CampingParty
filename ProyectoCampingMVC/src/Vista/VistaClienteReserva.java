package Vista;

import RiuRauLaf.RiuRauLaf;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.JOptionPane;


/**
 * Clase que representa la interfaz para el proceso de Nueva Reserva (VistaClienteReserva).
 * Utiliza un JTabbedPane para gestionar los pasos: Fechas, Parcelas, Miembros y Confirmación.
 */
public class VistaClienteReserva extends JFrame {

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
    private JButton btnAtrasMiembros;
    private JButton btnSiguienteMiembros;

    // === Pestaña Confirmación ===
    private JLabel labelFechasResumen;
    private JLabel labelParcelasResumen;
    private JLabel labelParticipantesResumen;
    private JButton btnConfirmarReserva;
    private JButton btnVolverAtrasConfirmacion;

    /**
     * Constructor para inicializar la interfaz.
     */
    public VistaClienteReserva() {
        // Configuración básica del JFrame
        RiuRauLaf.setup();
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

    /** Crea el contenido de la pestaña "Fecha de la reserva". */
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
        
        // Cuando se pulse el botón, se abre un selector de fecha
        btnCalendarioEntrada.addActionListener(e -> mostrarSelectorFecha(campoEntrada));
        btnCalendarioSalida.addActionListener(e -> mostrarSelectorFecha(campoSalida));


        JLabel labelSePuede = new JLabel("¡Se puede");
        labelSePuede.setFont(new Font("SansSerif", Font.ITALIC, 14));

        // Posicionamiento con GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(labelFechas, gbc);

        gbc.gridy = 1; gbc.gridwidth = 1;
        panelContenido.add(labelEntrada, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JPanel panelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelEntrada.add(campoEntrada);
        panelEntrada.add(btnCalendarioEntrada);
        panelContenido.add(panelEntrada, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panelContenido.add(labelSalida, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        JPanel panelSalida = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSalida.add(campoSalida);
        panelSalida.add(btnCalendarioSalida);
        panelContenido.add(panelSalida, gbc);

        gbc.gridx = 2; gbc.gridy = 2; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(labelSePuede, gbc);

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

    /** Crea el contenido de la pestaña "Parcelas". */
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

        // Cuadrícula de Parcelas (3x3)
        panelCuadriculaParcelas = new JPanel(new GridLayout(3, 3, 5, 5));
        for (int i = 0; i < 9; i++) {
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
     * Abre un diálogo con un JSpinner de fecha y, si el usuario pulsa Aceptar,
     * escribe la fecha en el JTextField dado con formato dd/MM/yyyy.
     */
    private void mostrarSelectorFecha(JTextField destino) {
        // Formato de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Modelo de fecha para el spinner
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
        spinner.setEditor(editor);

        // Si el campo ya tiene algo, intentamos usarlo como valor inicial
        Date inicial = new Date();
        String texto = destino.getText().trim();
        if (!texto.isEmpty()) {
            try {
                inicial = sdf.parse(texto);
            } catch (ParseException ignored) {
                // Si no se puede parsear, dejamos la fecha actual
            }
        }
        spinner.setValue(inicial);

        // Mostramos el diálogo
        int resultado = JOptionPane.showConfirmDialog(
                this,
                spinner,
                "Selecciona una fecha",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Si el usuario confirma, volcamos la fecha al JTextField
        if (resultado == JOptionPane.OK_OPTION) {
            Date seleccionada = (Date) spinner.getValue();
            destino.setText(sdf.format(seleccionada));
        }
    }

    /** Crea el contenido de la pestaña "Miembros". */
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
        javax.swing.table.DefaultTableModel modelTabla =
        new javax.swing.table.DefaultTableModel(datos, columnas);
        tablaParticipantes = new JTable(modelTabla);

        JScrollPane scrollTabla = new JScrollPane(tablaParticipantes);

        // Posicionamiento con GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 0.0;
        panelContenido.add(labelAgregarParticipantes, gbc);

        gbc.gridx = 2; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 1.0;
        panelContenido.add(labelQuienViene, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(labelNombre, gbc);
        gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoNombreMiembro, gbc);

        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(labelEdad, gbc);
        gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoEdadMiembro, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(labelApellidos, gbc);
        gbc.gridy = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(campoApellidosMiembro, gbc);

        gbc.gridx = 1; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.EAST;
        panelContenido.add(btnAgregarMiembro, gbc);

        gbc.gridx = 2; gbc.gridy = 1; gbc.gridwidth = 2; gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 1.0;
        panelContenido.add(scrollTabla, gbc);

        panel.add(panelContenido, BorderLayout.CENTER);

        // Botones Inferiores (Atrás/Siguiente)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        btnAtrasMiembros = new JButton("Atrás");
        btnSiguienteMiembros = new JButton("Siguiente");
        panelBotones.add(btnAtrasMiembros);
        panelBotones.add(btnSiguienteMiembros);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    /** Crea el contenido de la pestaña "Confirmación". */
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

        // Posicionamiento con GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(labelMiReserva, gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(labelEstasSeguro, gbc);

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

    public JButton getBtnAtrasMiembros() {
        return btnAtrasMiembros;
    }

    public JButton getBtnSiguienteMiembros() {
        return btnSiguienteMiembros;
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
}
