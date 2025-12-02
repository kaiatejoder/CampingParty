package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Modelo {

    // Fichero sencillo para guardar las actividades
    private static final String FICHERO_ACTIVIDADES = "actividades.txt";

    private int descuento;
    private Parcelas parcelas;
    public ArrayList<Cliente> clientes;
    private ArrayList<Actividad> actividades;
    private Reservas reservas;
    private ArrayList<Staff> staffs;

    public Modelo(){
        this.descuento = 20;
        this.parcelas = new Parcelas();
        this.clientes = new ArrayList<>();
        this.actividades = new ArrayList<>();
        this.reservas = new Reservas();
        this.staffs = new ArrayList<>();
        
        // Datos de prueba iniciales
        inicializarDatosPrueba();

        // Intentar cargar actividades desde fichero (si existe)
        cargarActividadesDeFichero();
    }
    
    private void inicializarDatosPrueba() {
        // Clientes de prueba
        clientes.add(new Cliente("Juan Pérez", "12345678A", 30, 666111222, "juan@email.com", "password123"));
        clientes.add(new Cliente("María García", "87654321B", 25, 666333444, "maria@email.com", "password456"));
        
        // Actividades de prueba (si luego se carga de fichero, se sobrescribirán)
        actividades.add(new Actividad(1, "Yoga Matutino", new Date(), 20));
        actividades.add(new Actividad(2, "Senderismo", new Date(System.currentTimeMillis() + 86400000L), 15));
        actividades.add(new Actividad(3, "Taller Manualidades", new Date(System.currentTimeMillis() + 172800000L), 10));
        
        // Reservas de prueba
        Cliente cliente = clientes.get(0);
        Reserva reserva = new Reserva(new Date(), new Date(System.currentTimeMillis() + 86400000L * 5), cliente);
        reserva.setPrecioTotal(5, descuento);
        reservas.addReserva(reserva);
    }
    
    // ========== MÉTODOS DE AUTENTICACIÓN ==========
    
    public Cliente autenticarCliente(String usuario, String contrasena) {
        for (Cliente c : clientes) {
            if (c.getUser() != null && c.getUser().equals(usuario) &&
                c.getPass() != null && c.getPass().equals(contrasena)) {
                return c;
            }
        }
        return null;
    }
    
    public boolean registrarCliente(Cliente nuevoCliente) {
        // Verificar si el usuario ya existe
        for (Cliente c : clientes) {
            if (c.getUser().equals(nuevoCliente.getUser())) {
                return false;
            }
        }
        clientes.add(nuevoCliente);
        return true;
    }
    
    // ========== MÉTODOS DE PARCELAS ==========
    
    public boolean[] getParcelasLibres(){
        return this.parcelas.getLibres();
    }
    
    public Parcela getParcela(int index) {
        return parcelas.getParcela(index);
    }
    
    public void reservarParcela(int index) {
        parcelas.setReservada(index);
    }
    
    public void liberarParcela(int index) {
        parcelas.liberarParcela(index);
    }
    
    // ========== MÉTODOS DE RESERVAS ==========
    
    public boolean crearReserva(Reserva reserva) {
        // Verificar disponibilidad de parcelas
        for (Parcela p : reserva.getParcelas()) {
            if (!p.isLibre()) {
                return false;
            }
        }
        
        // Reservar parcelas
        for (Parcela p : reserva.getParcelas()) {
            p.reservarParcela();
        }
        
        reservas.addReserva(reserva);
        return true;
    }
    
    public boolean cancelarReserva(int index) {
        if (index >= 0 && index < reservas.size()) {
            Reserva reserva = reservas.getReserva(index);
            
            // Liberar parcelas
            for (Parcela p : reserva.getParcelas()) {
                p.liberarParcela();
            }
            
            reservas.cancelarReserva(index);
            return true;
        }
        return false;
    }
    
    public Reservas getReservasCliente(Cliente cliente) {
        Reservas reservasCliente = new Reservas();
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.getReserva(i);
            if (r.getCliente().equals(cliente)) {
                reservasCliente.addReserva(r);
            }
        }
        return reservasCliente;
    }
    
    // ========== MÉTODOS DE ACTIVIDADES ==========
    
    public ArrayList<Actividad> getActividadesDisponibles() {
        ArrayList<Actividad> disponibles = new ArrayList<>();
        for (Actividad a : actividades) {
            if (a.hayPlazasDisponibles()) {
                disponibles.add(a);
            }
        }
        return disponibles;
    }
    
    public boolean inscribirEnActividad(Cliente cliente, int idActividad) {
        for (Actividad a : actividades) {
            if (a.getIdActividad() == idActividad) {
                boolean ok = a.agregarParticipante(cliente);
                if (ok) {
                    // Podríamos guardar también participantes, pero para simplificar
                    // solo persistimos la definición de la actividad, no la lista de inscritos.
                    guardarActividadesEnFichero();
                }
                return ok;
            }
        }
        return false;
    }
    
    public boolean cancelarInscripcionActividad(Cliente cliente, int idActividad) {
        for (Actividad a : actividades) {
            if (a.getIdActividad() == idActividad) {
                boolean ok = a.eliminarParticipante(cliente);
                if (ok) {
                    guardarActividadesEnFichero();
                }
                return ok;
            }
        }
        return false;
    }
    
    public boolean crearActividad(Actividad actividad) {
        // Verificar que no existe actividad con mismo ID
        for (Actividad a : actividades) {
            if (a.getIdActividad() == actividad.getIdActividad()) {
                return false;
            }
        }
        actividades.add(actividad);
        guardarActividadesEnFichero();  // persistir nueva actividad
        return true;
    }

    /** Genera un nuevo ID de actividad incremental (máx ID actual + 1). */
    public int generarNuevoIdActividad() {
        int max = 0;
        for (Actividad a : actividades) {
            if (a.getIdActividad() > max) {
                max = a.getIdActividad();
            }
        }
        return max + 1;
    }

    // ========== PERSISTENCIA DE ACTIVIDADES EN FICHERO ==========

    /**
     * Guarda las actividades en un fichero de texto simple:
     * id;tipo;fechaMillis;maxParticipantes
     * (no se guardan los participantes para mantenerlo simple).
     */
    private void guardarActividadesEnFichero() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO_ACTIVIDADES))) {
            for (Actividad a : actividades) {
                int id = a.getIdActividad();
                String tipo = a.getTipo();
                Date fecha = a.getFechaHora();
                int max = a.getMaxParticipantes();

                long millis = fecha.getTime();
                bw.write(id + ";" + tipo + ";" + millis + ";" + max);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga actividades desde fichero (si existe) y sustituye las de prueba.
     */
    private void cargarActividadesDeFichero() {
        File f = new File(FICHERO_ACTIVIDADES);
        if (!f.exists()) {
            return; // no hay fichero -> usamos las de prueba
        }
        ArrayList<Actividad> cargadas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length < 4) continue;

                int id = Integer.parseInt(partes[0]);
                String tipo = partes[1];
                long millis = Long.parseLong(partes[2]);
                int max = Integer.parseInt(partes[3]);

                Date fecha = new Date(millis);
                Actividad a = new Actividad(id, tipo, fecha, max);
                cargadas.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return; // si falla la carga, nos quedamos con las de prueba
        }

        if (!cargadas.isEmpty()) {
            this.actividades = cargadas;
        }
    }
    
    // ========== GETTERS Y SETTERS ==========
    
    public int getDescuento() {
        return descuento;
    }
    
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    
    public ArrayList<Actividad> getActividades() {
        return new ArrayList<>(actividades);
    }
    
    public Reservas getTodasLasReservas() {
        return reservas;
    }
    
    public int getTotalClientes() {
        return clientes.size();
    }
    
    public int getTotalActividades() {
        return actividades.size();
    }
    
    public int getTotalReservas() {
        return reservas.size();
    }
}
