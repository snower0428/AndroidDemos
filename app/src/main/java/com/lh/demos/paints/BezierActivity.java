package com.lh.demos.paints;

import android.support.v4.app.Fragment;

import com.lh.demos.base.BaseFragmentActivity;

public class BezierActivity extends BaseFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new BezierFragment();
    }
}
