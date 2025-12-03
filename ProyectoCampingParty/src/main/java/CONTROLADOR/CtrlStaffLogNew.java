package CONTROLADOR;

import VISTA.StaffLogNew;
import MODELO.Modelo;
import MODELO.Staff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Controlador para la vista StaffLogNew (Registro de entrada/salida y asignacion de parcelas).
 * Gestiona la actualización del estado de ocupación de parcelas en BD.
 *
 * @author Carla Terol
 */
public class CtrlStaffLogNew implements ActionListener {

    private StaffLogNew vista;
    private final Modelo modelo;

    public CtrlStaffLogNew(StaffLogNew vista, Modelo modelo, Staff staff) {
        this.vista = vista;
        this.modelo = modelo;
        init();
    }

    /**
     * Asigna la vista a este controlador (inyección después de instanciación).
     */
    public void setVista(StaffLogNew vista) {
        this.vista = vista;
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

    /**
     * Registra la entrada de un cliente en una parcela.
     * Actualiza el estado de la parcela a ocupada.
     */
    @SuppressWarnings("unused")
    public void registrarEntrada(int idParcela, Date fechaEntrada) {
        try {
            if (fechaEntrada == null) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fecha de entrada", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Actualizar parcela como ocupada
            java.sql.Date sqlFecha = new java.sql.Date(fechaEntrada.getTime());
            boolean resultado = modelo.getDAO().actualizarParcela(idParcela, true, false, sqlFecha, null);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "✓ Entrada registrada exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista, "✗ Error al registrar entrada", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Registra la salida de un cliente de una parcela.
     * Actualiza el estado de la parcela a libre.
     */
    @SuppressWarnings("unused")
    public void registrarSalida(int idParcela, Date fechaSalida) {
        try {
            if (fechaSalida == null) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fecha de salida", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Actualizar parcela como libre
            java.sql.Date sqlFecha = new java.sql.Date(fechaSalida.getTime());
            boolean resultado = modelo.getDAO().actualizarParcela(idParcela, false, false, null, sqlFecha);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "✓ Salida registrada exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista, "✗ Error al registrar salida", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Registra entrada/salida combinada (entrada actual, salida futura).
     */
    public void registrarEntradaSalida(int idParcela, Date fechaEntrada, Date fechaSalida) {
        try {
            if (fechaEntrada == null || fechaSalida == null) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar fechas de entrada y salida", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (fechaEntrada.after(fechaSalida)) {
                JOptionPane.showMessageDialog(vista, "La fecha de salida debe ser posterior a la de entrada", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Actualizar parcela como ocupada con fechas
            boolean resultado = modelo.getDAO().actualizarParcela(idParcela, true, false, fechaEntrada, fechaSalida);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "✓ Entrada/Salida registrada exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "✗ Error al registrar entrada/salida", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
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

