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
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.lh.demos.R;

/**
 * Created by leihui on 2018/10/16.
 * HeartMapView
 */

public class HeartMapView extends View {

    private Paint mPaint;
    private int mItemWaveLength = 0;
    private int dx = 0;

    private Bitmap mBitmapDst;
    private Bitmap mBitmapSrc;

    private Canvas mCanvas;
    private PorterDuffXfermode mXfermode;

    public HeartMapView(Context context) {
        super(context);
        init();
    }

    public HeartMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);

        mBitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.heartmap, null);
        mBitmapSrc = Bitmap.createBitmap(mBitmapDst.getWidth(), mBitmapDst.getHeight(), Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mBitmapSrc);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        mItemWaveLength = mBitmapDst.getWidth();
        startAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 清空bitmap
        mCanvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
        // 画上矩形
        mCanvas.drawRect(mBitmapDst.getWidth() - dx, 0, mBitmapDst.getWidth(), mBitmapDst.getHeight(), mPaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        // 模式合成
        canvas.drawBitmap(mBitmapDst, 0, 0, mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(mBitmapSrc, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(6000);
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
