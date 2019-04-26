package com.lh.demos.designs.strategy;

import android.util.Log;

/**
 * Created by leihui on 2019/3/25.
 * Duck
 */

public abstract class Duck {

    protected FlyBehavior mFlyBehavior;
    protected QuackBehavior mQuackBehavior;

    public Duck() {

    }

    public abstract void display();

    public void swim() {
        Log.d("lh123", "Duck swim");
    }

    public void performFly() {
        if (mFlyBehavior != null) {
            mFlyBehavior.fly();
        }
    }

    public void performQuack() {
        if (mQuackBehavior != null) {
            mQuackBehavior.quack();
        }
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        mFlyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        mQuackBehavior = quackBehavior;
    }
}
