package com.lh.demos.paints;

import android.support.v4.app.Fragment;

import com.lh.demos.base.BaseFragmentActivity;

/**
 * Created by Administrator on 2018/8/22.
 * PathTextActivity
 */

public class PathTextActivity extends BaseFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PathTextFragment();
    }
}
