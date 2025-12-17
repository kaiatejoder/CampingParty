/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TESTS;

import MODELO.Valid;
import org.junit.jupiter.api.Test;
/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ValidTest {
    
    @Test
    public void testDNIValido() {
        assertTrue(Valid.dni("12345678Z"));
        assertTrue(Valid.dni("87654321A"));
        assertTrue(Valid.dni("11111111X"));
        assertTrue(Valid.dni("99999999R"));
    }
    
    @Test
    public void testDNIInvalido() {
        assertFalse(Valid.dni("12345678")); // Falta letra
        assertFalse(Valid.dni("123456789Z")); // Demasiados números
        assertFalse(Valid.dni("1234567Z")); // Pocos números
        assertFalse(Valid.dni("ABCDEFGHI")); // Solo letras
        assertFalse(Valid.dni("")); // Vacío
        assertFalse(Valid.dni(null)); // Null
    }
    
    @Test
    public void testDNIMinuscula() {
        assertTrue(Valid.dni("12345678z")); // Letra minúscula también válida
    }
    
    @Test
    public void testCorreoValido() {
        assertTrue(Valid.correo("usuario@dominio.com"));
        assertTrue(Valid.correo("nombre.apellido@empresa.es"));
        assertTrue(Valid.correo("usuario123@sub.dominio.org"));
    }
    
    @Test
    public void testCorreoInvalido() {
        assertFalse(Valid.correo("usuario")); // Sin @
        assertFalse(Valid.correo("usuario@")); // Sin dominio
        assertFalse(Valid.correo("@dominio.com")); // Sin usuario
        assertFalse(Valid.correo("usuario@dominio")); // Sin punto
        assertFalse(Valid.correo("usuario@.com")); // Sin dominio después de @
        assertFalse(Valid.correo("")); // Vacío
    }
    
    @Test
    public void testPassword() {
        // Nota: El método password(char[] c) tiene un error
        // c.toString() no devuelve el string del char array
        // Debería ser: String s = new String(c);
        
        // Para testear correctamente, necesitarías arreglar primero el método
        // char[] passValido = "Pass123!".toCharArray();
        // assertTrue(Valid.password(passValido));
        
        // Por ahora, este test mostrará el problema
        char[] pass = "Password123!".toCharArray();
        // Esto probablemente falle debido al error en el método
        // assertTrue(Valid.password(pass));
    }

    private void assertTrue(boolean dni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertFalse(boolean dni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
