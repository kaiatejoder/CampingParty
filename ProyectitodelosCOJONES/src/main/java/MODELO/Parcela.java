/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.util.Date;

/**
 * Clase Parcela que representa una parcela con sus atributos y métodos.
 * Cada parcela tiene un identificador, metros cuadrados, disponibilidad de luz y precio.
 * 
 * @author Carla Terol
 */
public class Parcela {
    private float m2;
    private int id;
    private boolean luz;
    private float precio;
    private boolean ocupada;
    private boolean reservada;
    private Date fechaOcupacion;
    private Date fechaFuera;


    public Parcela(int id, float m2, boolean luz, float precio){
        this.id = id;
        this.m2 = m2;
        this.luz = luz;
        this.precio = precio;
        this.ocupada = false;
        this.reservada = false;
    }
    public String ParcelaParser(){
        String s = id + "," + m2 + "," + luz + "," + precio + "," + ocupada + "," + reservada;
        if(reservada == true || ocupada == true)
            s = s + "," + fechaOcupacion + "," + fechaFuera; 
        
        s = s+"\n";
        return s;
    }
    /**
     * Destructor de la parcela
     */ 
    public void liberarParcela(){
        this.ocupada = false;
        this.reservada = false;
        this.fechaFuera = null;
        this.fechaOcupacion = null;
    }
    public boolean isLibre(){
        return ocupada == false && reservada == false;
    }
    public void reservarParcela(){
        this.reservada = true;
    }
    public int getId(){
        return id;
    }
    public float getM2(){
        return m2;
    }
    public boolean hayLuz(){
        return luz;
    }
    public float getPrecio(){
        return precio;
    }
    public void setM2(float m2){
        this.m2 = m2;
    }
    public void setLuz(boolean luz){
        this.luz = luz;
    }
    public void setPrecio(float precio){
        this.precio = precio;
    }
    @Override
    public String toString(){
        String s;
        if (this.isLibre())
            s = "Sí";
        else
            s = "No";

        return "Parcela " + id + "\n "
         + m2 + "m2 \n Luz: " + luz 
         + " - Precio: " + precio + "€ \n Libre: " + s ;
    }   
}
