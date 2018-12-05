package com.lh.demos.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by leihui on 2018/11/27.
 * PointView
 */

public class PointView extends View {

    private Point mCurrentPoint;
    private Paint mPaint;

    public PointView(Context context) {
        super(context);
        initView(context);
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mCurrentPoint != null) {
            canvas.drawCircle(getWidth()/2, getHeight()/2, mCurrentPoint.getRadius(), mPaint);
        }
    }

    public void doAnimation() {
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), new Point(20), new Point(200));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentPoint = (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(2000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }
}
