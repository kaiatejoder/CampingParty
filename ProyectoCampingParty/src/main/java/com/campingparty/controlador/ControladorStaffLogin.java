package com.campingparty.controlador;

import com.campingparty.modelo.Modelo;
import com.campingparty.modelo.Staff;
import com.campingparty.vista.StaffLogin;
import com.campingparty.vista.StaffSignIn;
import com.campingparty.vista.Welcome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para el flujo de login de Staff.
 * Maneja: volver atrás e iniciar sesión del staff.
 *
 * @author Carla Terol (Controlador MVC)
 */
public class ControladorStaffLogin extends CtrlStaff {

    private final Welcome welcome;
    private final StaffLogin staffLogin;
    private final StaffSignIn staffSignIn;

    public ControladorStaffLogin(Modelo modelo, Welcome welcome, StaffLogin staffLogin, StaffSignIn staffSign) {
        this.welcome = welcome;
        this.staffLogin = staffLogin;
        this.staffSignIn = staffSign;
        init();
    }

    // Constructor simplificado para compatibilidad (legacy)
    public ControladorStaffLogin(Welcome welcome, StaffLogin staffLogin, StaffSignIn staffIn) {
        this(null, welcome, staffLogin, staffIn);
    }

    private void init() {
        // Registrar listeners centralizados por ActionCommand
        staffLogin.addListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                
                if (cmd.equals("btnBack")) {
                    staffLogin.setVisible(false);
                    welcome.setLocationRelativeTo(null);
                    welcome.setVisible(true);
                } 
                else if (cmd.equals("btnLogIn")) {
                    // Abrir StaffMain sin staff específico (se obtiene en el StaffSignIn)
                    CONTROLADOR.getInstance().abrirStaffMain(null);
                    staffLogin.setVisible(false);
                }
            }
        });
        staffSignIn.addActionListeners(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                String cmd = evt.getActionCommand();
                
                if (cmd.equals("Login")) {
            
                        String usuario = staffSignIn.jTextField1.getText();
                        String contrasena = new String(staffSignIn.jPasswordField1.getPassword());

                        if (usuario.isBlank() || contrasena.isBlank()) {
                            javax.swing.JOptionPane.showMessageDialog(staffSignIn,
                            "Por favor, complete ambos campos.",
                            "Campos vacíos", javax.swing.JOptionPane.WARNING_MESSAGE);
                            }

                        MODELO.Staff staff = Staff.autenticar(usuario, contrasena);

                        if (staff == null) {
                        javax.swing.JOptionPane.showMessageDialog(staffSignIn,
                                "Usuario o contraseña incorrectos.",
                                "Error de autenticación", javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    javax.swing.JOptionPane.showMessageDialog(staffSignIn,
                            "Bienvenido/a, " + staff.getNombre() + "!",
                            "Inicio de sesión correcto", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    // Abre la pantalla principal del staff
                    CONTROLADOR.getInstance().abrirStaffMain(staff);
                    staffSignIn.dispose();
            }

            else if (cmd.equals("Back")) {
                staffLogin.setLocationRelativeTo(staffSignIn);
                staffLogin.setVisible(true);
                staffSignIn.dispose();
            }
        }});

            



    }
}


