/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Carla Terol
 */
public class Modelo {
    private int descuento;
    private Parcelas parcelas;
    private ArrayList<Cliente> clientes;
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


}
