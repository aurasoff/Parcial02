package org.uniquindio.edu.co.poo.parcial02.model;

public abstract class Inmueble {
    protected String ciudad;
    protected int numHabitaciones;
    protected int numPisos;
    protected double precio;

    public String getCiudad() {
        return ciudad;
    }

    public int getHabitaciones() {
        return numHabitaciones;
    }

    public int getPisos() {
        return numPisos;
    }

    public double getPrecio() {
        return precio;

    }

        @Override
        public String toString() {
            return "Ciudad: " + ciudad + ", Habitaciones: " + numHabitaciones +
                    ", Pisos: " + numPisos + ", Precio: $" + precio;

    }
}
