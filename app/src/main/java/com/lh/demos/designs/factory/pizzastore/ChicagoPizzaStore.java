package com.lh.demos.designs.factory.pizzastore;

import com.lh.demos.designs.factory.pizza.ChicagoStyleCheesePizza;
import com.lh.demos.designs.factory.pizza.ChicagoStyleClamPizza;
import com.lh.demos.designs.factory.pizza.ChicagoStylePepperoniPizza;
import com.lh.demos.designs.factory.pizza.ChicagoStyleVeggiePizza;
import com.lh.demos.designs.factory.pizza.Pizza;

/**
 * Created by leihui on 2019/5/25.
 * ChicagoPizzaStore
 */

public class ChicagoPizzaStore extends PizzaStore {

    private Pizza pizza;

    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            pizza = new ChicagoStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new ChicagoStylePepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new ChicagoStyleClamPizza();
        } else if (type.equals("veggie")) {
            pizza = new ChicagoStyleVeggiePizza();
        }
        return pizza;
    }
}
