/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TESTS;

import MODELO.Actividad;
import MODELO.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ActividadTest {
    
    private Actividad actividad;
    private Cliente cliente;
    private Date fecha;
    
    @BeforeEach
    public void setUp() {
        fecha = new Date(System.currentTimeMillis() + 86400000); // Mañana
        actividad = new Actividad(1, fecha, 0, 10, "Futbol", "Partido de futbol", "Piscina");
        cliente = new Cliente("Juan Pérez", "12345678A", 30);
    }
    
    @Test
    public void testCrearActividad() {
        assertEquals(1, actividad.getIdActividad());
        assertEquals("Futbol", actividad.getTitulo());
        assertEquals("Partido de futbol", actividad.getDesc());
        assertEquals("Piscina", actividad.getLoc());
        assertEquals(10, actividad.getMaxParticipantes());
        assertEquals(0, actividad.getTipo("Piscina"));
        assertNotNull(actividad.getFechaHora());
        assertEquals(0, actividad.getParticipantes().size());
        assertNull(actividad.getGanador());
    }
    
    @Test
    public void testAgregarParticipanteExitoso() {
        assertTrue(actividad.agregarParticipante(cliente));
        assertEquals(1, actividad.getParticipantes().size());
        assertEquals(cliente, actividad.getParticipantes().get(0));
    }
    
    @Test
    public void testAgregarParticipanteLimiteMaximo() {
        for (int i = 0; i < 10; i++) {
            Cliente nuevoCliente = new Cliente("Cliente " + i, "DNI" + i, 20 + i);
            assertTrue(actividad.agregarParticipante(nuevoCliente));
        }
        
        assertEquals(10, actividad.getParticipantes().size());
        
        // Intentar agregar uno más debería fallar
        Cliente clienteExtra = new Cliente("Extra", "DNIExtra", 25);
        assertFalse(actividad.agregarParticipante(clienteExtra));
        assertEquals(10, actividad.getParticipantes().size());
    }
    
    @Test
    public void testEliminarParticipante() {
        actividad.agregarParticipante(cliente);
        assertEquals(1, actividad.getParticipantes().size());
        
        assertTrue(actividad.eliminarParticipante(cliente));
        assertEquals(0, actividad.getParticipantes().size());
    }
    
    @Test
    public void testEliminarParticipanteNoExistente() {
        Cliente otroCliente = new Cliente("Otro", "87654321B", 25);
        assertFalse(actividad.eliminarParticipante(otroCliente));
    }
    
    @Test
    public void testSetLocPorTipo() {
        actividad.setLoc(0); // Piscina
        assertEquals("Piscina", actividad.getLoc());
        
        actividad.setLoc(1); // Fronton
        assertEquals("Fronton", actividad.getLoc());
        
        actividad.setLoc(2); // Club Social
        assertEquals("Club Social", actividad.getLoc());
    }
    
    @Test
    public void testSetGanador() {
        actividad.setGanador(cliente);
        assertEquals(cliente, actividad.getGanador());
    }
    
    @Test
    public void testSetters() {
        actividad.setTitulo("Nuevo título");
        actividad.setDesc("Nueva descripción");
        actividad.setLoc("Nueva ubicación");
        actividad.setMaxParticipantes(20);
        
        assertEquals("Nuevo título", actividad.getTitulo());
        assertEquals("Nueva descripción", actividad.getDesc());
        assertEquals("Nueva ubicación", actividad.getLoc());
        assertEquals(20, actividad.getMaxParticipantes());
    }
    
    @Test
    public void testToString() {
        String result = actividad.toString();
        assertTrue(result.contains("0 - ")); // Tipo
        assertTrue(result.contains("(0/10)")); // Participantes
    }

    private void assertEquals(int i, int idActividad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertNotNull(Date fechaHora) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertNull(Cliente ganador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertTrue(boolean agregarParticipante) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(Cliente cliente, Cliente get) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertFalse(boolean agregarParticipante) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
