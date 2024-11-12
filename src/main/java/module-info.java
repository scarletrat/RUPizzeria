module main.rupizzeria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens main.rupizzeria to javafx.fxml;
    exports main.rupizzeria;
}