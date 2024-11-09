package main.rupizzeria;

import java.util.ArrayList;

public class Order {
    private int number;
    private ArrayList<Pizza> pizzas;

    public Order(){
        this.pizzas = new ArrayList<>();
    }

    public void setOrderNumber(int number){
        this.number = number;
    }

    public int getNumber(){
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

}
