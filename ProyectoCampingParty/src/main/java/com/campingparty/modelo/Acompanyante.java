package com.campingparty.modelo;

/**
 * Clase que representa un acompañante en una reserva.
 * Los acompañantes son personas que acompañan al cliente principal.
 * 
 * @author Carla Terol
 */
public class Acompanyante extends Persona {
    private int edad;

    public Acompanyante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.role = 2; // Rol de ACOMPAÑANTE
    }

    public Acompanyante(String nombre, String dni, int edad) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.role = 2; // Rol de ACOMPAÑANTE
    }

    // Getters
    public int getEdad() {
        return edad;
    }

    // Setters
    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}


