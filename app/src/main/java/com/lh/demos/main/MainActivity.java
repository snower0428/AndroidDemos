package com.lh.demos.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jaeger.library.StatusBarUtil;
import com.lh.demos.R;
import com.lh.demos.base.BaseFragmentActivity;

public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.colorPrimary));
        showToolbar(false);
    }

    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}
