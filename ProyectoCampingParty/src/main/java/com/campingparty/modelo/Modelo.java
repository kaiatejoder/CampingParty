package com.campingparty.modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 * MODELO - Clase central que gestiona TODOS los datos del sistema.
 * 
 * Responsabilidades:
 * - Gestionar parcelas, actividades, reservas
 * - Consultar y actualizar datos en la base de datos
 * - Validar operaciones de negocio
 * - NO contiene lógica de interfaz gráfica
 * 
 * El Controlador solicita datos al Modelo y los pasa a la Vista.
 * La Vista nunca accede directamente al Modelo.
 * 
 * @author Carla Terol
 */
public class Modelo {
    private int descuento;
    private ArrayList<Parcela> parcelas;
    private ArrayList<Actividad> actividades;
    private ArrayList<Reserva> reservas;
    private ArrayList<Cliente> clientes;
    private DAO dao;

    public Modelo() {
        this.descuento = 20;
        this.parcelas = new ArrayList<>();
        this.actividades = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.dao = new DAO();
        
        inicializarParcelas();
    }

    /**
     * Inicializa las 16 parcelas del camping
     */
    private void inicializarParcelas() {
        for (int i = 0; i < 16; i++) {
            Parcela p = new Parcela(i + 1, 20 + i * 5, i % 2 == 0, 15 + i * 2);
            parcelas.add(p);
        }
    }

    

    // ==================== MÉTODOS RELATIVOS A PARCELAS ====================

    /**
     * Obtiene todas las parcelas
     */
    public ArrayList<Parcela> getParcelas() {
        return parcelas;
    }

    /**
     * Obtiene una parcela por ID
     */
    public Parcela getParcela(int id) {
        for (Parcela p : parcelas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Obtiene el estado de todas las parcelas (libres o ocupadas)
     */
    public boolean[] getParcelasLibres() {
        boolean[] libres = new boolean[parcelas.size()];
        for (int i = 0; i < parcelas.size(); i++) {
            libres[i] = parcelas.get(i).isLibre();
        }
        return libres;
    }

    /**
     * Obtiene parcelas disponibles para un rango de fechas
     */
    public ArrayList<Parcela> getParcelasDisponibles(Date fechaInicio, Date fechaFin) {
        ArrayList<Parcela> disponibles = new ArrayList<>();
        for (Parcela p : parcelas) {
            if (p.isLibre()) {
                disponibles.add(p);
            }
        }
        return disponibles;
    }

    // ==================== MÉTODOS RELATIVOS A ACTIVIDADES ====================

    /**
     * Obtiene todas las actividades
     */
    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    /**
     * Obtiene una actividad por ID
     */
    public Actividad getActividad(int id) {
        for (Actividad a : actividades) {
            if (a.getIdActividad() == id) {
                return a;
            }
        }
        return null;
    }

    /**
     * Añade una nueva actividad
     */
    public void addActividad(Actividad a) {
        if (!actividades.contains(a)) {
            actividades.add(a);
        }
    }

    /**
     * Elimina una actividad
     */
    public void removeActividad(Actividad a) {
        actividades.remove(a);
    }

    /**
     * Obtiene las plantillas de actividades
     */
    public ArrayList<Actividad> getPlantillasActividades() {
        ArrayList<Actividad> plantillas = new ArrayList<>();
        for (Actividad a : actividades) {
            if (a.getTipo() == 3) { // Tipo 3 es Plantilla
                plantillas.add(a);
            }
        }
        return plantillas;
    }

    // ==================== MÉTODOS RELATIVOS A RESERVAS ====================

    /**
     * Obtiene todas las reservas
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Obtiene una reserva por ID
     */
    public Reserva getReserva(int id) {
        for (Reserva r : reservas) {
            if (r.getIdReserva() == id) {
                return r;
            }
        }
        return null;
    }

    /**
     * Crea una nueva reserva
     */
    public Reserva crearReserva(Cliente cliente, Date fechaInicio, Date fechaFin) {
        Reserva r = new Reserva(fechaInicio, fechaFin, cliente);
        reservas.add(r);
        cliente.addReserva(r);
        return r;
    }

    /**
     * Elimina una reserva
     */
    public void removeReserva(Reserva r) {
        if (reservas.remove(r)) {
            Cliente c = r.getCliente();
            if (c != null) {
                c.removeReserva(r);
            }
            // Liberar las parcelas de la reserva
            for (Parcela p : r.getParcelas()) {
                p.liberarParcela();
            }
        }
    }

    /**
     * Confirma una reserva (guardándola en BD)
     */
    public boolean confirmarReserva(Reserva r) {
        // Aquí iría la lógica para guardar en BD a través del DAO
        try {
             dao.guardarReserva(r);
            return true;
        } catch (Exception e) {
            System.err.println("Error al confirmar reserva: " + e.getMessage());
            return false;
        }
    }

    // ==================== MÉTODOS RELATIVOS A CLIENTES ====================

    /**
     * Obtiene todos los clientes
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Obtiene un cliente por ID
     */
    public Cliente getCliente(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Autentica un cliente con usuario y contraseña
     */
    public Cliente autenticarCliente(String usuario, String contrasena) {
        return dao.getCliente(usuario, contrasena);
    }

    /**
     * Registra un nuevo cliente
     */
    public boolean registrarCliente(Cliente cliente) {
        if (!clientes.contains(cliente)) {
            clientes.add(cliente);
            return dao.agregarCliente(cliente);
        }
        return false;
    }

    /**
     * Añade un cliente
     */
    public void addCliente(Cliente c) {
        if (!clientes.contains(c)) {
            clientes.add(c);
        }
    }

    // ==================== MÉTODOS RELATIVOS A STAFF ====================

    public Staff getStaff(String user, String pass){
        return dao.getStaff(user, pass);
    }

    // ==================== MÉTODOS DE CONFIGURACIÓN ====================

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public DAO getDAO() {
        return dao;
    }

    /**
     * Carga las actividades desde BD
     */
    public void cargarActividades() {
        try {
            ArrayList<Actividad> acts = dao.getActividades();
            actividades.addAll(acts);
        } catch (Exception e) {
            System.err.println("Error al cargar actividades: " + e.getMessage());
        }
    }
    public Cliente getCliente(String user, String pass){
        return dao.getCliente(user, pass);
    }
    
    /**
     * Obtiene la descripción general del estado del modelo
     */
    @Override
    public String toString() {
        return "Modelo{" +
                "parcelas=" + parcelas.size() +
                ", actividades=" + actividades.size() +
                ", reservas=" + reservas.size() +
                ", clientes=" + clientes.size() +
                ", descuento=" + descuento + "%" +
                '}';
    }
}


