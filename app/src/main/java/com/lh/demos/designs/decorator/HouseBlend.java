package com.lh.demos.designs.decorator;

/**
 * Created by leihui on 2019/5/18.
 * HouseBlend
 */

public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
