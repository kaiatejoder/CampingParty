package com.campingparty.modelo;

import java.util.Date;

/**
 * Clase que representa una parcela del camping.
 * Contiene información sobre su tamaño, disponibilidad y precio.
 * 
 * @author Carla Terol
 */
public class Parcela {
    private int id;
    private float m2;
    private boolean luz;
    private float precio;
    private boolean ocupada;
    private boolean reservada;
    private Date fechaOcupacion;
    private Date fechaLiberacion;

    public Parcela(int id, float m2, boolean luz, float precio) {
        this.id = id;
        this.m2 = m2;
        this.luz = luz;
        this.precio = precio;
        this.ocupada = false;
        this.reservada = false;
    }

    /**
     * Libera la parcela (la deja disponible)
     */
    public void liberarParcela() {
        this.ocupada = false;
        this.reservada = false;
        this.fechaLiberacion = null;
        this.fechaOcupacion = null;
    }

    /**
     * Verifica si la parcela está libre
     */
    public boolean isLibre() {
        return !ocupada && !reservada;
    }

    /**
     * Marca la parcela como reservada
     */
    public void reservarParcela() {
        this.reservada = true;
    }

    /**
     * Marca la parcela como ocupada
     */
    public void ocuparParcela() {
        this.ocupada = true;
    }

    // Getters
    public int getId() {
        return id;
    }

    public float getM2() {
        return m2;
    }

    public boolean hayLuz() {
        return luz;
    }

    public float getPrecio() {
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

    public Date getFechaLiberacion() {
        return fechaLiberacion;
    }

    // Setters
    public void setM2(float m2) {
        this.m2 = m2;
    }

    public void setLuz(boolean luz) {
        this.luz = luz;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setFechaOcupacion(Date fechaOcupacion) {
        this.fechaOcupacion = fechaOcupacion;
    }

    public void setFechaLiberacion(Date fechaLiberacion) {
        this.fechaLiberacion = fechaLiberacion;
    }

    @Override
    public String toString() {
        return "Parcela{" +
                "id=" + id +
                ", m2=" + m2 +
                ", luz=" + luz +
                ", precio=" + precio +
                ", estado=" + (isLibre() ? "LIBRE" : (ocupada ? "OCUPADA" : "RESERVADA")) +
                '}';
    }
}


