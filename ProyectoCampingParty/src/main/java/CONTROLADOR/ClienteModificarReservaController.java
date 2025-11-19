package CONTROLADOR;

import MODELO.Modelo;
import VISTA.VistaClienteModificarReserva;

public class ClienteModificarReservaController {
    private final Modelo model;
    private final VistaClienteModificarReserva view;

    public ClienteModificarReservaController(Modelo model, VistaClienteModificarReserva view) {
        this.model = model;
        this.view = view;
    }

    // métodos placeholder para futuras migraciones
    public void onCancel() {
        view.setVisible(false);
    }
}
