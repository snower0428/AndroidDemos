package com.lh.demos.designs.strategy;

import android.util.Log;

/**
 * Created by leihui on 2019/3/25.
 * FlyRocketPowered
 */

public class FlyRocketPowered implements FlyBehavior {

    @Override
    public void fly() {
        Log.d("lh123", "FlyRocketPowered fly");
    }
}
