package Controlador;

import Modelo.Modelo;
import Vista.ClienteLogLogin;
import Vista.ClienteLogin;
import Vista.ClienteSignOn;
import Vista.TrabajadorLogin;
import Vista.TrabajadorSesion;
import Vista.VistaBienvenida;

import javax.swing.SwingUtilities;

/**
 * Controlador principal de la aplicación.
 * Se encarga de crear el Modelo, las vistas básicas y el resto de controladores.
 */
public class Controlador {

    private final Modelo modelo;

    // Vistas compartidas
    private final VistaBienvenida vistaBienvenida;
    private final ClienteLogin vistaClienteLogin;
    private final ClienteLogLogin vistaClienteLogLogin;
    private final ClienteSignOn vistaClienteSignOn;
    private final TrabajadorLogin vistaTrabajadorLogin;
    private final TrabajadorSesion vistaTrabajadorSesion;

    // Controladores
    private final ControladorBienvenida controladorBienvenida;
    private final ControladorClienteLogin controladorClienteLogin;
    private final ControladorStaffLogin controladorStaffLogin;

    public Controlador() {
        // Modelo
        modelo = new Modelo();

        // Vistas base
        vistaBienvenida = new VistaBienvenida();
        vistaClienteLogin = new ClienteLogin();
        vistaClienteLogLogin = new ClienteLogLogin();
        vistaClienteSignOn = new ClienteSignOn();
        vistaTrabajadorLogin = new TrabajadorLogin();
        vistaTrabajadorSesion = new TrabajadorSesion();

        // Controlador login cliente
        controladorClienteLogin =
                new ControladorClienteLogin(
                        modelo,
                        vistaBienvenida,
                        vistaClienteLogin,
                        vistaClienteLogLogin,
                        vistaClienteSignOn
                );

        // Controlador login staff
        controladorStaffLogin =
                new ControladorStaffLogin(
                        modelo,
                        vistaBienvenida,
                        vistaTrabajadorLogin,
                        vistaTrabajadorSesion
                );

        // Controlador de la pantalla de bienvenida
        controladorBienvenida =
                new ControladorBienvenida(
                        modelo,
                        vistaBienvenida,
                        vistaClienteLogin,
                        vistaTrabajadorLogin
                );
    }

    /**
     * Método de arranque de la interfaz.
     */
    public void iniciar() {
        SwingUtilities.invokeLater(() -> vistaBienvenida.setVisible(true));
    }
}
