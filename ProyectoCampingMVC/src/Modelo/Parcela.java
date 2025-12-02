package Modelo;

/**
 *
 * @author Sergio Gimenez Gomez
 */
import java.util.Date;

/**
 * Clase Parcela que representa una parcela con sus atributos y métodos.
 * Cada parcela tiene un identificador, metros cuadrados, disponibilidad de luz y precio.
 * 
 * @author Carla Terol
 */
public class Parcela {
    private float m2;
    private int id;
    private boolean luz;
    private float precio;
    private boolean ocupada;
    private boolean reservada;
    private Date fechaOcupacion;
    private Date fechaFuera;

    public Parcela(int id, float m2, boolean luz, float precio){
        this.id = id;
        this.m2 = m2;
        this.luz = luz;
        this.precio = precio;
        this.ocupada = false;
        this.reservada = false;
        this.fechaOcupacion = null;
        this.fechaFuera = null;
    }
    
    public String ParcelaParser(){
        String s = id + "," + m2 + "," + luz + "," + precio + "," + ocupada + "," + reservada;
        if(reservada || ocupada) {
            s = s + "," + fechaOcupacion + "," + fechaFuera; 
        }
        s = s + "\n";
        return s;
    }
    
    /**
     * Liberar la parcela
     */ 
    public void liberarParcela(){
        this.ocupada = false;
        this.reservada = false;
        this.fechaFuera = null;
        this.fechaOcupacion = null;
    }
    
    public boolean isLibre(){
        return !ocupada && !reservada;
    }
    
    public void reservarParcela(){
        this.reservada = true;
        this.fechaOcupacion = new Date(); // Fecha actual
    }
    
    public void ocuparParcela(){
        this.ocupada = true;
        this.reservada = false;
        this.fechaOcupacion = new Date(); // Fecha actual
    }
    
    // Getters y Setters
    public int getId(){
        return id;
    }
    
    public float getM2(){
        return m2;
    }
    
    public boolean hayLuz(){
        return luz;
    }
    
    public float getPrecio(){
        return precio;
    }
    
    public boolean isOcupada() {
        return ocupada;
    }
    
    public boolean isReservada() {
        return reservada;
    }
    
    public Date getFechaOcupacion() {
        return fechaOcupacion;
    }
    
    public Date getFechaFuera() {
        return fechaFuera;
    }
    
    public void setM2(float m2){
        this.m2 = m2;
    }
    
    public void setLuz(boolean luz){
        this.luz = luz;
    }
    
    public void setPrecio(float precio){
        this.precio = precio;
    }
    
    public void setFechaFuera(Date fechaFuera) {
        this.fechaFuera = fechaFuera;
    }
    
    @Override
    public String toString(){
        String estado;
        if (this.isLibre()) {
            estado = "Sí";
        } else if (reservada) {
            estado = "Reservada";
        } else {
            estado = "Ocupada";
        }

        return "Parcela " + id + "\n "
             + m2 + "m2 \n Luz: " + luz 
             + " - Precio: " + precio + "€ \n Libre: " + estado;
    }   
}