package com.lh.demos.designs.command;

/**
 * Created by leihui on 2019/6/20.
 * GarageDoorOpenCommand
 */

public class GarageDoorOpenCommand implements Command {

    private GarageDoor mGarageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        mGarageDoor = garageDoor;
    }

    @Override
    public void execute() {
        if (mGarageDoor != null) {
            mGarageDoor.up();
        }
    }
}
