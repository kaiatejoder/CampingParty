package com.isii.campingmvc.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private final LocalDate fechaIn;
    private final LocalDate fechaOut;
    private final Cliente cliente;
    private final List<Integer> parcelas = new ArrayList<>();

    public Reserva(LocalDate in, LocalDate out, Cliente c) {
        this.fechaIn = in;
        this.fechaOut = out;
        this.cliente = c;
    }

    public LocalDate getFechaIn() { return fechaIn; }
    public LocalDate getFechaOut() { return fechaOut; }
    public Cliente getCliente() { return cliente; }
    public List<Integer> getParcelas() { return parcelas; }

    public void addParcela(int index) { parcelas.add(index); }
}
