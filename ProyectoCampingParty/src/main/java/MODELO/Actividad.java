/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.util.ArrayList;
import java.util.Date;

public class Actividad {
    private int idActividad;
    private String tipo;
    private Date fechaHora;
    private int maxParticipantes;
    private ArrayList<Cliente> participantes;
    private Cliente ganador;
    
    public Actividad(int idActividad, String tipo, Date fechaHora, int maxParticipantes){
        this.idActividad = idActividad;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
        this.maxParticipantes = maxParticipantes;
        this.participantes = new ArrayList<>();
        this.ganador = null;
    }

    public int getIdActividad(){ 
        return idActividad;
    }
    
    public String getTipo(){ 
        return tipo; 
    }
    
    public Date getFechaHora(){ 
        return fechaHora; 
    }
    
    public int getMaxParticipantes(){ 
        return maxParticipantes; 
    }
    
    public ArrayList<Cliente> getParticipantes(){ 
        return participantes; 
    }
    
    public Cliente getGanador(){ 
        return ganador; 
    }


    public void setTipo(String tipo){ 
        this.tipo = tipo; 
    }
    
    public void setFechaHora(Date fechaHora){ 
        this.fechaHora = fechaHora; 
    }
    
    public void setMaxParticipantes(int maxParticipantes){ 
        this.maxParticipantes = maxParticipantes; 
    }
    
    public void setGanador(Cliente ganador){ 
        this.ganador = ganador; 
    }


    public boolean agregarParticipante(Cliente c){
    boolean agregado = false;

    if(participantes.size() < maxParticipantes && !participantes.contains(c)){
        participantes.add(c);
        agregado = true;
    }

    return agregado;
}


    public boolean eliminarParticipante(Cliente c){
        return participantes.remove(c);
    }

    @Override
    public String toString(){
        return tipo + " - " + fechaHora + " (" + participantes.size() + "/" + maxParticipantes + ")";
    }
}

