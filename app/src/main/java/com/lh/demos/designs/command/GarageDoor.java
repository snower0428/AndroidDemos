package com.lh.demos.designs.command;

import android.util.Log;

/**
 * Created by leihui on 2019/6/20.
 * GarageDoor
 */

public class GarageDoor {

    public void up() {
        Log.d("lh123", "GarageDoor is Open");
    }

    public void down() {
        Log.d("lh123", "GarageDoor is Close");
    }

    public void stop() {
        Log.d("lh123", "GarageDoor stop");
    }

    public void lightOn() {
        Log.d("lh123", "GarageDoor lightOn");
    }

    public void lightOff() {
        Log.d("lh123", "GarageDoor lightOff");
    }
}
