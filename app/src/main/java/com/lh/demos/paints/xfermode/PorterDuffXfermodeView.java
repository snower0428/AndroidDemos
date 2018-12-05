package com.lh.demos.paints.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lh.core.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leihui on 2018/10/12.
 * AvoidXfermodeView
 */

public class PorterDuffXfermodeView extends View {

    private Context mContext;

    private String[] mTitles = {
            "Clear", "Src", "Dst", "SrcOver",
            "DstOver", "SrcIn", "DstIn", "SrcOut",
            "DstOut", "SrcATop", "DstATop", "Xor",
            "Darken", "Lighten", "Multiply", "Screen"
    };

    private PorterDuff.Mode[] mModes = {
            PorterDuff.Mode.CLEAR, PorterDuff.Mode.SRC, PorterDuff.Mode.DST, PorterDuff.Mode.SRC_OVER,
            PorterDuff.Mode.DST_OVER, PorterDuff.Mode.SRC_IN, PorterDuff.Mode.DST_IN, PorterDuff.Mode.SRC_OUT,
            PorterDuff.Mode.DST_OUT, PorterDuff.Mode.SRC_ATOP, PorterDuff.Mode.DST_ATOP, PorterDuff.Mode.XOR,
            PorterDuff.Mode.DARKEN, PorterDuff.Mode.LIGHTEN, PorterDuff.Mode.MULTIPLY, PorterDuff.Mode.SCREEN
    };

    private int mWidth;
    private int mHeight;
    private int mTitleHeight;
    private int mXInterval = 20;
    private int mNumberOfColumns = 4;

    private Bitmap mBitmapDst;
    private Bitmap mBitmapSrc;
    private Paint mPaint;
    private Rect mRect = new Rect(0, 0, 0, 0);

    private List<PorterDuffXfermode> mXfermodeList = new ArrayList<>();

    public PorterDuffXfermodeView(Context context) {
        super(context);
        init(context);
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void init(Context context) {
        mContext = context;
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        int screenWidth = ScreenUtil.getCurrentScreenWidth(context);

        mWidth = (screenWidth - mXInterval*(mNumberOfColumns-1)) / mNumberOfColumns;
        mHeight = mWidth;
        mTitleHeight = ScreenUtil.dip2px(context, 30);

        mBitmapDst = makeDst(mWidth/2, mHeight/2);
        mBitmapSrc = makeSrc(mWidth/2, mHeight/2);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);

        for (int i = 0; i < mModes.length; i++) {
            mXfermodeList.add(new PorterDuffXfermode(mModes[i]));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(ScreenUtil.dip2px(mContext, 1.f));
        mPaint.setTextSize(ScreenUtil.sp2px(mContext, 14.f));

        int x;
        int y;
        for (int i = 0; i < mTitles.length; i++) {
            String title = mTitles[i];

            // 左上角的点
            x = (mWidth+mXInterval) * (i%mNumberOfColumns);
            y = 100 + (mHeight + mTitleHeight) * (i/mNumberOfColumns);

            // 画正方形
            mPaint.setStyle(Paint.Style.STROKE);
            mRect.set(x, y, x + mWidth, y + mHeight);
            canvas.drawRect(mRect, mPaint);

            // 写文字，文字居中
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float textWidth = mPaint.measureText(title);
            float xMid = x+mWidth/2;
            float bottom = y;
            float baseLineY = bottom - fontMetrics.bottom;

            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(title, xMid - textWidth/2, baseLineY - 10, mPaint);

            int layerId = canvas.saveLayer(x, y, x + mWidth, y + mHeight, mPaint, Canvas.ALL_SAVE_FLAG);

            canvas.drawBitmap(mBitmapDst, x, y, mPaint);
            mPaint.setXfermode(mXfermodeList.get(i));
            canvas.drawBitmap(mBitmapSrc, x + mWidth / 4, y + mHeight / 4, mPaint);
            mPaint.setXfermode(null);

            canvas.restoreToCount(layerId);
        }
    }

    private static Bitmap makeDst(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFFFFCC44);
        canvas.drawOval(new RectF(0, 0, width, height), paint);
        return bitmap;
    }

    private static Bitmap makeSrc(int widht, int height) {
        Bitmap bitmap = Bitmap.createBitmap(widht, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(0, 0, widht, height, paint);
        return bitmap;
    }
}
