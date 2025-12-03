package MAIN;
import CONTROLADOR.CONTROLADOR;

/**
 *
 * @author Carla Terol
 */
public class ProyectoCampingParty {

    public static void main(String[] args) {
        // Instancia el controlador principal, que se encarga de:
        // 1. Crear todas las vistas
        // 2. Crear el modelo
        // 3. Instanciar todos los controladores
        // 4. Conectar los listeners
        CONTROLADOR controlador = CONTROLADOR.inicializar();
        
        // Inicia la aplicación (muestra Welcome)
        controlador.iniciar();
    }
}
