package CONTROLADOR;

import MODELO.Modelo;
import VISTA.ClienteLogin;

/**
 * Controlador mínimo para `ClienteLogin`.
 */
public class ClienteLoginController {
    private final Modelo model;
    private final ClienteLogin view;

    public ClienteLoginController(Modelo model, ClienteLogin view) {
        this.model = model;
        this.view = view;
    }

    public void onRegister() {
        // Abrir pantalla de registro
        view.dispose();
        new VISTA.ClienteSignOn(model).setVisible(true);
    }

    public void onLogin() {
        // Abrir ventana de login de cliente (legacy)
        view.dispose();
        VISTA.ClienteLogLogin clientelog = new VISTA.ClienteLogLogin();
        clientelog.setVisible(true);
        clientelog.setLocationRelativeTo(null);
    }

    public void onBack() {
        // Volver a la pantalla principal de selección
        view.dispose();
        VISTA.TrabajadorLogin ventanaTrabajador = new VISTA.TrabajadorLogin();
        VISTA.ClienteLogin ventanaCliente = new VISTA.ClienteLogin();
        VISTA.VistaLogin ventanaPrincipal = new VISTA.VistaLogin(ventanaTrabajador, ventanaCliente);
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setLocationRelativeTo(null);
    }
}
