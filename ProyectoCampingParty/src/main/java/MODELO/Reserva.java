/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.sql.Date;
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
    private int id;
    private boolean checkedIn;
    private Cliente c;
    private ArrayList<Parcela> parcelas;
    private ArrayList<Tienda> tiendas;
    private ArrayList<Acompanyante> acompanyantes;
    private ArrayList<Participacion> participaciones;
    private float precioTotal;
    private float precioDia;

    public Reserva(Date inicioReserva, Date finReserva, Cliente c){
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
        this.c = c;
        this.checkedIn = false;
        this.parcelas = new ArrayList<>();
        this.tiendas = new ArrayList<>();
        this.acompanyantes = new ArrayList<>();
    }
    public Reserva(Date inicioReserva, Date finReserva, ArrayList<Parcela> p,  ArrayList<Tienda> t, ArrayList<Acompanyante> a,  Cliente c){
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
        this.c = c;
        this.checkedIn = false;
        this.parcelas = p;
        this.tiendas = t;
        this.acompanyantes = a;
    }
    //Getters y Setters
    public Date getInicioReserva() {
        return inicioReserva;
    }   
    public boolean getCheck(){
        return checkedIn;
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
    public void setId(int id){
        this.id= id;
    }
    public int getId(){
         return this.id;
    }
    public Cliente getC() {
        return c;
    }
    public Cliente getCliente() {
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
    public float getPrecioDia() {
        return precioDia;
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
    public ArrayList<Parcela> getParcelas() {
        return parcelas;
    }
    public Parcela getParcela(int i) {
        return parcelas.get(i);
    }
    public void addTienda(Tienda t){
        tiendas.add(t);
    }
    public void setCheck(boolean b){
        this.checkedIn = b;
    }
    public void setParcelas(ArrayList<Parcela> parcelas) {
        this.parcelas = parcelas;
    }
    public ArrayList<Tienda> getTiendas() {
        return tiendas;
    }
    public void setTiendas(ArrayList<Tienda> tiendas) {
        this.tiendas = tiendas;
    }
    
    public void addAcompanyante(String n, String a, int d){
        String nom = n +" "+ a;
        this.acompanyantes.add(new Acompanyante(nom, d));
    }
    public void addAcompanyante(Acompanyante a){
        this.acompanyantes.add(a);
    }
   public ArrayList<Acompanyante> getAcompanyantes(){
       return this.acompanyantes;
   }
   public void toHist(){
       
   }
    
}
