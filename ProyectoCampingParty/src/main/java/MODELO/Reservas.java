/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.util.ArrayList;



/**
 *
 * @author Carla Terol
 */
public class Reservas {
    private ArrayList<Reserva> reservas;
    public Reservas(){
        this.reservas = new ArrayList<>();
    }
    public ArrayList<Reserva> getReservas(){
        return reservas;
    }
    public void addReserva(Reserva r){
        reservas.add(r);
    }
    public void cancelarReserva(int index){
        reservas.remove(index);
    }
    public void finReserva(int index){
        //POR AHORA HACEMOS LO MISMO QUE CANCELAR, PERO EN UN FUTURO ESTA SERÁ PARA ANTES DE BORRARLA
        //AÑADIRLA AL HISTÓRICO DE RESERVAS
        reservas.remove(index);
    }

}
