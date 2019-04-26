package com.lh.demos.designs.strategy;

import android.util.Log;

/**
 * Created by leihui on 2019/3/25.
 * FlyWithWings
 */

public class FlyWithWings implements FlyBehavior{

    @Override
    public void fly() {
        Log.d("lh123", "FlyWithWings fly");
    }
}
