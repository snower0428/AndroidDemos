package com.lh.demos.designs.strategy;

import android.util.Log;

/**
 * Created by leihui on 2019/3/25.
 * Quack
 */

public class Quack implements QuackBehavior {

    @Override
    public void quack() {
        Log.d("lh123", "Quack quack");
    }
}
