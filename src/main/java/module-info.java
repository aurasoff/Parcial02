module org.uniquindio.edu.co.poo.parcial02 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uniquindio.edu.co.poo.parcial02 to javafx.fxml;
    exports org.uniquindio.edu.co.poo.parcial02;
}