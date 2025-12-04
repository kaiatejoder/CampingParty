package com.campingparty.modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa una actividad del camping.
 * Las actividades tienen participantes y pueden ser de diferentes tipos.
 * 
 * @author Carla Terol
 */
public class Actividad {
    private int idActividad;
    private String titulo;
    private String descripcion;
    private String ubicacion;
    private int tipo; // 0: Piscina, 1: Fronton, 2: Club Social, 3: Plantilla
    private Date fechaHora;
    private int maxParticipantes;
    private ArrayList<Cliente> participantes;
    private Cliente ganador;

    public static final String[] TIPOS_ACTIVIDAD = {
        "Piscina",
        "Fronton",
        "Club Social",
        "Plantilla"
    };

    public Actividad(int idActividad, int tipo, Date fechaHora, int maxParticipantes) {
        this.idActividad = idActividad;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
        this.maxParticipantes = maxParticipantes;
        this.participantes = new ArrayList<>();
        this.ubicacion = getTipoAsString(tipo);
    }

    public Actividad(int idActividad, int tipo, int maxParticipantes, String titulo, String descripcion, String ubicacion) {
        this.idActividad = idActividad;
        this.tipo = tipo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.maxParticipantes = maxParticipantes;
        this.participantes = new ArrayList<>();
    }

    public Actividad(int idActividad, int tipo, Date fechaHora, int maxParticipantes, String titulo, String descripcion, String ubicacion) {
        this.idActividad = idActividad;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.maxParticipantes = maxParticipantes;
        this.participantes = new ArrayList<>();
    }

    // Getters
    public int getIdActividad() {
        return idActividad;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getTipo() {
        return tipo;
    }

    public String getTipoAsString() {
        return getTipoAsString(tipo);
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public ArrayList<Cliente> getParticipantes() {
        return participantes;
    }

    public int getNumParticipantes() {
        return participantes.size();
    }

    public Cliente getGanador() {
        return ganador;
    }

    public boolean estaLlena() {
        return participantes.size() >= maxParticipantes;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setTipo(int tipo) {
        if (tipo >= 0 && tipo < TIPOS_ACTIVIDAD.length) {
            this.tipo = tipo;
        }
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        this.maxParticipantes = maxParticipantes;
    }

    public void setGanador(Cliente ganador) {
        this.ganador = ganador;
    }

    // Operaciones con participantes
    public boolean agregarParticipante(Cliente c) {
        if (!estaLlena() && !participantes.contains(c)) {
            participantes.add(c);
            return true;
        }
        return false;
    }

    public boolean eliminarParticipante(Cliente c) {
        return participantes.remove(c);
    }

    public boolean contieneParticipante(Cliente c) {
        return participantes.contains(c);
    }

    // Métodos útiles
    public static String getTipoAsString(int tipo) {
        if (tipo >= 0 && tipo < TIPOS_ACTIVIDAD.length) {
            return TIPOS_ACTIVIDAD[tipo];
        }
        return "DESCONOCIDO";
    }

    public static int getTipoFromString(String tipoStr) {
        for (int i = 0; i < TIPOS_ACTIVIDAD.length; i++) {
            if (TIPOS_ACTIVIDAD[i].equals(tipoStr)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return getTipoAsString() + " - " + (fechaHora != null ? fechaHora.toString() : "Sin fecha") +
                " (" + participantes.size() + "/" + maxParticipantes + ")";
    }
}


