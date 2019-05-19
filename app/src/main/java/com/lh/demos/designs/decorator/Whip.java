package com.lh.demos.designs.decorator;

/**
 * Created by leihui on 2019/5/18.
 * Whip
 */

public class Whip extends CondimentDecorator {

    private Beverage mBeverage;

    public Whip(Beverage beverage) {
        mBeverage = beverage;
    }

    @Override
    public String getDescription() {
        return mBeverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        return 0.10 + mBeverage.cost();
    }
}
