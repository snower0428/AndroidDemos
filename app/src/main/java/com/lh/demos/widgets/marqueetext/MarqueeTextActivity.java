package com.lh.demos.widgets.marqueetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lh.demos.R;
import com.lh.demos.base.BaseConstants;

public class MarqueeTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee_text);
        initToolbar();
        initView();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        TextView tvTitle = findViewById(R.id.toolbar_title);
        tvTitle.setText(getIntent().getStringExtra(BaseConstants.NAVIGATION_TITLE_KEY));
    }

    private void initView() {
        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);
        Button btnPause = findViewById(R.id.btn_pause);
        Button btnResume = findViewById(R.id.btn_resume);

        final MarqueeTextView marquee1 = findViewById(R.id.marquee1);
        final MarqueeTextView marquee2 = findViewById(R.id.marquee2);
        final MarqueeTextView marquee3 = findViewById(R.id.marquee3);
        final MarqueeTextView marquee4 = findViewById(R.id.marquee4);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marquee1.startScroll();
                marquee2.startScroll();
                marquee3.startScroll();
                marquee4.startScroll();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marquee1.stopScroll();
                marquee2.stopScroll();
                marquee3.stopScroll();
                marquee4.stopScroll();
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marquee1.pauseScroll();
                marquee2.pauseScroll();
                marquee3.pauseScroll();
                marquee4.pauseScroll();
            }
        });
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marquee1.resumeScroll();
                marquee2.resumeScroll();
                marquee3.resumeScroll();
                marquee4.resumeScroll();
            }
        });
    }
}
