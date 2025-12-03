package com.campingparty.controlador;

import com.campingparty.vista.VistaClienteReserva;
import com.campingparty.modelo.*;
import java.awt.event.*;
import java.util.*;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Controlador para la vista VistaClienteReserva (Gestión de reservas de clientes).
 * Gestiona la validación de fechas, parcelas y la inserción de reservas en BD.
 *
 * @author Carla Terol
 */
public class ControladorVistaClienteReserva implements ActionListener {

    private VistaClienteReserva vista;
    private final Modelo modelo;
    private final Cliente cliente;

    public ControladorVistaClienteReserva(VistaClienteReserva vista, Modelo modelo, Cliente cliente) {
        this.vista = vista;
        this.modelo = modelo;
        this.cliente = cliente;
    }

    /**
     * Asigna la vista a este controlador (inyección después de instanciación).
     */
    public void setVista(VistaClienteReserva vista) {
        this.vista = vista;
    }

    /**
     * Procesa la confirmación de una nueva reserva.
     * Valida fechas y parcelas, luego inserta en BD.
     */
    public void confirmarReserva(Date fechaIn, Date fechaOut, boolean[] parcelasSelect) {
        try {
            // Validar que se seleccionó al menos una parcela
            boolean parcelaSeleccionada = false;
            for (boolean p : parcelasSelect) {
                if (p) {
                    parcelaSeleccionada = true;
                    break;
                }
            }

            if (!parcelaSeleccionada) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar al menos una parcela", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar fechas
            if (fechaIn == null || fechaOut == null) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar fechas válidas", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (fechaIn.after(fechaOut)) {
                JOptionPane.showMessageDialog(vista, "La fecha de salida debe ser posterior a la de entrada", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear lista de parcelas seleccionadas
            ArrayList<Parcela> parcelasReserva = new ArrayList<>();
            for (int i = 0; i < parcelasSelect.length; i++) {
                if (parcelasSelect[i]) {
                    Parcela p = new Parcela(i + 1, 0, false, 0);
                    parcelasReserva.add(p);
                }
            }

            // Crear listas vacías de tiendas y acompañantes para esta llamada básica
            ArrayList<Tienda> tiendas = new ArrayList<>();
            ArrayList<Acompanyante> acompanyantes = new ArrayList<>();

            // Crear objeto reserva con los parámetros correctos
            Reserva reserva = new Reserva(fechaIn, fechaOut, parcelasReserva, tiendas, acompanyantes, cliente);

            // Obtener el ID del cliente por su DNI
            int clientId = modelo.getDAO().obtenerIdClientePorDNI(cliente.getDni());

            if (clientId <= 0) {
                JOptionPane.showMessageDialog(vista, "Cliente no encontrado en la base de datos", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Insertar reserva en base de datos
            boolean resultado = modelo.getDAO().agregarReserva(reserva, clientId);

            if (resultado) {
                // Actualizar estado de parcelas en base de datos (reservadas = true, fechas)
                java.sql.Date sqlFechaIn = new java.sql.Date(fechaIn.getTime());
                java.sql.Date sqlFechaOut = new java.sql.Date(fechaOut.getTime());

                for (int i = 0; i < parcelasSelect.length; i++) {
                    if (parcelasSelect[i]) {
                        modelo.getDAO().actualizarParcela(i + 1, false, true, fechaIn, fechaOut);
                    }
                }

                JOptionPane.showMessageDialog(vista, "✓ Reserva confirmada exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "✗ Error al confirmar la reserva", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Este método se implementa cuando sea necesario manejar eventos de botones
    }
}


