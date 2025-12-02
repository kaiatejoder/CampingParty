package CONTROLADOR;

import VISTA.StaffAct;
import MODELO.Modelo;
import MODELO.Staff;
import MODELO.Actividad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

/**
 * Controlador para la vista StaffAct (Gestion de Actividades por el Staff).
 *
 * @author Carla Terol
 */
public class CtrlStaffAct implements ActionListener {

    @SuppressWarnings("unused")
    private final StaffAct vista;
    private final Modelo modelo;
    private final DefaultListModel<String> listModel;

    public CtrlStaffAct(StaffAct vista, Modelo modelo, Staff staff) {
        this.vista = vista;
        this.modelo = modelo;
        this.listModel = new DefaultListModel<>();
    }

    @SuppressWarnings("unused")
    private void cargarListaActividades() {
        listModel.clear();
        try {
            for (Actividad act : modelo.getActs()) {
                listModel.addElement(act.getIdActividad() + " - " + act.getTipo());
            }
        } catch (Exception e) {
            System.err.println("Error cargando actividades: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Implementar acciones segun botones
    }
}
