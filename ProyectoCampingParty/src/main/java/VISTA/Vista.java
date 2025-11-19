package VISTA;

import java.awt.event.ActionListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Carla Terol <Carla Terol>
 */
public class Vista {
    VistaLogin vLog;
    VistaCliente vCliente;
    VistaClienteReserva newReserva;
    VistaClienteModificarReserva editReserva;
    VistaReservas verReservas;
    ClienteLogin cLog;
    ClienteLogLogin cLogLogin;
    ClienteSignOn cRegistro;
    ClienteConReserva cliReserva;
    TrabajadorLogin staffLogin;
    TrabajadorSesión staffSesion;
    ActividadSeleccionada actSelect;
    StaffMain staffPrincipal;
    VistaStaffActividad staffAct;
    VistaStaffEntrada staffEntrar;
    
    public void addActionListener (ActionListener al){
        vLog.addActionListener(al);
    }
}
