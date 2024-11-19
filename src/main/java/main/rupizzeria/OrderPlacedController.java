package main.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Controller in MVC model manages list of orders, exports them to a file, and controls orderList-view. Allows you to switch between displays.
 * @author Gordon Lin, Christopher Lee modified Nov. 18, 2024
 */
public class OrderPlacedController {
    private RUPizzeriaMainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    private ArrayList<Order> orderlist = new ArrayList<>();
    private ArrayList<Integer> indexes = new ArrayList<>();


    @FXML
    private ListView<Pizza> orders;
    @FXML
    private ComboBox<Integer> indexBox;
    @FXML
    private TextField total;


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
        orderlist = mainController.getOrderlist();
        if(!orderlist.isEmpty()){
            indexBox.setValue(orderlist.getFirst().getOrderNumber());
            orders.getItems().addAll(mainController.getPizzas());
        }
        updateComboBox();
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
     * Updates combo box as indexes array list gets changed
     */
    private void updateComboBox() {
        for (Order order : orderlist) {
            indexes.add(order.getOrderNumber());
        }
        indexBox.setItems(FXCollections.observableArrayList(indexes));
        indexes = new ArrayList<>();
    }

    @FXML
    /**
     * Set the view for order list and updateCost
     */
    private void setListView(){
        Order orderSelected = new Order();
        for (Order order : orderlist ) {
            if(indexBox.getValue()==null){
                return;
            }
            if (order.getOrderNumber() == (indexBox.getValue())) {
                orderSelected = getOrder(indexBox.getValue());
            }
        }
        ObservableList<Pizza> list = FXCollections.observableArrayList(orderSelected.getPizzas());
        orders.setItems(list);

        double sum = 0;
        for(Pizza pizza: orderSelected.getPizzas()){
            double roundedNumber = Math.round(pizza.price() * 100.0) / 100.0;
            sum += roundedNumber;
        }
        double tax = sum * 6.625 / 100;
        tax = Math.round(tax * 100.0) / 100.0;
        total.setText(Double.toString(sum+tax));
    }

    @FXML
    /**
     * Removes an order from order list and combo box
     */
    protected void removeOrder(){
        if(!orderlist.isEmpty()&&indexBox.getValue()!=null) {
           orders.getItems().clear();
           int orderNumber = indexBox.getValue();
           Order removeOrder = getOrder(orderNumber);
           orderlist.remove(removeOrder);
           indexBox.getItems().remove(indexBox.getValue());
            if(!orderlist.isEmpty()){
                indexBox.setValue(orderlist.getFirst().getOrderNumber());
                orders.getItems().addAll(mainController.getPizzas());
            }
        }
    }

    /**
     * Helper method to get the order from the list given order number.
     * @param number the order number.
     * @return the order.
     */
    private Order getOrder(int number){
        for(int i = 0; i<orderlist.size();i++){
            if(orderlist.get(i).getOrderNumber() == number){
                return orderlist.get(i);
            }
        }
        return null;
    }
    @FXML
    /**
     * Exports the orders to a file.
     */
    protected void export() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);
        fileChooser.setTitle("Source file for export");
        if(file != null){
            writeToFile(file, orderlist);
        }
    }

    /**
     * Helper method to write the orders to a file.
     * @param file file to write orders into
     * @param orders orders to be written into file
     * @throws FileNotFoundException if file does not exist or cannot be accessed will throw error
     */
    private void writeToFile(File file, ArrayList<Order> orders) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        for(Order order: orders){
            pw.println("Order Number: " + order.getOrderNumber());
            for(Pizza pizza: order.getPizzas()){
                pw.println(pizza);
            }
        }
        pw.close();
    }

    @FXML
    /**
     * Enlarges image when mouse hovers over
     */
    protected void imagePopout(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setScaleX(1.2);
        label.setScaleY(1.2);
    }

    @FXML
    /**
     * Turns image back to normal when mouse exits image
     */
    protected void imagePopoutExit(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }

}

