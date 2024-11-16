package main.rupizzeria;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.control.Label;

public class ShoppingCartController {
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

    @FXML
    /**
     * Navigate back to the menu view.
     */
    public void displayMenu() {
        mainController.displayMenuView();
    }

    @FXML
    protected void imagePopout(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setScaleX(1.2);
        label.setScaleY(1.2);
    }
    @FXML
    protected void imagePopoutExit(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }
}
