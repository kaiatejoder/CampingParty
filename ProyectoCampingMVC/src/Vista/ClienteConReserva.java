package Vista;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ClienteConReserva extends JFrame {
    
    public ClienteConReserva() {
        setTitle("Cliente con Reserva");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Título
        JLabel titulo = new JLabel("¡Hola, Cliente!");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titulo, BorderLayout.NORTH);
        
        // Panel central con calendario y actividades
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        
        // Calendario
        centerPanel.add(crearCalendario(), BorderLayout.NORTH);
        
        // Separador
        JSeparator separator = new JSeparator();
        centerPanel.add(separator, BorderLayout.CENTER);
        
        // Botón Ver actividades
        JButton verActividadesBtn = new JButton("Ver actividades");
        verActividadesBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(verActividadesBtn);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Panel inferior con día seleccionado y actividades
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Día seleccionado
        JLabel diaSeleccionadoLabel = new JLabel("Día seleccionado");
        diaSeleccionadoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(diaSeleccionadoLabel, BorderLayout.NORTH);
        
        // Mis actividades
        JLabel misActividadesLabel = new JLabel("Mis actividades");
        misActividadesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        misActividadesLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        bottomPanel.add(misActividadesLabel, BorderLayout.CENTER);
        
        // Tabla de actividades
        bottomPanel.add(crearTablaActividades(), BorderLayout.SOUTH);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private JPanel crearCalendario() {
        JPanel calendarioPanel = new JPanel();
        calendarioPanel.setLayout(new BorderLayout());
        calendarioPanel.setBackground(Color.WHITE);
        calendarioPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        // Crear tabla de calendario
        String[] columnNames = {"", "3", "4", "5", "6", "7", "8", "9"};
        String[][] data = {
            {"10", "11", "12", "13", "14", "15", "16", ""},
            {"17", "18", "19", "20", "21", "22", "23", ""},
            {"24", "25", "26", "27", "28", "29", "30", ""}
        };
        
        JTable tablaCalendario = new JTable(data, columnNames);
        tablaCalendario.setRowHeight(30);
        tablaCalendario.setEnabled(false); // Sin funcionalidad
        
        JScrollPane scrollPane = new JScrollPane(tablaCalendario);
        calendarioPanel.add(scrollPane, BorderLayout.CENTER);
        
        return calendarioPanel;
    }
    
    private JScrollPane crearTablaActividades() {
        // Columnas de la tabla de actividades
        String[] columnNames = {"Hora", "Lugar", "Actividad", "Acompañant..."};
        
        // Datos vacíos
        String[][] data = {};
        
        JTable tablaActividades = new JTable(data, columnNames);
        tablaActividades.setRowHeight(25);
        tablaActividades.setEnabled(false); // Sin funcionalidad
        
        JScrollPane scrollPane = new JScrollPane(tablaActividades);
        scrollPane.setPreferredSize(new Dimension(0, 100));
        
        return scrollPane;
    }
}