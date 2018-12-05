package com.lh.demos.paints.xfermode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.lh.demos.R;

/**
 * Created by leihui on 2018/10/16.
 * CircleWaveDSTIN
 */

public class CircleWaveDSTIN extends View {

    private Paint mPaint;
    private Path mPath;
    private int mItemWaveLength = 1000;
    private int dx;

    private Bitmap mBitmapDst;
    private Bitmap mBitmapSrc;
    private Canvas mCanvas;
    private PorterDuffXfermode mXfermode;

    public CircleWaveDSTIN(Context context) {
        super(context);
        init();
    }

    public CircleWaveDSTIN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleWaveDSTIN(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mPath = new Path();

        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.text_shade, null);
        mBitmapDst = Bitmap.createBitmap(mBitmapSrc.getWidth(), mBitmapSrc.getHeight(), Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mBitmapDst);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        startAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        generateWavePath();

        // 先清空bitmap上的图像，然后再画上path
        mCanvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
        mCanvas.drawPath(mPath, mPaint);

        canvas.drawBitmap(mBitmapSrc, 0, 0, mPaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mBitmapDst, 0, 0, mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(mBitmapSrc, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }

    private void generateWavePath() {
        mPath.reset();
        int originY = mBitmapSrc.getHeight() / 2;
        int halfWaveLen = mItemWaveLength / 2;
        mPath.moveTo(-mItemWaveLength + dx, originY);
        for (int i = -mItemWaveLength; i <= getWidth() + mItemWaveLength; i+=mItemWaveLength) {
            mPath.rQuadTo(halfWaveLen/2, -50, halfWaveLen, 0);
            mPath.rQuadTo(halfWaveLen/2, 50, halfWaveLen, 0);
        }
        mPath.lineTo(mBitmapSrc.getWidth(), mBitmapSrc.getHeight());
        mPath.lineTo(0, mBitmapSrc.getHeight());
        mPath.close();
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dx = (int) valueAnimator.getAnimatedValue();
                Log.d("lh123", "dx:" + dx);
                postInvalidate();
            }
        });
        animator.start();
    }
}
