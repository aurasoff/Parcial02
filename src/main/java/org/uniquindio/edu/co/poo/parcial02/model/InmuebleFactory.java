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
                return new Casa.Builder()
                        .setCiudad(ciudad)
                        .setNumHabitaciones(numHabitaciones)
                        .setNumPisos(numPisos)
                        .setPrecio(precio)
                        .build();

            case APARTAMENTO:
                return new Apartamento.Builder()
                        .setCiudad(ciudad)
                        .setNumHabitaciones(numHabitaciones)
                        .setNumPisos(numPisos)
                        .setPrecio(precio)
                        .build();

            case FINCA:
                return new Finca.Builder()
                        .setCiudad(ciudad)
                        .setNumHabitaciones(numHabitaciones)
                        .setNumPisos(numPisos)
                        .setPrecio(precio)
                        .build();

            case LOCAL:
                return new Local.Builder()
                        .setCiudad(ciudad)
                        .setNumHabitaciones(numHabitaciones)
                        .setNumPisos(numPisos)
                        .setPrecio(precio)
                        .build();

            default:
                throw new IllegalArgumentException("Tipo de inmueble no válido: " + tipo);
        }
    }
}






