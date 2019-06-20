package com.lh.demos.designs.factory.pizzastore;

import com.lh.demos.designs.factory.pizza.CaliforniaStyleCheesePizza;
import com.lh.demos.designs.factory.pizza.CaliforniaStyleClamPizza;
import com.lh.demos.designs.factory.pizza.CaliforniaStylePepperoniPizza;
import com.lh.demos.designs.factory.pizza.CaliforniaStyleVeggiePizza;
import com.lh.demos.designs.factory.pizza.Pizza;

/**
 * Created by leihui on 2019/5/25.
 * CaliforniaPizzaStore
 */

public class CaliforniaPizzaStore extends PizzaStore {

    private Pizza pizza;

    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            pizza = new CaliforniaStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new CaliforniaStylePepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new CaliforniaStyleClamPizza();
        } else if (type.equals("veggie")) {
            pizza = new CaliforniaStyleVeggiePizza();
        }
        return pizza;
    }
}
