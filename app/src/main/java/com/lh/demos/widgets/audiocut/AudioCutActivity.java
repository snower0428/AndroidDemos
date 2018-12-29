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

        //stv.setTrackTemplateData(template);
        stv.setDuration(20000); // 音频时间
        stv.setCutDuration(10000);//屏幕左边跑到右边持续的时间
        //stv.setTrackFragmentCount(30);//1 中是一个片段，这个参数表示重复1中片段画10次
        stv.setLoopRun(true);//设置是否循环跑进度
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
