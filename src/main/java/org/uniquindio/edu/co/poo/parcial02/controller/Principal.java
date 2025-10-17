package org.uniquindio.edu.co.poo.parcial02.controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import org.uniquindio.edu.co.poo.parcial02.app.HelloApplication;

public class Principal {
    @FXML
    private VBox contenedorPrincipal;

    public Principal() {
    }

    @FXML
    private void onMostarLista() {
        cargarVista("/org/uniquindio/edu/co/poo/parcial02/Tabla.fxml");
    }
/// "/org/uniquindio/edu/co/poo/parcial02/principal.fxml"
    @FXML
    private void onRegistrar() {
        cargarVista("/org/uniquindio/edu/co/poo/parcial02/RegistrarView.fxml");
    }
//
    private void cargarVista(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(rutaFXML));
            Parent vista = loader.load();

            this.contenedorPrincipal.getChildren().clear();
            this.contenedorPrincipal.getChildren().add(vista);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la vista: " + rutaFXML, AlertType.ERROR);
            e.printStackTrace();
        } catch (NullPointerException e) {
            mostrarAlerta("Error", "Recurso no encontrado: " + rutaFXML, AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public VBox getContenedorPrincipal() {
        return this.contenedorPrincipal;
    }

    @FXML
    private void onVolver() {
        this.volverAlDashboard();
    }

    private void volverAlDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/org/uniquindio/edu/co/poo/parcial02"));
            Parent dashboard = (Parent)loader.load();
            this.contenedorPrincipal.getChildren().clear();
            this.contenedorPrincipal.getChildren().add(dashboard);
        } catch (IOException e) {
            this.mostrarAlerta("Error", "No se pudo volver al dashboard", AlertType.ERROR);
            e.printStackTrace();
        }

    }
}