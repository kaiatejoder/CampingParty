package CONTROLADOR;

import MODELO.Modelo;
import VISTA.ClienteLogin;
import VISTA.ClienteLogLogin;
import VISTA.ClienteSignOn;
import VISTA.VistaLogin;

/**
 * Controlador para la pantalla donde el cliente elige:
 * - Iniciar sesión
 * - Registrarse
 * - Volver atrás
 */
public class ClienteLoginController {
    private final Modelo model;
    private final ClienteLogin view;
    private final VistaLogin vistaLogin; // para poder volver atrás

    public ClienteLoginController(Modelo model, ClienteLogin view, VistaLogin vistaLogin) {
        this.model = model;
        this.view = view;
        this.vistaLogin = vistaLogin;
    }

    public void onRegister() {
        // Abrir pantalla de registro usando el mismo modelo
        view.dispose();
        ClienteSignOn signOnView = new ClienteSignOn(model);
        signOnView.setLocationRelativeTo(null);
        signOnView.setVisible(true);
    }

    public void onLogin() {
        // Abrir ventana donde se introduce usuario/contraseña
        view.dispose();
        ClienteLogLogin loginView = new ClienteLogLogin();
        ClienteLogLoginController loginController =
                new ClienteLogLoginController(model, loginView);
        loginView.setController(loginController);
        loginView.setLocationRelativeTo(null);
        loginView.setVisible(true);
    }

    public void onBack() {
        // Volver a la pantalla principal de selección (la que ya teníamos creada)
        view.dispose();
        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setVisible(true);
    }
}
