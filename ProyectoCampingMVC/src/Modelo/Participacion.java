package Modelo;

/**
 *
 * @author Sergio Gimenez Gomez
 */
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
    
    public boolean isAsistido(){ 
        return asistido; 
    }
    
    public boolean isSancionado(){ 
        return sancionado; 
    }

    public void marcarAsistencia(){
        this.asistido = true;
    }
    
    public void quitarAsistencia() {
        this.asistido = false;
    }

    public void sancionar(){
        this.sancionado = true;
    }
    
    public void quitarSancion() {
        this.sancionado = false;
    }

    @Override
    public String toString(){
        return "Participación en " + actividad.getTipo() + " - Cliente: " + cliente.getNombre();
    }
}