package com.isii.campingmvc.model;

public class Cliente {
    private final String nombre;
    private final String dni;

    public Cliente(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    @Override
    public String toString() { return nombre + " (" + dni + ")"; }
}
