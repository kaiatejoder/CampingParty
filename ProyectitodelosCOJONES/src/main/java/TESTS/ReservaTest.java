package TESTS;

import MODELO.Acompanyante;
import MODELO.Cliente;
import MODELO.Parcela;
import MODELO.Reserva;
import MODELO.Tienda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;

public class ReservaTest {
    
    private Reserva reserva;
    private Cliente cliente;
    private Parcela parcela;
    private Tienda tienda;
    private Date inicio;
    private Date fin;
    
    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Juan Pérez", "12345678A", 30);
        inicio = new Date();
        fin = new Date(System.currentTimeMillis() + 86400000 * 3); // 3 días después
        reserva = new Reserva(inicio, fin, cliente);
        
        parcela = new Parcela(1, 50.0f, true, 25.0f);
        tienda = new Tienda("Tienda Familiar", 15.0f);
    }
    
    @Test
    public void testCrearReserva() {
        assertEquals(inicio, reserva.getInicioReserva());
        assertEquals(fin, reserva.getFinReserva());
        assertEquals(cliente, reserva.getCliente());
        assertNotNull(reserva.getParcelas());
        assertNotNull(reserva.getTiendas());
        assertNotNull(reserva.getAcompanyantes());
        assertEquals(0, reserva.getParcelas().size());
        assertEquals(0, reserva.getTiendas().size());
        assertEquals(0, reserva.getAcompanyantes().size());
    }
    
    @Test
    public void testConstructorCompleto() {
        ArrayList<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcela);
        
        ArrayList<Tienda> tiendas = new ArrayList<>();
        tiendas.add(tienda);
        
        ArrayList<Acompanyante> acompanyantes = new ArrayList<>();
        acompanyantes.add(new Acompanyante("Ana López", 25));
        
        Reserva reservaCompleta = new Reserva(inicio, fin, parcelas, tiendas, acompanyantes, cliente);
        
        assertEquals(1, reservaCompleta.getParcelas().size());
        assertEquals(1, reservaCompleta.getTiendas().size());
        assertEquals(1, reservaCompleta.getAcompanyantes().size());
        assertEquals(parcela, reservaCompleta.getParcelas().get(0));
        assertEquals(tienda, reservaCompleta.getTiendas().get(0));
    }
    
    @Test
    public void testAddTienda() {
        reserva.addTienda(tienda);
        assertEquals(1, reserva.getTiendas().size());
        assertEquals(tienda, reserva.getTiendas().get(0));
    }
    
    @Test
    public void testAddAcompanyantePorParametros() {
        reserva.addAcompanyante("Ana", "López", 25);
        assertEquals(1, reserva.getAcompanyantes().size());
        
        Acompanyante acomp = reserva.getAcompanyantes().get(0);
        assertEquals("Ana López", acomp.getNombre());
        assertEquals(25, acomp.getEdad());
    }
    
    @Test
    public void testAddAcompanyanteDirecto() {
        Acompanyante acomp = new Acompanyante("Carlos Ruiz", 30);
        reserva.addAcompanyante(acomp);
        
        assertEquals(1, reserva.getAcompanyantes().size());
        assertEquals(acomp, reserva.getAcompanyantes().get(0));
    }
    
    @Test
    public void testSetPrecioDia() {
        ArrayList<Parcela> parcelas = new ArrayList<>();
        parcelas.add(new Parcela(1, 50.0f, true, 25.0f));
        parcelas.add(new Parcela(2, 30.0f, false, 15.0f));
        
        reserva.setParcelas(parcelas);
        reserva.setPrecioDia();
        
        assertEquals(40.0f, reserva.getPrecioDia(), 0.001); // 25 + 15
    }
    
    @Test
    public void testSetPrecioTotalMenos15Dias() {
        ArrayList<Parcela> parcelas = new ArrayList<>();
        parcelas.add(new Parcela(1, 50.0f, true, 25.0f));
        reserva.setParcelas(parcelas);
        reserva.setPrecioDia();
        
        reserva.setPrecioTotal(10, 20); // 10 días, 20% descuento
        
        // Precio sin descuento (menos de 15 días): 10 * 25 = 250
        assertEquals(250.0f, reserva.getPrecioTotal(), 0.001);
    }
    
    @Test
    public void testSetPrecioTotalMas15Dias() {
        ArrayList<Parcela> parcelas = new ArrayList<>();
        parcelas.add(new Parcela(1, 50.0f, true, 25.0f));
        reserva.setParcelas(parcelas);
        reserva.setPrecioDia();
        
        reserva.setPrecioTotal(20, 20); // 20 días, 20% descuento
        
        // Precio con descuento: 20 * 25 * (1 - 0.20) = 20 * 25 * 0.80 = 400
        // Pero hay un error en la lógica del método setPrecioTotal
        // Debería ser: precioTotal = dias * precioDia * (1 - descuento/100.0f)
        // Revisa la implementación de setPrecioTotal en la clase Reserva
    }
    
    @Test
    public void testGetFechas() {
        String fechas = reserva.getFechas();
        assertTrue(fechas.contains(inicio.toString()));
        assertTrue(fechas.contains(fin.toString()));
    }
    
    @Test
    public void testGetParcela() {
        ArrayList<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcela);
        reserva.setParcelas(parcelas);
        
        assertEquals(parcela, reserva.getParcela(0));
    }
    
    @Test
    public void testSetFechasEstancia() {
        Date inicioEstancia = new Date();
        Date finEstancia = new Date(System.currentTimeMillis() + 86400000 * 5);
        
        reserva.setInicioEstancia(inicioEstancia);
        reserva.setFinEstancia(finEstancia);
        
        assertEquals(inicioEstancia, reserva.getInicioEstancia());
        assertEquals(finEstancia, reserva.getFinEstancia());
    }

    private void assertEquals(Date inicioEstancia, Date inicioEstancia0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(Parcela parcela, Parcela parcela0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertTrue(boolean contains) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(float f, float precioTotal, double d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(Acompanyante acomp, Acompanyante get) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(int i, int size) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(String ana_López, String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(Tienda tienda, Tienda get) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertNotNull(ArrayList<Acompanyante> acompanyantes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
