package CONTROLADOR;

import MODELO.Modelo;
import VISTA.*;
import javax.swing.SwingUtilities;

public class CONTROLADOR{

    private final Modelo modelo;

    // Vistas compartidas
    private final Welcome vWelcome;
    private final ClientLogin vistaClienteLogin;
    private final ClientSignIn vistaClienteSignIn;
    private final ClienteSignOn vistaClienteSignOn;
    private final ClienteConReserva vistaCliRes;
    private final VistaCliente vCli;
    private final VistaClienteReserva vCliRes;
    private final VistaClienteModificarReserva vCliModRes;
    private final VistaReservas vReservas;
    private final StaffLogin vistaStaffLogin;
    private final StaffSignIn vistaStaffSignIn;
    private final StaffAct stAct;
    private final StaffMain stMain;
    private final StaffLogNew stRes;
    private final ActividadSeleccionada actSelect;
    // Controladores
    private final CtrlWelcome controladorBienvenida;
    private final CtrlCli controladorClienteLogin;
    private final CtrlStaff controladorStaffLogin;

    public CONTROLADOR() {
        // Modelo
        modelo = new Modelo();

        // Vistas base
        
        vistaClienteSignIn = new ClientSignIn(modelo);
        vistaClienteSignOn = new ClienteSignOn(modelo);
        vistaClienteLogin = new ClientLogin(vistaClienteSignIn, vistaClienteSignOn);
        vistaStaffLogin = new StaffLogin();
        vistaStaffSignIn = new StaffSignIn();
        vWelcome = new Welcome();
        

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
