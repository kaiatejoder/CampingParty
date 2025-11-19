package CONTROLADOR;

import MODELO.Modelo;
import VISTA.ClienteLogLogin;

/**
 * Controlador mínimo para la ventana de login de cliente (ClienteLogLogin).
 */
public class ClienteLogLoginController {
    private final Modelo model;
    private final ClienteLogLogin view;

    public ClienteLogLoginController(Modelo model, ClienteLogLogin view) {
        this.model = model;
        this.view = view;
    }

    public void onLogin(String usuario, String password) {
        if (usuario == null || password == null || usuario.isEmpty() || password.isEmpty()) return;
        int i = model.tryUserPass(usuario, password);
        if (i > 0) {
            MODELO.Cliente c = model.clientes.get(i);
            VISTA.VistaCliente ventanaCliente = new VISTA.VistaCliente(c);
            view.dispose();
            ventanaCliente.setVisible(true);
            ventanaCliente.setLocationRelativeTo(null);
        } else {
            javax.swing.JOptionPane.showMessageDialog(view, "Usuario o contraseña incorrectos");
        }
    }
}
