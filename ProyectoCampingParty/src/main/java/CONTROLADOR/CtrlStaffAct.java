package CONTROLADOR;

import VISTA.StaffAct;
import VISTA.ActividadSeleccionada;
import MODELO.Modelo;
import MODELO.Staff;
import MODELO.Actividad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;

/**
 * Controlador para la vista StaffAct (Gestión de Actividades por el Staff).
 * @author Carla Terol (Adaptado por Gemini)
 */
public class CtrlStaffAct implements ActionListener {

    private final StaffAct vista;
    private final Modelo modelo;
    private final Staff staff;
    private final DefaultListModel<String> listModel;

    public CtrlStaffAct(StaffAct vista, Modelo modelo, Staff staff) {
        this.vista = vista;
        this.modelo = modelo;
        this.staff = staff;
        this.listModel = new DefaultListModel<>();
        iniciarControlador();
    }

    private void iniciarControlador() {
        try {
            vista.getjButton1().addActionListener(this); // Botón de Añadir Actividad
            vista.getjList1().setModel(listModel);
            
            // Clase interna anónima para el doble click en la lista
            vista.getjList1().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        handleActividadSeleccionada();
                    }
                }
            });
            
            cargarListaActividades();

        } catch (Exception e) {
             System.err.println("Error al iniciar ControladorStaffAct: " + e.getMessage());
        }
    }
    
    // Carga la lista de actividades desde el modelo
    private void cargarListaActividades() {
        listModel.clear();
        for (Actividad act : modelo.getActs()) {
            listModel.addElement(act.getIdActividad() + " - " + act.getTipo() + " (" + act.getFechaHora() + ")");
        }
    }

    private void handleActividadSeleccionada() {
        String seleccion = vista.getjList1().getSelectedValue();
        if (seleccion != null) {
            // Asumo que el ID está al principio (ej: "1 - Taller de Nudos...")
            try {
                int idActividad = Integer.parseInt(seleccion.split(" - ")[0]);
                // Buscar la Actividad real en el modelo
                Actividad actividad = null;
                for (Actividad act : modelo.getActs()) {
                    if (act.getIdActividad() == idActividad) {
                        actividad = act;
                        break;
                    }
                }
                
                if (actividad != null) {
                    // Abrir el diálogo (ActividadSeleccionada.java) para ver detalles
                    ActividadSeleccionada dialog = new ActividadSeleccionada(vista, true);
                    dialog.getjLabel1().setText("Detalles de: " + actividad.getTipo());
                    // Deberías pasar la actividad al diálogo para que cargue los datos (participantes, ganador, etc.)
                    dialog.setLocationRelativeTo(vista);
                    dialog.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(vista, "Error al obtener ID de la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getjButton1()) {
            // Lógica para añadir una nueva actividad
            String tipo = (String) vista.getjComboBox1().getSelectedItem();
            Date fecha = vista.getjDateChooser2().getDate();
            String maxPartStr = vista.getjTextField1().getText().trim();

            if (tipo == null || fecha == null || maxPartStr.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Rellena todos los campos de la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int maxParticipantes = Integer.parseInt(maxPartStr);
                int newId = modelo.getActs().size() + 1; // Generar un ID simple
                
                Actividad nuevaActividad = new Actividad(newId, tipo, fecha, maxParticipantes);
                modelo.getActs().add(nuevaActividad);
                
                JOptionPane.showMessageDialog(vista, "Actividad '" + tipo + "' creada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarListaActividades(); // Recargar la lista

            } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(vista, "El máximo de participantes debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}