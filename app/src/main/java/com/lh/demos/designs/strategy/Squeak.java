package com.lh.demos.designs.strategy;

import android.util.Log;

/**
 * Created by leihui on 2019/3/25.
 * Squeak
 */

public class Squeak implements QuackBehavior {

    @Override
    public void quack() {
        Log.d("lh123", "Squeak quack");
    }
}
