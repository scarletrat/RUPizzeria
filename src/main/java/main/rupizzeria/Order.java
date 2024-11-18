package main.rupizzeria;

import java.util.ArrayList;

public class Order {
    private int number;
    private ArrayList<Pizza> pizzas;

    public Order(){
        this.pizzas = new ArrayList<>();
    }

    public Order(ArrayList<Pizza> pizzas){
        this.pizzas = pizzas;
    }

    public void setOrderNumber(int number){
        this.number = number;
    }

    public int getOrderNumber(){
        return this.number;
    }

    public ArrayList<Pizza> getPizzas(){
        return this.pizzas;
    }

    public void add(Pizza pizza){
        this.pizzas.add(pizza);
    }

    public void remove(Pizza pizza){
        this.pizzas.remove(pizza);
    }

//    @Override
//    public String toString() {
//        String top = "";
//        for(int i = 0;i < toppings.size(); i++){
//            top = top + " " + toppings.get(i) + ",";
//        }
//        return " - " + crust + "),  " + " Toppings: " + top + " " + size + " $"+this.price();
//    }
}
