package com.lh.demos.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.lh.demos.R;
import com.lh.demos.base.BaseAppCompatActivity;

public class TweenAnimationActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private Button mBtnAlpha;
    private Button mBtnScale;
    private Button mBtnRotate;
    private Button mBtnTrans;
    private Button mBtnSet;

    private TextView mTextView;

    private boolean bUseCode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);

        setTitle("TweenAnimation");

        mBtnAlpha = findViewById(R.id.btn_alpha);
        mBtnAlpha.setOnClickListener(this);
        mBtnScale = findViewById(R.id.btn_scale);
        mBtnScale.setOnClickListener(this);
        mBtnRotate = findViewById(R.id.btn_rotate);
        mBtnRotate.setOnClickListener(this);
        mBtnTrans = findViewById(R.id.btn_trans);
        mBtnTrans.setOnClickListener(this);
        mBtnSet = findViewById(R.id.btn_set);
        mBtnSet.setOnClickListener(this);

        mTextView = findViewById(R.id.tv_text);

        bUseCode = true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_alpha:
                onAlpha();
                break;
            case R.id.btn_scale:
                onScale();
                break;
            case R.id.btn_rotate:
                onRotate();
                break;
            case R.id.btn_trans:
                onTrans();
                break;
            case R.id.btn_set:
                onSet();
                break;
            default:
                break;
        }
    }

    private void onAlpha() {
        if (bUseCode) {
            Animation animation = new AlphaAnimation(1.f, 0.1f);
            animation.setDuration(3000);
            animation.setFillBefore(true);
            mTextView.startAnimation(animation);
        } else {
            // 加载动画资源
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
            // 开启动画
            mTextView.startAnimation(animation);
        }
    }

    private void onScale() {
        if (bUseCode) {
            Animation animation = new ScaleAnimation(0.f, 1.4f, 0.f, 1.4f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(700);
            animation.setFillAfter(true);
            mTextView.startAnimation(animation);
        } else {
            // 加载动画资源
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
            // 开启动画
            mTextView.startAnimation(animation);
        }
    }

    private void onRotate() {
        if (bUseCode) {
            Animation animation = new RotateAnimation(0, -650,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(3000);
            animation.setFillAfter(true);
            mTextView.startAnimation(animation);
        } else {
            // 加载动画资源
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
            // 开启动画
            mTextView.startAnimation(animation);
        }
    }

    private void onTrans() {
        if (bUseCode) {
            Animation animation = new TranslateAnimation(0, -80, 0, -80);
            animation.setDuration(2000);
            animation.setFillBefore(true);
            mTextView.startAnimation(animation);
        } else {
            // 加载动画资源
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.trans_anim);
            // 开启动画
            mTextView.startAnimation(animation);
        }
    }

    private void onSet() {
        if (bUseCode) {
            Animation alphaAnimation = new AlphaAnimation(0.f, 1.f);
            Animation scaleAnimation = new ScaleAnimation(0.f, 1.4f, 0.f, 1.4f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            Animation rotateAnimation = new RotateAnimation(0, 720,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            // shareInterpolator取值true或false，
            // 取true时，指在AnimationSet中定义一个插值器（interpolater），它下面的所有动画共同。
            // 如果设为false，则表示它下面的动画自己定义各自的插值器。
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(alphaAnimation);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(rotateAnimation);
            animationSet.setDuration(3000);
            animationSet.setFillAfter(true);
            mTextView.startAnimation(animationSet);
        } else {
            // 加载动画资源
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.set_anim);
            // 开启动画
            mTextView.startAnimation(animation);
        }
    }
}
