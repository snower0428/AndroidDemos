package com.lh.demos.designs.command;

/**
 * Created by leihui on 2019/6/25.
 * StereoOnWithCDCommand
 */

public class StereoOnWithCDCommand implements Command {

    private Stereo mStereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        mStereo = stereo;
    }

    @Override
    public void execute() {
        if (mStereo != null) {
            mStereo.on();
            mStereo.setCd();
            mStereo.setVolume();
        }
    }
}
