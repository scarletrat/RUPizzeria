package main.rupizzeria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildYourOwnTest {

    @Test
    void price() {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        Pizza pizza = pizzaFactory.createBuildYourOwn();
        pizza.setSize(Size.SMALL);
        assertEquals(8.99, pizza.price());
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.BEEF);
        assertEquals(12.37, pizza.price());
        pizza.setSize(Size.MEDIUM);
        assertEquals(14.37, pizza.price());
        pizza.setSize(Size.LARGE);
        assertEquals(16.37, pizza.price());
        pizza.removeTopping(Topping.BEEF);
        assertEquals(14.68, pizza.price());


    }
}