package Modelo;

import java.util.ArrayList;

public class Reservas {
    private ArrayList<Reserva> reservas;
    
    public Reservas(){
        this.reservas = new ArrayList<>();
    }
    
    public ArrayList<Reserva> getReservas(){
        return new ArrayList<>(reservas);
    }
    
    public Reserva getReserva(int index){
        if (index >= 0 && index < reservas.size()) {
            return reservas.get(index);
        }
        return null;
    }
    
    public void addReserva(Reserva r){
        reservas.add(r);
    }
    
    public void cancelarReserva(int index){
        if (index >= 0 && index < reservas.size()) {
            reservas.remove(index);
        }
    }
    
    public void finReserva(int index){
        if (index >= 0 && index < reservas.size()) {
            reservas.remove(index);
        }
    }
    
    public int size() {
        return reservas.size();
    }
    
    public boolean isEmpty() {
        return reservas.isEmpty();
    }
    
    public boolean remove(int index) {
        if (index >= 0 && index < reservas.size()) {
            reservas.remove(index);
            return true;
        }
        return false;
    }
}