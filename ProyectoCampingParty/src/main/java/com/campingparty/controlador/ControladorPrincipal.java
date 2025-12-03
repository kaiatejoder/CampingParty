package com.campingparty.controlador;

import com.campingparty.modelo.*;
import com.campingparty.vista.*;

/**
 * CONTROLADOR PRINCIPAL - Orquesta toda la aplicación.
 * 
 * Responsabilidades:
 * - Actúa como mediador entre Modelo y Vistas
 * - Recibe eventos de las Vistas (usuario haciendo clic, etc.)
 * - Solicita datos al Modelo
 * - Actualiza las Vistas con los datos del Modelo
 * - Implementa la lógica de flujo de la aplicación
 * 
 * La comunicación es:
 * Vista -> Controlador -> Modelo
 *     <-         <-
 * 
 * Las vistas NUNCA acceden directamente al Modelo.
 * El Modelo NUNCA conoce la existencia de las Vistas.
 * 
 * @author Carla Terol
 */
public class ControladorPrincipal {
    private static ControladorPrincipal instancia;
    
    private Modelo modelo;
    
    // Vistas (cada una se crea cuando es necesaria)
    private VistaBienvenida vistaBienvenida;
    private VistaClienteLogin vistaClienteLogin;
    private VistaStaffLogin vistaStaffLogin;
    private VistaCliente vistaCliente;

    /**
     * Constructor privado para patrón Singleton
     */
    private ControladorPrincipal() {
        // Crear el Modelo
        this.modelo = new Modelo();
        
        // Las vistas se crearán bajo demanda
    }

    /**
     * Obtiene la instancia singleton del controlador
     */
    public static ControladorPrincipal getInstance() {
        if (instancia == null) {
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }

    /**
     * Inicia la aplicación mostrando la vista de bienvenida
     */
    public void iniciar() {
        vistaBienvenida = new VistaBienvenida(this);
        vistaBienvenida.setVisible(true);
    }

    /**
     * El usuario ha hecho clic en "Login Cliente"
     */
    public void abrirLoginCliente() {
        if (vistaClienteLogin == null) {
            vistaClienteLogin = new VistaClienteLogin(this);
        }
        vistaClienteLogin.setVisible(true);
    }

    /**
     * El usuario ha hecho clic en "Login Staff"
     */
    public void abrirLoginStaff() {
        if (vistaStaffLogin == null) {
            vistaStaffLogin = new VistaStaffLogin(this);
        }
        vistaStaffLogin.setVisible(true);
    }

    /**
     * El usuario ha introducido credenciales de cliente
     */
    public void autenticarCliente(String usuario, String contrasena) {
        Cliente cliente = modelo.autenticarCliente(usuario, contrasena);
        if (cliente != null) {
            // Autenticación exitosa
            abrirVistaPrincipalCliente(cliente);
            if (vistaClienteLogin != null) {
                vistaClienteLogin.setVisible(false);
            }
        } else {
            // Mostrar error
            if (vistaClienteLogin != null) {
                vistaClienteLogin.mostrarError("Usuario o contraseña incorrectos");
            }
        }
    }

    /**
     * El usuario ha introducido credenciales de staff
     */
    public void autenticarStaff(String usuario, String contrasena) {
        Staff staff = modelo.autenticarStaff(usuario, contrasena);
        if (staff != null) {
            // Autenticación exitosa - abrir interfaz de staff
            abrirVistaPrincipalStaff(staff);
            if (vistaStaffLogin != null) {
                vistaStaffLogin.setVisible(false);
            }
        } else {
            // Mostrar error
            if (vistaStaffLogin != null) {
                vistaStaffLogin.mostrarError("Usuario o contraseña incorrectos");
            }
        }
    }

    /**
     * Abre la interfaz principal del cliente
     */
    public void abrirVistaPrincipalCliente(Cliente cliente) {
        VistaCliente vistaCliente = new VistaCliente();
        vistaCliente.setCliente(cliente);
        vistaCliente.setVisible(true);
    }

    /**
     * Abre la interfaz principal del staff
     */
    public void abrirVistaPrincipalStaff(Staff staff) {
        // TODO: Implementar apertura de vista principal Staff
    }

    /**
     * Abre la vista para registrar entrada de clientes (Staff Log New)
     */
    public void abrirVistaStaffLogNew(Staff staff) {
        // TODO: Implementar apertura de vista Staff Log New
    }

    /**
     * Abre la vista para administrar reservas (Staff Act)
     */
    public void abrirVistaStaffAct(Staff staff) {
        // TODO: Implementar apertura de vista Staff Act
    }

    /**
     * Abre la vista de reservas del cliente
     */
    public void abrirVistaReservas(Cliente cliente) {
        // TODO: Implementar apertura de vista Reservas
    }

    /**
     * Abre la vista para modificar una reserva
     */
    public void abrirVistaModificarReserva(Cliente cliente, Reserva reserva) {
        // TODO: Implementar apertura de vista Modificar Reserva
    }

    /**
     * Abre la vista de selección de actividad
     */
    public void abrirVistaActividad(Actividad actividad) {
        // TODO: Implementar apertura de vista Actividad
    }

    /**
     * El usuario hace clic en "Nueva Reserva"
     */
    public void abrirReserva(Cliente cliente) {
        // TODO: Implementar apertura de nueva reserva
    }

    /**
     * Llamado cuando el usuario confirma una reserva
     */
    public void confirmarReserva(Reserva reserva) {
        if (modelo.confirmarReserva(reserva)) {
            System.out.println("Reserva confirmada: " + reserva);
        }
    }

    /**
     * Obtiene el modelo (para acceso interno)
     */
    public Modelo getModelo() {
        return modelo;
    }

    /**
     * Cierra la aplicación
     */
    public void cerrar() {
        if (modelo != null && modelo.getDAO() != null) {
            modelo.getDAO().cerrar();
        }
        System.exit(0);
    }
}


