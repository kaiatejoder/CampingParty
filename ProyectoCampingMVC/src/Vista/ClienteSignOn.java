package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
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
    
    public ClienteSignOn() {
        setTitle("Proceso de Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("Datos Legales", crearVistaDatosLegales());
        tabbedPane.addTab("Datos Contacto", crearVistaDatosContacto());
        tabbedPane.addTab("Confirmación", crearVistaConfirmacion());
        
        add(tabbedPane);
        setVisible(true);
    }
    
    private JPanel crearVistaDatosLegales() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("Datos legales", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(titulo, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        formPanel.setBackground(Color.WHITE);
        
        JLabel dniLabel = new JLabel("DNI");
        JTextField dniField = new JTextField("DNI");
        
        JLabel nombreLabel = new JLabel("Nombre");
        JTextField nombreField = new JTextField("Nombre...");
        
        JLabel apellidosLabel = new JLabel("Apellidos");
        JTextField apellidosField = new JTextField("Apellidos");
        
        JLabel edadLabel = new JLabel("Edad");
        JTextField edadField = new JTextField("0");
        
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
        
        JButton siguienteBtn = new JButton("Siguiente");
        bottomPanel.add(siguienteBtn);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        JSeparator separator = new JSeparator();
        panel.add(separator, BorderLayout.PAGE_END);
        
        JLabel datosLabel = new JLabel("Datos", JLabel.CENTER);
        datosLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel.add(datosLabel, BorderLayout.AFTER_LAST_LINE);
        
        return panel;
    }
    
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
        
        JPanel telefonoPanel = new JPanel(new BorderLayout());
        telefonoPanel.setBackground(Color.WHITE);
        JLabel telefonoLabel = new JLabel("Número de teléfono");
        JTextField telefonoField = new JTextField("Nº de teléfono");
        telefonoPanel.add(telefonoLabel, BorderLayout.NORTH);
        telefonoPanel.add(telefonoField, BorderLayout.CENTER);
        
        JPanel emailPanel = new JPanel(new BorderLayout());
        emailPanel.setBackground(Color.WHITE);
        JLabel emailLabel = new JLabel("Correo electrónico");
        JTextField emailField = new JTextField("hola@campingriurau.es");
        emailPanel.add(emailLabel, BorderLayout.NORTH);
        emailPanel.add(emailField, BorderLayout.CENTER);
        
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBackground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Contraseña");
        
        JPanel passwordFieldPanel = new JPanel(new BorderLayout());
        JPasswordField passwordField = new JPasswordField();
        passwordField.setText("**************");
        
        JButton verBtn = new JButton("ver");
        passwordFieldPanel.add(passwordField, BorderLayout.CENTER);
        passwordFieldPanel.add(verBtn, BorderLayout.EAST);
        
        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordFieldPanel, BorderLayout.CENTER);
        
        formPanel.add(telefonoPanel);
        formPanel.add(emailPanel);
        formPanel.add(passwordPanel);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.WHITE);
        
        JButton atrasBtn = new JButton("Atrás");
        JButton siguienteBtn = new JButton("Siguiente");
        
        bottomPanel.add(atrasBtn);
        bottomPanel.add(siguienteBtn);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        JSeparator separator = new JSeparator();
        panel.add(separator, BorderLayout.PAGE_END);
        
        JLabel datosLabel = new JLabel("Datos Contacto Confirma", JLabel.CENTER);
        datosLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel.add(datosLabel, BorderLayout.AFTER_LAST_LINE);
        
        return panel;
    }
    
    private JPanel crearVistaConfirmacion() {
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
        
        JCheckBox privacidadCheck = new JCheckBox("He leído y acepto la Política de Privacidad");
        JCheckBox avisoLegalCheck = new JCheckBox("He leído y acepto el Aviso Legal");
        
        checkPanel.add(privacidadCheck);
        checkPanel.add(avisoLegalCheck);
        
        panel.add(checkPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.WHITE);
        
        JButton registrarBtn = new JButton("Registrarme");
        bottomPanel.add(registrarBtn);
        
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        JLabel datosLabel = new JLabel("Datos Contacto Confirma", JLabel.CENTER);
        datosLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panel.add(datosLabel, BorderLayout.PAGE_END);
        
        return panel;
    }
}