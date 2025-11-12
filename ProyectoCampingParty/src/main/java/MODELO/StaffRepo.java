package MODELO;

import java.util.ArrayList;
import java.util.List;

public class StaffRepo {

    private static final List<Staff> STAFFS = new ArrayList<>();

    static {
        // Usuarios de prueba
        STAFFS.add(new Staff("abel.staff", "1234", "Abel Saiz"));
        STAFFS.add(new Staff("sergio.op", "abcd", "Sergio Martín"));
        STAFFS.add(new Staff("carla.admin", "admin", "Carla Terol"));
    }

    /** Retorna el Staff si las credenciales son correctas, o null si no coincide */
    public static Staff autenticar(String usuario, String contrasena) {
        for (Staff s : STAFFS) {
            if (s.getUsuario().equalsIgnoreCase(usuario.trim()) &&
                s.getContrasena().equals(contrasena)) {
                return s;
            }
        }
        return null;
    }
}
