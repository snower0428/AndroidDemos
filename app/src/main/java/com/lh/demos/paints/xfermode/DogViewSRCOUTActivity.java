package com.lh.demos.paints.xfermode;

import android.os.Bundle;

import com.lh.demos.R;
import com.lh.demos.base.BaseAppCompatActivity;
import com.lh.demos.base.BaseConstants;

public class DogViewSRCOUTActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_view_src_out);
        setTitle(getIntent().getStringExtra(BaseConstants.NAVIGATION_TITLE_KEY));
    }
}
