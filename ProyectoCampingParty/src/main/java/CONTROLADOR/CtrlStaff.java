
package CONTROLADOR;

import MODELO.Modelo;
import VISTA.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Carla Terol 
 */
public class CtrlStaff {
    Modelo m;
    StaffIn sLog;
    StaffMain sMain;
    StaffLogNew sIn;
    StaffSignIn sNew;
    StaffLogin sLogIn;
    
    public CtrlStaff(Modelo m,StaffLogin sLog,StaffMain sMain, StaffLogin sLogin){
        sLogIn = sLogin;
    }
    public void init(){
        sLogIn.getLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sNew.setVisible(true);
                sLog.setVisible(false);
            }
        });
        sNew.getLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sNew.setVisible(true);
                sLog.setVisible(false);
            }
        });
            }
    }
}