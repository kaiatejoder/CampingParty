package com.campingparty.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un miembro del staff del camping.
 * Hereda de Persona y contiene métodos específicos del staff.
 * 
 * @author Carla Terol
 */
public class Staff extends Persona {
    private static final List<Staff> STAFFS = new ArrayList<>();

    public Staff(String usuario, String contrasena, String nombre, String dni, int tlf) {
        this.user = usuario;
        this.pass = contrasena;
        this.nombre = nombre;
        this.role = 0; // Rol de STAFF
        this.dni = dni;
        this.tlf = tlf;
    }

    public Staff(String usuario, String contrasena, String nombre, String dni, int tlf, int id) {
        this.user = usuario;
        this.pass = contrasena;
        this.nombre = nombre;
        this.role = 0; // Rol de STAFF
        this.dni = dni;
        this.tlf = tlf;
        this.id = id;
    }

    /**
     * Inicializa el sistema con usuarios de prueba
     */
    public static void init() {
        STAFFS.clear();
        // Usuarios de prueba
        STAFFS.add(new Staff("abelstaff@hotmail.com", "1234", "Abel Saiz", "53889931Z", 612233445));
        STAFFS.add(new Staff("sergiop@hotmail.com", "abcd", "Sergio Giménez", "12345678A", 698765432));
        STAFFS.add(new Staff("carlaadmin@hotmail.com", "admin", "Carla Terol", "87654321B", 677889900));
    }

    /**
     * Autentica un usuario del staff
     * @param usuario El usuario
     * @param contrasena La contraseña
     * @return El staff si es válido, null si no
     */
    public static Staff autenticar(String usuario, String contrasena) {
        for (Staff s : STAFFS) {
            if (s.getUser().equalsIgnoreCase(usuario.trim()) &&
                s.getPass().equals(contrasena)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Obtiene todos los staffs registrados
     */
    public static List<Staff> getAll() {
        return new ArrayList<>(STAFFS);
    }

    /**
     * Añade un nuevo staff
     */
    public static void addStaff(Staff staff) {
        if (!STAFFS.contains(staff)) {
            STAFFS.add(staff);
        }
    }
}


