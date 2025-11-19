package CONTROLADOR;

import MODELO.Modelo;
import VISTA.ClienteConReserva;

/**
 * Controlador mínimo para la vista `ClienteConReserva`.
 */
public class ClienteConReservaController {
    private final Modelo model;
    private final ClienteConReserva view;

    public ClienteConReservaController(Modelo model, ClienteConReserva view) {
        this.model = model;
        this.view = view;
    }

    public void onVerActividades() {
        javax.swing.JOptionPane.showMessageDialog(view, "Ver actividades (no implementado)");
    }
}
