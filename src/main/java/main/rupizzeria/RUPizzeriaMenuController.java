package main.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class RUPizzeriaMenuController {
    private RUPizzeriaMainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;

    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox<String> pizzaStyle;
    @FXML
    private ComboBox<String> pizzaType;
    @FXML
    private ToggleGroup sizeGroup;
    @FXML
    private RadioButton small;
    @FXML
    private RadioButton medium;
    @FXML
    private RadioButton large;
    @FXML
    private TextField crust;
    @FXML
    private ListView<Topping> avaliable;
    @FXML
    private ListView<Topping> selected;
    @FXML
    private Button toLeft;
    @FXML
    private Button toRight;
    @FXML
    private TextField price;

    @FXML
    /**
     * This method is automatically performed after the fxml file is loaded.
     * Set the initial values for the GUI objects here.
     */
    void initialize() {
        try {
            File file = new File("src/main/resources/main/rupizzeria/chicago-byo.jpg");
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
                ObservableList<String> style = FXCollections.observableArrayList("Chicago Style", "New York Style");
                pizzaStyle.setItems(style);
                pizzaStyle.setValue("Chicago Style");
                ObservableList<String> type = FXCollections.observableArrayList("Deluxe", "BBQ Chicken", "Meatzza", "Build your own");
                pizzaType.setItems(type);
                pizzaType.setValue("Build your own");
                small.setSelected(true);
                crust.setText("Pan");
                crust.setEditable(false);
                price.setEditable(false);
                updateMain();
            } else {
                System.out.println("File does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * Update the view.
     */
    @FXML
    public void updateMain(){
        avaliable.getItems().clear();
        avaliable.getItems().addAll(Topping.values());
        selected.getItems().clear();
        updateButton();
        updateToppingsandCrust();
        avaliable.setDisable(!pizzaType.getValue().equalsIgnoreCase("Build your own"));
        updatePrice();
        updateImage();
    }

    /**
     * Helper method to disable/enable the toppings selection button
     */
    private void updateButton(){
        if(pizzaType.getValue().equalsIgnoreCase("Build Your own")){
            toLeft.setDisable(false);
            toRight.setDisable(false);
        }else{
            toRight.setDisable(true);
            toLeft.setDisable(true);
        }
    }

    /**
     * Helper method to get the pizza.
     * @return the pizza.
     */
    private Pizza getPizza(){
        Pizza pizza = null;
        if(pizzaStyle.getValue().equalsIgnoreCase("Chicago Style")){
            PizzaFactory temp = new ChicagoPizza();
            if(pizzaType.getValue().equalsIgnoreCase("Deluxe")){
                pizza = temp.createDeluxe();
            }else if(pizzaType.getValue().equalsIgnoreCase("BBQ Chicken")){
                pizza = temp.createBBQChicken();
            }else if(pizzaType.getValue().equalsIgnoreCase("Meatzza")){
                pizza = temp.createMeatzza();
            }else if(pizzaType.getValue().equalsIgnoreCase("Build your own")){
                pizza = temp.createBuildYourOwn();
            }
        } else if (pizzaStyle.getValue().equalsIgnoreCase("New York Style")) {
                PizzaFactory temp = new NYPizza();
            if(pizzaType.getValue().equalsIgnoreCase("Deluxe")){
                pizza = temp.createDeluxe();
            }else if(pizzaType.getValue().equalsIgnoreCase("BBQ Chicken")){
                pizza = temp.createBBQChicken();
            }else if(pizzaType.getValue().equalsIgnoreCase("Meatzza")){
                pizza = temp.createMeatzza();
            }else if(pizzaType.getValue().equalsIgnoreCase("Build your own")){
                pizza = temp.createBuildYourOwn();
                avaliable.setDisable(false);
            }
        }
        RadioButton selected = (RadioButton)sizeGroup.getSelectedToggle();
        assert pizza != null;
        pizza.setSize(Size.getSize(selected.getText()));
        return pizza;
    }

    /**
     * Helper method to update toppings list and crust output.
     */
    private void updateToppingsandCrust() {
        Pizza pizza = getPizza();
        selected.getItems().addAll(pizza.getToppings());
        crust.setText(pizza.getCrust().toString());
    }

    /**
     * Add toppings from the available to selected and remove the topping from available. Maximum 7.
     */
    public void addTopping(){
        if(avaliable.getSelectionModel().getSelectedItem() == null){
            return;
        }
        if(selected.getItems().size() == 7){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Maximum number of toppings");
            alert.setContentText("At most 7 toppings!");
            alert.showAndWait();
            return;
        }
        Topping temp = avaliable.getSelectionModel().getSelectedItem();
        avaliable.getItems().remove(temp);
        selected.getItems().add(temp);
        updatePrice();
    }

    /**
     * Remove toppings from the selected and add it back to available.
     */
    public void removeTopping(){
        if(selected.getSelectionModel().getSelectedItem() == null){
            return;
        }
        Topping temp = selected.getSelectionModel().getSelectedItem();
        selected.getItems().remove(temp);
        avaliable.getItems().add(temp);
        updatePrice();
    }

    /**
     * Update the price of the pizza.
     */
    public void updatePrice(){
        Pizza pizza = getPizza();
        if(pizzaType.getValue().equalsIgnoreCase("Build your own")){
            ObservableList<Topping> toppings = selected.getItems();
            for (Topping topping : toppings) {
                pizza.addTopping(topping);
            }
        }
        double roundedNumber = Math.round(pizza.price() * 100.0) / 100.0;
        price.setText(Double.toString(roundedNumber));
    }

    /**
     * Updates the image with the selected pizza.
     */
    private void updateImage(){
        File file = null;
        if(pizzaStyle.getValue().equalsIgnoreCase("Chicago Style") &&pizzaType.getValue().equalsIgnoreCase("Deluxe")){
            file = new File("src/main/resources/main/rupizzeria/chicago-deluxe.jpg");
        }else if(pizzaStyle.getValue().equalsIgnoreCase("Chicago Style") &&pizzaType.getValue().equalsIgnoreCase("BBQ Chicken")){
            file = new File("src/main/resources/main/rupizzeria/chicago-bbq.png");
        }else if(pizzaStyle.getValue().equalsIgnoreCase("Chicago Style") &&pizzaType.getValue().equalsIgnoreCase("Meatzza")){
            file = new File("src/main/resources/main/rupizzeria/chicago-meat.jpg");
        }else if(pizzaStyle.getValue().equalsIgnoreCase("Chicago Style") &&pizzaType.getValue().equalsIgnoreCase("Build your own")){
            file = new File("src/main/resources/main/rupizzeria/chicago-byo.jpg");
        }else if(pizzaStyle.getValue().equalsIgnoreCase("New York Style") &&pizzaType.getValue().equalsIgnoreCase("Deluxe")){
            file = new File("src/main/resources/main/rupizzeria/ny-deluxe.jpg");
        } else if (pizzaStyle.getValue().equalsIgnoreCase("New York Style") &&pizzaType.getValue().equalsIgnoreCase("BBQ Chicken")) {
            file = new File("src/main/resources/main/rupizzeria/ny-bbq.jpg");
        } else if (pizzaStyle.getValue().equalsIgnoreCase("New York Style") &&pizzaType.getValue().equalsIgnoreCase("Meatzza")) {
            file = new File("src/main/resources/main/rupizzeria/ny-meat.jpg");
        } else if (pizzaStyle.getValue().equalsIgnoreCase("New York Style") &&pizzaType.getValue().equalsIgnoreCase("Build your own")) {
            file = new File("src/main/resources/main/rupizzeria/ny-byo.jpg");

        }
        assert file != null;
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    @FXML
    public void addToCart(){
        mainController.addCart(getPizza());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Order");
        alert.setContentText("Pizza added to your shopping cart!");
        alert.showAndWait();

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
