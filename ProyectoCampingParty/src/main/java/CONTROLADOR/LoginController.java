package CONTROLADOR;

import MODELO.Modelo;
import VISTA.VistaLogin;

/**
 * Controlador esqueleto para la pantalla de bienvenida/login.
 */
public class LoginController {

    private final Modelo model;
    private final VistaLogin view;

    public LoginController(Modelo model, VistaLogin view) {
        this.model = model;
        this.view = view;
    }

    public void onClientSelected() {
        // TODO: navegar a flujo cliente
        view.setVisible(false);
        // la vista puede crear/mostrar ClienteLogin si es necesario
    }

    public void onStaffSelected() {
        // TODO: navegar a flujo staff
        view.setVisible(false);
    }
}
