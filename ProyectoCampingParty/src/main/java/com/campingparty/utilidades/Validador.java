package com.campingparty.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * CLASE DE VALIDACIÓN - Contiene métodos estáticos para validar datos.
 * 
 * Responsabilidades:
 * - Validar formato de email
 * - Validar DNI
 * - Validar edades
 * - Validar fechas
 * - Etc.
 * 
 * @author Carla Terol
 */
public class Validador {

    /**
     * Valida un email
     */
    public static boolean esEmailValido(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    /**
     * Valida un DNI español (formato básico)
     */
    public static boolean esDNIValido(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return false;
        }
        // Formato: 8 dígitos + 1 letra
        String dniRegex = "^[0-9]{8}[A-Z]$";
        return Pattern.compile(dniRegex).matcher(dni.toUpperCase()).matches();
    }

    /**
     * Valida que una edad sea válida
     */
    public static boolean esEdadValida(int edad) {
        return edad >= 0 && edad <= 150;
    }

    /**
     * Valida que una edad sea mayor de edad (>= 18)
     */
    public static boolean esMayorDeEdad(int edad) {
        return edad >= 18;
    }

    /**
     * Valida que un teléfono sea válido
     */
    public static boolean esTelefonoValido(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return true; // Teléfono es opcional
        }
        String telefonoRegex = "^[0-9]{9}$";
        return Pattern.compile(telefonoRegex).matcher(telefono).matches();
    }

    /**
     * Valida que una contraseña tenga una longitud mínima
     */
    public static boolean esContraseñaValida(String contrasena) {
        return contrasena != null && contrasena.length() >= 4;
    }

    /**
     * Valida que un nombre no esté vacío
     */
    public static boolean esNombreValido(String nombre) {
        return nombre != null && !nombre.trim().isEmpty() && nombre.length() >= 3;
    }

    /**
     * Valida que dos fechas sean válidas (inicio < fin)
     */
    public static boolean sonFechasValidas(Date fechaInicio, Date fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            return false;
        }
        return fechaInicio.before(fechaFin);
    }

    /**
     * Valida que una fecha no esté en el pasado
     */
    public static boolean esDateNoEnPasado(Date fecha) {
        if (fecha == null) {
            return false;
        }
        return fecha.after(new Date());
    }

    /**
     * Valida que un número de metros cuadrados sea válido
     */
    public static boolean sonMetrosCuadradosValidos(float m2) {
        return m2 > 0 && m2 <= 10000;
    }

    /**
     * Valida que un precio sea válido
     */
    public static boolean esPrecioValido(float precio) {
        return precio >= 0 && precio <= 10000;
    }

    /**
     * Método general de validación para registros de cliente
     */
    public static boolean esRegistroClienteValido(String nombre, String dni, int edad, 
                                                    String email, String contrasena) {
        return esNombreValido(nombre) &&
               esDNIValido(dni) &&
               esEdadValida(edad) &&
               esMayorDeEdad(edad) &&
               esEmailValido(email) &&
               esContraseñaValida(contrasena);
    }

    /**
     * Convierte un valor a boolean de forma segura
     */
    public static boolean toBoolean(String valor) {
        return valor != null && (valor.equals("1") || valor.equalsIgnoreCase("true"));
    }

    /**
     * Convierte un valor a int de forma segura
     */
    public static int toInt(String valor, int porDefecto) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return porDefecto;
        }
    }

    /**
     * Convierte un valor a float de forma segura
     */
    public static float toFloat(String valor, float porDefecto) {
        try {
            return Float.parseFloat(valor);
        } catch (NumberFormatException e) {
            return porDefecto;
        }
    }
}
