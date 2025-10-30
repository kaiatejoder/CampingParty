/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
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
    private ArrayList<Participacion> participaciones;
    private ArrayList<Reserva> reservas;
    private ArrayList<String> histReservas;
    
    public Cliente(String nombre, String dni, String telefono, int edad, String username, String password){
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
        this.histReservas = new ArrayList<>();
    }
    public Cliente(){
       this.histReservas = new ArrayList<>(); 
    }

    public String getNombre(){
        return nombre;
    }

    public String getDni(){
        return dni;
    }
    public void setNombre(String s){
        this.nombre = s;
    }

    public void setDni(String s){
        this.dni = s;
    }
    public void setTelefono(String s){
        this.telefono = s;
    }
    public void setEdad(int s){
        this.edad = s;
    }
    public void setUser(String s){
        this.username = s;
    }
    public void setPassword(char[] s){
        this.password = s;
    }
    public void setPhone(String s){
        this.telefono = s;
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
    public Cliente read(String s){
        String[] p = s.split(";");
        Cliente c = new Cliente(p[0],p[1],p[2],Integer.parseInt(p[3]),p[4],p[5]);
        c.parseHist(p[6]);
        return c;
    }
    public void anyadirHist(Reserva r){
        String s;
        s= r.getInicioEstancia().toString() + "," +r.getFinEstancia().toString()
                +","+r.getPrecioTotal()+ "-";
        this.histReservas.add(s);
    }
    /**
     * ParseHist hace un parse para el historial de reservas
     * @param s 
     */
    public void parseHist(String s){
        String[] p = s.split("-");
        for(int i =0; i <=p.length;i++){
            this.histReservas.add(p[i]);
            
        }
        
        this.histReservas.add(s);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        String s;
        s = this.dni +";"+ this.nombre +";"+ this.telefono +";"+ Integer.toString(this.edad)+";"+
                this.username +";"+ this.password+";";
           for (int i =0; i<= this.histReservas.size();i++){
               s += histReservas.get(i);
           }
           return s;
    }
}
