package com.lh.demos.designs.command;

/**
 * Created by leihui on 2019/6/24.
 * LigitOffCommand
 */

public class LigitOffCommand implements Command {

    private Light mLight;

    public LigitOffCommand(Light light) {
        mLight = light;
    }

    @Override
    public void execute() {
        if (mLight != null) {
            mLight.off();
        }
    }
}
