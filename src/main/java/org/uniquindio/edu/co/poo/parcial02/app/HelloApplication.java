package org.uniquindio.edu.co.poo.parcial02.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Cargar el FXML desde la ruta correcta
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/uniquindio/edu/co/poo/parcial02/Principal.fxml"));

        // O si está en resources, usa esta ruta:
        // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/principal.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Sistema Inmobiliario");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}