package com.campingparty.modelo;



/**
 * Clase que representa un miembro del staff del camping.
 * Hereda de Persona y contiene métodos específicos del staff.
 * 
 * @author Carla Terol
 */
public class Staff extends Persona {

    public Staff(String usuario, String contrasena, String nombre, String dni, int tlf) {
        this.user = usuario;
        this.pass = contrasena;
        this.nombre = nombre;
        this.role = 2; // Rol de STAFF
        this.dni = dni;
        this.tlf = tlf;
    }

    public Staff(String nombre, String dni, int id, String usuario, String contrasena) {
        this.user = usuario;
        this.pass = contrasena;
        this.nombre = nombre;
        this.role = 2; // Rol de STAFF
        this.dni = dni;
        this.id = id;
    }
}


