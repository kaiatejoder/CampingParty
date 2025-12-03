
package MODELO;

import java.util.*;
/**
 * @class actividad
 * @param id el ID de la actividad
 * @param titulo el titulo de la actividad
 * @param desc la descripcion de la actividad
 * @param loc la localizacion de la actividad
 * @param tipo el tipo de actividad (1. piscina, 2. fronton, 3. club social, 5. plantilla)
 * @param fechaHora la fecha y hora de la actividad
 * @param maxParticipantes el numero maximo de participantes
 * @param participantes la lista de participantes inscritos
 * @param ganador el cliente ganador (si aplica)
 * 
 * @author Carla Terol <Carla Terol>
 */
public class Actividad {
    private int idActividad;
    private String titulo;
    private String desc;
    private String loc;
    private int tipo;
    private Date fechaHora;
    private int maxParticipantes;
    private ArrayList<Cliente> participantes;
    private Cliente ganador;
    
    public Actividad(int idActividad, int tipo, Date fechaHora, int maxParticipantes){
        this.idActividad = idActividad;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
        this.maxParticipantes = maxParticipantes;
        this.participantes = new ArrayList<>();
        this.ganador = null;
    }
    
    public Actividad(int idActividad,Date fechaHora, int tipo, int maxParticipantes, String tit, String desc, String loc ){
        this.idActividad = idActividad;
        this.fechaHora = fechaHora;
        this.setLoc(tipo);
        this.titulo = tit;
        this.desc = desc;
        this.maxParticipantes = maxParticipantes;
        this.participantes = new ArrayList<>();
        this.ganador = null;
    }
    
    public Actividad(int idActividad, int tipo, int maxParticipantes, String tit, String desc, String loc ){
        this.idActividad = idActividad;
        this.setLoc(tipo);
        this.titulo = tit;
        this.desc = desc;
        this.maxParticipantes = maxParticipantes;
        this.participantes = new ArrayList<>();
        this.ganador = null;
    }
    public Actividad(int idActividad, int maxParticipantes, String tit, String desc, String loc ){
        this.idActividad = idActividad;
        this.tipo = this.getTipo(loc);
        this.titulo = tit;
        this.desc = desc;
        this.maxParticipantes = maxParticipantes;
        this.participantes = new ArrayList<>();
        this.ganador = null;
    }

    public int getIdActividad(){ 
        return idActividad;
    }
    
    public int getTipoCl(){ 
        return tipo; 
    }
    /**
     * SetLoc - Pone la ubicación según el tipo.
     * Puede ser: 
     * 1 - Piscina
     * 2 - Fronton
     * 3 - Club social
     * @param tipo 
     */
    public void setLoc(int tipo){
        String[] posible = {"Piscina","Fronton","Club Social","Plantilla"};
        this.loc = posible[tipo];
    }
    public String getLoc(){
        return this.loc;
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
    public String getTitulo(){ 
        return titulo; 
    }
    public String getDesc(){ 
        return desc; 
    }
    
    public void setTitulo(String titulo){ 
        this.titulo = titulo; 
    }
    public void setDesc(String desc){ 
        this.desc = desc; 
    }
    public void setLoc(String loc){ 
        this.loc = loc; 
    }
    


    public void setTipo(int tipo){ 
        if (tipo < 4)
            this.tipo = tipo; 
    }
    
    public int getTipo(String loc){
        int res = -1;
       String[] posible = {"Piscina","Fronton","Club Social","Plantilla"};
       for( int i =0; i <4 ; i++)
       {
            if (loc == posible[i] ){
                res=  i;
            }
            
    }
       return res;
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

