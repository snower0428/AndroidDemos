package com.lh.demos.designs.decorator;

/**
 * Created by leihui on 2019/5/18.
 * Decaf
 */

public class Decaf extends Beverage {

    public Decaf() {
        description = "Decaf";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
