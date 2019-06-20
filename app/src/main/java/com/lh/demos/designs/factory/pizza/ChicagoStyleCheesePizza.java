package com.lh.demos.designs.factory.pizza;

import android.util.Log;

/**
 * Created by leihui on 2019/5/25.
 * ChicagoStyleCheesePizza
 */

public class ChicagoStyleCheesePizza extends Pizza {

    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";

        toppings.add("Shredded Mozzarella Cheese");
    }

    @Override
    public void cut() {
        Log.d("lh123", "Cutting the pizza into square slices");
    }
}
