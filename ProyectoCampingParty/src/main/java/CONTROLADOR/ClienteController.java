package CONTROLADOR;

import MODELO.Modelo;
import MODELO.Cliente;
import VISTA.VistaCliente;

/**
 * Controlador esqueleto para la vista de cliente.
 * Mantiene la mínima API que la vista espera y delega operaciones al modelo.
 */
public class ClienteController {

    private final Modelo model;
    private final VistaCliente view;
    private final Cliente cliente;

    public ClienteController(Modelo model, VistaCliente view, Cliente cliente) {
        this.model = model;
        this.view = view;
        this.cliente = cliente;
    }

    public void onVerReservas() {
        // TODO: implementar navegación / carga de reservas
        view.showMessage("Acción: ver reservas (no implementada todavía)");
    }

    public void onVolver() {
        // cerrar la vista o volver al menú anterior
        view.setVisible(false);
    }

    public void onCancelarReserva() {
        // TODO: implementar cancelación de reserva en el modelo
        view.showMessage("Acción: cancelar reserva (no implementada todavía)");
    }

    public void onModificarReserva() {
        // TODO: abrir pantalla de modificación
        view.showMessage("Acción: modificar reserva (no implementada todavía)");
    }
}
