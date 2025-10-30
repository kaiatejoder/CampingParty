/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.Actividad;
import MODELO.Cliente;
import MODELO.Modelo;
import MODELO.Parcela;
import MODELO.Participacion;
import MODELO.Reserva;
import MODELO.Tienda;
import VISTA.ActividadSeleccionada;
import VISTA.ClienteConReserva;
import VISTA.ClienteLogLogin;
import VISTA.ClienteLogin;
import VISTA.ClienteSignOn;
import VISTA.JDialogConfirmar;
import VISTA.TrabajadorLogin;
import VISTA.TrabajadorSesión;
import VISTA.VistaCliente;
import VISTA.VistaClienteModificarReserva;
import VISTA.VistaClienteReserva;
import VISTA.VistaLogin;
import VISTA.VistaReservas;
import VISTA.VistaStaffActividad;
import VISTA.VistaStaffEntrada;

/**
 *
 * @author Carla Terol
 */
public class Controlador {

private ActividadSeleccionada as;
private ClienteLogLogin cll;
private ClienteConReserva ccr;
private ClienteLogin cl;
private ClienteSignOn cso;
private JDialogConfirmar jdc;
private TrabajadorLogin tl;
private TrabajadorSesión ts;
private VistaCliente vc;
private VistaClienteModificarReserva vcmr;
private VistaClienteReserva vcr;
private VistaLogin vl;
private VistaReservas vr;
private VistaStaffActividad vsa;
private VistaStaffEntrada vse;
private Modelo m;
 public Controlador(Modelo m){
    this.m = new Modelo();
    
    tl = new TrabajadorLogin();
    cll = new ClienteLogLogin();
    cl = new ClienteLogin();
    cso = new ClienteSignOn();
    vc = new VistaCliente();
    vcr = new VistaClienteReserva();
    vcmr = new VistaClienteModificarReserva();
    vr = new VistaReservas();
    ts = new TrabajadorSesión();
    vse = new VistaStaffEntrada();
    vsa = new VistaStaffActividad();
    ccr = new ClienteConReserva();
    vl = new VistaLogin(tl,cl);
 }


}