package com.campingparty.modelo;

import java.util.ArrayList;

/**
 * Clase que representa un cliente del camping.
 * El cliente puede realizar reservas y tener tiendas.
 * 
 * @author Carla Terol
 */
public class Cliente extends Persona {
    private int edad;
    private ArrayList<Tienda> tiendas;
    private ArrayList<Reserva> reservas;

    public Cliente(String nombre, String dni, int edad) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.tiendas = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.role = 1; // Rol de CLIENTE
    }

    public Cliente(int id, String nombre, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.tiendas = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.role = 1; // Rol de CLIENTE
    }

    public Cliente(String nombre, String dni, int id, int edad, int telefono, String username, String password) {
        this.nombre = nombre;
        this.dni = dni;
        this.id = id;
        this.tlf = telefono;
        this.user = username;
        this.pass = password;
        this.edad = edad;
        this.tiendas = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.role = 1; // Rol de CLIENTE
    }

    // Getters
    public int getEdad() {
        return edad;
    }

    public ArrayList<Tienda> getTiendas() {
        return tiendas;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public boolean tieneReserva() {
        return !reservas.isEmpty();
    }

    public Reserva getReserva(int index) {
        if (index >= 0 && index < reservas.size()) {
            return reservas.get(index);
        }
        return null;
    }

    public Reserva getPrimeraReserva() {
        if (!reservas.isEmpty()) {
            return reservas.get(0);
        }
        return null;
    }

    // Setters
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setUser(String u) {
        this.user = u;
    }

    public void setPassword(String p) {
        this.pass = p;
    }

    public void setPhone(int telefono) {
        this.tlf = telefono;
    }

    // Operaciones con tiendas
    public void addTienda(Tienda t) {
        if (!tiendas.contains(t)) {
            tiendas.add(t);
        }
    }

    public void removeTienda(Tienda t) {
        tiendas.remove(t);
    }

    // Operaciones con reservas
    public void addReserva(Reserva r) {
        if (!reservas.contains(r)) {
            reservas.add(r);
        }
    }

    public void removeReserva(int index) {
        if (index >= 0 && index < reservas.size()) {
            reservas.remove(index);
        }
    }

    public void removeReserva(Reserva r) {
        reservas.remove(r);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                ", reservas=" + reservas.size() +
                ", tiendas=" + tiendas.size() +
                '}';
    }
}


