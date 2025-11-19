package com.isii.campingmvc.controller;

import com.isii.campingmvc.model.Modelo;
import com.isii.campingmvc.model.Reserva;
import com.isii.campingmvc.model.Cliente;
import com.isii.campingmvc.view.VistaSimpleReserva;
import java.time.LocalDate;

public class ReservaController {
    private final Modelo modelo;
    private final VistaSimpleReserva vista;

    public ReservaController(Modelo modelo, VistaSimpleReserva vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public boolean[] obtenerDisponibilidad() {
        return modelo.getParcelasLibres();
    }

    public void actualizarVistaParcelas() {
        vista.updateParcelas(modelo.getParcelasLibres());
    }

    public boolean crearReserva(LocalDate in, LocalDate out, String clienteNombre, int parcelaIndex) {
        if (in == null || out == null || !in.isBefore(out)) {
            vista.showMessage("Rango de fechas inválido");
            return false;
        }
        Cliente c = new Cliente(clienteNombre, "-");
        Reserva r = new Reserva(in, out, c);
        boolean ok = modelo.reservarParcela(parcelaIndex);
        if (ok) {
            r.addParcela(parcelaIndex);
            vista.showMessage("Reserva creada: parcela " + (parcelaIndex+1));
            actualizarVistaParcelas();
            return true;
        } else {
            vista.showMessage("La parcela no está disponible");
            return false;
        }
    }
}
