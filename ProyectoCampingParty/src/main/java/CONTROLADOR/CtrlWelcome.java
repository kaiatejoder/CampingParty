/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.*;
import VISTA.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controla la pantalla de bienvenida:
 *  - Botón "Soy Cliente"
 *  - Botón "Soy Staff"
 * @author Carla Terol 
 */
public class CtrlWelcome {
    private final Welcome vWel;
    private final ClientLogin cLog;
    private final StaffLogin sLog;

    public CtrlWelcome(Modelo md,
                                 Welcome vista,
                                 ClientLogin vCliLog,
                                 StaffLogin vStaffLog) {
        this.vWel = vista;
        this.cLog = vCliLog;
        this.sLog = vStaffLog;

        init();
    }

    private void init() {
        // Botón "Soy Cliente"
       
        vWel.addListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c = e.getActionCommand();
                if (c.equals("btnClient")) {
                    vWel.setVisible(false);
                    cLog.setLocationRelativeTo(vWel);
                    cLog.setVisible(true);
                } 
                else if (c.equals("btnStaff")) {
                    vWel.setVisible(false);
                    sLog.setLocationRelativeTo(vWel);
                    sLog.setVisible(true);
                }
        }});
        
    }
}
