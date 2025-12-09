/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Carla Terol
 */
public class Modelo {
    private int descuento;
    private Parcelas parcelas;
    
    private ArrayList<Actividad> acts;
    private Reservas r;
    private DAO dao;

    public Modelo(){
        this.descuento = 20;
        Parcela[] pArray = new Parcela[16];
        for(int i=0; i<16; i++){
            pArray[i] = new Parcela(i+1, 2 + i*5, i%2==0, 15 + i*2);
        }
        this.parcelas = new Parcelas(pArray);
        this.acts = new ArrayList<>();
        this.r = new Reservas();
        this.dao = new DAO(); // Inicializar DAO
    }
    public boolean[]getParcelasLibres(){
        return this.parcelas.getLibres();
    }

    public ArrayList<Actividad> getActs() {
        return this.acts;
    }
    
    public Cliente authCli(String u, String p){
        return dao.validarUserPass(u, p);
    }
    public Staff validStaff(String user, String pass){
        return dao.getStaff(user, pass);
    }
    public int getDescuento() {
        return descuento;
    }
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    
    public DAO getDAO() {
        return dao;
    }
    public ArrayList<Actividad> getPlantillas(){
        
        return dao.getPlantillasActividades();
    }

}
