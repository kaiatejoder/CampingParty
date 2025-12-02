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
    private final Modelo m;
    private final Welcome vWel;
    private final ClientLogin cLog;
    private final StaffLogin sLog;

    public CtrlWelcome(Modelo md,
                                 Welcome vista,
                                 ClientLogin vCliLog,
                                 StaffLogin vStaffLog) {
        this.m= md;
        this.vWel = vista;
        this.cLog = vCliLog;
        this.sLog = vStaffLog;

        init();
    }

    private void init() {
        // Botón "Soy Cliente"
        vWel.getCli().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vWel.setVisible(false);
                cLog.setVisible(true);
            }
        });

        // Botón "Soy Staff"
        vWel.getSt().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vWel.setVisible(false);
                sLog.setVisible(true);
            }
        });
        sLog.getBack().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sLog.setVisible(false);
                vWel.setVisible(true);
            }
        });
        cLog.getBack().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cLog.setVisible(false);
                vWel.setVisible(true);
            }
        });
    }
    

    private void cli() {
        vWel.setVisible(false);
        cLog.setLocationRelativeTo(vWel);
        cLog.setVisible(true);
    }

    private void st() {
        vWel.setVisible(false);
        sLog.setLocationRelativeTo(vWel);
        sLog.setVisible(true);
    }
}
