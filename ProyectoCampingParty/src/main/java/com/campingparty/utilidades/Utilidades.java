package com.campingparty.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CLASE DE UTILIDADES GENERALES
 * 
 * Contiene métodos útiles para la aplicación.
 * 
 * @author Carla Terol
 */
public class Utilidades {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    /**
     * Formatea una fecha
     */
    public static String formatearFecha(Date fecha) {
        if (fecha == null) {
            return "";
        }
        return dateFormat.format(fecha);
    }

    /**
     * Formatea una fecha y hora
     */
    public static String formatearFechaHora(Date fecha) {
        if (fecha == null) {
            return "";
        }
        return dateTimeFormat.format(fecha);
    }

    /**
     * Calcula días entre dos fechas
     */
    public static int calcularDias(Date fechaInicio, Date fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            return 0;
        }
        long diferencia = fechaFin.getTime() - fechaInicio.getTime();
        return (int) (diferencia / (1000 * 60 * 60 * 24)) + 1;
    }

    /**
     * Formatea un precio en euros
     */
    public static String formatearPrecio(float precio) {
        return String.format("%.2f€", precio);
    }

    /**
     * Centra un texto en un ancho determinado
     */
    public static String centrarTexto(String texto, int ancho) {
        if (texto == null) {
            texto = "";
        }
        if (texto.length() >= ancho) {
            return texto;
        }
        int padding = (ancho - texto.length()) / 2;
        return " ".repeat(padding) + texto;
    }

    /**
     * Capitaliza la primera letra de cada palabra
     */
    public static String capitalizarNombre(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        StringBuilder result = new StringBuilder();
        boolean capitalizar = true;
        
        for (char c : texto.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizar = true;
                result.append(c);
            } else if (capitalizar) {
                result.append(Character.toUpperCase(c));
                capitalizar = false;
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }

    /**
     * Obtiene el nombre completo formateado
     */
    public static String obtenerNombreCompleto(String nombre, String apellido1, String apellido2) {
        StringBuilder sb = new StringBuilder();
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            sb.append(nombre.trim());
        }
        if (apellido1 != null && !apellido1.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(apellido1.trim());
        }
        if (apellido2 != null && !apellido2.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(apellido2.trim());
        }
        
        return sb.toString();
    }

    /**
     * Log sencillo
     */
    public static void log(String mensaje) {
        System.out.println("[LOG] " + mensaje);
    }

    /**
     * Log de error
     */
    public static void logError(String mensaje) {
        System.err.println("[ERROR] " + mensaje);
    }

    /**
     * Log de éxito
     */
    public static void logExito(String mensaje) {
        System.out.println("[✓] " + mensaje);
    }
}
