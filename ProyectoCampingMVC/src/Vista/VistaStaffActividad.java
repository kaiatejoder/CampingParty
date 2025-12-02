package Vista;

import RiuRauLaf.RiuRauLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que representa la interfaz para crear o editar una actividad para el Staff (VistaStaffActividad).
 * Anteriormente mostrada como "Crear Actividad".
 */
public class VistaStaffActividad extends JFrame {
    
    // Componentes de la interfaz
    private JLabel labelTitulo;
    
    // Componentes del panel izquierdo (Plantillas)
    private JLabel labelPlantillas;
    private JList<String> listaPlantillas;
    private JScrollPane scrollPlantillas;
    
    // Componentes del panel derecho (Actividad)
    private JLabel labelActividad;
    private JLabel labelDiaHora;
    private JTextField campoDiaHora;
    private JButton btnCalendario;
    
    private JLabel labelLugar;
    private JComboBox<String> comboLugar;
    
    private JLabel labelTituloActividad;
    private JTextField campoTituloActividad;
    
    private JLabel labelEdad;
    private JComboBox<String> comboEdad;
    
    private JLabel labelDescripcion;
    private JTextArea areaDescripcion;
    private JScrollPane scrollDescripcion;
    
    private JButton btnAgregarActividad;

    /**
     * Constructor para inicializar la interfaz.
     */
    public VistaStaffActividad() {
        // Configuración básica del JFrame
        RiuRauLaf.setup();
        setTitle("Crear Actividad");
        setSize(700, 500); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
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
        // Título de la ventana
        labelTitulo = new JLabel("Crear Actividad");
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        // --- Plantillas (Izquierda) ---
        labelPlantillas = new JLabel("Plantillas");
        String[] plantillas = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        listaPlantillas = new JList<>(plantillas);
        scrollPlantillas = new JScrollPane(listaPlantillas);
        scrollPlantillas.setPreferredSize(new Dimension(150, 200));

        // --- Actividad (Derecha) ---
        labelActividad = new JLabel("Actividad");
        labelActividad.setFont(new Font("SansSerif", Font.BOLD, 16));

        labelDiaHora = new JLabel("Selecciona Día/Hora");
        campoDiaHora = new JTextField(10);
        btnCalendario = new JButton("🗓️");
        // 👉 Selector de fecha al pulsar el calendario
        btnCalendario.addActionListener(e -> mostrarSelectorFecha(campoDiaHora));

        labelLugar = new JLabel("Selecciona Lugar");
        String[] lugares = {"Piscina", "Pista Deportiva", "Salón Social", "Playa"};
        comboLugar = new JComboBox<>(lugares);
        comboLugar.setSelectedItem("Piscina");
        
        labelTituloActividad = new JLabel("Selecciona Título");
        campoTituloActividad = new JTextField("Título", 15);

        labelEdad = new JLabel("Selecciona Edad");
        String[] edades = {"Todos los Públicos", "Mayores de 18", "Niños"};
        comboEdad = new JComboBox<>(edades);
        comboEdad.setSelectedItem("Todos los Públicos");
        
        labelDescripcion = new JLabel("Descripción");
        areaDescripcion = new JTextArea(5, 20);
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);
        scrollDescripcion = new JScrollPane(areaDescripcion);
        
        btnAgregarActividad = new JButton("Agregar Actividad");
    }

    /**
     * Configura el layout del JFrame y añade los componentes.
     */
    private void configurarLayout() {
        setLayout(new BorderLayout(15, 15));

        // 1. Título (Norte)
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelNorte.add(labelTitulo);
        add(panelNorte, BorderLayout.NORTH);

        // 2. Panel Central (Plantillas + Formulario)
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                crearPanelPlantillas(),
                crearPanelFormulario()
        );
        splitPane.setDividerLocation(200); 
        splitPane.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        add(splitPane, BorderLayout.CENTER);

        // 3. Botón inferior (Sur)
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelSur.add(btnAgregarActividad);
        add(panelSur, BorderLayout.SOUTH);
    }
    
    /** Crea el panel para las Plantillas (Izquierda). */
    private JPanel crearPanelPlantillas() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelPlantillas.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(labelPlantillas);
        panel.add(Box.createVerticalStrut(5));
        
        scrollPlantillas.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(scrollPlantillas);
        
        return panel;
    }

    /** Crea el panel para el Formulario de Actividad (Derecha). */
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        
        // Título "Actividad"
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(labelActividad, gbc);
        
        // --- Fila 1: Día/Hora ---
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.weightx = 0.0;
        panel.add(labelDiaHora, gbc);
        
        gbc.gridx = 1; 
        JPanel panelDiaHora = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelDiaHora.add(campoDiaHora);
        panelDiaHora.add(btnCalendario);
        panel.add(panelDiaHora, gbc);
        
        // --- Fila 2: Lugar ---
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(labelLugar, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboLugar, gbc);
        
        // --- Fila 3: Título ---
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panel.add(labelTituloActividad, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoTituloActividad, gbc);
        
        // --- Fila 4: Edad ---
        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        panel.add(labelEdad, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboEdad, gbc);
        
        // --- Fila 5: Descripción ---
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(labelDescripcion, gbc);
        
        gbc.gridy = 6; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 1.0; 
        panel.add(scrollDescripcion, gbc);
        
        // Relleno inferior
        gbc.gridy = 7; gbc.weighty = 10.0;
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    // ==== Selector de fecha ====

    /**
     * Selector de fecha para el campo de día/hora.
     * Solo rellena la fecha en formato dd/MM/yyyy (la hora puedes escribirla luego a mano).
     */
    private void mostrarSelectorFecha(JTextField destino) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
        spinner.setEditor(editor);

        Date inicial = new Date();
        String texto = destino.getText().trim();
        if (!texto.isEmpty()) {
            try {
                inicial = sdf.parse(texto.split(" ")[0]);
            } catch (ParseException ignored) {}
        }
        spinner.setValue(inicial);

        int resultado = JOptionPane.showConfirmDialog(
                this,
                spinner,
                "Selecciona una fecha",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultado == JOptionPane.OK_OPTION) {
            Date seleccionada = (Date) spinner.getValue();
            destino.setText(sdf.format(seleccionada)); // luego puedes añadir la hora a mano
        }
    }

    // ================== GETTERS PARA EL CONTROLADOR ==================

    public JList<String> getListaPlantillas() {
        return listaPlantillas;
    }

    public JButton getBtnCalendario() {
        return btnCalendario;
    }

    public JButton getBtnAgregarActividad() {
        return btnAgregarActividad;
    }

    public JTextField getCampoDiaHora() {
        return campoDiaHora;
    }

    public JComboBox<String> getComboLugar() {
        return comboLugar;
    }

    public JTextField getCampoTituloActividad() {
        return campoTituloActividad;
    }

    public JComboBox<String> getComboEdad() {
        return comboEdad;
    }

    public JTextArea getAreaDescripcion() {
        return areaDescripcion;
    }

    // Utilidad: cambiar el título cuando se edita en vez de crear
    public void setTextoTituloVentana(String texto) {
        setTitle(texto);
        labelTitulo.setText(texto);
    }
}
