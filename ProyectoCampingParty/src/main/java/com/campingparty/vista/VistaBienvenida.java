package com.campingparty.vista;

import com.campingparty.controlador.ControladorPrincipal;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

/**
 * VISTA DE BIENVENIDA - Migrada de NetBeans
 * 
 * Esta es la primera pantalla de la aplicación.
 * Permite al usuario elegir entre:
 * - Iniciar sesión como Cliente
 * - Iniciar sesión como Staff
 * 
 * @author Carla Terol
 */
public class VistaBienvenida extends javax.swing.JFrame implements IVista {
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(VistaBienvenida.class.getName());
    
    private ControladorPrincipal controlador;

    public VistaBienvenida(ControladorPrincipal controlador) {
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
        setTitle("Camping Party - Bienvenida");
        setResizable(false);
        setLocationRelativeTo(null);

        jLabel1.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 24));
        jLabel1.setText("¡Os damos la bienvenida!");

        jButton1.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 18));
        jButton1.setText("Soy Cliente");
        jButton1.addActionListener(evt -> controlador.abrirLoginCliente());

        jButton2.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 18));
        jButton2.setText("Soy Staff");
        jButton2.addActionListener(evt -> controlador.abrirLoginStaff());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pack();
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
        // No hay campos que limpiar en esta vista
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


