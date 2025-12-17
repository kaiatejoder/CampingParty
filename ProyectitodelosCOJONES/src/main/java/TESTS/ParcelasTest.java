package TESTS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import MODELO.Parcela;
import MODELO.Parcelas;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ParcelasTest {
    
    private Parcelas parcelas;
    private Parcela[] arrayParcelas;
    
    @BeforeEach
    public void setUp() {
        arrayParcelas = new Parcela[3];
        arrayParcelas[0] = new Parcela(1, 50.0f, true, 25.0f);
        arrayParcelas[1] = new Parcela(2, 30.0f, false, 15.0f);
        arrayParcelas[2] = new Parcela(3, 40.0f, true, 20.0f);
        
        parcelas = new Parcelas(arrayParcelas);
    }
    
    @Test
    public void testCrearParcelas() {
        Parcela[] obtenidas = parcelas.getParcelas();
        
        assertEquals(3, obtenidas.length);
        assertEquals(1, obtenidas[0].getId());
        assertEquals(2, obtenidas[1].getId());
        assertEquals(3, obtenidas[2].getId());
    }
    
    @Test
    public void testGetParcela() {
        Parcela parcela = parcelas.getParcela(1); // Índice 1 = Parcela 2
        
        assertEquals(2, parcela.getId());
        assertEquals(30.0f, parcela.getM2(), 0.001);
        assertFalse(parcela.hayLuz());
        assertEquals(15.0f, parcela.getPrecio(), 0.001);
    }
    
    @Test
    public void testSetParcela() {
        parcelas.setParcela(0, 60.0f, false, 30.0f);
        Parcela parcelaModificada = parcelas.getParcela(0);
        
        assertEquals(1, parcelaModificada.getId()); // ID se mantiene (index+1)
        assertEquals(60.0f, parcelaModificada.getM2(), 0.001);
        assertFalse(parcelaModificada.hayLuz());
        assertEquals(30.0f, parcelaModificada.getPrecio(), 0.001);
    }
    
    @Test
    public void testGetLibres() {
        boolean[] libres = parcelas.getLibres();
        
        assertEquals(3, libres.length);
        assertTrue(libres[0]);
        assertTrue(libres[1]);
        assertTrue(libres[2]);
        
        // Reservamos una parcela
        arrayParcelas[1].reservarParcela();
        libres = parcelas.getLibres();
        
        assertTrue(libres[0]);
        assertFalse(libres[1]); // Ya no está libre
        assertTrue(libres[2]);
    }
    
    @Test
    public void testGetParcelaString() {
        String[] info = parcelas.getParcelaString(0);
        
        assertEquals(4, info.length);
        assertEquals("1", info[0]); // ID
        assertEquals("50.0", info[1]); // m2
        assertEquals("true", info[2]); // luz
        assertEquals("25.0", info[3]); // precio
    }
    
    @Test
    public void testGetM2() {
        float m2 = parcelas.getM2(0);
        assertEquals(50.0f, m2, 0.001);
    }
    
    @Test
    public void testSetReservada() {
        assertTrue(arrayParcelas[0].isLibre());
        
        parcelas.setReservada(0);
        assertFalse(arrayParcelas[0].isLibre());
    }

    private void assertFalse(boolean libre) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void assertTrue(boolean libre) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void assertEquals(float f, float m2, double d) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void assertEquals(String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void assertEquals(int i, int length) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
