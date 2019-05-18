package com.lh.demos.animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.lh.demos.R;
import com.lh.demos.base.BaseAppCompatActivity;

public class OfObjectAnimationActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private Button mBtnStart;
    private Button mBtnCancel;
    private TextView mTextView;

    private ValueAnimator mAnimator;

    private Button mBtnStart2;
    private PointView mPointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_of_object_animation);

        setTitle("OfObjectAnimation");

        mBtnStart = findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(this);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnCancel.setOnClickListener(this);

        mTextView = findViewById(R.id.tv_text);

        mBtnStart2 = findViewById(R.id.btn_start_2);
        mBtnStart2.setOnClickListener(this);
        mPointView = findViewById(R.id.point_view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                mAnimator = onStartAnim();
                break;
            case R.id.btn_start_2:
                onStartAnim2();
                break;
            case R.id.btn_cancel:
                onCancelAnim();
                break;
            default:
                break;
        }
    }

    private ValueAnimator onStartAnim() {
        ValueAnimator animator = ValueAnimator.ofObject(new CharEvaluator(), 'A', 'Z');
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                char c = (char) valueAnimator.getAnimatedValue();
                mTextView.setText(String.valueOf(c));
            }
        });
        animator.setDuration(10000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
        return animator;
    }

    private void onStartAnim2() {
        mPointView.doAnimation();
    }

    private void onCancelAnim() {
        if (mAnimator != null) {
            mAnimator.cancel();
        }
    }
}
