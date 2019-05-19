package com.lh.demos.designs.decorator;

/**
 * Created by leihui on 2019/5/18.
 * Milk
 */

public class Milk extends CondimentDecorator {

    private Beverage mBeverage;

    public Milk(Beverage beverage) {
        mBeverage = beverage;
    }

    @Override
    public String getDescription() {
        return mBeverage.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return 0.10 + mBeverage.cost();
    }
}
