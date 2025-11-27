package Controlador;

import Modelo.Modelo;
import Modelo.Staff;
import Vista.ActividadSeleccionada;
import Vista.StaffMain;
import Vista.VistaStaffActividad;
import Vista.VistaStaffEntrada;

import Modelo.Reserva;
import Modelo.Parcela;
import Modelo.Cliente;
import Modelo.Actividad;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controlador de la zona Staff:
 * - Gestión de entradas (VistaStaffEntrada)
 * - Gestión de actividades (VistaStaffActividad / ActividadSeleccionada)
 */
public class ControladorStaff {

    private final Modelo modelo;
    private final Staff staffLogueado;

    private final StaffMain vistaStaffMain;
    private final VistaStaffEntrada vistaStaffEntrada;
    private final VistaStaffActividad vistaStaffActividad;
    private final ActividadSeleccionada vistaActividadSeleccionada;

    // --- Gestión de entrada (reservas creadas por recepción) ---
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private JPanel[] celdasParcelasEntrada;
    private final List<Integer> parcelasSeleccionadasEntrada = new ArrayList<>();

    public ControladorStaff(Modelo modelo,
                            Staff staffLogueado,
                            StaffMain vistaStaffMain,
                            VistaStaffEntrada vistaStaffEntrada,
                            VistaStaffActividad vistaStaffActividad,
                            ActividadSeleccionada vistaActividadSeleccionada) {
        this.modelo = modelo;
        this.staffLogueado = staffLogueado;
        this.vistaStaffMain = vistaStaffMain;
        this.vistaStaffEntrada = vistaStaffEntrada;
        this.vistaStaffActividad = vistaStaffActividad;
        this.vistaActividadSeleccionada = vistaActividadSeleccionada;

        inicializar();
        inicializarGridParcelasEntrada();
    }

    // ==========================================================
    //  Inicialización de listeners
    // ==========================================================

    private void inicializar() {
        // --- StaffMain: pestaña Reservas ---
        vistaStaffMain.getBtnRegistrarEntrada().addActionListener(e -> onGestionarEntrada());
        vistaStaffMain.getBtnAdministrarReservas().addActionListener(e -> onVerReservas());
        vistaStaffMain.getBtnCambiarDescuento().addActionListener(e -> onCambiarDescuento());

        // --- StaffMain: pestaña Actividades ---
        vistaStaffMain.getBtnAgregarActividad().addActionListener(e -> mostrarVentanaActividad());

        // --- VistaStaffEntrada: asistente de entrada ---
        vistaStaffEntrada.getBtnSiguienteFechas().addActionListener(e -> onFechasEntradaSiguiente());
        vistaStaffEntrada.getBtnCancelarFechas().addActionListener(e -> cancelarEntrada());

        vistaStaffEntrada.getBtnSiguienteParcelas().addActionListener(e -> onParcelasEntradaSiguiente());
        vistaStaffEntrada.getBtnAtrasParcelas().addActionListener(e ->
                vistaStaffEntrada.getTabbedPane().setSelectedIndex(0)
        );

        vistaStaffEntrada.getBtnSiguienteMiembros().addActionListener(e -> onMiembrosEntradaSiguiente());
        vistaStaffEntrada.getBtnAtrasMiembros().addActionListener(e ->
                vistaStaffEntrada.getTabbedPane().setSelectedIndex(1)
        );

        vistaStaffEntrada.getBtnConfirmarEntrada().addActionListener(e -> confirmarEntrada());
        vistaStaffEntrada.getBtnVolverAtrasConfirmacion().addActionListener(e ->
                vistaStaffEntrada.getTabbedPane().setSelectedIndex(2)
        );

        // --- VistaStaffActividad: botón "Agregar Actividad" ---
        vistaStaffActividad.getBtnAgregarActividad().addActionListener(e -> onCrearActividadDesdeVentana());
    }

    // ==========================================================
    //  Acciones en StaffMain
    // ==========================================================

    /**
     * Muestra información básica de reservas (para demo).
     */
    private void onVerReservas() {
        int total = modelo.getTotalReservas();
        JOptionPane.showMessageDialog(
                vistaStaffMain,
                "Reservas totales actualmente: " + total,
                "Ver reservas",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Abre el asistente de registro de entrada.
     */
    private void onGestionarEntrada() {
        prepararFormularioEntrada();
        vistaStaffEntrada.getTabbedPane().setSelectedIndex(0);
        vistaStaffEntrada.setLocationRelativeTo(vistaStaffMain);
        vistaStaffEntrada.setVisible(true);
    }

    /**
     * Muestra la ventana para crear una actividad (formulario completo).
     */
    private void mostrarVentanaActividad() {
        // Limpiamos campos básicos
        vistaStaffActividad.getCampoDiaHora().setText("");
        vistaStaffActividad.getCampoTituloActividad().setText("Título");
        vistaStaffActividad.getAreaDescripcion().setText("");
        vistaStaffActividad.setTextoTituloVentana("Crear Actividad");

        vistaStaffActividad.setLocationRelativeTo(vistaStaffMain);
        vistaStaffActividad.setVisible(true);
    }

    /**
     * Cambia el descuento global del camping.
     */
    private void onCambiarDescuento() {
        String actual = Integer.toString(modelo.getDescuento());
        String nuevoStr = JOptionPane.showInputDialog(
                vistaStaffMain,
                "Descuento actual: " + actual + "%\nIntroduce nuevo descuento (%):",
                "Cambiar descuento",
                JOptionPane.QUESTION_MESSAGE
        );
        if (nuevoStr == null || nuevoStr.trim().isEmpty()) {
            return;
        }

        int nuevo;
        try {
            nuevo = Integer.parseInt(nuevoStr.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    vistaStaffMain,
                    "El descuento debe ser un número entero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (nuevo < 0 || nuevo > 100) {
            JOptionPane.showMessageDialog(
                    vistaStaffMain,
                    "El descuento debe estar entre 0 y 100.",
                    "Valor no válido",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        modelo.setDescuento(nuevo);
        JOptionPane.showMessageDialog(
                vistaStaffMain,
                "Descuento actualizado a " + nuevo + "%.",
                "Descuento cambiado",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==========================================================
    //  Asistente de entrada (VistaStaffEntrada)
    // ==========================================================

    /**
     * Pone a cero el formulario de entrada y recarga parcelas.
     */
    private void prepararFormularioEntrada() {
        vistaStaffEntrada.getCampoSalida().setText("");

        // reset resumen
        vistaStaffEntrada.getLabelFechasResumen().setText("Fechas:");
        vistaStaffEntrada.getLabelParcelasResumen().setText("Parcelas:");
        vistaStaffEntrada.getLabelParticipantesResumen().setText("Participantes:");

        parcelasSeleccionadasEntrada.clear();
        actualizarEstadoParcelasEntrada();
    }

    private void cancelarEntrada() {
        int opcion = JOptionPane.showConfirmDialog(
                vistaStaffEntrada,
                "¿Cancelar el registro de entrada?",
                "Cancelar entrada",
                JOptionPane.YES_NO_OPTION
        );
        if (opcion == JOptionPane.YES_OPTION) {
            vistaStaffEntrada.setVisible(false);
        }
    }

    /**
     * Paso 1: validar fecha de salida (la entrada la tomamos como hoy).
     */
    private void onFechasEntradaSiguiente() {
        String salidaStr = vistaStaffEntrada.getCampoSalida().getText().trim();

        if (salidaStr.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vistaStaffEntrada,
                    "Debes introducir la fecha de salida.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Date hoy = new Date();
        Date salida;
        try {
            salida = sdf.parse(salidaStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(
                    vistaStaffEntrada,
                    "La fecha debe tener formato dd/MM/yyyy.",
                    "Formato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!salida.after(hoy)) {
            JOptionPane.showMessageDialog(
                    vistaStaffEntrada,
                    "La fecha de salida debe ser posterior a hoy.",
                    "Rango de fechas incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        vistaStaffEntrada.getLabelFechasResumen().setText(
                "Fechas: " + sdf.format(hoy) + " - " + sdf.format(salida)
        );

        vistaStaffEntrada.getTabbedPane().setSelectedIndex(1); // Parcelas
    }

    /**
     * Paso 2: pasar a Miembros tras seleccionar parcelas.
     */
    private void onParcelasEntradaSiguiente() {
        if (parcelasSeleccionadasEntrada.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vistaStaffEntrada,
                    "Debes seleccionar al menos una parcela.",
                    "Sin parcelas seleccionadas",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Resumen de parcelas
        StringBuilder sb = new StringBuilder("Parcelas: ");
        for (int i = 0; i < parcelasSeleccionadasEntrada.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(parcelasSeleccionadasEntrada.get(i) + 1); // +1 para que se vea 1..N
        }
        vistaStaffEntrada.getLabelParcelasResumen().setText(sb.toString());

        vistaStaffEntrada.getTabbedPane().setSelectedIndex(2); // Miembros
    }

    /**
     * Paso 3: resumen rápido de nº de participantes y pasar a Confirmación.
     */
    private void onMiembrosEntradaSiguiente() {
        int total = vistaStaffEntrada.getTablaParticipantes().getRowCount();
        vistaStaffEntrada.getLabelParticipantesResumen().setText(
                "Participantes: " + total
        );

        vistaStaffEntrada.getTabbedPane().setSelectedIndex(3); // Confirmación
    }

    /**
     * Inicializa la cuadrícula de parcelas y añade listeners.
     */
    private void inicializarGridParcelasEntrada() {
        JPanel grid = vistaStaffEntrada.getPanelCuadriculaParcelas();
        Component[] comps = grid.getComponents();
        celdasParcelasEntrada = new JPanel[comps.length];

        for (int i = 0; i < comps.length; i++) {
            if (comps[i] instanceof JPanel) {
                JPanel celda = (JPanel) comps[i];
                celdasParcelasEntrada[i] = celda;
                final int idx = i;

                celda.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        onClickParcelaEntrada(idx);
                    }
                });
            }
        }
        actualizarEstadoParcelasEntrada();
    }

    /**
     * Pinta las parcelas según si están libres u ocupadas.
     */
    private void actualizarEstadoParcelasEntrada() {
        if (celdasParcelasEntrada == null) return;

        boolean[] libres = modelo.getParcelasLibres();
        Color colorLibre = new Color(152, 251, 152);
        Color colorOcupada = Color.LIGHT_GRAY;

        for (int i = 0; i < celdasParcelasEntrada.length; i++) {
            JPanel celda = celdasParcelasEntrada[i];
            boolean libre = (i < libres.length) && libres[i];

            if (parcelasSeleccionadasEntrada.contains(i)) {
                celda.setBackground(new Color(135, 206, 250)); // azul selección
            } else {
                celda.setBackground(libre ? colorLibre : colorOcupada);
            }

            String estado = libre ? "Libre" : "No disponible";
            celda.setToolTipText("Parcela " + (i + 1) + " - " + estado);
        }
    }

    /**
     * Selección / deselección de parcela desde VistaStaffEntrada.
     */
    private void onClickParcelaEntrada(int idx) {
        boolean[] libres = modelo.getParcelasLibres();
        boolean libre = (idx < libres.length) && libres[idx];

        if (!libre && !parcelasSeleccionadasEntrada.contains(idx)) {
            JOptionPane.showMessageDialog(
                    vistaStaffEntrada,
                    "Esta parcela no está disponible.",
                    "Parcela ocupada",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        JPanel celda = celdasParcelasEntrada[idx];
        Color colorLibre = new Color(152, 251, 152);
        Color colorSeleccionada = new Color(135, 206, 250);

        if (parcelasSeleccionadasEntrada.contains(idx)) {
            parcelasSeleccionadasEntrada.remove((Integer) idx);
            celda.setBackground(colorLibre);
        } else {
            parcelasSeleccionadasEntrada.add(idx);
            celda.setBackground(colorSeleccionada);
        }
    }

    /**
     * Paso final: crear la Reserva a nombre de un cliente genérico de recepción.
     */
    private void confirmarEntrada() {
        String salidaStr = vistaStaffEntrada.getCampoSalida().getText().trim();

        Date hoy = new Date();
        Date salida;
        try {
            salida = sdf.parse(salidaStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    vistaStaffEntrada,
                    "Fechas no válidas.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Cliente genérico para reservas hechas en recepción
        Cliente cliente = new Cliente(
                "Cliente recepción",
                "00000000X",
                0,
                0,
                "",
                ""
        );

        Reserva reserva = new Reserva(hoy, salida, cliente);

        for (Integer idx : parcelasSeleccionadasEntrada) {
            Parcela p = modelo.getParcela(idx);
            reserva.agregarParcela(p);
        }

        boolean ok = modelo.crearReserva(reserva);
        if (!ok) {
            JOptionPane.showMessageDialog(
                    vistaStaffEntrada,
                    "Alguna de las parcelas seleccionadas ya no está libre.",
                    "No se pudo registrar la entrada",
                    JOptionPane.ERROR_MESSAGE
            );
            actualizarEstadoParcelasEntrada();
            return;
        }

        JOptionPane.showMessageDialog(
                vistaStaffEntrada,
                "Entrada registrada correctamente.",
                "Entrada confirmada",
                JOptionPane.INFORMATION_MESSAGE
        );

        vistaStaffEntrada.setVisible(false);
    }

    // ==========================================================
    //  Creación de actividad desde VistaStaffActividad
    // ==========================================================

    // ==========================================================
    //  Creación de actividad desde VistaStaffActividad
    // ==========================================================

    private void onCrearActividadDesdeVentana() {
        String titulo = vistaStaffActividad.getCampoTituloActividad().getText().trim();
        String diaHora = vistaStaffActividad.getCampoDiaHora().getText().trim();

        if (titulo.isEmpty() || titulo.equals("Título")) {
            JOptionPane.showMessageDialog(
                    vistaStaffActividad,
                    "Debes introducir un título/tipo para la actividad.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (diaHora.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vistaStaffActividad,
                    "Debes introducir una fecha (dd/MM/yyyy).",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Aceptamos solo parte fecha
        String fechaStr = diaHora.split(" ")[0];
        Date fecha;
        try {
            fecha = sdf.parse(fechaStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(
                    vistaStaffActividad,
                    "La fecha debe tener formato dd/MM/yyyy.",
                    "Formato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Aforo fijo para demo
        int maxParticipantes = 20;

        int nuevoId = modelo.generarNuevoIdActividad();
        Actividad actividad = new Actividad(nuevoId, titulo, fecha, maxParticipantes);

        boolean creada = modelo.crearActividad(actividad);
        if (!creada) {
            JOptionPane.showMessageDialog(
                    vistaStaffActividad,
                    "Ya existe una actividad con el ID " + nuevoId + ".",
                    "No se pudo crear la actividad",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                vistaStaffActividad,
                "Actividad creada:\n" + titulo +
                        " (" + sdf.format(fecha) + "), plazas: " + maxParticipantes,
                "Actividad agregada",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Añadir a la tabla "Mi día" en StaffMain
        actualizarTablaMiDia(actividad);

        vistaStaffActividad.setVisible(false);
    }

    /**
     * Añade la actividad recién creada a la tabla "Mi día" de StaffMain.
     * Para el prototipo, dejamos la columna Hora vacía.
     */
    private void actualizarTablaMiDia(Actividad actividad) {
        javax.swing.table.TableModel oldModel = vistaStaffMain.getTablaMiDia().getModel();
        int oldRows = oldModel.getRowCount();

        String[] columnas = {"Hora", "Actividad"};
        Object[][] datos = new Object[oldRows + 1][2];

        for (int r = 0; r < oldRows; r++) {
            datos[r][0] = oldModel.getValueAt(r, 0);
            datos[r][1] = oldModel.getValueAt(r, 1);
        }

        datos[oldRows][0] = "";                  // hora vacía
        datos[oldRows][1] = actividad.getTipo(); // usamos tipo como nombre visible

        javax.swing.table.DefaultTableModel nuevoModelo =
                new javax.swing.table.DefaultTableModel(datos, columnas) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

        vistaStaffMain.setModeloMiDia(nuevoModelo);
    }

}
