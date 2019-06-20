package com.lh.demos.designs.factory.pizza;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by leihui on 2019/5/25.
 * Pizza
 */

public abstract class Pizza {

    protected String name;
    protected String dough;
    protected String sauce;
    protected ArrayList toppings = new ArrayList();

    public void prepare() {
        Log.d("lh123", "preparing " + name);
        Log.d("lh123", "Tossing dough...");
        Log.d("lh123", "Adding sause...");
        Log.d("lh123", "Adding toppings: ");
        for (int i = 0; i < toppings.size(); i++) {
            Log.d("lh123", " " + toppings.get(i));
        }
    }

    public void bake() {
        Log.d("lh123", "Bake for 25 minutes at 350");
    }

    public void cut() {
        Log.d("lh123", "Cutting the pizza into diagonal slices");
    }

    public void box() {
        Log.d("lh123", "Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }
}
