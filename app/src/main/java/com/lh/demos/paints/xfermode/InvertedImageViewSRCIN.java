package com.lh.demos.paints.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lh.demos.R;

/**
 * Created by leihui on 2018/10/15.
 * InvertedImageViewSRCIN
 */

public class InvertedImageViewSRCIN extends View {

    private Bitmap mBitmapDst;
    private Bitmap mBitmapSrc;
    private Bitmap mBitmapRevert;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;

    public InvertedImageViewSRCIN(Context context) {
        super(context);
        init();
    }

    public InvertedImageViewSRCIN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InvertedImageViewSRCIN(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mBitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.dog_invert_shade, null);
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.dog, null);

        // 生成倒影图
        Matrix matrix = new Matrix();
        matrix.setScale(1.f, -1.f);
        mBitmapRevert = Bitmap.createBitmap(mBitmapSrc, 0, 0, mBitmapSrc.getWidth(), mBitmapSrc.getHeight(), matrix, true);

        mPaint = new Paint();
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 先画出小狗图片
        canvas.drawBitmap(mBitmapSrc, 0, 0, mPaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        // 再画出倒影
        canvas.translate(0, mBitmapSrc.getHeight());
        canvas.drawBitmap(mBitmapDst, 0, 0, mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(mBitmapRevert, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }
}
