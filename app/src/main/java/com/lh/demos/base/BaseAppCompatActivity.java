package com.lh.demos.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.lh.demos.R;

/**
 * Created by leihui on 2018/12/7.
 * BaseAppCompatActivity
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    private LinearLayout mRootLayout;
    private Toolbar mToolbar;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        setStatusBar();
        initToolbar();
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        mRootLayout = findViewById(R.id.root_layout);
        if (mRootLayout != null) {
            mRootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (mTitle != null) {
            mTitle.setText(title);
        }
    }

    public void hideBackIcon() {
        mToolbar.setNavigationIcon(null);
    }

    public void showToolbar(boolean show) {
        if (show) {
            mToolbar.setVisibility(View.VISIBLE);
        } else {
            mToolbar.setVisibility(View.GONE);
        }
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mTitle = findViewById(R.id.toolbar_title);
    }
}
