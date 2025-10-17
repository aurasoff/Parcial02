package org.uniquindio.edu.co.poo.parcial02.model;

import java.util.ArrayList;
import java.util.List;

// Patrón Facade - Proporciona una interfaz simplificada para el sistema de gestión de inmuebles
public class SistemaInmobiliarioFacade {
    private List<Inmueble> inmuebles;

    // Patrón Singleton - Una única instancia del Facade
    private static SistemaInmobiliarioFacade instance;

    private SistemaInmobiliarioFacade() {
        this.inmuebles = new ArrayList<>();
    }

    public static SistemaInmobiliarioFacade getInstance() {
        if (instance == null) {
            instance = new SistemaInmobiliarioFacade();
        }
        return instance;
    }

    // Método simplificado para registrar un inmueble - Facade
    public boolean registrarInmueble(String tipo, double precio, String ciudad,
                                     int numHabitaciones, int numPisos) {
        try {
            // Usar el enum TipoInmueble directamente
            TipoInmueble tipoInmueble = TipoInmueble.valueOf(tipo.toUpperCase());
            Inmueble inmueble = InmuebleFactory.crearInmueble(tipoInmueble, precio, ciudad, numHabitaciones, numPisos);

            // Agregar a la lista de inmuebles
            inmuebles.add(inmueble);
            return true;

        } catch (IllegalArgumentException e) {
            System.err.println("Tipo de inmueble no válido: " + tipo);
            return false;
        } catch (Exception e) {
            System.err.println("Error al registrar inmueble: " + e.getMessage());
            return false;
        }
    }

    // Método sobrecargado que acepta el enum directamente
    public boolean registrarInmueble(TipoInmueble tipo, double precio, String ciudad,
                                     int numHabitaciones, int numPisos) {
        try {
            Inmueble inmueble = InmuebleFactory.crearInmueble(tipo, precio, ciudad, numHabitaciones, numPisos);
            inmuebles.add(inmueble);
            return true;

        } catch (Exception e) {
            System.err.println("Error al registrar inmueble: " + e.getMessage());
            return false;
        }
    }

    // Método para registrar un Local usando el Builder pattern
    public boolean registrarLocalConBuilder(String ciudad, int numHabitaciones, int numPisos, double precio) {
        try {
            Local local = new Local.Builder()
                    .setCiudad(ciudad)
                    .setNumHabitaciones(numHabitaciones)
                    .setNumPisos(numPisos)
                    .setPrecio(precio)
                    .build();

            inmuebles.add(local);
            return true;

        } catch (Exception e) {
            System.err.println("Error al registrar local con Builder: " + e.getMessage());
            return false;
        }
    }

    // Método simplificado para obtener todos los inmuebles - Facade
    public List<Inmueble> obtenerTodosLosInmuebles() {
        return new ArrayList<>(inmuebles);
    }

    // Método simplificado para eliminar un inmueble - Facade
    public boolean eliminarInmueble(Inmueble inmueble) {
        return inmuebles.remove(inmueble);
    }

    // Método simplificado para eliminar inmueble por índice - Facade
    public boolean eliminarInmueblePorIndice(int indice) {
        if (indice >= 0 && indice < inmuebles.size()) {
            inmuebles.remove(indice);
            return true;
        }
        return false;
    }

    // Método simplificado para buscar inmuebles por ciudad - Facade
    public List<Inmueble> buscarPorCiudad(String ciudad) {
        List<Inmueble> resultado = new ArrayList<>();
        for (Inmueble inmueble : inmuebles) {
            if (obtenerCiudad(inmueble).equalsIgnoreCase(ciudad)) {
                resultado.add(inmueble);
            }
        }
        return resultado;
    }

    // Método auxiliar para obtener la ciudad de cualquier tipo de inmueble
    private String obtenerCiudad(Inmueble inmueble) {
        if (inmueble instanceof Casa) {
            return ((Casa) inmueble).getCiudad();
        } else if (inmueble instanceof Local) {
            return ((Local) inmueble).getCiudad();
        } else if (inmueble instanceof Apartamento) {
            return ((Apartamento) inmueble).getCiudad();
        } else if (inmueble instanceof Finca) {
            return ((Finca) inmueble).getCiudad();
        }
        return "";
    }

    // Método simplificado para obtener inmuebles por tipo - Facade
    public List<Inmueble> buscarPorTipo(TipoInmueble tipo) {
        List<Inmueble> resultado = new ArrayList<>();
        for (Inmueble inmueble : inmuebles) {
            if (inmueble.getClass().getSimpleName().equalsIgnoreCase(tipo.name())) {
                resultado.add(inmueble);
            }
        }
        return resultado;
    }

    // Método para obtener solo los Locales - Facade
    public List<Local> obtenerLocales() {
        List<Local> locales = new ArrayList<>();
        for (Inmueble inmueble : inmuebles) {
            if (inmueble instanceof Local) {
                locales.add((Local) inmueble);
            }
        }
        return locales;
    }

    // Método para obtener inmuebles por rango de precio - Facade
    public List<Inmueble> buscarPorRangoPrecio(double precioMin, double precioMax) {
        List<Inmueble> resultado = new ArrayList<>();
        for (Inmueble inmueble : inmuebles) {
            if (inmueble.getPrecio() >= precioMin && inmueble.getPrecio() <= precioMax) {
                resultado.add(inmueble);
            }
        }
        return resultado;
    }

    // Método para limpiar todos los inmuebles - Facade
    public void limpiarInmuebles() {
        inmuebles.clear();
    }

    // Método para obtener el número total de inmuebles - Facade
    public int getTotalInmuebles() {
        return inmuebles.size();
    }

    // Método para obtener el número de Locales - Facade
    public int getTotalLocales() {
        return obtenerLocales().size();
    }

    // Método para verificar si hay inmuebles registrados - Facade
    public boolean hayInmueblesRegistrados() {
        return !inmuebles.isEmpty();
    }

    // Método para obtener un inmueble por índice - Facade
    public Inmueble obtenerInmueble(int indice) {
        if (indice >= 0 && indice < inmuebles.size()) {
            return inmuebles.get(indice);
        }
        return null;
    }

    // Método para obtener los tipos de inmueble disponibles
    public TipoInmueble[] obtenerTiposInmueble() {
        return TipoInmueble.values();
    }
}