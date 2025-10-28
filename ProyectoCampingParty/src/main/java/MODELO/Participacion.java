/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

public class Participacion{
    private Cliente cliente;
    private Actividad actividad;
    private boolean asistido;
    private boolean sancionado;

    public Participacion(Cliente cliente, Actividad actividad){
        this.cliente = cliente;
        this.actividad = actividad;
        this.asistido = false;
        this.sancionado = false;
    }

    public Cliente getCliente(){ 
        return cliente; 
    }
    
    public Actividad getActividad(){ 
        return actividad; 
    }
    
    public boolean asistido(){ 
        return asistido; 
    }
    
    public boolean sancionado(){ 
        return sancionado; 
    }

    public void marcarAsistencia(){
        this.asistido = true;
    }

    public void sancionar(){
        this.sancionado = true;
    }

    @Override
    public String toString(){
        return "Participación en " + actividad.getTipo() + " - Cliente: " + cliente.getNombre();
    }
}
