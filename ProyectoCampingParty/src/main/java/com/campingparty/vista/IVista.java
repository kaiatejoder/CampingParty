package com.campingparty.vista;

/**
 * INTERFAZ BASE PARA VISTAS
 * 
 * Define el contrato que todas las vistas deben cumplir.
 * Las vistas son responsables únicamente de:
 * - Mostrar datos (UI)
 * - Captar eventos del usuario
 * - NO contienen lógica de negocio
 * 
 * @author Carla Terol
 */
public interface IVista {
    /**
     * Muestra un mensaje al usuario
     */
    void mostrarMensaje(String mensaje);

    /**
     * Muestra un error
     */
    void mostrarError(String error);

    /**
     * Limpia los campos de la interfaz
     */
    void limpiar();

    /**
     * Muestra la vista
     */
    void mostrar();

    /**
     * Oculta la vista
     */
    void ocultar();
}


