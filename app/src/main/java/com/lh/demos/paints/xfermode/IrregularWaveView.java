package com.lh.demos.paints.xfermode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.lh.demos.R;

/**
 * Created by leihui on 2018/10/17.
 * IrregularWaveView
 */

public class IrregularWaveView extends View {

    private Bitmap mBitmapDst;
    private Bitmap mBitmapSrc;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;

    private int mItemWaveLength = 0;
    private int dx = 0;
    private Rect mRectDst;
    private Rect mRectSrc;

    public IrregularWaveView(Context context) {
        super(context);
        init();
    }

    public IrregularWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IrregularWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mBitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.wave_bg, null);
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.circle_shape, null);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        mItemWaveLength = mBitmapDst.getWidth() - mBitmapSrc.getWidth();

        mRectDst = new Rect(0, 0, 0, mBitmapSrc.getHeight());
        mRectSrc = new Rect(0, 0, mBitmapSrc.getWidth(), mBitmapSrc.getHeight());

        startAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmapSrc, 0, 0, mPaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        int left = dx;
        int right = dx + mBitmapSrc.getWidth();
        mRectDst.left = left;
        mRectDst.right = right;

        canvas.drawBitmap(mBitmapDst, mRectDst, mRectSrc, mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(mBitmapSrc, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(4000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dx = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
