package com.lh.demos.paints.xfermode;

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
import android.view.MotionEvent;
import android.view.View;

import com.lh.demos.R;

/**
 * Created by leihui on 2018/10/15.
 * DogViewSRCOUT
 */

public class DogViewSRCOUT extends View {

    private Bitmap mBitmapDst;
    private Bitmap mBitmapSrc;
    private Paint mPaint;
    private Path mPath;
    private float mPreX;
    private float mPreY;
    private PorterDuffXfermode mXfermode;
    private Canvas mCanvas;

    public DogViewSRCOUT(Context context) {
        super(context);
        init();
    }

    public DogViewSRCOUT(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DogViewSRCOUT(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.dog, null);
        mBitmapDst = Bitmap.createBitmap(mBitmapSrc.getWidth(), mBitmapSrc.getHeight(), Bitmap.Config.ARGB_8888);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(45);

        mPath = new Path();
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        mCanvas = new Canvas(mBitmapDst);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        // 先把手指轨迹画到Dst上
        mCanvas.drawPath(mPath, mPaint);

        canvas.drawBitmap(mBitmapDst, 0, 0, mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(mBitmapSrc, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mPreX+event.getX())/2;
                float endY = (mPreY+event.getY())/2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }
}
