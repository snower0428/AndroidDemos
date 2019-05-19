package com.lh.demos.designs.decorator;

/**
 * Created by leihui on 2019/5/18.
 * Beverage
 */

public abstract class Beverage {

    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
