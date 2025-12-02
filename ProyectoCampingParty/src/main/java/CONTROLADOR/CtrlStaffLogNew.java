package CONTROLADOR;

import VISTA.StaffLogNew;
import MODELO.Modelo;
import MODELO.Staff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Controlador para la vista StaffLogNew (Registro de entrada/salida y asignacion de parcelas).
 *
 * @author Carla Terol
 */
public class CtrlStaffLogNew implements ActionListener {

    private final StaffLogNew vista;
    private final Modelo modelo;

    public CtrlStaffLogNew(StaffLogNew vista, Modelo modelo, Staff staff) {
        this.vista = vista;
        this.modelo = modelo;
        init();
    }

    private void init() {
        try {
            cargarEstadoParcelas();
        } catch (Exception e) {
            System.err.println("Error al iniciar CtrlStaffLogNew: " + e.getMessage());
        }
    }

    private void cargarEstadoParcelas() {
        boolean[] libres = modelo.getParcelasLibres();
        System.out.println("Parcelas cargadas: " + libres.length);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implementar logica de botones
    }

    @SuppressWarnings("unused")
    private void handleEntrada() {
        JOptionPane.showMessageDialog(vista, "Entrada registrada", "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

    @SuppressWarnings("unused")
    private void handleSalida() {
        JOptionPane.showMessageDialog(vista, "Salida registrada", "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
}
