package com.lh.demos.designs.decorator;

/**
 * Created by leihui on 2019/5/18.
 * Soy
 */

public class Soy extends CondimentDecorator {

    private Beverage mBeverage;

    public Soy(Beverage beverage) {
        mBeverage = beverage;
    }

    @Override
    public String getDescription() {
        return mBeverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return 0.15 + mBeverage.cost();
    }
}
