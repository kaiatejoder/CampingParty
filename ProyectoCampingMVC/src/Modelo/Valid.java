package Modelo;

/**
 *
 * @author Sergio Gimenez Gomez
 */
public class Valid {
    public static boolean dni (String s){
        if (s == null || s.length() != 9) {
            return false;
        }
        
        // Verificar que los primeros 8 caracteres son dígitos
        String numeros = s.substring(0, 8);
        if (!numeros.matches("\\d{8}")) {
            return false;
        }
        
        // Verificar que el último carácter es una letra
        char letra = s.charAt(8);
        return Character.isLetter(letra);
    }
    
    public static boolean correo(String s){
        if(s == null || s.trim().isEmpty()) {
            return false;
        }
        
        // Patrón más robusto para validar email
        return s.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
    
    public static boolean password(char[] c){
        if (c == null || c.length < 8) {
            return false;
        }
        
        String s = new String(c); // ✅ CORREGIDO: Conversión correcta de char[] a String
        
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneEspecial = false;
        
        for (char caracter : c) {
            if (Character.isUpperCase(caracter)) tieneMayuscula = true;
            if (Character.isLowerCase(caracter)) tieneMinuscula = true;
            if (Character.isDigit(caracter)) tieneNumero = true;
            if (".,-=_*/?¿!@#$%^&*()".indexOf(caracter) >= 0) tieneEspecial = true;
        }
        
        return tieneMayuscula && tieneMinuscula && tieneNumero && tieneEspecial;
    }
    
    // Método sobrecargado para String
    public static boolean password(String s) {
        return password(s.toCharArray());
    }
}