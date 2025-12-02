package Controlador;

import Modelo.Cliente;
import Modelo.Modelo;
import Modelo.Valid;
import Vista.ClienteLogLogin;
import Vista.ClienteLogin;
import Vista.ClienteSignOn;
import Vista.VistaBienvenida;
import Vista.VistaCliente;
import Vista.VistaClienteModificarReserva;
import Vista.VistaClienteReserva;
import Vista.VistaClienteReservaEditado;
import Vista.VistaReservas;
import Vista.ClienteConReserva;
import Vista.VistaClienteActividades;   // 👈 IMPORT NUEVO

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controla todo el flujo de login / registro de clientes.
 *
 * Pantallas:
 *  - VistaBienvenida (solo para volver atrás)
 *  - ClienteLogin (menú cliente: registrarse / iniciar sesión / volver atrás)
 *  - ClienteLogLogin (formulario login)
 *  - ClienteSignOn (registro multi-pestaña)
 */
public class ControladorClienteLogin {

    private final Modelo modelo;
    private final VistaBienvenida vistaBienvenida;
    private final ClienteLogin vistaClienteLogin;
    private final ClienteLogLogin vistaClienteLogLogin;
    private final ClienteSignOn vistaClienteSignOn;

    public ControladorClienteLogin(Modelo modelo,
                                   VistaBienvenida vistaBienvenida,
                                   ClienteLogin vistaClienteLogin,
                                   ClienteLogLogin vistaClienteLogLogin,
                                   ClienteSignOn vistaClienteSignOn) {
        this.modelo = modelo;
        this.vistaBienvenida = vistaBienvenida;
        this.vistaClienteLogin = vistaClienteLogin;
        this.vistaClienteLogLogin = vistaClienteLogLogin;
        this.vistaClienteSignOn = vistaClienteSignOn;

        inicializar();
    }

    private void inicializar() {
        // ----- Pantalla "ClienteLogin" -----
        vistaClienteLogin.getBotonRegistrarse().addActionListener(e -> mostrarRegistro());
        vistaClienteLogin.getBotonIniciarSesion().addActionListener(e -> mostrarPantallaLogin());
        vistaClienteLogin.getBotonVolverAtras().addActionListener(e -> volverABienvenidaDesdeClienteLogin());

        // ----- Pantalla "ClienteLogLogin" -----
        vistaClienteLogLogin.getBotonLogin().addActionListener(e -> onLoginCliente());

        // ----- Pantalla "ClienteSignOn" (registro) -----
        // Navegación entre pestañas
        vistaClienteSignOn.getDatosLegalesSiguienteBtn().addActionListener(e -> irAPestaniaContacto());
        vistaClienteSignOn.getContactoAtrasBtn().addActionListener(e -> irAPestaniaDatosLegales());
        vistaClienteSignOn.getContactoSiguienteBtn().addActionListener(e -> irAPestaniaConfirmacion());

        // Registro final
        vistaClienteSignOn.getRegistrarBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRegistrarCliente();
            }
        });
    }

    // ================== ClienteLogin ==================

    private void mostrarRegistro() {
        vistaClienteLogin.setVisible(false);
        vistaClienteSignOn.setLocationRelativeTo(vistaClienteLogin);
        vistaClienteSignOn.setVisible(true);
    }

    private void mostrarPantallaLogin() {
        vistaClienteLogin.setVisible(false);
        vistaClienteLogLogin.setLocationRelativeTo(vistaClienteLogin);
        vistaClienteLogLogin.setVisible(true);
    }

    private void volverABienvenidaDesdeClienteLogin() {
        vistaClienteLogin.setVisible(false);
        vistaBienvenida.setLocationRelativeTo(vistaClienteLogin);
        vistaBienvenida.setVisible(true);
    }

    // ================== ClienteLogLogin (login) ==================

    private void onLoginCliente() {
        String usuario = vistaClienteLogLogin.getCampoUsuario().getText().trim();
        String password = new String(vistaClienteLogLogin.getCampoPassword().getPassword());

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vistaClienteLogLogin,
                    "Debes introducir usuario y contraseña.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Cliente cliente = modelo.autenticarCliente(usuario, password);
        if (cliente == null) {
            JOptionPane.showMessageDialog(
                    vistaClienteLogLogin,
                    "Usuario o contraseña incorrectos.",
                    "Error de autenticación",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Login correcto → creamos vistas de cliente y su controlador de reservas
        VistaCliente vistaCliente = new VistaCliente();
        VistaClienteReserva vistaClienteReserva = new VistaClienteReserva();
        VistaClienteModificarReserva vistaClienteModificar = new VistaClienteModificarReserva();
        VistaClienteReservaEditado vistaClienteReservaEditado = new VistaClienteReservaEditado();
        VistaReservas vistaReservas = new VistaReservas();
        ClienteConReserva vistaClienteConReserva = new ClienteConReserva();
        VistaClienteActividades vistaClienteActividades = new VistaClienteActividades(); // 👈 NUEVA VISTA

        new ControladorClienteReservas(
                modelo,
                cliente,
                vistaCliente,
                vistaClienteReserva,
                vistaClienteModificar,
                vistaClienteReservaEditado,
                vistaReservas,
                vistaClienteConReserva,
                vistaClienteActividades      // 👈 NUEVO PARÁMETRO
        );

        JOptionPane.showMessageDialog(
                vistaClienteLogLogin,
                "¡Bienvenido, " + cliente.getNombre() + "!",
                "Login correcto",
                JOptionPane.INFORMATION_MESSAGE
        );

        vistaClienteLogLogin.dispose(); // Cerramos la ventana de login
        vistaCliente.setLocationRelativeTo(null);
        vistaCliente.setVisible(true);
    }

    // ================== ClienteSignOn (registro) ==================

    private void irAPestaniaContacto() {
        vistaClienteSignOn.getTabbedPane().setSelectedIndex(1);
    }

    private void irAPestaniaDatosLegales() {
        vistaClienteSignOn.getTabbedPane().setSelectedIndex(0);
    }

    private void irAPestaniaConfirmacion() {
        vistaClienteSignOn.getTabbedPane().setSelectedIndex(2);
    }

    private void onRegistrarCliente() {
        // Datos legales
        String dni = vistaClienteSignOn.getDniField().getText().trim();
        String nombre = vistaClienteSignOn.getNombreField().getText().trim();
        String apellidos = vistaClienteSignOn.getApellidosField().getText().trim();
        String edadStr = vistaClienteSignOn.getEdadField().getText().trim();

        // Contacto
        String telefonoStr = vistaClienteSignOn.getTelefonoField().getText().trim();
        String email = vistaClienteSignOn.getEmailField().getText().trim();
        String password = new String(vistaClienteSignOn.getPasswordField().getPassword());

        // Confirmación
        boolean privacidadOk = vistaClienteSignOn.getPrivacidadCheck().isSelected();
        boolean avisoLegalOk = vistaClienteSignOn.getAvisoLegalCheck().isSelected();

        // Validaciones básicas
        if (!privacidadOk || !avisoLegalOk) {
            JOptionPane.showMessageDialog(
                    vistaClienteSignOn,
                    "Debes aceptar la política de privacidad y el aviso legal.",
                    "Condiciones no aceptadas",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty()
                || edadStr.isEmpty() || telefonoStr.isEmpty()
                || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vistaClienteSignOn,
                    "Todos los campos son obligatorios.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!Valid.dni(dni)) {
            JOptionPane.showMessageDialog(
                    vistaClienteSignOn,
                    "El DNI introducido no es válido.",
                    "DNI incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!Valid.password(password)) {
            JOptionPane.showMessageDialog(
                    vistaClienteSignOn,
                    "La contraseña no cumple los requisitos de seguridad.",
                    "Contraseña débil",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int edad;
        int telefono;
        try {
            edad = Integer.parseInt(edadStr);
            telefono = Integer.parseInt(telefonoStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    vistaClienteSignOn,
                    "Edad y teléfono deben ser números.",
                    "Formato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String nombreCompleto = nombre + " " + apellidos;

        Cliente nuevoCliente = new Cliente(
                nombreCompleto,
                dni,
                edad,
                telefono,
                email,
                password
        );

        boolean registrado = modelo.registrarCliente(nuevoCliente);
        if (!registrado) {
            JOptionPane.showMessageDialog(
                    vistaClienteSignOn,
                    "Ya existe un cliente con ese usuario (email).",
                    "Usuario duplicado",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                vistaClienteSignOn,
                "Registro completado. Ahora puedes iniciar sesión.",
                "Registro correcto",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Volvemos a la pantalla de login de cliente
        vistaClienteSignOn.dispose();
        vistaClienteLogLogin.setLocationRelativeTo(null);
        vistaClienteLogLogin.setVisible(true);
    }
}
