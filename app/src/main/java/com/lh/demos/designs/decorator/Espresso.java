package com.lh.demos.designs.decorator;

/**
 * Created by leihui on 2019/5/18.
 * Espresso
 */

public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
