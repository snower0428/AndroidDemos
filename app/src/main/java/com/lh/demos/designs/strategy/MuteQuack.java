package com.lh.demos.designs.strategy;

import android.util.Log;

/**
 * Created by leihui on 2019/3/25.
 * MuteQuack
 */

public class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        Log.d("lh123", "MuteQuack quack");
    }
}
