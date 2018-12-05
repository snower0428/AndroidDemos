package com.lh.demos.paints;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2018/8/30.
 * BezierView2
 */

public class BezierView2 extends View {

    private Paint mPaint;
    private Path mPath;
    private int mItemWaveLength = 800;
    private int mDx = 0;
    private int mDy = 0;
    private boolean mMoveDown = true;

    public BezierView2(Context context) {
        super(context);
        init();
    }

    public BezierView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        int originY = 300;
        if (mMoveDown) {
            mDy += 2;
        } else {
            mDy -= 2;
        }
        if (originY+mDy > getHeight()) {
            mMoveDown = false;
        } else if (originY+mDy < 300) {
            mMoveDown = true;
        }
        int halfWaveLen = mItemWaveLength/2;
        mPath.moveTo(-mItemWaveLength+mDx, originY+mDy);
        for (int i = -mItemWaveLength; i < getWidth()+mItemWaveLength; i+=mItemWaveLength) {
            mPath.rQuadTo(halfWaveLen/2, -100, halfWaveLen, 0);
            mPath.rQuadTo(halfWaveLen/2, 100, halfWaveLen, 0);
        }
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(0, getHeight());
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mDx = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
