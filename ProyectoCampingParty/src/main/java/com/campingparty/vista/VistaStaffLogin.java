package com.campingparty.vista;

import com.campingparty.controlador.ControladorPrincipal;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

/**
 * VISTA STAFF LOGIN - Migrada de NetBeans
 * 
 * Pantalla donde el personal del camping inicia sesión.
 * 
 * @author Carla Terol
 */
public class VistaStaffLogin extends javax.swing.JFrame implements IVista {
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(VistaStaffLogin.class.getName());
    
    private ControladorPrincipal controlador;

    public VistaStaffLogin(ControladorPrincipal controlador) {
        this.controlador = controlador;
        FlatLightLaf.setup();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Camping Party - Login Staff");
        setResizable(false);
        setLocationRelativeTo(null);

        jLabel1.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Personal del Camping");

        jButton1.setText("Volver atrás");
        jButton1.addActionListener(evt -> {
            setVisible(false);
        });

        jButton2.setText("Iniciar sesión");
        jButton2.addActionListener(evt -> abrirLoginDialog());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(35, 35, 35))
        );

        pack();
    }

    private void abrirLoginDialog() {
        JDialog dialog = new JDialog(this, "Iniciar Sesión", true);
        dialog.setSize(350, 250);
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField(15);
        
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField(15);

        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");

        aceptar.addActionListener(e -> {
            String usuario = usuarioField.getText();
            String pass = new String(passField.getPassword());
            controlador.autenticarStaff(usuario, pass);
            dialog.dispose();
        });

        cancelar.addActionListener(e -> dialog.dispose());

        panel.add(usuarioLabel);
        panel.add(usuarioField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(passLabel);
        panel.add(passField);
        panel.add(Box.createVerticalStrut(20));

        JPanel botonesPanel = new JPanel();
        botonesPanel.add(aceptar);
        botonesPanel.add(cancelar);
        panel.add(botonesPanel);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void limpiar() {
        // No hay campos persistentes que limpiar
    }

    @Override
    public void mostrar() {
        setVisible(true);
    }

    @Override
    public void ocultar() {
        setVisible(false);
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration
}


