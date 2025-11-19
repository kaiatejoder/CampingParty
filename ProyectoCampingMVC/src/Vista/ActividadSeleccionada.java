package Vista;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Sergio Gimenez Gomez
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
     * Constructor para inicializar la interfaz.
     * Configura el JFrame y añade los componentes.
     */
    public ActividadSeleccionada() {
        // Configuración básica del JFrame
        setTitle("Actividad Seleccionada");
        setSize(400, 300); // Tamaño aproximado basado en la imagen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // --- LÍNEA MODIFICADA/ELIMINADA ---
        // setResizable(false); // Esta línea evitaba la maximización
        setResizable(true); // Se establece a true para permitir la redimensión y maximización
        
        setLocationRelativeTo(null); // Centrar la ventana

        // Inicializar componentes
        inicializarComponentes();

        // Configurar el layout y añadir paneles
        configurarLayout();
    }

    /**
     * Inicializa todas las instancias de los componentes de Swing.
     */
    private void inicializarComponentes() {
        // Título y detalles de la actividad
        labelTitulo = new JLabel("*Actividad Seleccionada*", SwingConstants.LEFT);
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 12));

        labelLugar = new JLabel("Lugar");
        valorLugar = new JLabel("Piscina"); // Valor fijo para la demo
        labelHora = new JLabel("Hora");
        valorHora = new JLabel("15:00"); // Valor fijo para la demo

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
        // Usaremos un BorderLayout para la estructura general del contenido.
        setLayout(new BorderLayout(10, 10));

        // --- Panel Superior (Título) ---
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorte.add(Box.createHorizontalStrut(20)); // Espacio a la izquierda
        panelNorte.add(labelTitulo);
        add(panelNorte, BorderLayout.NORTH);

        // --- Panel Central (Lugar, Hora, Asistentes, Botones Añadir/Borrar) ---
        JPanel panelCentro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // 1. Fila Lugar
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panelCentro.add(labelLugar, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelCentro.add(valorLugar, gbc);
        gbc.gridx = 2; gbc.anchor = GridBagConstraints.WEST;
        panelCentro.add(labelAsistentes, gbc);
        
        // 2. Fila Hora + Lista Asistentes
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panelCentro.add(labelHora, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelCentro.add(valorHora, gbc);
        
        // Botones Añadir/Borrar
        JPanel panelBotonesAsistentes = new JPanel(new GridLayout(2, 1, 5, 5));
        panelBotonesAsistentes.add(botonAnadir);
        panelBotonesAsistentes.add(botonBorrar);
        
        // Contenedor para la Lista y sus Botones
        JPanel panelListaYBotones = new JPanel(new BorderLayout(10, 0));
        panelListaYBotones.add(scrollListaAsistentes, BorderLayout.CENTER);
        panelListaYBotones.add(panelBotonesAsistentes, BorderLayout.EAST);

        // Colocar el Contenedor de Lista y Botones en el GridBagLayout
        gbc.gridx = 2; gbc.gridy = 1; 
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.gridheight = 2; // Ocupa dos filas (para darle altura a la lista)
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelCentro.add(panelListaYBotones, gbc);

        add(panelCentro, BorderLayout.CENTER);

        // --- Panel Inferior (Botones Atrás y Cancelar) ---
        JPanel panelBotonesInferiores = new JPanel();
        panelBotonesInferiores.setLayout(new BoxLayout(panelBotonesInferiores, BoxLayout.X_AXIS));

        // Añadir algo de espacio a la izquierda para el botón "Atrás"
        panelBotonesInferiores.add(Box.createHorizontalStrut(20)); 
        panelBotonesInferiores.add(botonAtras);
        panelBotonesInferiores.add(Box.createHorizontalGlue()); // Espacio de separación
        panelBotonesInferiores.add(botonCancelarReserva);
        panelBotonesInferiores.add(Box.createHorizontalStrut(20)); // Espacio a la derecha
        
        // Añadir el contenedor de botones inferiores al Sur del JFrame
        add(panelBotonesInferiores, BorderLayout.SOUTH);
    }
}