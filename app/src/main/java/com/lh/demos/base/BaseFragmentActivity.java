package com.lh.demos.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.lh.demos.R;

/**
 * Created by Administrator on 2018/8/21.
 * BaseFragmentActivity
 */

public abstract class BaseFragmentActivity extends BaseAppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }
    }
}
