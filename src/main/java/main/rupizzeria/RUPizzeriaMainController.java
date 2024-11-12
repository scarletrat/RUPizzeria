package main.rupizzeria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class RUPizzeriaMainController {
    private Stage primaryStage; //the reference of the main window.
    private Scene primaryScene; //the ref. of the scene set to the primaryStage


    /**
     * Set the reference of the stage and scene before show()
     * @param stage the stage used to display the scene
     * @param scene the scene set to the stage
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    @FXML
    protected void displayMenuView() {
        Stage view1 = new Stage(); //if we want to use a new window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("RUPizzeria Menu");
            RUPizzeriaMenuController menuViewController = fxmlLoader.getController();
            /*
              The statement below is to pass the references of the MainController objects
              to the SecondViewController object so the SecondViewController can call the
              public methods in the MainController or to navigate back to the main view.
             */
            menuViewController.setMainController(this, view1, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading menu-view.fxml.");
            alert.setContentText("Couldn't load menu-view.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayCartView() {
        Stage view1 = new Stage(); //if we want to use a new window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cart-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Shopping Cart");
            ShoppingCartController cartController = fxmlLoader.getController();
            /*
              The statement below is to pass the references of the MainController objects
              to the SecondViewController object so the SecondViewController can call the
              public methods in the MainController or to navigate back to the main view.
             */
            cartController.setMainController(this, view1, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading cart-view.fxml.");
            alert.setContentText("Couldn't load cart-view.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayListView() {
        Stage view1 = new Stage(); //if we want to use a new window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orderList-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Orders Placed");
            OrderPlacedController listController = fxmlLoader.getController();
            /*
              The statement below is to pass the references of the MainController objects
              to the SecondViewController object so the SecondViewController can call the
              public methods in the MainController or to navigate back to the main view.
             */
            listController.setMainController(this, view1, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading orderList-view.fxml.");
            alert.setContentText("Couldn't load orderList-view.fxml.");
            alert.showAndWait();
        }
    }
    @FXML
    protected void imagePopout(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        imageView.setScaleX(1.2);
        imageView.setScaleY(1.2);
    }
    @FXML
    protected void imagePopoutExit(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }


}