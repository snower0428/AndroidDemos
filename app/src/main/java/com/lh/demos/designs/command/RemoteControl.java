package com.lh.demos.designs.command;

/**
 * Created by leihui on 2019/6/24.
 * RemoteControl
 */

public class RemoteControl {

    private Command[] mOnCommands;
    private Command[] mOffCommands;

    public RemoteControl() {
        mOnCommands = new Command[7];
        mOffCommands = new Command[7];

        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            mOnCommands[i] = noCommand;
            mOffCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        mOnCommands[slot] = onCommand;
        mOffCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        mOnCommands[slot].execute();
    }

    public void offButtonWasPushed(int slot) {
        mOffCommands[slot].execute();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n---------- Remote Control  ----------\n");
        for (int i = 0; i < mOnCommands.length; i++) {
            String str = "[slot" + i + "] " + mOnCommands[i].getClass().getName() + "    " + mOffCommands[i].getClass().getName() + "\n";
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }
}
