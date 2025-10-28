/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.util.ArrayList;
/**
 * Clase Cliente que representa un cliente del camping.
 * @author HP
 */
public class Cliente {
    private String dni;
    private String nombre;
    private String telefono;
    private boolean hayReserva;
    private String username;
    private String password;
    private ArrayList<Participacion> participaciones;
    
    public Cliente(String nombre, String dni, String telefono, String username, String password){
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.hayReserva = false;
        this.username = username;
        this.password = password;
        this.participaciones = new ArrayList<>();
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
    public void tieneReserva(){
        this.hayReserva = true;
    }
    public void borrarReserva(){
        this.hayReserva = false;
    }
    public boolean getHayReserva(){
        return hayReserva;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    
    public ArrayList<Participacion> getParticipaciones(){
        return participaciones;
    }

    public void inscribirseEnActividad(Actividad a){
        Participacion p = new Participacion(this, a);
        participaciones.add(p);
        a.agregarParticipante(this);
    }

    public void cancelarActividad(Actividad a){
        participaciones.removeIf(p -> p.getActividad().equals(a));
        a.eliminarParticipante(this);
    }
}
