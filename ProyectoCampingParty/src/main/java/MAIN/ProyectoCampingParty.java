/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package MAIN;
import VISTA.ClienteLogin;
import VISTA.TrabajadorLogin;
import VISTA.VistaLogin;

/**
 *
 * @author Carla Terol
 */
public class ProyectoCampingParty {

    public static void main(String[] args) {
        TrabajadorLogin tl = new TrabajadorLogin();
        ClienteLogin cl = new ClienteLogin();
        VistaLogin vl = new VistaLogin(tl,cl);
        
        vl.setVisible(true);
        vl.setTitle("Camping Riurau");
    }
}
