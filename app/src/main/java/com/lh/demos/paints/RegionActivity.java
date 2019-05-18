package com.lh.demos.paints;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.lh.demos.base.BaseConstants;
import com.lh.demos.base.BaseFragmentActivity;

public class RegionActivity extends BaseFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RegionFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra(BaseConstants.NAVIGATION_TITLE_KEY));
    }
}
