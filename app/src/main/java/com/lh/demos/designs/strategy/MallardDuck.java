package com.lh.demos.designs.strategy;

import android.util.Log;

/**
 * Created by leihui on 2019/3/25.
 * MallardDuck
 */

public class MallardDuck extends Duck {

    public MallardDuck() {
        mFlyBehavior = new FlyWithWings();
        mQuackBehavior = new Quack();
    }

    @Override
    public void display() {
        Log.d("lh123", "MallardDuck display");
    }
}
