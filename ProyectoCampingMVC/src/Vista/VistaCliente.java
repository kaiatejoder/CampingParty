package Vista;

import RiuRauLaf.RiuRauLaf;
import javax.swing.*;
import java.awt.*;

/**
 * Vista principal del cliente.
 * Compatible con ControladorClienteReservas:
 *  - setTextoSaludo(...)
 *  - setTextoFechaProximaReserva(...)
 *  - getBtnHacerNuevaReserva()
 *  - getBtnVerReservasAnteriores()
 *  - getBtnModificar()
 *  - getBtnCancelar()
 *  - getBtnVolver()
 * Además añade botón "Actividades" para ver/inscribirse en actividades.
 */
public class VistaCliente extends JFrame {

    // Labels
    private JLabel labelSaludo;
    private JLabel labelProximaReserva;

    // Botones principales (reservas)
    private JButton btnHacerNuevaReserva;
    private JButton btnVerReservasAnteriores;
    private JButton btnModificar;
    private JButton btnCancelar;

    // 🔹 Nuevo botón para actividades
    private JButton btnVerActividades;

    // Botón para volver / cerrar sesión
    private JButton btnVolver;

    public VistaCliente() {
        RiuRauLaf.setup();
        setTitle("Zona Cliente");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        // Título / saludo
        labelSaludo = new JLabel("¡Hola Cliente!");
        labelSaludo.setFont(new Font("SansSerif", Font.BOLD, 28));

        labelProximaReserva = new JLabel("Próxima reserva: ---");
        labelProximaReserva.setFont(new Font("SansSerif", Font.PLAIN, 18));

        // Botones de reservas
        btnHacerNuevaReserva = new JButton("Hacer nueva reserva");
        btnVerReservasAnteriores = new JButton("Ver reservas anteriores");
        btnModificar = new JButton("Modificar reserva");
        btnCancelar = new JButton("Cancelar reserva");

        // 🔹 Botón de actividades
        btnVerActividades = new JButton("Actividades");

        // Botón para volver (cerrar sesión)
        btnVolver = new JButton("Cerrar sesión");
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(20, 20));

        // Panel norte con saludo y próxima reserva
        JPanel panelNorte = new JPanel(new GridLayout(2, 1, 5, 5));
        panelNorte.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        panelNorte.add(labelSaludo);
        panelNorte.add(labelProximaReserva);
        add(panelNorte, BorderLayout.NORTH);

        // Panel central con botones
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(6, 1, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));

        panelCentro.add(btnHacerNuevaReserva);
        panelCentro.add(btnVerReservasAnteriores);
        panelCentro.add(btnModificar);
        panelCentro.add(btnCancelar);

        // 🔹 Botón actividades en medio del flujo de cliente
        panelCentro.add(btnVerActividades);

        // Botón cerrar sesión
        panelCentro.add(btnVolver);

        add(panelCentro, BorderLayout.CENTER);
    }

    // =====================================================
    // Métodos que usa el ControladorClienteReservas
    // =====================================================

    /** Cambia el texto de saludo (ej: "Hola, Juan"). */
    public void setTextoSaludo(String texto) {
        labelSaludo.setText(texto);
    }

    /** Cambia el texto de la próxima reserva (ya incluye el prefijo en el controlador). */
    public void setTextoFechaProximaReserva(String texto) {
        labelProximaReserva.setText(texto);
    }

    // Botones

    public JButton getBtnHacerNuevaReserva() {
        return btnHacerNuevaReserva;
    }

    public JButton getBtnVerReservasAnteriores() {
        return btnVerReservasAnteriores;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    /** Botón "Cerrar sesión" / volver. */
    public JButton getBtnVolver() {
        return btnVolver;
    }

    // 🔹 Getter del botón de actividades (para ControladorClienteActividades o similar)
    public JButton getBtnVerActividades() {
        return btnVerActividades;
    }
}
