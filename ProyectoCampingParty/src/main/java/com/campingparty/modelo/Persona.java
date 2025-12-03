package com.campingparty.modelo;

/**
 * Clase base que representa una persona en el sistema.
 * Puede ser un Staff, Cliente o Acompañante.
 * Contiene atributos básicos como usuario, contraseña, nombre, DNI y rol.
 * 
 * @author Carla Terol
 */
public class Persona {
    protected String user;
    protected String pass;
    protected String nombre;
    protected String dni;
    protected int id;
    protected int tlf;
    protected int role;
    
    public static final String[] TIPOS_ROLE = {
        "STAFF",
        "CLIENTE",
        "ACOMPANYANTE"
    };

    public Persona(String usuario, String contrasena, String nombre, String dni, int tlf, int role, int id) {
        this.user = usuario;
        this.pass = contrasena;
        this.nombre = nombre;
        this.tlf = tlf;
        this.dni = dni;
        this.role = role;
        this.id = id;
    }

    public Persona(String usuario, String contrasena, String nombre, String dni, int tlf) {
        this.user = usuario;
        this.pass = contrasena;
        this.nombre = nombre;
        this.tlf = tlf;
        this.dni = dni;
    }

    public Persona() {
    }

    // Getters
    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getRole() {
        if (role >= 0 && role < TIPOS_ROLE.length) {
            return TIPOS_ROLE[role];
        }
        return "DESCONOCIDO";
    }

    public int getTlf() {
        return tlf;
    }

    public int getId() {
        return id;
    }

    public int getRoleId() {
        return role;
    }

    // Setters
    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTlf(int tlf) {
        this.tlf = tlf;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}


