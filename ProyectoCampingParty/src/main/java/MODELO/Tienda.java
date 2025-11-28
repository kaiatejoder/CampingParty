/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Carla Terol
 */
public class Tienda {
    String nombre;
    float m2;

    public Tienda(String nombre, float m2){
        this.nombre = nombre;
        this.m2 = m2;
    }
    public float getM2(){
        return m2;
    }
}