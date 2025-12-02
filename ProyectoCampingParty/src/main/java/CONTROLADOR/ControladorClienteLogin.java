package CONTROLADOR;

import MODELO.Modelo;
import VISTA.ClientLogin;
import VISTA.ClientSignIn;
import VISTA.ClienteSignOn;
import VISTA.Welcome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador concreto para la navegación del login de clientes.
 */
public class ControladorClienteLogin extends CtrlCli {

    private final Modelo modelo;
    private final Welcome welcome;
    private final ClientLogin clientLogin;
    private final ClientSignIn clientSignIn;
    private final ClienteSignOn clienteSignOn;

    public ControladorClienteLogin(Modelo modelo,
                                   Welcome welcome,
                                   ClientLogin clientLogin,
                                   ClientSignIn clientSignIn,
                                   ClienteSignOn clienteSignOn) {
        this.modelo = modelo;
        this.welcome = welcome;
        this.clientLogin = clientLogin;
        this.clientSignIn = clientSignIn;
        this.clienteSignOn = clienteSignOn;

        init();
    }

    private void init() {
        // Volver a bienvenida
        clientLogin.getBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientLogin.setVisible(false);
                welcome.setLocationRelativeTo(null);
                welcome.setVisible(true);
            }
        });

        // Abrir pantalla de registro
        clientLogin.getSignIn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Preferimos mostrar la vista que nos pasaron si existe
                if (clienteSignOn != null) {
                    clientLogin.setVisible(false);
                    clienteSignOn.setLocationRelativeTo(clientLogin);
                    clienteSignOn.setVisible(true);
                } else {
                    new ClienteSignOn(modelo).setVisible(true);
                    clientLogin.setVisible(false);
                }
            }
        });

        // Abrir pantalla de iniciar sesión (form existente)
        clientLogin.getLogIn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientSignIn != null) {
                    clientLogin.setVisible(false);
                    clientSignIn.setLocationRelativeTo(clientLogin);
                    clientSignIn.setVisible(true);
                } else {
                    new ClientSignIn(modelo).setVisible(true);
                    clientLogin.setVisible(false);
                }
            }
        });
    }
}
