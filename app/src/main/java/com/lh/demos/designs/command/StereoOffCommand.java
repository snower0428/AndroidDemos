package com.lh.demos.designs.command;

/**
 * Created by leihui on 2019/6/25.
 * StereoOffCommand
 */

public class StereoOffCommand implements Command {

    private Stereo mStereo;

    public StereoOffCommand(Stereo stereo) {
        mStereo = stereo;
    }

    @Override
    public void execute() {
        if (mStereo != null) {
            mStereo.off();
        }
    }
}
