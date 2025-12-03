package com.campingparty.controlador;

import com.campingparty.vista.StaffAct;
import com.campingparty.modelo.Modelo;
import com.campingparty.modelo.Staff;
import com.campingparty.modelo.Actividad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Controlador para la vista StaffAct (Gestion de Actividades por el Staff).
 * Gestiona: carga de plantillas, creación e inserción de actividades en BD.
 *
 * @author Carla Terol
 */
public class CtrlStaffAct implements ActionListener {

    private StaffAct vista;
    private final Modelo modelo;
    private ArrayList<Actividad> plantillas;

    public CtrlStaffAct(StaffAct vista, Modelo modelo, Staff staff) {
        this.vista = vista;
        this.modelo = modelo;
        this.plantillas = new ArrayList<>();
    }

    /**
     * Asigna la vista a este controlador (inyección después de instanciación).
     * Carga plantillas desde BD cuando se asigna la vista.
     */
    public void setVista(StaffAct vista) {
        this.vista = vista;
        cargarPlantillas();
    }

    /**
     * Carga las plantillas de actividades desde la BD (DAO).
     * Plantillas son actividades con tipo='5'
     */
    private void cargarPlantillas() {
        try {
            plantillas = modelo.getDAO().getPlantillasActividades();
            if (vista != null) {
                vista.cargarPlantillas(plantillas);
            }
            System.out.println("✓ Plantillas cargadas: " + plantillas.size());
        } catch (Exception e) {
            System.err.println("Error cargando plantillas: " + e.getMessage());
            if (vista != null) {
                JOptionPane.showMessageDialog(vista, "Error cargando plantillas: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Carga una plantilla seleccionada en el formulario de la vista.
     * Se invoca desde el listener de la lista de plantillas en StaffAct.
     *
     * @param indice El índice de la plantilla seleccionada
     */
    public void cargarPlantillaSeleccionada(int indice) {
        if (indice >= 0 && indice < plantillas.size() && vista != null) {
            Actividad plantilla = plantillas.get(indice);
            vista.cargarPlantillaEnFormulario(plantilla);
            System.out.println("✓ Plantilla " + indice + " cargada: " + plantilla.getIdActividad());
        }
    }

    /**
     * Crea una nueva actividad en la BD.
     * Valida los datos requeridos antes de insertar.
     *
     * @param titulo Título de la actividad
     * @param descripcion Descripción de la actividad
     * @param tipo Tipo/lugar de la actividad (Piscina, Frontón, Club Social, etc.)
     * @param audiencia Público objetivo (Todos, Niños, Adultos, Edad Avanzada)
     * @param fecha Fecha y hora de la actividad
     */
    public void crearActividad(String titulo, String descripcion, String tipo, 
                               String audiencia, Date fecha) {
        try {
            // Validar campos
            if (titulo == null || titulo.isEmpty() || titulo.equals("Título")) {
                JOptionPane.showMessageDialog(vista, "Ingrese un título válido", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (descripcion == null || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese una descripción", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (fecha == null) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fecha y hora", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear actividad (usando ID temporal 0, la BD lo generará)
            // tipo es un String (Piscina, Frontón, Club Social)
            // convertir a número: Piscina=1, Frontón=2, Club Social=3
            int tipoInt = convertirTipoAEntero(tipo);
            
            Actividad actividad = new Actividad(0, tipoInt, fecha, 50); // 50 como participantes máximos

            // Guardar en BD
            boolean resultado = modelo.getDAO().agregarActividad(actividad);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "✓ Actividad creada exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Recargar plantillas y limpiar formulario
            cargarPlantillas();
            } else {
                JOptionPane.showMessageDialog(vista, "✗ Error al crear la actividad", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Convierte la descripción del tipo de actividad a un entero.
     */
    private int convertirTipoAEntero(String tipo) {
        if (tipo == null) return 1;
        switch (tipo) {
            case "Piscina": return 1;
            case "Frontón": return 2;
            case "Club Social": return 3;
            default: return 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Este controlador usa listeners integrados en StaffAct
        // Los listeners delegados a métodos específicos en init()
    }
}

