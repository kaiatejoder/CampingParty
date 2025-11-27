package Controlador;

import Modelo.Modelo;
import Modelo.Staff;
import Vista.StaffMain;
import Vista.TrabajadorLogin;
import Vista.TrabajadorSesion;
import Vista.VistaBienvenida;
import Vista.VistaStaffActividad;
import Vista.VistaStaffEntrada;
import Vista.ActividadSeleccionada;

import javax.swing.*;

/**
 * Controla el flujo de login del personal de Staff.
 *
 * Pantallas:
 *  - TrabajadorLogin (ventana con "Iniciar sesión" / "Volver")
 *  - TrabajadorSesion (formulario usuario/contraseña)
 *  - StaffMain (panel principal staff, gestionado por ControladorStaff)
 */
public class ControladorStaffLogin {

    private final Modelo modelo;
    private final VistaBienvenida vistaBienvenida;
    private final TrabajadorLogin vistaTrabajadorLogin;
    private final TrabajadorSesion vistaTrabajadorSesion;

    public ControladorStaffLogin(Modelo modelo,
                                 VistaBienvenida vistaBienvenida,
                                 TrabajadorLogin vistaTrabajadorLogin,
                                 TrabajadorSesion vistaTrabajadorSesion) {
        this.modelo = modelo;
        this.vistaBienvenida = vistaBienvenida;
        this.vistaTrabajadorLogin = vistaTrabajadorLogin;
        this.vistaTrabajadorSesion = vistaTrabajadorSesion;

        inicializar();
    }

    private void inicializar() {
        // ---- TrabajadorLogin ----
        vistaTrabajadorLogin.getBtnIniciarSesion().addActionListener(e -> irASesion());
        vistaTrabajadorLogin.getBtnVolverAtras().addActionListener(e -> volverABienvenida());

        // ---- TrabajadorSesion ----
        vistaTrabajadorSesion.getBtnVolverAtras().addActionListener(e -> volverALoginStaff());
        vistaTrabajadorSesion.getBtnIniciar().addActionListener(e -> onLoginStaff());
    }

    private void irASesion() {
        vistaTrabajadorLogin.setVisible(false);
        vistaTrabajadorSesion.setLocationRelativeTo(vistaTrabajadorLogin);
        vistaTrabajadorSesion.setVisible(true);
    }

    private void volverABienvenida() {
        vistaTrabajadorLogin.setVisible(false);
        vistaBienvenida.setLocationRelativeTo(vistaTrabajadorLogin);
        vistaBienvenida.setVisible(true);
    }

    private void volverALoginStaff() {
        vistaTrabajadorSesion.setVisible(false);
        vistaTrabajadorLogin.setLocationRelativeTo(vistaTrabajadorSesion);
        vistaTrabajadorLogin.setVisible(true);
    }

    private void onLoginStaff() {
        String usuario = vistaTrabajadorSesion.getUsuario();
        String password = vistaTrabajadorSesion.getPassword();

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vistaTrabajadorSesion,
                    "Debes introducir usuario y contraseña.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Staff staff = Staff.autenticar(usuario, password);
        if (staff == null) {
            JOptionPane.showMessageDialog(
                    vistaTrabajadorSesion,
                    "Usuario o contraseña incorrectos.",
                    "Error de autenticación",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Login correcto → creamos vistas y controlador de staff
        StaffMain vistaStaffMain = new StaffMain();
        VistaStaffEntrada vistaStaffEntrada = new VistaStaffEntrada();
        VistaStaffActividad vistaStaffActividad = new VistaStaffActividad();
        ActividadSeleccionada vistaActividadSeleccionada = new ActividadSeleccionada();

        new ControladorStaff(
                modelo,
                staff,
                vistaStaffMain,
                vistaStaffEntrada,
                vistaStaffActividad,
                vistaActividadSeleccionada
        );

        JOptionPane.showMessageDialog(
                vistaTrabajadorSesion,
                "Bienvenido, " + staff.getNombre() + ".",
                "Login correcto",
                JOptionPane.INFORMATION_MESSAGE
        );

        vistaTrabajadorSesion.dispose();
        vistaStaffMain.setLocationRelativeTo(null);
        vistaStaffMain.setVisible(true);
    }
}
