package com.lh.demos.paints.xfermode;

import android.os.Bundle;

import com.lh.demos.R;
import com.lh.demos.base.BaseAppCompatActivity;
import com.lh.demos.base.BaseConstants;

public class PorterDuffXfermodeActivity2 extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porter_duff_xfermode2);
        setTitle(getIntent().getStringExtra(BaseConstants.NAVIGATION_TITLE_KEY));
    }
}
