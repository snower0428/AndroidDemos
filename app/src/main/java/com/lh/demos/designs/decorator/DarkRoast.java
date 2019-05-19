package com.lh.demos.designs.decorator;

/**
 * Created by leihui on 2019/5/18.
 * DarkRoast
 */

public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "Most Excellent Dark Roast";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}
