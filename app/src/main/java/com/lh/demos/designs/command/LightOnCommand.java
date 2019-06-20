package com.lh.demos.designs.command;

/**
 * Created by leihui on 2019/6/20.
 * LightOnCommand
 */

public class LightOnCommand implements Command {

    private Light mLight;

    public LightOnCommand(Light light) {
        mLight = light;
    }

    @Override
    public void execute() {
        if (mLight != null) {
            mLight.on();
        }
    }
}
