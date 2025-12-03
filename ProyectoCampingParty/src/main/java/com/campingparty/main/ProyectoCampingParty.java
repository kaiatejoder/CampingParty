package com.campingparty.main;

import com.campingparty.controlador.ControladorPrincipal;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * PUNTO DE ENTRADA DE LA APLICACIÓN
 * 
 * Esta es la clase main que inicia toda la aplicación.
 * 
 * Flujo:
 * 1. Configura el look and feel (FlatLightLaf)
 * 2. Instancia el ControladorPrincipal (Singleton)
 * 3. Llama a iniciar() para mostrar la vista de bienvenida
 * 4. El controlador gestiona toda la aplicación desde aquí
 * 
 * ARQUITECTURA MVC:
 * - MODELO: Classes en com.campingparty.modelo (datos y lógica)
 * - VISTA: Classes en com.campingparty.vista (interfaz gráfica)
 * - CONTROLADOR: Classes en com.campingparty.controlador (comunicación)
 * 
 * La base de datos (MySQL) se gestiona a través de DAO en el modelo.
 * Las vistas fueron diseñadas originalmente en NetBeans y han sido migradas.
 * 
 * @author Carla Terol
 */
public class ProyectoCampingParty {

    public static void main(String[] args) {
        try {
            // Configurar el look and feel FlatLightLaf
            FlatLightLaf.setup();
            
            // Instancia el controlador principal (Singleton)
            ControladorPrincipal controlador = ControladorPrincipal.getInstance();
            
            // Inicia la aplicación (muestra la vista de bienvenida)
            controlador.iniciar();
            
        } catch (Exception e) {
            System.err.println("Error fatal al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
