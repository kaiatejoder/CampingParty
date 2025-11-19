package Vista;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class VistaCliente extends JFrame {

    // Componentes de la interfaz
    private JLabel labelReportarIncidencia;
    private JLabel labelSaludo;
    private JLabel labelProximaReserva;
    private JLabel labelFecha; // Placeholder para la fecha real
    
    private JButton btnHacerNuevaReserva;
    private JButton btnVerReservasAnteriores;
    private JButton btnVolver;
    private JButton btnModificar;
    private JButton btnCancelar;

    /**
     * Constructor para inicializar la interfaz.
     * Configura el JFrame y añade los componentes.
     */
    public VistaCliente() {
        // Configuración básica del JFrame
        setTitle("Reportar Incidencia"); // Título en la barra de ventana
        setSize(600, 400); // Tamaño adecuado
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
        // Título de la ventana (como se ve en la imagen)
        labelReportarIncidencia = new JLabel("Reportar Incidencia", SwingConstants.LEFT);
        labelReportarIncidencia.setFont(new Font("SansSerif", Font.PLAIN, 12));
        
        // Saludo y encabezados
        labelSaludo = new JLabel("Hola, Cliente");
        labelSaludo.setFont(new Font("SansSerif", Font.PLAIN, 36));
        
        labelProximaReserva = new JLabel("Tu próxima reserva", SwingConstants.RIGHT);
        labelFecha = new JLabel("FECHA", SwingConstants.RIGHT); // Placeholder
        labelFecha.setFont(new Font("SansSerif", Font.PLAIN, 18));
        
        // Botones
        btnHacerNuevaReserva = new JButton("Hacer nueva reserva");
        btnVerReservasAnteriores = new JButton("Ver reservas anteriores");
        btnVolver = new JButton("Volver");
        btnModificar = new JButton("Modificar");
        btnCancelar = new JButton("Cancelar");
    }

    /**
     * Configura el layout del JFrame y añade los componentes.
     */
    private void configurarLayout() {
        // Usamos BorderLayout para el marco principal.
        setLayout(new BorderLayout());

        // 1. Título "Reportar Incidencia" (Norte)
        // Se usa un panel simple para contener el texto de la barra superior izquierda.
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelNorte.add(labelReportarIncidencia);
        add(panelNorte, BorderLayout.NORTH);

        // 2. Panel de Contenido Central (GridBagLayout para la estructura 2x2)
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.anchor = GridBagConstraints.NORTHWEST; // Anclaje por defecto
        gbc.fill = GridBagConstraints.NONE;
        
        // Columna de Saludo y Botones de la izquierda
        // ------------------------------------------

        // Fila 0: Saludo
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0.5; // Ocupa la mitad del espacio horizontal
        gbc.anchor = GridBagConstraints.WEST;
        panelCentral.add(labelSaludo, gbc);

        // Fila 1: Botón Hacer nueva reserva
        gbc.gridy = 1; 
        gbc.insets = new Insets(40, 15, 10, 15); // Más espacio arriba
        panelCentral.add(btnHacerNuevaReserva, gbc);
        
        // Fila 2: Botón Ver reservas anteriores
        gbc.gridy = 2; 
        gbc.insets = new Insets(10, 15, 10, 15);
        panelCentral.add(btnVerReservasAnteriores, gbc);

        // Fila 3: Botón Volver
        gbc.gridy = 3; 
        gbc.weighty = 1.0; // Empuja el contenido hacia arriba
        panelCentral.add(btnVolver, gbc);

        // Columna de Próxima Reserva y Botones de la derecha
        // ----------------------------------------------------
        
        // Fila 0: Tu próxima reserva
        gbc.gridx = 1; gbc.gridy = 0; 
        gbc.weightx = 0.5; 
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        panelCentral.add(labelProximaReserva, gbc);
        
        // Fila 1: FECHA
        gbc.gridy = 1; 
        gbc.insets = new Insets(5, 15, 5, 15);
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        panelCentral.add(labelFecha, gbc);
        
        // Panel para botones Modificar/Cancelar
        JPanel panelBotonesReserva = new JPanel(new GridLayout(2, 1, 5, 10));
        panelBotonesReserva.add(btnModificar);
        panelBotonesReserva.add(btnCancelar);

        // Fila 2: Botones Modificar/Cancelar
        gbc.gridy = 2; 
        gbc.insets = new Insets(30, 15, 10, 15); // Espacio entre Fecha y botones
        gbc.anchor = GridBagConstraints.EAST;
        panelCentral.add(panelBotonesReserva, gbc);

        // Añadir el panel de contenido al centro del JFrame
        add(panelCentral, BorderLayout.CENTER);
        
        // Nota: La llamada a setVisible(true) debe hacerse desde la clase principal (main).
    }
}