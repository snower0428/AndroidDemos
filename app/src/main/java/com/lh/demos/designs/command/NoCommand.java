package com.lh.demos.designs.command;

import android.util.Log;

/**
 * Created by leihui on 2019/6/24.
 * NoCommand
 */

public class NoCommand implements Command {

    @Override
    public void execute() {
        Log.d("lh123", "NoCommand execute");
    }
}
