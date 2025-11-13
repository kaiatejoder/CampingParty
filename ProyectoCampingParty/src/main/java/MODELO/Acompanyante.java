/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Carla Terol
 */
public class Acompanyante extends Persona {
    private int edad;
    public Acompanyante(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }
    
    public int getEdad(){
        return edad;
    }
}
