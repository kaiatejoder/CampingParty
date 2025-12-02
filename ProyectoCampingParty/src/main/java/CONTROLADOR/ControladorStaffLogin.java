package CONTROLADOR;

import VISTA.StaffLogin;
import VISTA.StaffMain;
import VISTA.Welcome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para el flujo de login de Staff.
 * Maneja: volver atrás e iniciar sesión del staff.
 *
 * @author Carla Terol (Controlador MVC)
 */
public class ControladorStaffLogin extends CtrlStaff {

    private final Welcome welcome;
    private final StaffLogin staffLogin;

    public ControladorStaffLogin(Welcome welcome, StaffLogin staffLogin) {
        this.welcome = welcome;
        this.staffLogin = staffLogin;
        init();
    }

    private void init() {
        // Volver a bienvenida
        staffLogin.getBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffLogin.setVisible(false);
                welcome.setLocationRelativeTo(null);
                welcome.setVisible(true);
            }
        });

        // Iniciar sesión: abrimos el panel principal del staff
        staffLogin.getLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StaffMain v = new StaffMain();
                v.setLocationRelativeTo(staffLogin);
                v.setVisible(true);
                staffLogin.setVisible(false);
            }
        });
    }
}
