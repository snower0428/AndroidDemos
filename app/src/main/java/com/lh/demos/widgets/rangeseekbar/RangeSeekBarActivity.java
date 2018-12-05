package com.lh.demos.widgets.rangeseekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lh.demos.R;

public class RangeSeekBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_seek_bar);

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
