package org.uniquindio.edu.co.poo.parcial02.model;

// Patrón Factory Method - Para crear diferentes tipos de inmuebles
public class InmuebleFactory {
    private TipoInmueble tipo;

    // Método factory para crear inmuebles según el tipo
    public static Inmueble crearInmueble(TipoInmueble tipo, double precio, String ciudad,
                                         int numHabitaciones, int numPisos) {
        // Patrón Factory Method - Creación de objetos basada en el tipo
        switch (tipo) {
            case CASA:
                return new Casa(precio, ciudad, numHabitaciones, numPisos);
            case APARTAMENTO:
                return new Apartamento(precio, ciudad, numHabitaciones, numPisos);
            case FINCA:
                return new Finca(precio, ciudad, numHabitaciones, numPisos);
            case LOCAL:
                return new Local(precio, ciudad, numHabitaciones, numPisos);
            default:
                throw new IllegalArgumentException("Tipo de inmueble no válido: " + tipo);
        }
    }
}

// Clases concretas para cada tipo de inmueble
class Casa extends Inmueble {
    private String ciudad;
    private int numHabitaciones;
    private int numPisos;

    public Casa(double precio, String ciudad, int numHabitaciones, int numPisos) {
        this.precio = precio;
        this.ciudad = ciudad;
        this.numHabitaciones = numHabitaciones;
        this.numPisos = numPisos;
    }

    // Getters y setters
    public String getCiudad() { return ciudad; }
    public int getNumHabitaciones() { return numHabitaciones; }
    public int getNumPisos() { return numPisos; }
}

class Apartamento extends Inmueble {
    private String ciudad;
    private int numHabitaciones;
    private int numPisos;

    public Apartamento(double precio, String ciudad, int numHabitaciones, int numPisos) {
        this.precio = precio;
        this.ciudad = ciudad;
        this.numHabitaciones = numHabitaciones;
        this.numPisos = numPisos;
    }

    // Getters y setters
    public String getCiudad() { return ciudad; }
    public int getNumHabitaciones() { return numHabitaciones; }
    public int getNumPisos() { return numPisos; }
}

class Finca extends Inmueble {
    private String ciudad;
    private int numHabitaciones;
    private int numPisos;

    public Finca(double precio, String ciudad, int numHabitaciones, int numPisos) {
        this.precio = precio;
        this.ciudad = ciudad;
        this.numHabitaciones = numHabitaciones;
        this.numPisos = numPisos;
    }

    // Getters y setters
    public String getCiudad() { return ciudad; }
    public int getNumHabitaciones() { return numHabitaciones; }
    public int getNumPisos() { return numPisos; }
}

class Local extends Inmueble {
    private String ciudad;
    private int numHabitaciones;
    private int numPisos;

    public Local(double precio, String ciudad, int numHabitaciones, int numPisos) {
        this.precio = precio;
        this.ciudad = ciudad;
        this.numHabitaciones = numHabitaciones;
        this.numPisos = numPisos;
    }

    // Getters y setters
    public String getCiudad() { return ciudad; }
    public int getNumHabitaciones() { return numHabitaciones; }
    public int getNumPisos() { return numPisos; }
}