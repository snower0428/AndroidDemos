package com.lh.demos.designs.factory.pizza;

/**
 * Created by leihui on 2019/5/25.
 * NYStyleCheesePizza
 */

public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza() {
        name = "NY Style Sause and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
    }
}
