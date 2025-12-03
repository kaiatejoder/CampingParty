/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Carla Terol <Carla Terol>
 */
public abstract class ListaPlantilla implements ListModel {
    ArrayList<String> plantilla;
    ArrayList<Actividad> plantillas;
    public ListaPlantilla(ArrayList<Actividad> plantillas){
        plantilla = new ArrayList();
        for(int i=0; i < plantillas.size(); i++){
            plantilla.add(plantillas.get(i).getTitulo());
    }
}
    /*public Actividad getAt(int index){
        Actividad act;
        act = new Actividad();
        return act;
    }*/
}

