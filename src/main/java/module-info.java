module main.rupizzeria {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.rupizzeria to javafx.fxml;
    exports main.rupizzeria;
}