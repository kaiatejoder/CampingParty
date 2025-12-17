/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TESTS;

import MODELO.Cliente;
import MODELO.Reserva;
import MODELO.Reservas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ReservasTest {
    
    private Reservas reservas;
    private Reserva reserva1;
    private Reserva reserva2;
    private Cliente cliente;
    
    @BeforeEach
    public void setUp() {
        reservas = new Reservas();
        cliente = new Cliente("Juan Pérez", "12345678A", 30);
        
        Date hoy = new Date();
        Date manana = new Date(System.currentTimeMillis() + 86400000);
        
        reserva1 = new Reserva(hoy, manana, cliente);
        reserva2 = new Reserva(manana, new Date(System.currentTimeMillis() + 86400000 * 2), cliente);
    }
    
    @Test
    public void testCrearReservas() {
        assertNotNull(reservas.getReservas());
        assertEquals(0, reservas.getReservas().size());
    }
    
    @Test
    public void testAddReserva() {
        reservas.addReserva(reserva1);
        assertEquals(1, reservas.getReservas().size());
        assertEquals(reserva1, reservas.getReserva(0));
        
        reservas.addReserva(reserva2);
        assertEquals(2, reservas.getReservas().size());
        assertEquals(reserva2, reservas.getReserva(1));
    }
    
    @Test
    public void testCancelarReserva() {
        reservas.addReserva(reserva1);
        reservas.addReserva(reserva2);
        assertEquals(2, reservas.getReservas().size());
        
        reservas.cancelarReserva(0);
        assertEquals(1, reservas.getReservas().size());
        assertEquals(reserva2, reservas.getReserva(0));
    }
    
    @Test
    public void testFinReserva() {
        reservas.addReserva(reserva1);
        assertEquals(1, reservas.getReservas().size());
        
        reservas.finReserva(0);
        assertEquals(0, reservas.getReservas().size());
    }
    
    @Test
    public void testGetReserva() {
        reservas.addReserva(reserva1);
        Reserva obtenida = reservas.getReserva(0);
        
        assertEquals(reserva1, obtenida);
        assertEquals(cliente, obtenida.getCliente());
    }

    private void assertEquals(Reserva reserva1, Reserva obtenida) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(int i, int size) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
