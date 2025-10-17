package org.uniquindio.edu.co.poo.parcial02.model;

public class Finca extends Inmueble{
        private String ciudad;
        private int numHabitaciones;
        private int numPisos;
        private double precio;

    public Finca(double precio, String ciudad, int numHabitaciones, int numPisos) {
        this.precio = precio;
        this.ciudad = ciudad;
        this.numHabitaciones = numHabitaciones;
        this.numPisos = numPisos;
    }

    public Finca(Builder builder) {
        this.precio = builder.precio;
        this.ciudad = builder.ciudad;
        this.numHabitaciones = builder.numHabitaciones;
        this.numPisos = builder.numPisos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public int getNumPisos() {
        return numPisos;
    }

    // Builder pattern para construcción flexible
    public static class Builder {
        private String ciudad;
        private int numHabitaciones;
        private int numPisos;
        private double precio;

        public Builder setCiudad(String ciudad) {
            this.ciudad = ciudad;
            return this;
        }

        public Builder setNumHabitaciones(int numHabitaciones) {
            this.numHabitaciones = numHabitaciones;
            return this;
        }

        public Builder setNumPisos(int numPisos) {
            this.numPisos = numPisos;
            return this;
        }

        public Builder setPrecio(double precio) {
            this.precio = precio;
            return this;
        }

        public Finca build() {
            return new Finca(this);
        }
    }
}