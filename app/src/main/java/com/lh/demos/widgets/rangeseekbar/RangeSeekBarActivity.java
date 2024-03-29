package com.lh.demos.widgets.rangeseekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lh.demos.R;
import com.lh.demos.base.BaseAppCompatActivity;
import com.lh.demos.base.BaseConstants;

public class RangeSeekBarActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_seek_bar);

        setTitle(getIntent().getStringExtra(BaseConstants.NAVIGATION_TITLE_KEY));
        initView();
    }

    private void initView() {
        AudioRangeSeekBar<Integer> seekBar = findViewById(R.id.range_seekbar2);
        seekBar.setThumbInterval(20);

        seekBar.setOnRangeSeekBarChangeListener(new AudioRangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesBeginChange(AudioRangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                Log.d("lh123", "onRangeSeekBarValuesBeginChange minValue: " + minValue + ", maxValue: " + maxValue);
            }

            @Override
            public void onRangeSeekBarValuesChanging(AudioRangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                Log.d("lh123", "onRangeSeekBarValuesChanging minValue: " + minValue + ", maxValue: " + maxValue);
            }

            @Override
            public void onRangeSeekBarValuesEndChange(AudioRangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                Log.d("lh123", "onRangeSeekBarValuesEndChange minValue: " + minValue + ", maxValue: " + maxValue);
            }
        });
    }
}
