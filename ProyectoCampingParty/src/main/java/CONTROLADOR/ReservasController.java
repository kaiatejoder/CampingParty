package CONTROLADOR;

import MODELO.Modelo;
import VISTA.VistaReservas;

/**
 * Controlador esqueleto para la vista de reservas.
 */
public class ReservasController {

    private final Modelo model;
    private final VistaReservas view;

    public ReservasController(Modelo model, VistaReservas view) {
        this.model = model;
        this.view = view;
    }

    public void onCheckIn() {
        // TODO: implementar lógica de check-in
        javax.swing.JOptionPane.showMessageDialog(view, "Acción: check-in (no implementada todavía)");
    }

    public void onBack() {
        view.setVisible(false);
    }
}
