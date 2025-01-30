package com.example.ut4_practica;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;

/**
 * Clase principal que inicia la aplicación
 */
public class Start extends Application {
    /**
     * Inicia la aplicación y carga la vista
     * @param stage Escenario principal.
     */
    @Override
    public void start(Stage stage) throws IOException {
        mostrarSplashScreen();
        Locale locale = Locale.getDefault();


        ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle", locale);
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("InicioSesion.fxml"), bundle);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setTitle(bundle.getString("app.registro"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Metodo para mostrar la pantalla de carga
     */
    private void mostrarSplashScreen() {
        SwingUtilities.invokeLater(() -> {
            SplashScreen splash = new SplashScreen();
            Thread splashThread = new Thread(splash);
            splashThread.start();

            try {
                splashThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
