package MODELO;

import java.util.ArrayList;
import java.util.List;

public class Staff extends Persona{
    private static final List<Staff> STAFFS = new ArrayList<>();

    public Staff(String usuario, String contrasena, String nombre, String dni,int tlf){
        this.user = usuario;
        this.pass = contrasena;
        this.nombre = nombre;
        this.role = 0; // Asignar rol de STAFF
        this.dni = dni;
        this.tlf = tlf;
    }

    public void init(){
        // Usuarios de prueba
        STAFFS.add(new Staff("abelstaff@hotmail.com", "1234", "Abel Saiz", "53889931Z", 612233445));
        STAFFS.add(new Staff("sergiop@hotmail.com", "abcd", "Sergio Giménez", "12345678A", 698765432));
        STAFFS.add(new Staff("carlaadmin@hotmail.com", "admin", "Carla Terol", "87654321B", 677889900));
    }
    
     public static Staff autenticar(String usuario, String contrasena) {
        for (Staff s : STAFFS) {
            if (s.getUser().equalsIgnoreCase(usuario.trim()) &&
                s.getPass().equals(contrasena)) {
                return s;
            }
        }
        return null;
    }

}
