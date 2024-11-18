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
        updateComboBox();
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

    void updateComboBox() {
        for (Order order : orderlist) {
            indexes.add(order.getOrderNumber());
        }
        indexBox.setItems(FXCollections.observableArrayList(indexes));
    }

    @FXML
    /**
     * Set the view for cartList and updateCost
     */
    private void setListView(){
        Order orderSelected = new Order();
        if(!orderlist.isEmpty()&&indexBox.getValue()!=null) {
            for (Order order : orderlist) {
                if (order.getOrderNumber() == (indexBox.getValue())) {
                    orderSelected = orderlist.get(indexBox.getValue() - 1);
                }
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
    protected void removeOrder(){
        if(!orderlist.isEmpty()&&indexBox.getValue()!=null) {
            for (Order order : orderlist) {
                if (order.getOrderNumber() == (indexBox.getValue())) {
                    indexBox.getItems().remove(indexBox.getValue());
                    break;
                }
            }
        }    }

    @FXML
    protected void export() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);
        fileChooser.setTitle("Source file for export");
        if(file != null){
            writeToFile(file, orderlist);
        }
    }

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

