package com.lh.demos.paints.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lh.demos.R;

/**
 * Created by leihui on 2018/10/15.
 * LightBookView
 */

public class LightBookView extends View {

    private Bitmap mBitmapDst;
    private Bitmap mBitmapSrc;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;

    public LightBookView(Context context) {
        super(context);
        init();
    }

    public LightBookView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LightBookView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mBitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.book_bg, null);
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.book_light, null);
        mPaint = new Paint();
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mBitmapDst, 0, 0, mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(mBitmapSrc, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }
}
