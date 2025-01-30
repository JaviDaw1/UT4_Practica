module com.example.ut4_practica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ut4_practica to javafx.fxml;
    exports com.example.ut4_practica;
}