package CONTROLADOR;

import MODELO.Modelo;
import VISTA.*;
import javax.swing.SwingUtilities;

public class CONTROLADOR{

    private final Modelo modelo;

    // Vistas compartidas
    private final Welcome vWelcome;
    private final ClientLogin vistaClienteLogin;
    private final ClientSignIn vistaClienteLogLogin;
    private final ClienteSignOn vistaClienteSignOn;
    private final StaffLogin vistaTrabajadorLogin;
    private final StaffLogin vistaStaffLogin;

    // Controladores
    private final CtrlWelcome controladorBienvenida;
    private final CtrlCli controladorClienteLogin;
    private final CtrlStaff controladorStaffLogin;

    public CONTROLADOR() {
        // Modelo
        modelo = new Modelo();

        // Vistas base
        vWelcome = new Welcome();
        vistaClienteLogin = new ClientLogin();
        vistaClienteLogLogin = new ClientSignIn();
        vistaClienteSignOn = new ClienteSignOn();
        vistaTrabajadorLogin = new StaffLogin();
        vistaStaffLogin = new StaffLogin();

        // Controlador login cliente
        controladorClienteLogin =
                new ControladorClienteLogin(
                        modelo,
                        vWelcome,
                        vistaClienteLogin,
                        vistaClienteLogLogin,
                        vistaClienteSignOn
                );

        // Controlador login staff
        controladorStaffLogin =
                new ControladorStaffLogin(
                        modelo,
                        vWelcome,
                        vistaTrabajadorLogin,
                        vistaStaffLogin
                );

        // Controlador de la pantalla de bienvenida
        controladorBienvenida =
                new CtrlWelcome(
                        modelo,
                        vWelcome,
                        vistaClienteLogin,
                        vistaTrabajadorLogin
                );
    }

    /**
     * Método de arranque de la interfaz.
     */
    public void iniciar() {
        SwingUtilities.invokeLater(() -> vWelcome.setVisible(true));
    }
}
