package TESTS;

import MODELO.Parcela;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ParcelaTest {
    
    private Parcela parcela;
    
    @BeforeEach
    public void setUp() {
        parcela = new Parcela(1, 50.0f, true, 25.0f);
    }
    
    @Test
    public void testCrearParcela() {
        assertEquals(1, parcela.getId());
        assertEquals(50.0f, parcela.getM2(), 0.001);
        assertTrue(parcela.hayLuz());
        assertEquals(25.0f, parcela.getPrecio(), 0.001);
        assertTrue(parcela.isLibre());
    }
    
    @Test
    public void testReservarParcela() {
        parcela.reservarParcela();
        assertFalse(parcela.isLibre());
    }
    
    @Test
    public void testLiberarParcela() {
        parcela.reservarParcela();
        assertFalse(parcela.isLibre());
        
        parcela.liberarParcela();
        assertTrue(parcela.isLibre());
    }
    
    @Test
    public void testSetters() {
        parcela.setM2(60.0f);
        parcela.setLuz(false);
        parcela.setPrecio(30.0f);
        
        assertEquals(60.0f, parcela.getM2(), 0.001);
        assertFalse(parcela.hayLuz());
        assertEquals(30.0f, parcela.getPrecio(), 0.001);
    }
    
    @Test
    public void testParcelaParserLibre() {
        String expected = "1,50.0,true,25.0,false,false\n";
        assertEquals(expected, parcela.ParcelaParser());
    }
    
    @Test
    public void testToString() {
        String result = parcela.toString();
        assertTrue(result.contains("Parcela 1"));
        assertTrue(result.contains("50.0m2"));
        assertTrue(result.contains("Luz: true"));
        assertTrue(result.contains("Precio: 25.0"));
        assertTrue(result.contains("Libre: Sí"));
    }
    
    @Test
    public void testParcelaOcupada() {
        Parcela parcelaOcupada = new Parcela(2, 30.0f, false, 15.0f);
        parcelaOcupada.reservarParcela();
        
        assertFalse(parcelaOcupada.isLibre());
        String parserResult = parcelaOcupada.ParcelaParser();
        assertTrue(parserResult.contains("false,true"));
    }

    private void assertTrue(boolean contains) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void assertFalse(boolean libre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void assertEquals(float f, float m2, double d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void assertEquals(int i, int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void assertEquals(String expected, String ParcelaParser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
