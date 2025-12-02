package Controlador;

import Modelo.Actividad;
import Modelo.Cliente;
import Modelo.Modelo;
import Modelo.Parcela;
import Modelo.Reserva;
import Modelo.Tienda;
import Vista.ClienteConReserva;
import Vista.VistaCliente;
import Vista.VistaClienteModificarReserva;
import Vista.VistaClienteReserva;
import Vista.VistaClienteReservaEditado;
import Vista.VistaClienteActividades;
import Vista.VistaReservas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controla el flujo de reservas del cliente:
 * - Pantalla principal cliente (VistaCliente)
 * - Asistente de nueva reserva (VistaClienteReserva)
 * - Actividades disponibles para el cliente
 */
public class ControladorClienteReservas {

    private final Modelo modelo;
    private final Cliente clienteLogueado;

    private final VistaCliente vistaCliente;
    private final VistaClienteReserva vistaClienteReserva;
    private final VistaClienteModificarReserva vistaClienteModificar;
    private final VistaClienteReservaEditado vistaClienteReservaEditado;
    private final VistaReservas vistaReservas;
    private final ClienteConReserva vistaClienteConReserva;
    private final VistaClienteActividades vistaClienteActividades;   // 👈 NUEVA VISTA

    private Tienda tiendaSeleccionada;

    // Gestión de selección de parcelas
    private JPanel[] celdasParcelas;
    private final List<Integer> parcelasSeleccionadas = new ArrayList<>();

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ControladorClienteReservas(Modelo modelo,
                                      Cliente clienteLogueado,
                                      VistaCliente vistaCliente,
                                      VistaClienteReserva vistaClienteReserva,
                                      VistaClienteModificarReserva vistaClienteModificar,
                                      VistaClienteReservaEditado vistaClienteReservaEditado,
                                      VistaReservas vistaReservas,
                                      ClienteConReserva vistaClienteConReserva,
                                      VistaClienteActividades vistaClienteActividades) {
        this.modelo = modelo;
        this.clienteLogueado = clienteLogueado;
        this.vistaCliente = vistaCliente;
        this.vistaClienteReserva = vistaClienteReserva;
        this.vistaClienteModificar = vistaClienteModificar;
        this.vistaClienteReservaEditado = vistaClienteReservaEditado;
        this.vistaReservas = vistaReservas;
        this.vistaClienteConReserva = vistaClienteConReserva;
        this.vistaClienteActividades = vistaClienteActividades;

        inicializarVistaCliente();
        inicializarVistaReserva();
        inicializarGridParcelas();
        inicializarActividadesCliente();   // 👈 inicializamos lógica de actividades
    }

    // ==========================================================
    //  Vista principal de cliente
    // ==========================================================

    private void inicializarVistaCliente() {
        // Saludo
        vistaCliente.setTextoSaludo("Hola, " + clienteLogueado.getNombre());

        if (clienteLogueado.getNumReservas() > 0) {
            vistaCliente.setTextoFechaProximaReserva("Tienes reservas activas.");
        } else {
            vistaCliente.setTextoFechaProximaReserva("No tienes ninguna reserva.");
        }

        // Botón "Hacer nueva reserva"
        vistaCliente.getBtnHacerNuevaReserva().addActionListener(e -> {
            prepararFormularioReserva();
            vistaCliente.setVisible(false);
            vistaClienteReserva.getTabbedPane().setSelectedIndex(0);
            vistaClienteReserva.setLocationRelativeTo(vistaCliente);
            vistaClienteReserva.setVisible(true);
        });

        // Botón "Ver reservas anteriores"
        vistaCliente.getBtnVerReservasAnteriores().addActionListener(e ->
                JOptionPane.showMessageDialog(
                        vistaCliente,
                        "Aquí se podría mostrar el historial de reservas.",
                        "Reservas anteriores",
                        JOptionPane.INFORMATION_MESSAGE
                ));

        vistaCliente.getBtnModificar().addActionListener(e ->
                JOptionPane.showMessageDialog(
                        vistaCliente,
                        "Modificar reserva pendiente de implementar.",
                        "Modificar reserva",
                        JOptionPane.INFORMATION_MESSAGE
                ));

        vistaCliente.getBtnCancelar().addActionListener(e ->
                JOptionPane.showMessageDialog(
                        vistaCliente,
                        "Cancelar reserva pendiente de implementar.",
                        "Cancelar reserva",
                        JOptionPane.INFORMATION_MESSAGE
                ));

        // 👇 Botón "Actividades" -> abre la ventana de actividades
        vistaCliente.getBtnVerActividades().addActionListener(e -> mostrarActividadesCliente());

        vistaCliente.getBtnVolver().addActionListener(e -> {
            int op = JOptionPane.showConfirmDialog(
                    vistaCliente,
                    "¿Seguro que quieres cerrar sesión?",
                    "Cerrar sesión",
                    JOptionPane.YES_NO_OPTION
            );
            if (op == JOptionPane.YES_OPTION) {
                vistaCliente.dispose();
            }
        });
    }

    // ==========================================================
    //  Inicialización de VistaClienteReserva (asistente)
    // ==========================================================

    private void inicializarVistaReserva() {
        // Pestaña "Fechas"
        vistaClienteReserva.getBtnCancelarFechas().addActionListener(e -> cancelarAsistente());
        vistaClienteReserva.getBtnSiguienteFechas().addActionListener(e -> onFechasSiguiente());

        // Pestaña "Parcelas"
        vistaClienteReserva.getBtnAtrasParcelas().addActionListener(e ->
                vistaClienteReserva.getTabbedPane().setSelectedIndex(0));
        vistaClienteReserva.getBtnSiguienteParcelas().addActionListener(e -> onParcelasSiguiente());

        // Pestaña "Miembros"
        vistaClienteReserva.getBtnAtrasMiembros().addActionListener(e ->
                vistaClienteReserva.getTabbedPane().setSelectedIndex(1));
        vistaClienteReserva.getBtnSiguienteMiembros().addActionListener(e -> onMiembrosSiguiente());
        vistaClienteReserva.getBtnAgregarMiembro().addActionListener(e -> onAgregarMiembro());

        // Pestaña "Confirmación"
        vistaClienteReserva.getBtnVolverAtrasConfirmacion().addActionListener(e ->
                vistaClienteReserva.getTabbedPane().setSelectedIndex(2));
        vistaClienteReserva.getBtnConfirmarReserva().addActionListener(e -> onConfirmarReserva());

        vistaClienteReserva.getBtnAtrasParcelas().addActionListener(e ->
                vistaClienteReserva.getTabbedPane().setSelectedIndex(0));
        vistaClienteReserva.getBtnSiguienteParcelas().addActionListener(e -> onParcelasSiguiente());

        // Botón "Agregar" tienda
        vistaClienteReserva.getBtnAgregarTienda().addActionListener(e -> onAgregarTienda());
    }

    /**
     * Limpia el formulario y recarga el estado real de las parcelas.
     */
    private void prepararFormularioReserva() {
        // Limpiar campos de fechas
        vistaClienteReserva.getCampoEntrada().setText("");
        vistaClienteReserva.getCampoSalida().setText("");

        // Limpiar tabla de participantes
        DefaultTableModel modelTabla = getModeloTablaParticipantes();
        modelTabla.setRowCount(0);

        // Limpiar resumen
        vistaClienteReserva.getLabelFechasResumen().setText("Fechas:");
        vistaClienteReserva.getLabelParcelasResumen().setText("Parcelas:");
        vistaClienteReserva.getLabelParticipantesResumen().setText("Participantes:");

        // Limpiar tienda seleccionada
        tiendaSeleccionada = null;
        vistaClienteReserva.getCampoNombreTienda().setText("Nombre...");
        vistaClienteReserva.getCampoM2().setText("Metros cuadrados...");
        vistaClienteReserva.getBtnAgregarTienda().setText("Agregar");

        // Limpiar selección de parcelas y recargar colores
        parcelasSeleccionadas.clear();
        actualizarEstadoParcelasDesdeModelo();
    }

    private void cancelarAsistente() {
        int op = JOptionPane.showConfirmDialog(
                vistaClienteReserva,
                "¿Cancelar la creación de la reserva?",
                "Cancelar",
                JOptionPane.YES_NO_OPTION
        );
        if (op == JOptionPane.YES_OPTION) {
            vistaClienteReserva.setVisible(false);
            vistaCliente.setLocationRelativeTo(null);
            vistaCliente.setVisible(true);
        }
    }

    // ==========================================================
    //  Paso 1: Fechas
    // ==========================================================

    private void onFechasSiguiente() {
        String entradaStr = vistaClienteReserva.getCampoEntrada().getText().trim();
        String salidaStr = vistaClienteReserva.getCampoSalida().getText().trim();

        if (entradaStr.isEmpty() || salidaStr.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "Debes introducir la fecha de entrada y salida.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Date entrada;
        Date salida;
        try {
            entrada = sdf.parse(entradaStr);
            salida = sdf.parse(salidaStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "Las fechas deben tener formato dd/MM/yyyy.",
                    "Formato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!salida.after(entrada)) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "La fecha de salida debe ser posterior a la de entrada.",
                    "Rango de fechas incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Guardamos resumen de fechas
        vistaClienteReserva.getLabelFechasResumen().setText(
                "Fechas: " + sdf.format(entrada) + " - " + sdf.format(salida)
        );

        // Pasamos a pestaña Parcelas
        vistaClienteReserva.getTabbedPane().setSelectedIndex(1);
    }

    // ==========================================================
    //  Paso 2: Parcelas
    // ==========================================================

    private void inicializarGridParcelas() {
        JPanel grid = vistaClienteReserva.getPanelCuadriculaParcelas();
        Component[] comps = grid.getComponents();
        celdasParcelas = new JPanel[comps.length];

        for (int i = 0; i < comps.length; i++) {
            if (comps[i] instanceof JPanel) {
                JPanel celda = (JPanel) comps[i];
                celdasParcelas[i] = celda;
                final int idx = i;

                celda.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        onClickParcela(idx);
                    }
                });
            }
        }

        actualizarEstadoParcelasDesdeModelo();
    }

    /**
     * Lee del Modelo qué parcelas están libres y pinta las celdas.
     */
    private void actualizarEstadoParcelasDesdeModelo() {
        if (celdasParcelas == null) return;

        boolean[] libres = modelo.getParcelasLibres();
        Color colorLibre = new Color(152, 251, 152);
        Color colorOcupada = Color.LIGHT_GRAY;

        for (int i = 0; i < celdasParcelas.length; i++) {
            JPanel celda = celdasParcelas[i];
            boolean libre = (i < libres.length) && libres[i];

            if (parcelasSeleccionadas.contains(i)) {
                celda.setBackground(new Color(135, 206, 250)); // azulito
            } else {
                celda.setBackground(libre ? colorLibre : colorOcupada);
            }

            String estado = libre ? "Libre" : "No disponible";
            celda.setToolTipText("Parcela " + (i + 1) + " - " + estado);
        }
    }

    private void onClickParcela(int idx) {
        boolean[] libres = modelo.getParcelasLibres();
        boolean libre = (idx < libres.length) && libres[idx];

        if (!libre && !parcelasSeleccionadas.contains(idx)) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "Esta parcela no está disponible.",
                    "Parcela ocupada",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        JPanel celda = celdasParcelas[idx];
        Color colorLibre = new Color(152, 251, 152);
        Color colorSeleccionada = new Color(135, 206, 250); // azul

        if (parcelasSeleccionadas.contains(idx)) {
            // Deseleccionar
            parcelasSeleccionadas.remove((Integer) idx);
            celda.setBackground(colorLibre);
        } else {
            // Seleccionar
            parcelasSeleccionadas.add(idx);
            celda.setBackground(colorSeleccionada);
        }
    }

    private void onAgregarTienda() {
        String nombre = vistaClienteReserva.getCampoNombreTienda().getText().trim();
        String m2Str = vistaClienteReserva.getCampoM2().getText().trim();

        if (nombre.isEmpty() || nombre.equals("Nombre...")
                || m2Str.isEmpty() || m2Str.equals("Metros cuadrados...")) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "Rellena el nombre de la tienda y los metros cuadrados.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        float m2;
        try {
            m2 = Float.parseFloat(m2Str);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "Los metros cuadrados deben ser un número.",
                    "Formato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (m2 <= 0) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "Los metros cuadrados deben ser mayores que cero.",
                    "Valor no válido",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Guardamos la tienda para esta reserva
        tiendaSeleccionada = new Tienda(nombre, m2);

        JOptionPane.showMessageDialog(
                vistaClienteReserva,
                "Tienda añadida: " + tiendaSeleccionada.toString(),
                "Tienda agregada",
                JOptionPane.INFORMATION_MESSAGE
        );

        vistaClienteReserva.getBtnAgregarTienda().setText("Tienda añadida");
    }

    private void onParcelasSiguiente() {
        if (parcelasSeleccionadas.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "Debes seleccionar al menos una parcela.",
                    "Sin parcelas seleccionadas",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        StringBuilder sb = new StringBuilder("Parcelas: ");
        for (int i = 0; i < parcelasSeleccionadas.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(parcelasSeleccionadas.get(i) + 1);
        }

        if (tiendaSeleccionada != null) {
            sb.append(" | Tienda: ").append(tiendaSeleccionada.toString());
        }

        vistaClienteReserva.getLabelParcelasResumen().setText(sb.toString());
        vistaClienteReserva.getTabbedPane().setSelectedIndex(2);
    }

    // ==========================================================
    //  Paso 3: Miembros
    // ==========================================================

    private DefaultTableModel getModeloTablaParticipantes() {
        return (DefaultTableModel) vistaClienteReserva.getTablaParticipantes().getModel();
    }

    private void onAgregarMiembro() {
        String nombre = vistaClienteReserva.getCampoNombreMiembro().getText().trim();
        String apellidos = vistaClienteReserva.getCampoApellidosMiembro().getText().trim();
        String edadStr = vistaClienteReserva.getCampoEdadMiembro().getText().trim();

        if (nombre.isEmpty() || apellidos.isEmpty() || edadStr.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "Rellena nombre, apellidos y edad.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "La edad debe ser un número.",
                    "Edad incorrecta",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        DefaultTableModel modelTabla = getModeloTablaParticipantes();
        modelTabla.addRow(new Object[]{nombre, apellidos, edad});

        int total = modelTabla.getRowCount();
        vistaClienteReserva.getLabelParticipantesResumen().setText(
                "Participantes: " + total
        );
    }

    private void onMiembrosSiguiente() {
        vistaClienteReserva.getTabbedPane().setSelectedIndex(3);
    }

    // ==========================================================
    //  Paso 4: Confirmación y creación de Reserva
    // ==========================================================

    private void onConfirmarReserva() {
        String entradaStr = vistaClienteReserva.getCampoEntrada().getText().trim();
        String salidaStr = vistaClienteReserva.getCampoSalida().getText().trim();

        Date entrada;
        Date salida;
        try {
            entrada = sdf.parse(entradaStr);
            salida = sdf.parse(salidaStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "Fechas no válidas.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Crear objeto Reserva con fechas y cliente
        Reserva reserva = new Reserva(entrada, salida, clienteLogueado);

        // Añadir parcelas seleccionadas
        for (Integer idx : parcelasSeleccionadas) {
            Parcela p = modelo.getParcela(idx);
            reserva.agregarParcela(p);
        }

        // Añadir la tienda si existe
        if (tiendaSeleccionada != null) {
            reserva.agregarTienda(tiendaSeleccionada);
        }

        // Acompañantes leyendo la tabla
        DefaultTableModel modelTabla = getModeloTablaParticipantes();
        for (int i = 0; i < modelTabla.getRowCount(); i++) {
            String nombre = (String) modelTabla.getValueAt(i, 0);
            String apellidos = (String) modelTabla.getValueAt(i, 1);
            int edad = (int) modelTabla.getValueAt(i, 2);
            reserva.agregarAcompanyante(nombre + " " + apellidos + " (" + edad + ")");
        }

        // Intentamos crear la reserva en el Modelo
        boolean ok = modelo.crearReserva(reserva);
        if (!ok) {
            JOptionPane.showMessageDialog(
                    vistaClienteReserva,
                    "Alguna de las parcelas ya no está libre.",
                    "No se pudo crear la reserva",
                    JOptionPane.ERROR_MESSAGE
            );
            actualizarEstadoParcelasDesdeModelo();
            return;
        }

        // Guardamos también la reserva en el cliente
        clienteLogueado.addReserva(reserva);

        JOptionPane.showMessageDialog(
                vistaClienteReserva,
                "Reserva creada correctamente.",
                "Reserva confirmada",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Actualizar texto en la pantalla de cliente
        vistaCliente.setTextoFechaProximaReserva(
                sdf.format(entrada) + " - " + sdf.format(salida)
        );

        // Cerrar asistente y volver a VistaCliente
        vistaClienteReserva.setVisible(false);
        vistaCliente.setLocationRelativeTo(null);
        vistaCliente.setVisible(true);
    }

    // ==========================================================
    //  ACTIVIDADES PARA EL CLIENTE
    // ==========================================================

    /** Inicializa los listeners de la ventana de actividades. */
    private void inicializarActividadesCliente() {
        // Botones de la ventana de actividades
        vistaClienteActividades.getBtnApuntarse().addActionListener(e -> onApuntarseActividad());
        vistaClienteActividades.getBtnCerrar().addActionListener(e -> vistaClienteActividades.setVisible(false));
    }

    /** Abre la ventana de actividades y carga las actividades disponibles desde el Modelo. */
    private void mostrarActividadesCliente() {
        List<Actividad> disponibles = modelo.getActividadesDisponibles();

        String[] columnas = {"ID", "Tipo", "Fecha", "Plazas"};
        Object[][] datos = new Object[disponibles.size()][4];

        for (int i = 0; i < disponibles.size(); i++) {
            Actividad a = disponibles.get(i);
            datos[i][0] = a.getIdActividad();
            datos[i][1] = a.getTipo();
            datos[i][2] = sdf.format(a.getFechaHora());
            datos[i][3] = a.getPlazasDisponibles() + "/" + a.getMaxParticipantes();
        }

        DefaultTableModel modeloTabla =
                new DefaultTableModel(datos, columnas) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

        vistaClienteActividades.setModeloTabla(modeloTabla);
        vistaClienteActividades.setLocationRelativeTo(vistaCliente);
        vistaClienteActividades.setVisible(true);
    }

    /** Inscribe al cliente en la actividad seleccionada en la tabla. */
    private void onApuntarseActividad() {
        int fila = vistaClienteActividades.getTablaActividades().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    vistaClienteActividades,
                    "Debes seleccionar una actividad.",
                    "Ninguna actividad seleccionada",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Object val = vistaClienteActividades.getTablaActividades().getValueAt(fila, 0);
        int idActividad;
        if (val instanceof Integer) {
            idActividad = (Integer) val;
        } else {
            idActividad = Integer.parseInt(val.toString());
        }

        boolean ok = modelo.inscribirEnActividad(clienteLogueado, idActividad);
        if (!ok) {
            JOptionPane.showMessageDialog(
                    vistaClienteActividades,
                    "No ha sido posible inscribirse (puede que ya no queden plazas o ya estés apuntado).",
                    "Error al inscribirse",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    vistaClienteActividades,
                    "Te has apuntado correctamente a la actividad.",
                    "Inscripción realizada",
                    JOptionPane.INFORMATION_MESSAGE
            );
            // refrescamos la tabla para actualizar plazas disponibles
            mostrarActividadesCliente();
        }
    }
}
