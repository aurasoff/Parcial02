package org.uniquindio.edu.co.poo.parcial02.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

import javafx.util.Duration;
import org.uniquindio.edu.co.poo.parcial02.model.Inmueble;

public class Tablacontroller {
    private static Tablacontroller instance;

    public static Tablacontroller getInstance() {
        if (instance == null) {
            instance = new Tablacontroller();
        }
        return instance;
    }

    @FXML
    private TableView<Inmueble> tablaProductos;

    @FXML
    private TableColumn<Inmueble, String> colTipo;

    @FXML
    private TableColumn<Inmueble, String> colCiudad;

    @FXML
    private TableColumn<Inmueble, Integer> colHabitaciones;

    @FXML
    private TableColumn<Inmueble, Integer> colPisos;

    @FXML
    private TableColumn<Inmueble, Double> colPrecio;

    @FXML
    private Label lblTitulo;

    @FXML
    private Button btnEliminar;

    // 🟢 Lista observable para la tabla (Observer)
    private final ObservableList<Inmueble> inmueblesObservable = FXCollections.observableArrayList();

    // Lista normal por si necesitas acceder sin JavaFX
    private final ArrayList<Inmueble> listaInmuebles = new ArrayList<>();

    @FXML
    public void initialize() {
        configurarColumnas();
        tablaProductos.setItems(inmueblesObservable);
    }

    // 🟢 Configurar cómo se muestran los datos en cada columna
    private void configurarColumnas() {
        colTipo.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getClass().getSimpleName())
        );

        colCiudad.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(obtenerCiudad(cellData.getValue()))
        );

        colHabitaciones.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(obtenerHabitaciones(cellData.getValue())).asObject()
        );

        colPisos.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(obtenerPisos(cellData.getValue())).asObject()
        );

        colPrecio.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPrecio()).asObject()
        );
    }

    //  Métodos auxiliares
    private String obtenerCiudad(Inmueble inmueble) {
        try {
            return (String) inmueble.getClass().getMethod("getCiudad").invoke(inmueble);
        } catch (Exception e) {
            return "N/A";
        }
    }

    private int obtenerHabitaciones(Inmueble inmueble) {
        try {
            return (int) inmueble.getClass().getMethod("getNumHabitaciones").invoke(inmueble);
        } catch (Exception e) {
            return 0;
        }
    }

    private int obtenerPisos(Inmueble inmueble) {
        try {
            return (int) inmueble.getClass().getMethod("getNumPisos").invoke(inmueble);
        } catch (Exception e) {
            return 0;
        }
    }

    public void agregarInmueble(Inmueble inmueble) {
        inmueblesObservable.add(inmueble);
        listaInmuebles.add(inmueble);
    }

    @FXML
    private void onEliminarProducto() {
        Inmueble inmuebleSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();

        if (inmuebleSeleccionado != null) {
            inmueblesObservable.remove(inmuebleSeleccionado);
            listaInmuebles.remove(inmuebleSeleccionado);

            mostrarAlerta("Inmueble Eliminado", "El inmueble ha sido eliminado correctamente.", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Ningún Inmueble Seleccionado", "Selecciona un inmueble para eliminar.", Alert.AlertType.WARNING);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void onVolver() {
        try {
            // Cargar la vista principal (archivo Principal.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/parcial02/Principal.fxml"
            ));
            Parent vistaPrincipal = loader.load();

            // Obtener la escena actual desde la tabla
            Scene escenaActual = tablaProductos.getScene();

            // Efecto de transición (opcional)
            vistaPrincipal.setOpacity(0);
            escenaActual.setRoot(vistaPrincipal);

            FadeTransition ft = new FadeTransition(Duration.millis(300), vistaPrincipal);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();

        } catch (IOException e) {
            // Si algo falla al cargar la vista
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al volver");
            alerta.setHeaderText("No se pudo cargar la vista principal");
            alerta.setContentText("Verifica que el archivo Principal.fxml esté en la ruta correcta.");
            alerta.showAndWait();
            e.printStackTrace();
        }
    }


    public void limpiarTabla() {
        inmueblesObservable.clear();
        listaInmuebles.clear();
    }
}
