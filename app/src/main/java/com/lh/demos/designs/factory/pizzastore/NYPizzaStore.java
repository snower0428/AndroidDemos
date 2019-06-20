package com.lh.demos.designs.factory.pizzastore;

import com.lh.demos.designs.factory.pizza.NYStyleCheesePizza;
import com.lh.demos.designs.factory.pizza.NYStyleClamPizza;
import com.lh.demos.designs.factory.pizza.NYStylePepperoniPizza;
import com.lh.demos.designs.factory.pizza.NYStyleVeggiePizza;
import com.lh.demos.designs.factory.pizza.Pizza;

/**
 * Created by leihui on 2019/5/25.
 * NYPizzaStore
 */

public class NYPizzaStore extends PizzaStore {

    private Pizza pizza;

    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            pizza = new NYStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new NYStylePepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new NYStyleClamPizza();
        } else if (type.equals("veggie")) {
            pizza = new NYStyleVeggiePizza();
        }
        return pizza;
    }
}
