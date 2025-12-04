package com.campingparty.vista;



import com.campingparty.controlador.ControladorPrincipal;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

/**
 * VISTA CLIENTE LOGIN - Migrada de NetBeans
 * 
 * Pantalla donde el cliente puede:
 * - Iniciar sesión (Login)
 * - Registrarse (Sign On)
 * - Volver atrás
 * 
 * @author Carla Terol
 */
public class VistaClienteLogin extends javax.swing.JFrame implements IVista {
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(VistaClienteLogin.class.getName());
    
    private ControladorPrincipal controlador;

    public VistaClienteLogin(ControladorPrincipal controlador) {
        this.controlador = controlador;
        FlatLightLaf.setup();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Camping Party - Login Cliente");
        setResizable(false);
        setLocationRelativeTo(null);

        jLabel1.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Identifícate");

        jButton1.setText("Volver atrás");
        jButton1.addActionListener(evt -> {
            setVisible(false);
        });

        jButton2.setText("Iniciar sesión");
        jButton2.addActionListener(evt -> abrirLoginDialog());

        jButton3.setText("Registrarme");
        jButton3.addActionListener(evt -> abrirSignOnDialog());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton3)
                        .addGap(40, 40, 40)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
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
            controlador.autenticarCliente(usuario, pass);
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

    private void abrirSignOnDialog() {
        JDialog dialog = new JDialog(this, "Registrarse", true);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField(15);
        
        JLabel dniLabel = new JLabel("DNI:");
        JTextField dniField = new JTextField(15);
        
        JLabel edadLabel = new JLabel("Edad:");
        JTextField edadField = new JTextField(15);
        
        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField(15);
        
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField(15);

        JButton aceptar = new JButton("Registrarse");
        JButton cancelar = new JButton("Cancelar");

        aceptar.addActionListener(e -> {
            mostrarMensaje("Funcionalidad de registro aún no implementada");
            dialog.dispose();
        });

        cancelar.addActionListener(e -> dialog.dispose());

        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(dniLabel);
        panel.add(dniField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(edadLabel);
        panel.add(edadField);
        panel.add(Box.createVerticalStrut(10));
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
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration
}


