package com.lh.demos.designs.strategy;

import android.util.Log;

/**
 * Created by leihui on 2019/3/25.
 * FlyNoWay
 */

public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        Log.d("lh123", "FlyNoWay fly");
    }
}
