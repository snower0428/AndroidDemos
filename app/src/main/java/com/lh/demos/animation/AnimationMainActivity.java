package com.lh.demos.animation;

import android.support.v4.app.Fragment;

import com.lh.demos.base.BaseFragmentActivity;

/**
 * Created by leihui on 2018/10/22.
 * AnimationMainActivity
 */

public class AnimationMainActivity extends BaseFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AnimationMainFragment();
    }
}
