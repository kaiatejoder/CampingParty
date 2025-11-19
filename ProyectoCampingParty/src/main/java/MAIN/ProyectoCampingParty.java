package MAIN;
import CONTROLADOR.Controlador;
import MODELO.Modelo;
import VISTA.ClienteLogin;
import VISTA.TrabajadorLogin;
import VISTA.VistaLogin;

/**
 *
 * @author Carla Terol
 */
public class ProyectoCampingParty {

    public static void main(String[] args) {
        Modelo m = new Modelo();
        Controlador c = new Controlador(new VistaLogin(), m);
    }
}
