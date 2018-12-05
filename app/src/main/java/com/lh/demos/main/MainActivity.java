package com.lh.demos.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lh.demos.base.BaseFragmentActivity;

public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}
