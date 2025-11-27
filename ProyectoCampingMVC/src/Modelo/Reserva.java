package Modelo;

import java.util.Date;
import java.util.ArrayList;

public class Reserva {
    private Date inicioReserva;
    private Date finReserva;
    private Date inicioEstancia;
    private Date finEstancia;
    private Cliente cliente;
    private ArrayList<Parcela> parcelas;
    private ArrayList<Tienda> tiendas;
    private ArrayList<String> acompanyantes;
    private ArrayList<Participacion> participaciones;
    private float precioTotal;
    private float precioDia;
    private int idReserva;
    private static int contadorId = 1;

    public Reserva(Date inicioReserva, Date finReserva, Cliente cliente){
        this.idReserva = contadorId++;
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
        this.cliente = cliente;
        this.parcelas = new ArrayList<>();
        this.tiendas = new ArrayList<>();
        this.acompanyantes = new ArrayList<>();
        this.participaciones = new ArrayList<>();
    }
    
    public Reserva(Date inicioReserva, Date finReserva, ArrayList<Parcela> p,  
                   ArrayList<Tienda> t, ArrayList<String> a, Cliente cliente){
        this.idReserva = contadorId++;
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
        this.cliente = cliente;
        this.parcelas = p;
        this.tiendas = t;
        this.acompanyantes = a;
        this.participaciones = new ArrayList<>();
    }

    // ========== MÉTODOS CORREGIDOS ==========
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    // ========== MÉTODOS DE CÁLCULO DE PRECIOS ==========
    
    public void setPrecioDia() {
        float precio = 0;
        for (Parcela p : parcelas) {
            precio += p.getPrecio();
        }
        this.precioDia = precio;
    }
    
    public void setPrecioTotal(int dias, int descuento) {
        setPrecioDia();
        float p;
        if (dias > 15) {
            p = dias * precioDia * (1 - descuento / 100.0f);
        } else {
            p = dias * precioDia;
        }
        this.precioTotal = p;
    }
    
    public int calcularDiasEstancia() {
        if (inicioEstancia != null && finEstancia != null) {
            long diff = finEstancia.getTime() - inicioEstancia.getTime();
            return (int) (diff / (1000 * 60 * 60 * 24));
        }
        return 0;
    }
    
    // ========== MÉTODOS DE GESTIÓN ==========
    
    public boolean agregarParcela(Parcela parcela) {
        if (parcela.isLibre()) {
            parcelas.add(parcela);
            return true;
        }
        return false;
    }
    
    public boolean agregarTienda(Tienda tienda) {
        return tiendas.add(tienda);
    }
    
    public boolean agregarAcompanyante(String acompanyante) {
        return acompanyantes.add(acompanyante);
    }
    
    public boolean agregarParticipacion(Participacion participacion) {
        return participaciones.add(participacion);
    }
    
    // ========== GETTERS Y SETTERS ==========
    
    public int getIdReserva() {
        return idReserva;
    }
    
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
    
    public ArrayList<Parcela> getParcelas() {
        return new ArrayList<>(parcelas);
    }
    
    public ArrayList<Tienda> getTiendas() {
        return new ArrayList<>(tiendas);
    }
    
    public ArrayList<String> getAcompanyantes() {
        return new ArrayList<>(acompanyantes);
    }
    
    public ArrayList<Participacion> getParticipaciones() {
        return new ArrayList<>(participaciones);
    }
    
    public String getFechas(){
        return inicioReserva + " - " + finReserva;
    }
    
    @Override
    public String toString() {
        return "Reserva #" + idReserva + " - Cliente: " + cliente.getNombre() + 
               " - Fechas: " + getFechas() + " - Precio: " + precioTotal + "€";
    }
}