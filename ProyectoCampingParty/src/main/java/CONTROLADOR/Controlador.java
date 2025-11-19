/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.Modelo;
import VISTA.ClienteLogin;
import VISTA.VistaLogin;
import java.awt.event.*;
import java.awt.event.WindowEvent;

/**
 *
 * @author PERSONAL
 */
public class Controlador {
    private VistaLogin vLog;
    private Modelo m;
    private ClienteLogin vCliente;

    public Controlador(Modelo modelo,VistaLogin vista) {
        this.m = modelo;
        this.vLog = vista;
        this.vCliente = new ClienteLogin();
        vista.addWindowListener(new ControllerWindowListener());
        vista.setActionListener(new CampingActionListener());
    }

    class ControllerWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println(" CalculadoraController : Cerrar ventana.");
            System.exit(0);
        }

    }

    public class CampingActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            switch (command){
                case "cliente":
                    vLog.setVisible(false);
                    vCliente.setVisible(true);
                    break;
            }
        }}
}
