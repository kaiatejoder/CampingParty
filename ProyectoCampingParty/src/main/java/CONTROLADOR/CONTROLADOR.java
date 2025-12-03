package CONTROLADOR;

import MODELO.Modelo;
import MODELO.Cliente;
import MODELO.Staff;
import VISTA.*;
import javax.swing.SwingUtilities;

public class CONTROLADOR{

    private static CONTROLADOR instancia;
    private final Modelo modelo;

    // Vistas compartidas
    private final Welcome vWelcome;
    private final ClientLogin vistaClienteLogin;
    private final ClientSignIn vistaClienteLogLogin;
    private final ClienteSignOn vistaClienteSignOn;
    private final StaffLogin vistaTrabajadorLogin;
    private final StaffSignIn staffSignIn;

    // Controladores
    @SuppressWarnings("unused")
    private final CtrlWelcome controladorBienvenida;
    @SuppressWarnings("unused")
    private final ControladorClienteLogin controladorClienteLogin;
    @SuppressWarnings("unused")
    private final ControladorStaffLogin controladorStaffLogin;
    @SuppressWarnings("unused")
    private final ControladorClienteSignOn controladorClienteSignOn;

    private CONTROLADOR() {
        // Modelo - Se crea una única instancia
        modelo = new Modelo();

        // Vistas base
        vWelcome = new Welcome();
        vistaClienteLogin = new ClientLogin();
        vistaClienteLogLogin = new ClientSignIn(modelo);
        staffSignIn = new StaffSignIn();
        
        // Vista ClienteSignOn sin parámetros
        vistaClienteSignOn = new ClienteSignOn();
        
        // Controlador para ClienteSignOn
        controladorClienteSignOn = new ControladorClienteSignOn(null, modelo);
        
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
                        modelo,
                        vWelcome,
                        vistaTrabajadorLogin, staffSignIn
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

    /**
     * Obtiene la instancia singleton de CONTROLADOR.
     */
    public static CONTROLADOR getInstance() {
        if (instancia == null) {
            instancia = new CONTROLADOR();
        }
        return instancia;
    }

    /**
     * Inicializa la instancia singleton (llamado desde MAIN).
     */
    public static CONTROLADOR inicializar() {
        if (instancia == null) {
            instancia = new CONTROLADOR();
        }
        return instancia;
    }

    /**
     * Obtiene una instancia del modelo.
     */
    public Modelo getModelo() {
        return modelo;
    }

    /**
     * Abre la vista principal de cliente (VistaCliente).
     */
    public void abrirVistaCliente(Cliente cliente) {
        VistaCliente vistaCliente = new VistaCliente(cliente);
        vistaCliente.setVisible(true);
        vistaCliente.setLocationRelativeTo(null);
    }

    /**
     * Abre la vista principal de staff (StaffMain).
     */
    public void abrirStaffMain(Staff staff) {
        StaffMain stMain = new StaffMain(staff, modelo);
        stMain.setVisible(true);
        stMain.setLocationRelativeTo(null);
    }

    /**
     * Abre la vista de reserva con su controlador.
     */
    public void abrirVistaReserva(Cliente cliente) {
        ControladorVistaClienteReserva controladorReserva = 
            new ControladorVistaClienteReserva(null, modelo, cliente);
        VistaClienteReserva vistaReserva = 
            new VistaClienteReserva(modelo, cliente, controladorReserva);
        controladorReserva.setVista(vistaReserva);
        vistaReserva.setVisible(true);
    }

    /**
     * Abre la vista de actividades con su controlador.
     */
    public void abrirVistaActividades(Staff staff) {
        CtrlStaffAct controladorActividades = 
            new CtrlStaffAct(null, modelo, staff);
        StaffAct vistaActividades = 
            new StaffAct(controladorActividades);
        controladorActividades.setVista(vistaActividades);
        vistaActividades.setVisible(true);
    }

    /**
     * Abre la vista de entrada/salida con su controlador.
     */
    public void abrirVistaEntradaSalida(Staff staff) {
        CtrlStaffLogNew controladorEntradaSalida = 
            new CtrlStaffLogNew(null, modelo, staff);
        StaffLogNew vistaEntradaSalida = 
            new StaffLogNew(controladorEntradaSalida);
        controladorEntradaSalida.setVista(vistaEntradaSalida);
        vistaEntradaSalida.setVisible(true);
    }
}
