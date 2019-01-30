package com.lh.demos.widgets.audiocut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lh.demos.R;

public class AudioCutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_cut);

        final AudioTrackScrollView stv = findViewById(R.id.stv);
        Button btn = findViewById(R.id.btnRestart);
        Button btnPause = findViewById(R.id.btnPause);
        Button btnStop = findViewById(R.id.btnStop);
        Button btnStart = findViewById(R.id.btnStart);
        final SeekBar iseekBar = findViewById(R.id.seek_bar);

        final TextView tv = findViewById(R.id.tv);
        //1.每个Track小块的数据,不设置也可以，有默认
        //float[] template = {0.9f,0.6f,0.7f,0.5f,0.8f,0.4f,0.5f,0.2f,0.6f,0.8f,0.8f};

        int count = 88;
        float[] data = new float[count];
        for (int i = 0; i < count; i++) {
            float random = 30 + (((float) Math.random() * 100) % 60);
            data[i] = random / 100;
        }

//        float[] data2 = new float[count - 8];
//        for (int i = 0; i < data2.length; i++) {
//            data2[i] = data[i];
//        }

//        final AudioTrackScrollView stv_bg = findViewById(R.id.stv_bg);
//        stv_bg.setTrackTemplateData(data2);
//        stv_bg.setContinueMove(true);

        //stv.setTrackTemplateData(template);
        stv.setTrackTemplateData(data);
        stv.setDuration(80000); // 音频时间
        stv.setCutDuration(10000);//屏幕左边跑到右边持续的时间
        stv.setStartTime(0);
        stv.setEmptyWidth(4);
//        stv.setTrackOffsetCount(4);
        //stv.setTrackFragmentCount(30);//1 中是一个片段，这个参数表示重复1中片段画10次
        //stv.setLoopRun(true);//设置是否循环跑进度
        //stv.setSpaceSize(18);
        //stv.setTrackItemWidth(18);
        stv.setOnProgressRunListener(new AudioTrackScrollView.OnProgressRunListener() {
            @Override
            public void onTrackStart(int ms) {

            }

            @Override
            public void onTrackStartTimeChange(int ms) {
                tv.setText("从 "+ms*1f/1000f+" 秒开始");
            }

            @Override
            public void onTrackEnd() {

            }
        });

//        stv.setOnScrollListener(new AudioTrackScrollView.OnScrollListener() {
//            @Override
//            public void onScroll(AudioTrackScrollView scrollTrackView) {
//                int scrollX = scrollTrackView.getScrollX();
//                stv_bg.setScrollX(scrollX);
//            }
//
//            @Override
//            public void onScrollEnd(AudioTrackScrollView scrollTrackView) {
//                int scrollX = scrollTrackView.getScrollX();
//                stv_bg.setScrollX(scrollX);
//            }
//
//            @Override
//            public void onFling(AudioTrackScrollView scrollTrackView) {
//                int scrollX = scrollTrackView.getScrollX();
//                stv_bg.setScrollX(scrollX);
//            }
//        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stv.restartMove();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stv.stopMove();
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stv.pauseMove();
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stv.startMove();
            }
        });
        iseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                boolean isCanSetProgress = (boolean) iseekBar.getTag();
                if(isCanSetProgress){
                    stv.setRealProgress(progress*1f/100f);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                iseekBar.setTag(true);
                stv.setProgressContinue(false);

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                iseekBar.setTag(false);
                stv.setProgressContinue(true);
            }
        });
    }
}
