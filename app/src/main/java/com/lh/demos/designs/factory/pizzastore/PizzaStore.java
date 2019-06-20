package com.lh.demos.designs.factory.pizzastore;

import com.lh.demos.designs.factory.pizza.Pizza;

/**
 * Created by leihui on 2019/5/25.
 * PizzaStore
 */

public abstract class PizzaStore {

    public Pizza orderPizza(String type) {

        Pizza pizza;

        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    abstract Pizza createPizza(String type);
}
