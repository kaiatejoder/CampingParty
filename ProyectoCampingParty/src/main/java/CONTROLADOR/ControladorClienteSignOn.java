package CONTROLADOR;

import VISTA.ClienteSignOn;
import MODELO.Modelo;
import MODELO.Cliente;
import MODELO.Valid;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Controlador para la vista ClienteSignOn (Registro de nuevos clientes).
 * Gestiona la validación de datos y la inserción en base de datos.
 *
 * @author Carla Terol
 */
public class ControladorClienteSignOn implements ActionListener {

    private ClienteSignOn vista;
    private final Modelo modelo;

    public ControladorClienteSignOn(ClienteSignOn vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        init();
    }

    /**
     * Asigna la vista a este controlador (inyección después de instanciación).
     */
    public void setVista(ClienteSignOn vista) {
        this.vista = vista;
        // Cuando se asigna la vista, registrar listeners
        registrarListeners();
    }

    private void init() {
        // Inicialización si es necesaria
    }

    /**
     * Registra los listeners centralizados
     */
    private void registrarListeners() {
        if (vista != null) {
            vista.addListeners(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    
                    if (cmd.equals("btnBack")) {
                        vista.dispose();
                    } 
                    else if (cmd.equals("btnRegister")) {
                        // La validación y registro ya se hace en el método registrarCliente
                        // que se llama desde el ActionListener del botón en la vista
                    }
                }
            });
        }
    }

    /**
     * Procesa el registro de un nuevo cliente.
     * Valida los datos del formulario y los inserta en la BD.
     */
    public void registrarCliente(String nombre, String dni, int edad, int telefono, 
                                 String usuario, String contrasena) {
        try {
            // Validar campos
            if (nombre.isEmpty() || dni.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar contraseña
            if (!Valid.password(contrasena.toCharArray())) {
                JOptionPane.showMessageDialog(vista, 
                    "Contraseña débil. Mínimo 8 caracteres con mayúscula, minúscula y número", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear cliente
            Cliente clienteNuevo = new Cliente(nombre, dni, 0, edad, telefono, usuario, contrasena);

            // Insertar en BD
            boolean resultado = modelo.getDAO().agregarCliente(clienteNuevo);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "✓ Registro exitoso", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                // Cerrar ventana después de 1.5 segundos
                javax.swing.Timer timer = new javax.swing.Timer(1500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        vista.dispose();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                JOptionPane.showMessageDialog(vista, "✗ Error al registrar el cliente", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Este método se implementa cuando sea necesario manejar eventos de botones
    }
}
