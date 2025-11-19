package CONTROLADOR;

import MODELO.Modelo;
import VISTA.StaffMain;

/**
 * Controlador esqueleto para las vistas de staff.
 */
public class StaffController {

    private final Modelo model;
    private final StaffMain view;

    public StaffController(Modelo model, StaffMain view) {
        this.model = model;
        this.view = view;
    }

    public void onRegistrarEntrada() {
        // TODO: implementar lógica para registrar entrada
        javax.swing.JOptionPane.showMessageDialog(view, "Acción: registrar entrada (no implementada)");
    }

    public void onAdministrarReservas() {
        // TODO: navegar a administración de reservas
        javax.swing.JOptionPane.showMessageDialog(view, "Acción: administrar reservas (no implementada)");
    }

    public void onCambiarDescuento() {
        // TODO: cambiar descuento
        javax.swing.JOptionPane.showMessageDialog(view, "Acción: cambiar descuento (no implementada)");
    }
}
