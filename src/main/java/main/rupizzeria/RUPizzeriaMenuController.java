package main.rupizzeria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class RUPizzeriaMenuController {
    private RUPizzeriaMainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;


    /**
     * Get the reference to the MainController object.
     * We can call any public method defined in the controller through the reference.
     */
    public void setMainController (RUPizzeriaMainController controller,
                                   Stage stage,
                                   Stage primaryStage,
                                   Scene primaryScene) {
        mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

    @FXML
    /**
     * Navigate back to the main view.
     */
    public void displayCart() {
        mainController.displayCartView();
    }

    @FXML
    /**
     * Navigate back to the main view.
     */
    public void displayMain() {
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        primaryStage.setTitle("Welcome to RUPizzeria!");
    }

}
