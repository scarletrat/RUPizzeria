package main.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * Controller in MVC model used to add orders to the order list and controls cart-view. Allows you to switch between displays.
 *  * @author Gordon Lin, Christopher Lee modified Nov. 08, 2024
 */
public class ShoppingCartController {
    private RUPizzeriaMainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    private ArrayList<Pizza> pizzas;
    private ObservableList<Pizza> list;


    @FXML
    private ListView<Pizza> cartList;
    @FXML
    private TextField subtotalText;
    @FXML
    private TextField taxText;
    @FXML
    private TextField totalText;

    /**
     * Set the view for cartList and updateCost
     */
    private void setListView(){
        pizzas = mainController.getPizzas();
        if(!pizzas.isEmpty()) {
            list = FXCollections.observableArrayList(pizzas);
            cartList.getItems().addAll(list);
        }
        updateCost();
    }

    /**
     * Update the subtotal, sales tax, and total cost of the order cart.
     */
    private void updateCost(){
        double subtotal = 0.0;
        if(!pizzas.isEmpty()) {
            for (int i = 0; i < pizzas.size(); i++) {
                subtotal += pizzas.get(i).price();
            }
            if (subtotal == 0.0) {
                return;
            }
            double roundedSub = Math.round(subtotal * 100.0) / 100.0;
            subtotalText.setText(Double.toString(roundedSub));
            double tax = subtotal * 6.625 / 100;
            tax = Math.round(tax * 100.0) / 100.0;
            taxText.setText(Double.toString(tax));
            totalText.setText(Double.toString(roundedSub + tax));
        }
        else {
            subtotalText.setText(null);
            taxText.setText(null);
            totalText.setText(null);
        }
    }

    /**
     * Remove the selected pizza from the listView.
     */
    public void removePizza(){
        if(cartList.getSelectionModel().getSelectedItem() == null){
            return;
        }
        Pizza pizza = cartList.getSelectionModel().getSelectedItem();
        pizzas.remove(pizza);
        cartList.getItems().remove(pizza);

        setListView();
    }

    /**
     * clears all pizzas from list view
     */
    public void clearList(){
        pizzas.clear();
        cartList.getItems().clear();
        setListView();
    }
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
        setListView();
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
      Navigate back to the menu view.
     */
    public void displayMenu() {
        mainController.displayMenuView();
    }

    @FXML
    /**
     * enlarge image when mouse enters image
     */
    protected void imagePopout(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setScaleX(1.2);
        label.setScaleY(1.2);
    }
    @FXML
    /**
     * returns image to normal when mouse exits image
     */
    protected void imagePopoutExit(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }

    /**
     * adds order to order list
     */
    public void placeOrder(){
        mainController.placeOrder();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Order");
        alert.setContentText("Pizza order placed!");
        alert.showAndWait();
        clearList();
    }


}
