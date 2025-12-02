package Modelo;

/**
 *
 * @author Sergio Gimenez Gomez
 */
import java.util.ArrayList;
import java.util.List;

public class Staff extends Persona{
    private static final List<Staff> STAFFS = new ArrayList<>();
    private static boolean initialized = false;

    public Staff(String usuario, String contrasena, String nombre, String dni, int tlf){
        super(usuario, contrasena, nombre, dni, tlf, 0); // role = 0 para STAFF
    }

    public static void init(){
        if (!initialized) {
            // Usuarios de prueba
            STAFFS.add(new Staff("abelstaff@hotmail.com", "1234", "Abel Saiz", "53889931Z", 612233445));
            STAFFS.add(new Staff("sergiop@hotmail.com", "abcd", "Sergio Giménez", "12345678A", 698765432));
            STAFFS.add(new Staff("carlaadmin@hotmail.com", "admin", "Carla Terol", "87654321B", 677889900));
            initialized = true;
        }
    }
    
    public static Staff autenticar(String usuario, String contrasena) {
        // Asegurar que los staffs estén inicializados
        if (!initialized) {
            init();
        }
        
        for (Staff s : STAFFS) {
            if (s.getUser().equalsIgnoreCase(usuario.trim()) &&
                s.getPass().equals(contrasena)) {
                return s;
            }
        }
        return null;
    }
    
    public static List<Staff> getStaffs() {
        if (!initialized) {
            init();
        }
        return new ArrayList<>(STAFFS); // Devolver copia para proteger la lista original
    }
}