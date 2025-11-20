package CONTROLADOR;

import MODELO.Modelo;
import VISTA.VistaLogin;
import VISTA.ClienteLogin;
import VISTA.TrabajadorLogin;

/**
 * Controlador para la pantalla de bienvenida/login.
 * Se encarga de reaccionar cuando el usuario pulsa
 * "Soy Cliente" o "Soy Staff" en VistaLogin.
 */
public class LoginController {

    private final Modelo model;
    private final VistaLogin view;

    public LoginController(Modelo model, VistaLogin view) {
        this.model = model;
        this.view = view;
    }

    public void onClientSelected() {
        // Ocultamos la pantalla de bienvenida
        view.setVisible(false);
        // Mostramos la ventana de login de cliente asociada a la vista
        ClienteLogin clienteLogin = view.getClienteLogin();
        if (clienteLogin != null) {
            clienteLogin.setLocationRelativeTo(view);
            clienteLogin.setVisible(true);
        }
    }

    public void onStaffSelected() {
        // Ocultamos la pantalla de bienvenida
        view.setVisible(false);
        // Mostramos la ventana de login de staff asociada a la vista
        TrabajadorLogin trabajadorLogin = view.getTrabajadorLogin();
        if (trabajadorLogin != null) {
            trabajadorLogin.setLocationRelativeTo(view);
            trabajadorLogin.setVisible(true);
        }
    }
}
