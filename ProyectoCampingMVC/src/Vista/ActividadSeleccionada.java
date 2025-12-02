package Vista;

import RiuRauLaf.RiuRauLaf;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana de detalle de una actividad seleccionada:
 * - Lugar, hora
 * - Lista de asistentes
 * - Botones para añadir/borrar asistentes y gestionar la reserva
 */
public class ActividadSeleccionada extends JFrame {

    // Componentes de la interfaz
    private JLabel labelTitulo;
    private JLabel labelLugar;
    private JLabel valorLugar;
    private JLabel labelHora;
    private JLabel valorHora;
    private JLabel labelAsistentes;
    private JList<String> listaAsistentes;
    private JScrollPane scrollListaAsistentes;
    private JButton botonAnadir;
    private JButton botonBorrar;
    private JButton botonAtras;
    private JButton botonCancelarReserva;

    /**
     * Constructor: configura la ventana y su contenido.
     */
    public ActividadSeleccionada() {
        RiuRauLaf.setup();
        // Configuración básica del JFrame
        setTitle("Actividad Seleccionada");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

        // Inicializar componentes
        inicializarComponentes();

        // Configurar el layout y añadir paneles
        configurarLayout();
        // setVisible(false); // El controlador decidirá cuándo mostrarla
    }

    /**
     * Inicializa todas las instancias de los componentes de Swing.
     */
    private void inicializarComponentes() {
        // Título y detalles de la actividad
        labelTitulo = new JLabel("*Actividad Seleccionada*", SwingConstants.LEFT);
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 12));

        labelLugar = new JLabel("Lugar");
        valorLugar = new JLabel("Piscina"); // valor por defecto, el controlador lo puede cambiar

        labelHora = new JLabel("Hora");
        valorHora = new JLabel("15:00"); // valor por defecto

        // Lista de Asistentes
        labelAsistentes = new JLabel("Asistentes");
        String[] datosAsistentes = {"Asistente1", "Asistente2", "Asistente3", "Asistente4"};
        listaAsistentes = new JList<>(datosAsistentes);
        listaAsistentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollListaAsistentes = new JScrollPane(listaAsistentes);

        // Botones
        botonAnadir = new JButton("Añadir");
        botonBorrar = new JButton("Borrar");
        botonAtras = new JButton("Atrás");
        botonCancelarReserva = new JButton("Cancelar Reserva");
    }

    /**
     * Configura el layout del JFrame y añade los paneles organizados.
     */
    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        // --- Panel Superior (Título) ---
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorte.add(Box.createHorizontalStrut(20)); // Espacio a la izquierda
        panelNorte.add(labelTitulo);
        add(panelNorte, BorderLayout.NORTH);

        // --- Panel Central ---
        JPanel panelCentro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Lugar + Asistentes (cabecera)
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panelCentro.add(labelLugar, gbc);
        gbc.gridx = 1;
        panelCentro.add(valorLugar, gbc);
        gbc.gridx = 2;
        panelCentro.add(labelAsistentes, gbc);

        // Fila 1: Hora + Lista Asistentes + Botones Añadir/Borrar
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panelCentro.add(labelHora, gbc);
        gbc.gridx = 1;
        panelCentro.add(valorHora, gbc);

        JPanel panelBotonesAsistentes = new JPanel(new GridLayout(2, 1, 5, 5));
        panelBotonesAsistentes.add(botonAnadir);
        panelBotonesAsistentes.add(botonBorrar);

        JPanel panelListaYBotones = new JPanel(new BorderLayout(10, 0));
        panelListaYBotones.add(scrollListaAsistentes, BorderLayout.CENTER);
        panelListaYBotones.add(panelBotonesAsistentes, BorderLayout.EAST);

        gbc.gridx = 2; gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelCentro.add(panelListaYBotones, gbc);

        add(panelCentro, BorderLayout.CENTER);

        // --- Panel Inferior (Botones Atrás y Cancelar) ---
        JPanel panelBotonesInferiores = new JPanel();
        panelBotonesInferiores.setLayout(new BoxLayout(panelBotonesInferiores, BoxLayout.X_AXIS));

        panelBotonesInferiores.add(Box.createHorizontalStrut(20));
        panelBotonesInferiores.add(botonAtras);
        panelBotonesInferiores.add(Box.createHorizontalGlue());
        panelBotonesInferiores.add(botonCancelarReserva);
        panelBotonesInferiores.add(Box.createHorizontalStrut(20));

        add(panelBotonesInferiores, BorderLayout.SOUTH);
    }

    // ================== GETTERS / SETTERS PARA EL CONTROLADOR ==================

    public JButton getBotonAnadir() {
        return botonAnadir;
    }

    public JButton getBotonBorrar() {
        return botonBorrar;
    }

    public JButton getBotonAtras() {
        return botonAtras;
    }

    public JButton getBotonCancelarReserva() {
        return botonCancelarReserva;
    }

    public JList<String> getListaAsistentes() {
        return listaAsistentes;
    }

    public JLabel getLabelTitulo() {
        return labelTitulo;
    }

    public JLabel getLabelLugar() {
        return labelLugar;
    }

    public JLabel getValorLugarLabel() {
        return valorLugar;
    }

    public JLabel getLabelHora() {
        return labelHora;
    }

    public JLabel getValorHoraLabel() {
        return valorHora;
    }

    // Atajos cómodos para cambiar textos desde el controlador
    public void setLugar(String lugar) {
        valorLugar.setText(lugar);
    }

    public void setHora(String hora) {
        valorHora.setText(hora);
    }

    public void setTituloActividad(String tituloActividad) {
        labelTitulo.setText(tituloActividad);
    }

    /** Reemplaza la lista completa de asistentes. */
    public void setAsistentes(String[] asistentes) {
        listaAsistentes.setListData(asistentes);
    }
}
