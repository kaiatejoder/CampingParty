/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.*;
import VISTA.*;
import java.util.Date;
import javax.swing.*;

/**
 * CONTROLADOR PRINCIPAL
 * @author Carla Terol
 */
public class Ctrl {
    private Welcome vWel;
    private ClientLogin cLog;
    private ClientSignIn cIn;
    private ClientSignOn cOn;
    private ClientMain cM;
    private StaffMain sM;
    private ClientRes cRes;
    private StaffSignIn sIn;
    private StaffLogin sLog;
    private Modelo m;
    // **************** CAMBIAR DE VISTAS ******************* 
    public void toWelcome(JFrame fr){
        JFrame fra = fr;
        vWel.setVisible(true);
        fra.setVisible(false);
    }
    public void toCLog(JFrame fr){
        JFrame fra = fr;
        this.cLog.setVisible(true);
        fra.setVisible(false);
    }
    public void toSLog(JFrame fr){
        JFrame fra = fr;
        this.sLog.setVisible(true);
        fra.setVisible(false);
    }
    public void toSignIn(JFrame fr, char s){
        JFrame fra = fr;
        if(s == 'c')
            this.cIn.setVisible(true);
        else if(s == 's')
            this.sIn.setVisible(true);
        fra.setVisible(false);
    }
    public void toSignUp(JFrame fr){
        JFrame fra = fr;
            this.cOn.setVisible(true);
        fra.setVisible(false);
    }
    
    public void auth(String user, String pass, Date d, JLabel lE, int r, JFrame fra){
        JFrame f = fra;
        Date fecha = d;
        if(r == 1)
        {
            Cliente cl = m.authCli(user, pass);
            if (cl != null)
            {
                if(cl.tieneResAhora(fecha)!= null){
                    cRes = new ClientMain(cl, this);}
                else 
                    {
                cM = new ClientMain(cl, this);
                cM.setVisible(true);
                
                f.setVisible(false);
            }}
            else
                lE.setText("ERROR: Usuario o contraseña no válidos");
        }
        else if(r == 2)
        {
            Staff cl = m.validStaff(user, pass);
            if (cl != null)
            {
                sM = new StaffMain(cl, this);
                sM.setVisible(true);
                f.setVisible(false);
            }
            else
                lE.setText("ERROR: Usuario o contraseña no válidos");
        }
    }
    public void SignUp(Cliente cl, JFrame fra){
        JFrame fr = fra;
        if(m.getDAO().agregarCliente(cl)){
            javax.swing.JOptionPane.showMessageDialog(fr,
            "Bienvenido/a, " + cl.getNombre() + "!",
            "Registro realizado correctamente", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            toCLog(fr);
        }
        else{
            javax.swing.JOptionPane.showMessageDialog(fr,"Error de registro",
            "Ha habido un problema. Por favor, inténtalo más tarde", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
