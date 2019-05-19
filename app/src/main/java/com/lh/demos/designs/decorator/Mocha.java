package com.lh.demos.designs.decorator;

/**
 * Created by leihui on 2019/5/18.
 * Mocha
 */

public class Mocha extends CondimentDecorator {

    private Beverage mBeverage;

    public Mocha(Beverage beverage) {
        mBeverage = beverage;
    }

    @Override
    public String getDescription() {
        return mBeverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return 0.20 + mBeverage.cost();
    }
}
