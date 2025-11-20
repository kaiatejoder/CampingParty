package MAIN;

import CONTROLADOR.LoginController;
import CONTROLADOR.ClienteLoginController;
import MODELO.Modelo;
import VISTA.ClienteLogin;
import VISTA.TrabajadorLogin;
import VISTA.VistaLogin;

/**
 * Punto de entrada de la aplicación CampingParty.
 */
public class ProyectoCampingParty {

    public static void main(String[] args) {
        // Un único modelo compartido
        Modelo model = new Modelo();

        // Crear vistas principales
        TrabajadorLogin vistaTrabajadorLogin = new TrabajadorLogin();
        ClienteLogin vistaClienteLogin = new ClienteLogin();
        VistaLogin vistaLogin = new VistaLogin(vistaTrabajadorLogin, vistaClienteLogin);

        // Controlador de la pantalla de bienvenida
        LoginController loginController = new LoginController(model, vistaLogin);
        vistaLogin.setController(loginController);

        // Controlador de la pantalla ClienteLogin (elige iniciar sesión / registrarse / volver)
        ClienteLoginController clienteLoginController =
                new ClienteLoginController(model, vistaClienteLogin, vistaLogin);
        vistaClienteLogin.setController(clienteLoginController);

        // Mostrar la pantalla inicial
        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setVisible(true);
    }
}
