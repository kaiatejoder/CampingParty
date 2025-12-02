package Vista;

import RiuRauLaf.RiuRauLaf;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Vista para que el cliente vea las actividades disponibles y se apunte.
 */
public class VistaClienteActividades extends JFrame {

    private JLabel labelTitulo;
    private JTable tablaActividades;
    private JScrollPane scrollTabla;
    private JButton btnApuntarse;
    private JButton btnCerrar;

    public VistaClienteActividades() {
        RiuRauLaf.setup();
        setTitle("Actividades disponibles");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        labelTitulo = new JLabel("Actividades disponibles");
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));

        String[] columnas = {"ID", "Tipo", "Fecha", "Plazas máx."};
        Object[][] datos = {};
        tablaActividades = new JTable(new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tablaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollTabla = new JScrollPane(tablaActividades);

        btnApuntarse = new JButton("Apuntarme");
        btnCerrar = new JButton("Cerrar");
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelNorte.add(labelTitulo);
        add(panelNorte, BorderLayout.NORTH);

        add(scrollTabla, BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelSur.add(btnApuntarse);
        panelSur.add(btnCerrar);
        add(panelSur, BorderLayout.SOUTH);
    }

    // ========== GETTERS PARA EL CONTROLADOR ==========

    public JTable getTablaActividades() {
        return tablaActividades;
    }

    public JButton getBtnApuntarse() {
        return btnApuntarse;
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }

    public void setModeloTabla(DefaultTableModel modelo) {
        tablaActividades.setModel(modelo);
    }
}
