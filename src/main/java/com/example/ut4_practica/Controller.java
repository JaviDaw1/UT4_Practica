package com.example.ut4_practica;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controlador para la vista de registro
 */
public class Controller {
    @FXML
    private TextField usuarioField, telefonoField;
    @FXML
    private PasswordField contrasenaField;
    @FXML
    private ComboBox<String> diaBox, mesBox, anioBox, prefijoBox, idiomaBox;
    @FXML
    private RadioButton masculinoBtn, femeninoBtn, otroBtn;
    @FXML
    private Label errorLabel;
    @FXML
    private Button enviarBtn;

    private Stage stage;

    /**
     * Inicializa la interfaz cargando valores en los componentes
     */
    public void initialize() {
        IntStream.rangeClosed(1, 31).forEach(d -> diaBox.getItems().add(String.valueOf(d)));
        mesBox.getItems().addAll("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        IntStream.rangeClosed(1900, 2024).forEach(a -> anioBox.getItems().add(String.valueOf(a)));
        prefijoBox.getItems().addAll("+34 (ES)", "+1 (US)", "+44 (UK)", "+49 (DE)");
        idiomaBox.getItems().addAll("Español", "Inglés", "Francés");
        enviarBtn.setOnAction(e -> validarFormulario());
        enviarBtn.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                enviarBtn.fire();
            }
        });
        idiomaBox.setOnAction(e -> cambiarIdioma(idiomaBox.getValue()));
    }

    private void validarFormulario() {
        boolean camposLlenos = !usuarioField.getText().isEmpty() &&
                !contrasenaField.getText().isEmpty() &&
                diaBox.getValue() != null &&
                mesBox.getValue() != null &&
                anioBox.getValue() != null &&
                prefijoBox.getValue() != null &&
                !telefonoField.getText().isEmpty();

        if (!camposLlenos) {
            errorLabel.setText("Por favor, rellena todos los campos.");
            errorLabel.setVisible(true);
        } else {
            errorLabel.setVisible(false);
            System.out.println("Formulario enviado con éxito");
        }
    }

    /**
     * Cambia el idioma de la interfaz según la selección que escoja el usuario
     * @param idioma idioma seleccionado
     */
    private void cambiarIdioma(String idioma) {
        Locale locale;
        switch (idioma) {
            case "Inglés":
                locale = new Locale("en", "US");
                break;
            case "Francés":
                locale = new Locale("fr", "FR");
                break;
            default:
                locale = new Locale("es", "ES");
                break;
        }

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle", locale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Registro.fxml"), bundle);
            Scene scene = new Scene(loader.load(), 400, 500);

            if (stage == null) {
                stage = (Stage) idiomaBox.getScene().getWindow();
            }
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegistroAction(ActionEvent event) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioSesion.fxml"), bundle);
            Scene scene = new Scene(loader.load(), 400, 500);

            if (stage == null) {
                stage = (Stage) idiomaBox.getScene().getWindow();
            }
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSalirAction(ActionEvent event) {
        System.out.println("Salir seleccionado");
        System.exit(0);
    }

    @FXML
    private void handleAcercaDeAction(ActionEvent event) {
        System.out.println("Acerca de seleccionado");
    }

    @FXML
    private void handleOpcionesAction(ActionEvent event) {
        System.out.println("Opciones seleccionadas");
    }

    @FXML
    private void handleConfiguracionAction(ActionEvent event) {
        System.out.println("Configuración seleccionada");
    }
}
