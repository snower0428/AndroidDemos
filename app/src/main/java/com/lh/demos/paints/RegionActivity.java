package com.lh.demos.paints;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.lh.demos.base.BaseFragmentActivity;

public class RegionActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return new RegionFragment();
    }
}
