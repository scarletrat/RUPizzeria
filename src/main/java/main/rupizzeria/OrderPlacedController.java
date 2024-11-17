package main.rupizzeria;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class OrderPlacedController {
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
    public void displayMain() {
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        primaryStage.setTitle("Welcome to RUPizzeria!");
    }

}
