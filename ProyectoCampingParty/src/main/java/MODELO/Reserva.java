/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;
import java.util.Date;
import java.util.ArrayList;



/**
 *
 * @author HP
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
    private float precioTotal;

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
}
