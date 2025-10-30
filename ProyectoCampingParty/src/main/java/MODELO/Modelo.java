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
    private ArrayList<Reserva> res;
    
    
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
