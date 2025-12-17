package TESTS;

import MODELO.Cliente;
import MODELO.Reserva;
import MODELO.Tienda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ClienteTest {
    
    private Cliente cliente;
    private Tienda tienda;
    private Reserva reserva;
    
    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Juan Pérez", "12345678A", 30, 25, 612345678, "juan@email.com", "Password123");
        tienda = new Tienda("Tienda Familiar", 15.0f);
        reserva = new Reserva(new Date(), new Date(System.currentTimeMillis() + 86400000), cliente);
    }
    
    @Test
    public void testCrearCliente() {
        assertEquals("Juan Pérez", cliente.getNombre());
        assertEquals("12345678A", cliente.getDni());
        assertEquals(30, cliente.getEdad());
        assertEquals(25, cliente.getId());
        assertEquals(612345678, cliente.getTlf());
        assertEquals("juan@email.com", cliente.getUser());
        assertEquals("Password123", cliente.getPass());
    }
    
    @Test
    public void testConstructorSimplificado() {
        Cliente clienteSimple = new Cliente("Ana López", "87654321B", 25);
        
        assertEquals("Ana López", clienteSimple.getNombre());
        assertEquals("87654321B", clienteSimple.getDni());
        assertEquals(25, clienteSimple.getEdad());
        assertNotNull(clienteSimple.getReservas());
        assertNotNull(clienteSimple.getTiendas());
    }
    
    @Test
    public void testAgregarTienda() {
        cliente.addTienda(tienda);
        ArrayList<Tienda> tiendas = cliente.getTiendas();
        
        assertEquals(1, tiendas.size());
        assertEquals("Tienda Familiar", tiendas.get(0).getNombre());
        assertEquals(15.0f, tiendas.get(0).getM2(), 0.001);
    }
    
    @Test
    public void testAgregarReserva() {
        cliente.addReserva(reserva);
        
        assertTrue(cliente.tieneReserva());
    }
    
    @Test
    public void testTieneReservaSinReservas() {
        assertFalse(cliente.tieneReserva());
    }
    
    @Test
    public void testBorrarReserva() {
        cliente.addReserva(reserva);
        assertTrue(cliente.tieneReserva());
        
        cliente.borrarReserva(0);
        assertFalse(cliente.tieneReserva());
    }
    
    @Test
    public void testSetters() {
        cliente.setUser("nuevo@email.com");
        cliente.setPass("NuevaPass123");
        cliente.setTlf(699999999);
        cliente.setPhone(611111111);
        
        assertEquals("nuevo@email.com", cliente.getUser());
        assertEquals("NuevaPass123", cliente.getPass());
        assertEquals(611111111, cliente.getTlf()); // setPhone sobrescribe setTlf
    }
    
    @Test
    public void testToString() {
        String expected = "12345678A;Juan Pérez;30;612345678;juan@email.com;Password123\n";
        assertEquals(expected, cliente.toString());
    }
    
    @Test
    public void testGetReserva() {
        cliente.addReserva(reserva);
        Reserva reservaObtenida = cliente.getReserva();
        
        assertNotNull(reservaObtenida);
        assertEquals(cliente, reservaObtenida.getCliente());
    }

    private void assertEquals(String juan_Pérez, String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(int i, int edad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertNotNull(Reserva reservaObtenida) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertFalse(boolean tieneReserva) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertTrue(boolean tieneReserva) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(float f, float m2, double d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
