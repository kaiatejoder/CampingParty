package CONTROLADOR;

import MODELO.Cliente;
import MODELO.Modelo;
import VISTA.ClientLogin;
import VISTA.ClientSignIn;
import VISTA.ClienteSignOn;
import VISTA.Welcome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador concreto para la navegación del login de clientes.
 * Maneja: volver atrás, registro y login de clientes.
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
        // Listeners para ClientLogin
        clientLogin.addListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                
                if (cmd.equals("btnBack")) {
                    clientLogin.setVisible(false);
                    welcome.setLocationRelativeTo(null);
                    welcome.setVisible(true);
                } 
                else if (cmd.equals("btnSignIn")) {
                    if (clienteSignOn != null) {
                        clientLogin.setVisible(false);
                        clienteSignOn.setLocationRelativeTo(clientLogin);
                        clienteSignOn.setVisible(true);
                    }
                } 
                else if (cmd.equals("btnLogIn")) {
                    if (clientSignIn != null) {
                        clientLogin.setVisible(false);
                        clientSignIn.setLocationRelativeTo(clientLogin);
                        clientSignIn.setVisible(true);
                    }
                }
            }
        });

        // Listeners para ClientSignIn
        clientSignIn.AddListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                
                if (cmd.equals("SignIn")) {
                    String usuario = clientSignIn.jTextField1.getText().trim();
                    String password = new String(clientSignIn.jPasswordField1.getPassword());
                    
                    if (usuario.isEmpty() || password.isEmpty()) {
                        clientSignIn.jLabelError.setText("ERROR: Usuario o contraseña incorrectos");
                    } else {
                        Cliente c = modelo.tryUserPass(usuario, password);
                        if (c != null) {
                            // Usar controlador central para abrir VistaCliente
                            CONTROLADOR.getInstance().abrirVistaCliente(c);
                            clientSignIn.dispose();
                        } else {
                            clientSignIn.jLabelError.setText("ERROR: Usuario o contraseña incorrectos");
                        }
                    }
                } 
                else if (cmd.equals("goBack")) {
                    clientSignIn.setVisible(false);
                    clientLogin.setLocationRelativeTo(null);
                    clientLogin.setVisible(true);
                }
            }
        });
    }
}
