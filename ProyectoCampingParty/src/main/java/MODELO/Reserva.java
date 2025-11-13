/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Carla Terol
 */
public class Reserva {
    private Date inicioReserva;
    private Date finReserva;
    private Date inicioEstancia;
    private Date finEstancia;
    private Cliente c;
    private ArrayList<Parcela> parcelas;
    private ArrayList<Tienda> tiendas;
    private ArrayList<String> acompanyantes;
    private ArrayList<Participacion> participaciones;
    private float precioTotal;
    private float precioDia;

    public Reserva(Date inicioReserva, Date finReserva, Cliente c){
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
        this.c = c;
        this.parcelas = new ArrayList<>();
        this.tiendas = new ArrayList<>();
        this.acompanyantes = new ArrayList<>();
    }
    public Reserva(Date inicioReserva, Date finReserva, ArrayList<Parcela> p,  ArrayList<Tienda> t, ArrayList<String> a,  Cliente c){
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
        this.c = c;
        this.parcelas = p;
        this.tiendas = t;
        this.acompanyantes = a;
    }
    //Getters y Setters
    public Date getInicioReserva() {
        return inicioReserva;
    }   
    public void setInicioReserva(Date inicioReserva) {
        this.inicioReserva = inicioReserva;
    }
    public Date getFinReserva() {
        return finReserva;
    }
    public void setFinReserva(Date finReserva) {
        this.finReserva = finReserva;
    }
    public Cliente getC() {
        return c;
    }
    public void setInicioEstancia(Date inicioEstancia) {
        this.inicioEstancia = inicioEstancia;
    }
    public Date getInicioEstancia() {
        return inicioEstancia;
    }
    public void setFinEstancia(Date finEstancia) {
        this.finEstancia = finEstancia;
    }
    public Date getFinEstancia() {
        return finEstancia;
    }
    public float getPrecioTotal() {
        return precioTotal;
    }
    public void setPrecioDia(){
        float precio = 0;
        for (int i =0; i <= parcelas.size(); i++){
            Parcela p = parcelas.get(i);
            precio += p.getPrecio();
        }
        this.precioDia = precio;
    }
    public void setPrecioTotal(int dias, int descuento) {
        float p;
        if(dias > 15){
             p = dias * (descuento / 100.0f);
        }
        else
            p = dias * precioDia;
        
        this.precioTotal = p;
    }
    public String getFechas(){
        return inicioReserva + " - " + finReserva;
    }
   
    
}
