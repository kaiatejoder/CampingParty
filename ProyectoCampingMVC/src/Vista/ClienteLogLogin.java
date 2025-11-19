package Vista;

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
    private LoginPanel loginPanel;
    
    public ClienteLogLogin(){
        loginPanel = new LoginPanel();
        this.setLayout(new BorderLayout());
        add(loginPanel, BorderLayout.CENTER);
        
        this.setTitle("Inicio de Sesión");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); 
    }
}

class LoginPanel extends JPanel {
    private final JLabel titulo, usuarioLabel, passwordLabel;
    private final JTextField usuarioField;
    private final JPasswordField passwordField;
    private final JButton verPassword, loginBtn;

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
        
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(verPassword, BorderLayout.EAST);
        centroPanel.add(passwordPanel);
        
        add(centroPanel, BorderLayout.CENTER);
        
        JPanel surPanel = new JPanel();
        loginBtn = new JButton("Iniciar Sesión");
        surPanel.add(loginBtn);
        add(surPanel, BorderLayout.SOUTH);
    }
    
    private void togglePasswordVisibility() {
        if (passwordField.getEchoChar() != 0) {
            passwordField.setEchoChar((char)0);
            verPassword.setText("ocultar");
        } else {
            passwordField.setEchoChar('•');
            verPassword.setText("ver");
        }
    }
}