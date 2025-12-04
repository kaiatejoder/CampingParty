package com.campingparty.modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa una reserva en el camping.
 * Una reserva contiene parcelas, tiendas, acompañantes y el cliente que la hizo.
 * 
 * @author Carla Terol
 */
public class Reserva {
    private int idReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private Cliente cliente;
    private ArrayList<Parcela> parcelas;
    private ArrayList<Tienda> tiendas;
    private ArrayList<Acompanyante> acompanyantes;
    private float precioPorDia;
    private float precioTotal;
    private static int contadorId = 0;

    public Reserva(Date fechaInicio, Date fechaFin, Cliente cliente) {
        this.idReserva = ++contadorId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente = cliente;
        this.parcelas = new ArrayList<>();
        this.tiendas = new ArrayList<>();
        this.acompanyantes = new ArrayList<>();
        this.precioPorDia = 0;
        this.precioTotal = 0;
    }

    public Reserva(int idReserva, Date fechaInicio, Date fechaFin, Cliente cliente,
                   String parcelas, String tiendas, String acompanyantes) {
        this.idReserva = idReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente = cliente;
        this.parcelas = parcelas != null ? parcelas : new ArrayList<>();
        this.tiendas = tiendas != null ? tiendas : new ArrayList<>();
        this.acompanyantes = acompanyantes != null ? acompanyantes : new ArrayList<>();
        this.precioPorDia = 0;
        this.precioTotal = 0;
    }
    public ArrayList<Parcela> getParcelas(String s){
        ArrayList<Parcela> res;
        
    }
    public String parcelastoString(){
        String res;
        res = "[";
        for(int i=0; i < parcelas.size()- 1; i++)
            res = res + Integer.toString(parcelas.get(i).getId()) + ",";
        res+= Integer.toString(parcelas.getLast().getId());
         res += "]";
         return res;
    }
    
    public Reserva(int idReserva, Date fechaInicio, Date fechaFin, Cliente cliente,
                   ArrayList<Parcela> parcelas, ArrayList<Tienda> tiendas, ArrayList<Acompanyante> acompanyantes) {
        this.idReserva = idReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente = cliente;
        this.parcelas = parcelas != null ? parcelas : new ArrayList<>();
        this.tiendas = tiendas != null ? tiendas : new ArrayList<>();
        this.acompanyantes = acompanyantes != null ? acompanyantes : new ArrayList<>();
        this.precioPorDia = 0;
        this.precioTotal = 0;
    }

    // Getters
    public int getIdReserva() {
        return idReserva;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Parcela> getParcelas() {
        return parcelas;
    }

    public ArrayList<Tienda> getTiendas() {
        return tiendas;
    }

    public ArrayList<Acompanyante> getAcompanyantes() {
        return acompanyantes;
    }

    public float getPrecioPorDia() {
        return precioPorDia;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public int getNumDias() {
        if (fechaInicio != null && fechaFin != null) {
            long diferencia = fechaFin.getTime() - fechaInicio.getTime();
            return (int) (diferencia / (1000 * 60 * 60 * 24)) + 1;
        }
        return 0;
    }

    // Setters
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setPrecioPorDia(float precioPorDia) {
        this.precioPorDia = precioPorDia;
        calcularPrecioTotal();
    }

    // Operaciones con parcelas
    public void addParcela(Parcela p) {
        if (!parcelas.contains(p)) {
            parcelas.add(p);
            p.reservarParcela();
            actualizarPrecio();
        }
    }

    public void removeParcela(Parcela p) {
        if (parcelas.remove(p)) {
            p.liberarParcela();
            actualizarPrecio();
        }
    }

    public void removeParcela(int indice) {
        if (indice >= 0 && indice < parcelas.size()) {
            Parcela p = parcelas.remove(indice);
            p.liberarParcela();
            actualizarPrecio();
        }
    }

    public int getNumParcelas() {
        return parcelas.size();
    }

    // Operaciones con tiendas
    public void addTienda(Tienda t) {
        if (!tiendas.contains(t)) {
            tiendas.add(t);
        }
    }

    public void removeTienda(Tienda t) {
        tiendas.remove(t);
    }

    public int getNumTiendas() {
        return tiendas.size();
    }

    // Operaciones con acompañantes
    public void addAcompanyante(Acompanyante a) {
        if (!acompanyantes.contains(a)) {
            acompanyantes.add(a);
        }
    }

    public void removeAcompanyante(Acompanyante a) {
        acompanyantes.remove(a);
    }

    public int getNumAcompanyantes() {
        return acompanyantes.size();
    }

    // Cálculos
    private void actualizarPrecio() {
        float precioParcelasXDia = 0;
        for (Parcela p : parcelas) {
            precioParcelasXDia += p.getPrecio();
        }
        this.precioPorDia = precioParcelasXDia;
        calcularPrecioTotal();
    }

    private void calcularPrecioTotal() {
        int dias = getNumDias();
        if (dias > 0) {
            this.precioTotal = precioPorDia * dias;
        }
    }

    public float calcularPrecioTotalPublico() {
        actualizarPrecio();
        return precioTotal;
    }

    // Información
    public String getFechasFormato() {
        return fechaInicio + " a " + fechaFin;
    }

    public String getResumenReserva() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reserva #").append(idReserva).append("\n");
        sb.append("Cliente: ").append(cliente.getNombre()).append("\n");
        sb.append("Fechas: ").append(getFechasFormato()).append("\n");
        sb.append("Parcelas: ").append(parcelas.size()).append("\n");
        sb.append("Acompañantes: ").append(acompanyantes.size()).append("\n");
        sb.append("Tiendas: ").append(tiendas.size()).append("\n");
        sb.append("Precio Total: €").append(String.format("%.2f", precioTotal));
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + idReserva +
                ", fechas=" + getFechasFormato() +
                ", cliente=" + cliente.getNombre() +
                ", parcelas=" + parcelas.size() +
                ", precio=" + String.format("%.2f", precioTotal) + "€" +
                '}';
    }
}


