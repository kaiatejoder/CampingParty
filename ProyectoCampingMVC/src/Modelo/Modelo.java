package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Modelo {
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
        
        // Datos de prueba
        inicializarDatosPrueba();
    }
    
    private void inicializarDatosPrueba() {
        // Clientes de prueba
        clientes.add(new Cliente("Juan Pérez", "12345678A", 30, 666111222, "juan@email.com", "password123"));
        clientes.add(new Cliente("María García", "87654321B", 25, 666333444, "maria@email.com", "password456"));
        
        // Actividades de prueba
        actividades.add(new Actividad(1, "Yoga Matutino", new Date(), 20));
        actividades.add(new Actividad(2, "Senderismo", new Date(System.currentTimeMillis() + 86400000), 15));
        actividades.add(new Actividad(3, "Taller Manualidades", new Date(System.currentTimeMillis() + 172800000), 10));
        
        // Reservas de prueba
        Cliente cliente = clientes.get(0);
        Reserva reserva = new Reserva(new Date(), new Date(System.currentTimeMillis() + 86400000 * 5), cliente);
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
                return a.agregarParticipante(cliente);
            }
        }
        return false;
    }
    
    public boolean cancelarInscripcionActividad(Cliente cliente, int idActividad) {
        for (Actividad a : actividades) {
            if (a.getIdActividad() == idActividad) {
                return a.eliminarParticipante(cliente);
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
        return true;
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