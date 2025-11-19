package com.isii.campingmvc;

import com.isii.campingmvc.model.Modelo;
import com.isii.campingmvc.controller.ReservaController;
import com.isii.campingmvc.view.VistaSimpleReserva;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        // create view and controller on EDT
        SwingUtilities.invokeLater(() -> {
            ReservaController controller = new ReservaController(modelo, null);
            // create view after controller, then inject view into controller (two-way wiring)
            VistaSimpleReserva vista = new VistaSimpleReserva(controller);
            // re-wire controller's view by reflection-style setter: simple solution - recreate controller with view
            controller = new ReservaController(modelo, vista);
            vista.setVisible(true);
        });
    }
}
