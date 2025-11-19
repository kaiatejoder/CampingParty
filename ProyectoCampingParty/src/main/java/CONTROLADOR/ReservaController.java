package CONTROLADOR;

import MODELO.Acompanyante;
import MODELO.Modelo;
import MODELO.Reserva;
import MODELO.Tienda;
import VISTA.VistaClienteReserva;
import MODELO.Cliente;
import java.util.Date;

/**
 * Controlador mínimo para separar la lógica de `VistaClienteReserva`.
 * Mantiene la API pequeña y hace las acciones que antes estaban en la vista.
 */
public class ReservaController {
    private final Modelo m;
    private final VistaClienteReserva view;
    private final Cliente c;

    public ReservaController(Modelo modelo, VistaClienteReserva view, Cliente c) {
        this.m = modelo;
        this.view = view;
        this.c = c;
    }

    public void onFechaInChanged(Date d) {
        view.setFechaIn(d);
    }

    public void onFechaOutChanged(Date d) {
        view.setFechaOut(d);
        Date in = view.getFechaIn();
        if (in != null && in.before(d)) {
            view.setLabelFecha("¡Bien! Hay parcelas disponibles");
            Reserva r = new Reserva(in, d, c);
            view.setReservaObj(r);
        } else {
            view.setLabelFecha("¡Error! las fechas no representan un rango");
        }
    }

    public void onCheckParcelas() {
        boolean[] parcelas = m.getParcelasLibres();
        view.updateParcelasEnabled(parcelas);
        view.setSelectedTab(1);
    }

    public void onParcelaToggle(int index, boolean selected) {
        view.setParcelaSelected(index, selected);
    }

    public void addPart(String nombre, String apellidos, String edadStr) {
        String nom = nombre + " " + apellidos;
        Acompanyante a = new Acompanyante(nom, Integer.parseInt(edadStr));
        view.addAcompanyanteToReservation(a);
    }

    public void addTienda(String name, String m2Str) {
        Tienda t = new Tienda(name, Integer.parseInt(m2Str));
        boolean v = false;
        Reserva r = view.getReservaObj();
        if (r == null) {
            view.setLabelValidParcelas("Selecciona fechas primero");
            return;
        }
        for (int i = 0; i < 16; i++) {
            if (t.getM2() < r.getParcela(i).getM2()) {
                r.addTienda(t);
                v = true;
                view.setLabelValidParcelas("Parcela correctamente anyadida");
                break;
            }
        }
        if (!v) {
            view.setLabelValidParcelas("ERROR: La tienda es demasiado grande");
        }
    }

    public void onShowConfirm() {
        Reserva r = view.getReservaObj();
        if (r != null) {
            // ensure table model is up to date and switch to confirm tab
            view.setReservaObj(r);
            view.setSelectedTab(3);
        } else {
            view.setLabelValidParcelas("No hay reserva creada");
        }
    }
}
