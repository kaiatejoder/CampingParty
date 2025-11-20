package CONTROLADOR;

import MODELO.Modelo;
import VISTA.TrabajadorLogin;
import VISTA.TrabajadorSesión;
import VISTA.VistaLogin;

public class TrabajadorLoginController {

    private final Modelo model;
    private final TrabajadorLogin view;
    private final VistaLogin vistaLogin;

    public TrabajadorLoginController(Modelo model, TrabajadorLogin view, VistaLogin vistaLogin) {
        this.model = model;
        this.view = view;
        this.vistaLogin = vistaLogin;
    }

    public void onLogin() {
        // Abrir pantalla de autenticación del staff
        view.dispose();
        TrabajadorSesión sesion = new TrabajadorSesión(model);
        sesion.setLocationRelativeTo(null);
        sesion.setVisible(true);
    }

    public void onBack() {
        // Volver a la pantalla de bienvenida
        view.dispose();
        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setVisible(true);
    }
}
