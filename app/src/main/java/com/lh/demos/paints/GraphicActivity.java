package com.lh.demos.paints;

import android.support.v4.app.Fragment;

import com.lh.demos.base.BaseFragmentActivity;

/**
 * Created by Administrator on 2018/8/21.
 * GraphicActivity
 */

public class GraphicActivity extends BaseFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new GraphicFragment();
    }
}
