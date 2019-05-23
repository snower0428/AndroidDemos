package com.lh.demos.paints.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by leihui on 2019/5/23.
 * DstInDemoView2
 */

public class DstInDemoView2 extends View {

    private Context mContext;

    private float mCx = 300;
    private float mCy = 500;
    private float mRadius = 200;
    private Paint mPaint;
    private PorterDuffXfermode mXfermodeSrcOver = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
    private PorterDuffXfermode mXfermodeDstout = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    public DstInDemoView2(Context context) {
        super(context);
        init(context);
    }

    public DstInDemoView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DstInDemoView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        mPaint.setColor(Color.parseColor("#b2000000"));
        mPaint.setXfermode(mXfermodeSrcOver);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        mPaint.setColor(Color.parseColor("#ff000000"));
        mPaint.setXfermode(mXfermodeDstout);

        drawCircle(canvas);

        mPaint.setXfermode(null);
        canvas.restoreToCount(layer);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(mCx, mCy, mRadius, mPaint);
    }
}
