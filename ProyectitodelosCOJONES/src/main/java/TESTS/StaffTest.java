/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TESTS;

import MODELO.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class StaffTest {
    
    private Staff staff;
    
    @BeforeEach
    public void setUp() {
        staff = new Staff("admin@camping.com", "admin123", "Admin User", "12345678A", 612345678);
    }
    
    @Test
    public void testCrearStaff() {
        assertEquals("admin@camping.com", staff.getUser());
        assertEquals("admin123", staff.getPass());
        assertEquals("Admin User", staff.getNombre());
        assertEquals("12345678A", staff.getDni());
        assertEquals(612345678, staff.getTlf());
        assertEquals("STAFF", staff.getRole()); // role = 0 -> "STAFF"
    }
    
    @Test
    public void testAutenticacionExitosa() {
        // Primero inicializamos los staffs de prueba
        Staff staffTest = new Staff("test@test.com", "test123", "Test User", "11111111A", 600000000);
        staffTest.init(); // Esto inicializa la lista STAFFS
        
        Staff autenticado = Staff.autenticar("abelstaff@hotmail.com", "1234");
        
        assertNotNull(autenticado);
        assertEquals("Abel Saiz", autenticado.getNombre());
        assertEquals("53889931Z", autenticado.getDni());
    }
    
    @Test
    public void testAutenticacionFallida() {
        Staff staffTest = new Staff("test@test.com", "test123", "Test User", "11111111A", 600000000);
        staffTest.init();
        
        // Usuario incorrecto
        assertNull(Staff.autenticar("usuarioinexistente", "1234"));
        
        // Contraseña incorrecta
        assertNull(Staff.autenticar("abelstaff@hotmail.com", "contrasenaincorrecta"));
        
        // Ambos incorrectos
        assertNull(Staff.autenticar("usuario", "contrasena"));
    }
    
    @Test
    public void testAutenticacionCaseInsensitiveUser() {
        Staff staffTest = new Staff("test@test.com", "test123", "Test User", "11111111A", 600000000);
        staffTest.init();
        
        Staff autenticado = Staff.autenticar("ABELSTAFF@HOTMAIL.COM", "1234");
        
        assertNotNull(autenticado); // equalsIgnoreCase en la autenticación
        assertEquals("Abel Saiz", autenticado.getNombre());
    }
    
    @Test
    public void testSetPassword() {
        staff.setPass("nuevaContrasena456");
        assertEquals("nuevaContrasena456", staff.getPass());
    }

    private void assertEquals(String admin_User, String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(int i, int tlf) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertNotNull(Staff autenticado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertNull(Staff autenticar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
