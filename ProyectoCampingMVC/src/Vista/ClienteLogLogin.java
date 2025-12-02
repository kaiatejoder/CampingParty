package Vista;

import RiuRauLaf.RiuRauLaf;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ClienteLogLogin extends JFrame {

    private final LoginPanel loginPanel;

    public ClienteLogLogin() {
        RiuRauLaf.setup();
        loginPanel = new LoginPanel();
        this.setLayout(new BorderLayout());
        this.add(loginPanel, BorderLayout.CENTER);

        this.setTitle("Inicio de Sesión");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
    }

    // === GETTERS para que el controlador pueda trabajar con la vista ===

    public JButton getBotonLogin() {
        return loginPanel.getBotonLogin();
    }

    public JTextField getCampoUsuario() {
        return loginPanel.getCampoUsuario();
    }

    public JPasswordField getCampoPassword() {
        return loginPanel.getCampoPassword();
    }

    public JButton getBotonVerPassword() {
        return loginPanel.getBotonVerPassword();
    }
}

class LoginPanel extends JPanel {

    private final JLabel titulo;
    private final JLabel usuarioLabel;
    private final JLabel passwordLabel;
    private final JTextField usuarioField;
    private final JPasswordField passwordField;
    private final JButton verPassword;
    private final JButton loginBtn;
    

    public LoginPanel() {
        this.setLayout(new BorderLayout(0, 20));

        JPanel centroPanel = new JPanel(new GridLayout(5, 1, 0, 10));

        titulo = new JLabel("Hola de nuevo");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        centroPanel.add(titulo);

        usuarioLabel = new JLabel("Nombre de usuario");
        centroPanel.add(usuarioLabel);

        usuarioField = new JTextField();
        usuarioField.setText("admin");
        centroPanel.add(usuarioField);

        passwordLabel = new JLabel("Contraseña");
        centroPanel.add(passwordLabel);

        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordField = new JPasswordField();
        verPassword = new JButton("ver");

        // Botón para mostrar/ocultar contraseña
        verPassword.addActionListener(e -> togglePasswordVisibility());

        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(verPassword, BorderLayout.EAST);
        centroPanel.add(passwordPanel);

        this.add(centroPanel, BorderLayout.CENTER);

        JPanel surPanel = new JPanel();
        loginBtn = new JButton("Iniciar Sesión");
        surPanel.add(loginBtn);
        this.add(surPanel, BorderLayout.SOUTH);
    }

    private void togglePasswordVisibility() {
        if (passwordField.getEchoChar() != 0) {
            passwordField.setEchoChar((char) 0);
            verPassword.setText("ocultar");
        } else {
            passwordField.setEchoChar('•');
            verPassword.setText("ver");
        }
    }

    // === GETTERS para que ClienteLogLogin exponga al controlador ===

    public JTextField getCampoUsuario() {
        return usuarioField;
    }

    public JPasswordField getCampoPassword() {
        return passwordField;
    }

    public JButton getBotonLogin() {
        return loginBtn;
    }

    public JButton getBotonVerPassword() {
        return verPassword;
    }
}
