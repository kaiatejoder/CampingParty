/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.util.ArrayList;
/**
 * Clase Cliente que representa un cliente del camping.
 * @author Carla Terol
 */
public class Cliente extends Persona {
    
    private int edad;
    
    private ArrayList<Tienda> tiendas;
    private Reservas reservas;

    
    public Cliente(String nombre, String dni, int edad, int telefono, String username, String password){
        this.nombre = nombre;
        this.dni = dni;
        this.tlf = telefono;
        this.user = username;
        this.pass = password;
        this.edad = edad;
        this.tiendas = new ArrayList<>();
        this.reservas = new Reservas();
    }
     public Cliente(String nombre, String dni, int edad){
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.tiendas = new ArrayList<>();
        this.reservas = new Reservas();
    }

    public Cliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

   public void setUser(String u){
        this.user = u;
    }
    public void setPass(String p){
        this.pass = p;
    }
    public void setTlf(int t){
         this.tlf = t;
    }
    public void setPassword(String p){
        this.pass = p; }
     public void addReserva(Reserva r){
        this.reservas.addReserva(r);
    }
    public boolean tieneReserva(){
        return !this.reservas.isEmpty();
    }
    public void borrarReserva(int index){
        this.reservas.remove(index);
    }
    public Reserva getReserva(){
        return reservas.get(0);
    }
    public int getEdad(){
        return edad;
    }
    public Reservas getReservas(){
        return reservas;
    }
    public ArrayList<Tienda> getTiendas(){
        return tiendas;
    }
    public void addTienda(Tienda t){
        this.tiendas.add(t);
    }
    public void setPhone(int s){
        this.tlf = s;
    }
    

    @Override
    public String toString(){
        return dni +";" + nombre + ";" + edad + ";" + tlf + ";" + user + ";" + pass + "\n";
    }
}
