package Vista;

import RiuRauLaf.RiuRauLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ClienteSignOn extends JFrame {

    // --- Componentes que usará el controlador ---

    private JTabbedPane tabbedPane;

    // Datos legales
    private JTextField dniField;
    private JTextField nombreField;
    private JTextField apellidosField;
    private JTextField edadField;
    private JButton datosLegalesSiguienteBtn;

    // Datos contacto
    private JTextField telefonoField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton contactoAtrasBtn;
    private JButton contactoSiguienteBtn;
    private JButton verPasswordBtn;

    // Confirmación
    private JCheckBox privacidadCheck;
    private JCheckBox avisoLegalCheck;
    private JButton registrarBtn;

    public ClienteSignOn() {
        RiuRauLaf.setup();
        setTitle("Proceso de Registro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 500);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Datos Legales", crearVistaDatosLegales());
        tabbedPane.addTab("Datos Contacto", crearVistaDatosContacto());
        tabbedPane.addTab("Confirmación", crearVistaConfirmacion());

        add(tabbedPane);


    }

    // ================== PESTAÑA 1: DATOS LEGALES ==================

    private JPanel crearVistaDatosLegales() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Datos legales", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        formPanel.setBackground(Color.WHITE);

        JLabel dniLabel = new JLabel("DNI");
        dniField = new JTextField("DNI");

        JLabel nombreLabel = new JLabel("Nombre");
        nombreField = new JTextField("Nombre...");

        JLabel apellidosLabel = new JLabel("Apellidos");
        apellidosField = new JTextField("Apellidos");

        JLabel edadLabel = new JLabel("Edad");
        edadField = new JTextField("0");

        formPanel.add(dniLabel);
        formPanel.add(dniField);
        formPanel.add(nombreLabel);
        formPanel.add(nombreField);
        formPanel.add(apellidosLabel);
        formPanel.add(apellidosField);
        formPanel.add(edadLabel);
        formPanel.add(edadField);

        panel.add(formPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.WHITE);

        datosLegalesSiguienteBtn = new JButton("Siguiente");
        bottomPanel.add(datosLegalesSiguienteBtn);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        JSeparator separator = new JSeparator();
        panel.add(separator, BorderLayout.PAGE_END);

        

        return panel;
    }

    // ================== PESTAÑA 2: DATOS CONTACTO ==================

    private JPanel crearVistaDatosContacto() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Datos de contacto", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 1, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        formPanel.setBackground(Color.WHITE);

        // Teléfono
        JPanel telefonoPanel = new JPanel(new BorderLayout());
        telefonoPanel.setBackground(Color.WHITE);
        JLabel telefonoLabel = new JLabel("Número de teléfono");
        telefonoField = new JTextField("Nº de teléfono");
        telefonoPanel.add(telefonoLabel, BorderLayout.NORTH);
        telefonoPanel.add(telefonoField, BorderLayout.CENTER);

        // Email
        JPanel emailPanel = new JPanel(new BorderLayout());
        emailPanel.setBackground(Color.WHITE);
        JLabel emailLabel = new JLabel("Correo electrónico");
        emailField = new JTextField("hola@campingriurau.es");
        emailPanel.add(emailLabel, BorderLayout.NORTH);
        emailPanel.add(emailField, BorderLayout.CENTER);

        // Contraseña
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBackground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Contraseña");

        JPanel passwordFieldPanel = new JPanel(new BorderLayout());
        passwordField = new JPasswordField();
        passwordField.setText("**************");

        verPasswordBtn = new JButton("ver");
        verPasswordBtn.addActionListener(e -> togglePasswordVisibility());

        passwordFieldPanel.add(passwordField, BorderLayout.CENTER);
        passwordFieldPanel.add(verPasswordBtn, BorderLayout.EAST);

        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordFieldPanel, BorderLayout.CENTER);

        formPanel.add(telefonoPanel);
        formPanel.add(emailPanel);
        formPanel.add(passwordPanel);

        panel.add(formPanel, BorderLayout.CENTER);

        // Botones abajo
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.WHITE);

        contactoAtrasBtn = new JButton("Atrás");
        contactoSiguienteBtn = new JButton("Siguiente");

        bottomPanel.add(contactoAtrasBtn);
        bottomPanel.add(contactoSiguienteBtn);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        JSeparator separator = new JSeparator();
        panel.add(separator, BorderLayout.PAGE_END);

        JLabel datosLabel = new JLabel("Datos Contacto Confirma", JLabel.CENTER);
        datosLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel.add(datosLabel, BorderLayout.AFTER_LAST_LINE);

        return panel;
    }

    // ================== PESTAÑA 3: CONFIRMACIÓN ==================

    public JPanel crearVistaConfirmacion() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Confirmación", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel checkPanel = new JPanel();
        checkPanel.setLayout(new GridLayout(2, 1, 10, 10));
        checkPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        checkPanel.setBackground(Color.WHITE);

        privacidadCheck = new JCheckBox("He leído y acepto la Política de Privacidad");
        avisoLegalCheck = new JCheckBox("He leído y acepto el Aviso Legal");

        checkPanel.add(privacidadCheck);
        checkPanel.add(avisoLegalCheck);

        panel.add(checkPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.WHITE);

        registrarBtn = new JButton("Registrarme");
        bottomPanel.add(registrarBtn);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        JButton datosConfButton = new JButton("Confirmar");
        datosConfButton.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panel.add(datosConfButton, BorderLayout.PAGE_END);

        return panel;
    }

    // ================== LÓGICA INTERNA DE VISTA ==================

    private void togglePasswordVisibility() {
        if (passwordField.getEchoChar() != 0) {
            passwordField.setEchoChar((char) 0);
            verPasswordBtn.setText("ocultar");
        } else {
            passwordField.setEchoChar('•');
            verPasswordBtn.setText("ver");
        }
    }

    // ================== GETTERS PARA EL CONTROLADOR ==================

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    // Datos legales
    public JTextField getDniField() {
        return dniField;
    }

    public JTextField getNombreField() {
        return nombreField;
    }

    public JTextField getApellidosField() {
        return apellidosField;
    }

    public JTextField getEdadField() {
        return edadField;
    }

    public JButton getDatosLegalesSiguienteBtn() {
        return datosLegalesSiguienteBtn;
    }

    // Datos contacto
    public JTextField getTelefonoField() {
        return telefonoField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getContactoAtrasBtn() {
        return contactoAtrasBtn;
    }

    public JButton getContactoSiguienteBtn() {
        return contactoSiguienteBtn;
    }

    public JButton getVerPasswordBtn() {
        return verPasswordBtn;
    }

    // Confirmación
    public JCheckBox getPrivacidadCheck() {
        return privacidadCheck;
    }

    public JCheckBox getAvisoLegalCheck() {
        return avisoLegalCheck;
    }

    public JButton getRegistrarBtn() {
        return registrarBtn;
    }
    
}
