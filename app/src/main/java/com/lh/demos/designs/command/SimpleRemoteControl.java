package com.lh.demos.designs.command;

/**
 * Created by leihui on 2019/6/20.
 * SimpleRemoteControl
 */

public class SimpleRemoteControl {

    private Command mCommand;

    public SimpleRemoteControl() {

    }

    public void setCommand(Command command) {
        mCommand = command;
    }

    public void buttonWasPressed() {
        mCommand.execute();
    }
}
