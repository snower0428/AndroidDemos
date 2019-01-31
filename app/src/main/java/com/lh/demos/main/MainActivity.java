package com.lh.demos.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jaeger.library.StatusBarUtil;
import com.lh.demos.R;
import com.lh.demos.base.BaseFragmentActivity;

import cn.bmob.v3.Bmob;

public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bmob默认初始化
        Bmob.initialize(this, "d0f59510d94467f9df88ccba0ea5a608");

        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}
