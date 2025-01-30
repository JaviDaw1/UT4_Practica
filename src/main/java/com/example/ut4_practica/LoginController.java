package com.example.ut4_practica;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de inicio de sesión
 */
public class LoginController {
    @FXML
    private TextField usuarioField;
    @FXML
    private ComboBox<String> idiomaBox;
    @FXML
    private PasswordField contrasenaField;
    @FXML
    private Button loginBtn;
    @FXML
    private Label errorLabel;
    @FXML
    private Hyperlink registroLink;

    private Stage stage;

    /**
     * Inicializa la interfaz cargando valores en los componentes
     */
    @FXML
    public void initialize() {
        errorLabel.setVisible(false);
        idiomaBox.getItems().addAll("Español", "Inglés", "Francés");
        idiomaBox.setOnAction(e -> cambiarIdioma(idiomaBox.getValue()));
        loginBtn.setOnAction(event -> iniciarSesion());
        loginBtn.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                loginBtn.fire();
            }
        });
        registroLink.setOnAction(event -> abrirRegistro());
    }

    /**
     * Valida el formulario asegurando que todos los campos estén completos
     */
    private void iniciarSesion() {
        String usuario = usuarioField.getText();
        String contrasena = contrasenaField.getText();

        if (validarCredenciales(usuario, contrasena)) {
            errorLabel.setVisible(false);
            System.out.println("Inicio de sesión exitoso para el usuario: " + usuario);
        } else {
            errorLabel.setText("Usuario o contraseña incorrectos.");
            errorLabel.setVisible(true);
        }
    }

    private boolean validarCredenciales(String usuario, String contrasena) {
        return "admin".equals(usuario) && "1234".equals(contrasena);
    }

    /**
     * Cambia el idioma de la aplicación
     * @param idioma Idioma seleccionado.
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

    private void abrirRegistro() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle", Locale.getDefault());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Registro.fxml"), bundle);
            Scene registroScene = new Scene(loader.load(), 400, 500);

            if (stage == null) {
                stage = (Stage) registroLink.getScene().getWindow();
            }
            stage.setScene(registroScene);
            stage.setTitle("Registro");
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Error al cargar la vista de registro.");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void handleRegistroAction(ActionEvent event) {
        System.out.println("Inicio sesión seleccionado");
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
