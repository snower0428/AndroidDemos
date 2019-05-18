package com.lh.demos.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.lh.demos.base.BaseConstants;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra(BaseConstants.NAVIGATION_TITLE_KEY));
    }
}
