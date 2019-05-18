package com.lh.demos.widgets.cardview;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;

import com.lh.demos.R;
import com.lh.demos.base.BaseAppCompatActivity;
import com.lh.demos.base.BaseConstants;

public class CardViewActivity extends BaseAppCompatActivity {

    private CardView mCardView;
    private SeekBar mSeekBar1;
    private SeekBar mSeekBar2;
    private SeekBar mSeekBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        setTitle(getIntent().getStringExtra(BaseConstants.NAVIGATION_TITLE_KEY));
        initView();
    }

    private void initView() {
        mCardView = findViewById(R.id.card_view);
        mSeekBar1 = findViewById(R.id.sb_1);
        mSeekBar2 = findViewById(R.id.sb_2);
        mSeekBar3 = findViewById(R.id.sb_3);

        mSeekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mCardView.setRadius(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mCardView.setCardElevation(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mCardView.setContentPadding(i, i, i, i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
