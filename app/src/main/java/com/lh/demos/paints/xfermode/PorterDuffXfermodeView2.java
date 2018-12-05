package com.lh.demos.paints.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by leihui on 2018/10/15.
 * PorterDuffXfermodeView2
 */

public class PorterDuffXfermodeView2 extends View {

    private int mWidth = 400;
    private int mHeight = 400;
    private Bitmap mBitmapDst;
    private Bitmap mBitmapSrc;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;

    public PorterDuffXfermodeView2(Context context) {
        super(context);
        init();
    }

    public PorterDuffXfermodeView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PorterDuffXfermodeView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mBitmapDst = makeDst(mWidth, mHeight);
        mBitmapSrc = makeSrc(mWidth, mHeight);
        mPaint = new Paint();
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, mWidth*2, mHeight*2, mPaint);

        int layerId = canvas.saveLayer(0, 0, mWidth*2, mHeight*2, mPaint, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mBitmapDst, 0, 0, mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(mBitmapSrc, mWidth/2, mHeight/2, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }

    private static Bitmap makeDst(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFFFFCC44);
        canvas.drawOval(new RectF(0, 0, width, height), paint);
        return bitmap;
    }

    private static Bitmap makeSrc(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(0, 0, width, height, paint);
        return bitmap;
    }
}
