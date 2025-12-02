package MAIN;
import VISTA.ClientLogin;
import VISTA.StaffLogin;
import VISTA.Welcome;

/**
 *
 * @author Carla Terol
 */
public class ProyectoCampingParty {

    public static void main(String[] args) {
        StaffLogin tl = new StaffLogin();
        ClientLogin cl = new ClientLogin();
        Welcome vl = new Welcome(tl,cl);
        
        vl.setVisible(true);
        vl.setTitle("Camping Riurau");
    }
}
