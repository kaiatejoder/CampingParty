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
    public ArrayList<Cliente> clientes;
    private ArrayList<Actividad> acts;
    private Reservas r;

    public Modelo(){
        this.descuento = 20;
        Parcela[] pArray = new Parcela[16];
        for(int i=0; i<16; i++){
            pArray[i] = new Parcela(i+1, 2 + i*5, i%2==0, 15 + i*2);
        }
        this.parcelas = new Parcelas(pArray);
        this.clientes = new ArrayList<>();
        this.acts = new ArrayList<>();
        this.r = new Reservas();
    }
    public boolean[]getParcelasLibres(){
    return this.parcelas.getLibres();
}
    public void setDatos(){
        Cliente c = new Cliente("Lupe López","11111111Y",22,666666666,"lupelopez@pornhub.com","Lupita");
        clientes.add(c);
        Date dateIn = new Date(30,11,2025);
        Date dateOut = new Date(3,12,2025);
        Reserva res = new Reserva(dateIn,dateOut,c);
    }
    public ArrayList<String> getUserPass(){
        ArrayList<String> res = new ArrayList<>();
        for(int i =0; i< clientes.size(); i++){
            res.add(clientes.get(i).getUser());
            res.add(clientes.get(i).getPass());
        }
        return res;
    }
    public int tryUserPass(String u, String p){
        int b = 0;
        boolean user = false;
        boolean pass = false;
        
        for(int i =0; i< clientes.size(); i++){
            while(0<=b){
            user = u.equals(clientes.get(i).getUser());
            pass = p.equals(clientes.get(i).getPass());
            if(user &&pass){
            b = i/2;}
        }}
        return b;
    }
    public void write (String FileN,String s){
        try{
        File f = new File(FileN);
        
        FileWriter w = new FileWriter(f);
        w.write(s);
      w.close();  // must close manually
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}
    public int getDescuento() {
        return descuento;
    }
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

}
