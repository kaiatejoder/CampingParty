package com.isii.campingmvc.model;

public class Parcela {
    private final int id;
    private final int m2;
    private final boolean electrificada;
    private final double precio;

    public Parcela(int id, int m2, boolean electrificada, double precio) {
        this.id = id;
        this.m2 = m2;
        this.electrificada = electrificada;
        this.precio = precio;
    }

    public int getId() { return id; }
    public int getM2() { return m2; }
    public boolean isElectrificada() { return electrificada; }
    public double getPrecio() { return precio; }
}
