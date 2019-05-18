package com.lh.demos.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.demos.R;
import com.lh.demos.base.BaseAppCompatActivity;

public class AnimationClickTestActivity extends BaseAppCompatActivity {

    private TextView mTextView;
    private ValueAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_click_test);

        setTitle("AnimcationClickTest");

        Button btn1 = findViewById(R.id.btn_1);
        Button btn2 = findViewById(R.id.btn_2);
        Button btn3 = findViewById(R.id.btn_3);

        mTextView = findViewById(R.id.tv);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TranslateAnimation animation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 400,
                        Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 400);
                animation.setFillAfter(true);
                animation.setDuration(1000);
                mTextView.startAnimation(animation);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimator = doAnimation();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnimator != null) {
                    mAnimator.cancel();
                }
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnimationClickTestActivity.this,"clicked me", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ValueAnimator doAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 400);
        animator.setDuration(3000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                mTextView.layout(value, value, value + mTextView.getWidth(), value + mTextView.getHeight());
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.d("lh123", "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.d("lh123", "onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                Log.d("lh123", "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                Log.d("lh123", "onAnimationRepeat");
            }
        });
        animator.start();

        return animator;
    }
}
