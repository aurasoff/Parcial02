package org.uniquindio.edu.co.poo.parcial02.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import org.uniquindio.edu.co.poo.parcial02.app.HelloApplication;
import org.uniquindio.edu.co.poo.parcial02.model.SistemaInmobiliarioFacade;

import java.io.IOException;

public class RegistrarController {

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtHabitaciones;

    @FXML
    private TextField txtPisos;

    @FXML
    private TextField txtCiudad;

    @FXML
    private ComboBox<String> cbTipo;

    // @FXML
    // private VBox contenedorPrincipal;

    private SistemaInmobiliarioFacade sistemaInmobiliario;

    @FXML
    private void initialize() {
        // Inicializar el Facade - Patrón Singleton
        sistemaInmobiliario = SistemaInmobiliarioFacade.getInstance();

        // Inicializar el ComboBox
        if (cbTipo != null) {
            cbTipo.getItems().addAll("CASA", "APARTAMENTO", "FINCA", "LOCAL");
        }
    }

    @FXML
    private void onRegistrar() {
        try {
            // Validar campos obligatorios
            if (!validarCampos()) {
                return;
            }

            // Obtener datos del formulario
            double precio = Double.parseDouble(txtPrecio.getText());
            String ciudad = txtCiudad.getText();
            int numHabitaciones = Integer.parseInt(txtHabitaciones.getText());
            int numPisos = Integer.parseInt(txtPisos.getText());

            // Obtener tipo del ComboBox
            String tipo = (cbTipo != null && cbTipo.getValue() != null) ?
                    cbTipo.getValue() : "CASA";

            // Usar el Facade para registrar el inmueble - Patrón Facade
            boolean exito = sistemaInmobiliario.registrarInmueble(tipo, precio, ciudad, numHabitaciones, numPisos);

            if (exito) {
                mostrarAlerta("Éxito", "Inmueble registrado correctamente", AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No se pudo registrar el inmueble", AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Por favor ingrese valores numéricos válidos en precio, habitaciones y pisos", AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al registrar el inmueble: " + e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void onVolver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/parcial02/Principal.fxml"
            ));
            Parent vistaPrincipal = loader.load();

            // Obtener la escena actual desde cualquier componente
            javafx.scene.Scene escenaActual = txtPrecio.getScene();

            if (escenaActual != null) {
                escenaActual.setRoot(vistaPrincipal);
            } else {
                mostrarAlerta("Error", "No se pudo volver al menú principal", AlertType.ERROR);
            }

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la vista principal: " + e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        } catch (NullPointerException e) {
            mostrarAlerta("Error", "Archivo principal.fxml no encontrado", AlertType.ERROR);
            e.printStackTrace();
        }
    }

 // private void cargarVista(String rutaFXML) { ... }

    private boolean validarCampos() {
        StringBuilder errores = new StringBuilder();

        // Validar precio
        if (txtPrecio.getText().isEmpty()) {
            errores.append("• El precio es obligatorio\n");
        } else {
            try {
                double precio = Double.parseDouble(txtPrecio.getText());
                if (precio <= 0) {
                    errores.append("• El precio debe ser mayor a 0\n");
                }
            } catch (NumberFormatException e) {
                errores.append("• El precio debe ser un número válido\n");
            }
        }

        // Validar ciudad
        if (txtCiudad.getText().isEmpty()) {
            errores.append("• La ciudad es obligatoria\n");
        }

        // Validar habitaciones
        if (txtHabitaciones.getText().isEmpty()) {
            errores.append("• El número de habitaciones es obligatorio\n");
        } else {
            try {
                int habitaciones = Integer.parseInt(txtHabitaciones.getText());
                if (habitaciones < 0) {
                    errores.append("• El número de habitaciones no puede ser negativo\n");
                }
            } catch (NumberFormatException e) {
                errores.append("• El número de habitaciones debe ser un número entero\n");
            }
        }

        // Validar pisos
        if (txtPisos.getText().isEmpty()) {
            errores.append("• El número de pisos es obligatorio\n");
        } else {
            try {
                int pisos = Integer.parseInt(txtPisos.getText());
                if (pisos < 0) {
                    errores.append("• El número de pisos no puede ser negativo\n");
                }
            } catch (NumberFormatException e) {
                errores.append("• El número de pisos debe ser un número entero\n");
            }
        }

        // Validar tipo de inmueble
        if (cbTipo.getValue() == null) {
            errores.append("• Debe seleccionar un tipo de inmueble\n");
        }

        if (errores.length() > 0) {
            mostrarAlerta("Error de Validación", errores.toString(), AlertType.ERROR);
            return false;
        }

        return true;
    }

    private void limpiarCampos() {
        txtPrecio.clear();
        txtCiudad.clear();
        txtHabitaciones.clear();
        txtPisos.clear();
        if (cbTipo != null) {
            cbTipo.setValue(null);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // public void setContenedorPrincipal(VBox contenedorPrincipal) { ... }

    // Getters para testing
    public TextField getTxtPrecio() {
        return txtPrecio;
    }

    public TextField getTxtHabitaciones() {
        return txtHabitaciones;
    }

    public TextField getTxtPisos() {
        return txtPisos;
    }

    public TextField getTxtCiudad() {
        return txtCiudad;
    }

    public ComboBox<String> getCbTipo() {
        return cbTipo;
    }
}