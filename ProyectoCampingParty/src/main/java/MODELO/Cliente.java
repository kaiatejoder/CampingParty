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
public class Cliente {
    private String dni;
    private String nombre;
    private String telefono;
    private int edad;
    private String username;
    private String password;
    private ArrayList<Tienda> tiendas;
    private Reservas reservas;

    
    public Cliente(String nombre, String dni, int edad, String telefono, String username, String password){
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
        this.edad = edad;
        this.tiendas = new ArrayList<>();
        this.reservas = new Reservas();
    }

    public String getNombre(){
        return nombre;
    }

    public String getDni(){
        return dni;
    }

    public String getTelefono(){
        return telefono;
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
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
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
    
    @Override
    public String toString(){
        return dni +";" + nombre + ";" + edad + ";" + telefono + ";" + username + ";" + password + "\n";
    }
}
