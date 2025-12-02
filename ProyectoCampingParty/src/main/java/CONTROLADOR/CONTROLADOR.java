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

    // Controladores
    // Nota: aunque no se usan directamente, se crean para iniciar los listeners (efectos secundarios en init())
    @SuppressWarnings("unused")
    private final CtrlWelcome controladorBienvenida;
    @SuppressWarnings("unused")
    private final ControladorClienteLogin controladorClienteLogin;
    @SuppressWarnings("unused")
    private final ControladorStaffLogin controladorStaffLogin;

    public CONTROLADOR() {
        // Modelo
        modelo = new Modelo();

        // Vistas base
        vWelcome = new Welcome();
        vistaClienteLogin = new ClientLogin();
        vistaClienteLogLogin = new ClientSignIn(modelo);
        vistaClienteSignOn = new ClienteSignOn(modelo);
        vistaTrabajadorLogin = new StaffLogin();

        // Controlador login cliente
        controladorClienteLogin =
                new ControladorClienteLogin(
                        modelo,
                        vWelcome,
                        vistaClienteLogin,
                        vistaClienteLogLogin,
                        vistaClienteSignOn
                );

        // Controlador login staff (simplificado: solo pasa Welcome y StaffLogin principal)
        controladorStaffLogin =
                new ControladorStaffLogin(
                        vWelcome,
                        vistaTrabajadorLogin
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
        vWelcome.setLocationRelativeTo(null);
        SwingUtilities.invokeLater(() -> vWelcome.setVisible(true));
    }
}
