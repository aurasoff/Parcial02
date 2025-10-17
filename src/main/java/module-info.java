module org.uniquindio.edu.co.poo.parcial02 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uniquindio.edu.co.poo.parcial02 to javafx.fxml;
    exports org.uniquindio.edu.co.poo.parcial02.app;
    exports org.uniquindio.edu.co.poo.parcial02.controller;
    opens org.uniquindio.edu.co.poo.parcial02.controller to javafx.fxml;
    opens org.uniquindio.edu.co.poo.parcial02.app to javafx.fxml;
}