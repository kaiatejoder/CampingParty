package com.campingparty.controlador;

import com.campingparty.modelo.*;
import java.util.Date;

/**
 * CONTROLADOR DE RESERVAS - Gestiona la lógica de creación y modificación de reservas.
 * 
 * Responsabilidades:
 * - Validar fechas de reserva
 * - Gestionar parcelas seleccionadas
 * - Gestionar acompañantes
 * - Calcular precios
 * - Crear/modificar reservas
 * 
 * @author Carla Terol
 */
public class ControladorReserva {
    private Modelo modelo;

    public ControladorReserva(Modelo modelo) {
        this.modelo = modelo;
    }

    /**
     * Valida que las fechas sean correctas
     */
    public boolean validarFechas(Date fechaInicio, Date fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            return false;
        }
        return fechaFin.after(fechaInicio);
    }

    /**
     * Valida que al menos una parcela esté seleccionada
     */
    public boolean validarParcelas(Reserva reserva) {
        return reserva.getNumParcelas() > 0;
    }

    /**
     * Obtiene las parcelas disponibles para un rango de fechas
     */
    public Parcela[] getParcelasDisponibles(Date fechaInicio, Date fechaFin) {
        return modelo.getParcelasDisponibles(fechaInicio, fechaFin)
                .toArray(new Parcela[0]);
    }

    /**
     * Añade una parcela a la reserva
     */
    public void addParcelaAReserva(Reserva reserva, int idParcela) {
        Parcela p = modelo.getParcela(idParcela);
        if (p != null && p.isLibre()) {
            reserva.addParcela(p);
        }
    }

    /**
     * Elimina una parcela de la reserva
     */
    public void removeParcelaDeReserva(Reserva reserva, int idParcela) {
        Parcela p = modelo.getParcela(idParcela);
        if (p != null) {
            reserva.removeParcela(p);
        }
    }

    /**
     * Añade un acompañante a la reserva
     */
    public void addAcompanyanteAReserva(Reserva reserva, String nombre, String dni, int edad) {
        Acompanyante a = new Acompanyante(nombre, dni, edad);
        reserva.addAcompanyante(a);
    }

    /**
     * Añade una tienda a la reserva
     */
    public void addTiendaAReserva(Reserva reserva, String nombre, float m2) {
        Tienda t = new Tienda(nombre, m2);
        reserva.addTienda(t);
    }

    /**
     * Calcula el precio total de una reserva
     */
    public float calcularPrecio(Reserva reserva) {
        return reserva.getPrecioTotal();
    }

    /**
     * Crea una nueva reserva vacía
     */
    public Reserva crearReservaVacia(Cliente cliente, Date fechaInicio, Date fechaFin) {
        return new Reserva(fechaInicio, fechaFin, cliente);
    }

    /**
     * Valida una reserva completa antes de confirmarla
     */
    public boolean validarReserva(Reserva reserva) {
        return reserva != null &&
               validarParcelas(reserva) &&
               validarFechas(reserva.getFechaInicio(), reserva.getFechaFin());
    }

    /**
     * Confirma una reserva
     */
    public boolean confirmarReserva(Reserva reserva) {
        if (validarReserva(reserva)) {
            return modelo.confirmarReserva(reserva);
        }
        return false;
    }

    /**
     * Cancela una reserva
     */
    public void cancelarReserva(Reserva reserva) {
        modelo.removeReserva(reserva);
    }
}


