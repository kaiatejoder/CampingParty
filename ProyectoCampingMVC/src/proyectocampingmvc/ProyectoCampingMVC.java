package proyectocampingmvc;

import Modelo.Modelo;
import Vista.VistaBienvenida;

/**
 *
 * @author Sergio Gimenez Gomez
 */
public class ProyectoCampingMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Modelo m = new Modelo();
        VistaBienvenida v = new VistaBienvenida();
        v.setVisible(true);
        
    }
    
}
