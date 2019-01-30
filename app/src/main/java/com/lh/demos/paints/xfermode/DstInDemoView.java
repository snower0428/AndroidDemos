package com.lh.demos.paints.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lh.core.utils.ScreenUtil;
import com.lh.demos.R;

/**
 * Created by leihui on 2019/1/9.
 * SrcInDemoView
 */

public class DstInDemoView extends View {

    private Context mContext;

    private Paint mSlipPaint = null;
    private Paint mMaskPaint = null;
    private Paint mGrayMaskPaint = null;
    private Paint mTrackPaint = null;


    private int mSpaceSize;
    private int mTrackItemWidth;
    private int mTrackFragmentCount;
    private float[] mTrackTemplateData;
    private int mTrackTemplateCount;

    private int mBackgroundColor;
    private int mForegroundColor;

    private Bitmap mBitmapShape = null;
    private Bitmap mBitmapMask = null;
    private Bitmap mBitmapGrayMask = null;

    private boolean mIsNewMask = true;

    private float mLastX = 0;
    private float mMoveX = 0;
    private float mMoveByX = 0;
    private float mLeft = 0;

    public DstInDemoView(Context context) {
        super(context);
        init(context);
    }

    public DstInDemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DstInDemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mSpaceSize = ScreenUtil.dip2px(mContext, 2.5f);
        mTrackItemWidth = ScreenUtil.dip2px(mContext, 5);
        mTrackFragmentCount = 1;
        int count = 40;
        mTrackTemplateData = new float[count];
        for (int i = 0; i < count; i++) {
            float random = 30 + (((float) Math.random() * 100) % 60);
            mTrackTemplateData[i] = random / 100;
        }
        mTrackTemplateCount = mTrackTemplateData.length;

        mBackgroundColor = mContext.getResources().getColor(R.color.colorPrimary);
        mForegroundColor = mContext.getResources().getColor(R.color.colorAccent);

        mSlipPaint = new Paint();
        mSlipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mSlipPaint.setFilterBitmap(false);

        mMaskPaint = new Paint();
        mMaskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mMaskPaint.setFilterBitmap(false);

        mGrayMaskPaint = new Paint();
        mGrayMaskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //mGrayMaskPaint.setFilterBitmap(false);

        mTrackPaint = new Paint();
        mTrackPaint.setAntiAlias(true);
        mTrackPaint.setColor(Color.LTGRAY);
        mTrackPaint.setStyle(Paint.Style.FILL);
        mTrackPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getRawX();
                Log.d("lh123", "mLastX:" + mLastX);
                return true;
            case MotionEvent.ACTION_MOVE:
                mMoveX = event.getRawX();
                mMoveByX = mMoveX - mLastX;
                mLastX = mMoveX;
                mIsNewMask = true;
                Log.d("lh123", "mMoveX:" + mMoveX);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawTrack(canvas, mBackgroundColor);

        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        drawTrack(canvas, mForegroundColor);
        //切割
//        if (mBitmapShape == null || mBitmapShape.isRecycled()) {
//            mBitmapShape = getShape(getWidth(), getHeight());
//        }
        //canvas.drawBitmap(mBitmapShape, 0, 0, mSlipPaint);

        //画透明格子
        if (mIsNewMask) {
            mBitmapMask = getMask(getWidth(), getHeight());
            mBitmapGrayMask = getGrayMask(getWidth(), getHeight());
            mIsNewMask = false;
        }
        canvas.drawBitmap(mBitmapMask, 0, 0, mMaskPaint);
        canvas.drawBitmap(mBitmapGrayMask, 0, 0, mGrayMaskPaint);

        canvas.restoreToCount(layer);
    }

    //画竖条
    private void drawTrack(Canvas canvas, int color) {
        mTrackPaint.setColor(color);
        mTrackPaint.setStrokeWidth(ScreenUtil.dip2px(mContext, 5));
        if (mTrackTemplateCount <= 0) {
            return;
        }
        int cy = canvas.getHeight();

        for (int j = 0; j < mTrackFragmentCount; j++) {
            for (int i = 0; i < mTrackTemplateCount; i++) {
                int x = mSpaceSize + (mTrackItemWidth + mSpaceSize) * i + (mTrackItemWidth + mSpaceSize) * mTrackTemplateCount * j;
                canvas.drawLine(x, cy - ScreenUtil.dip2px(getContext(), 10), x,
                        cy - mTrackTemplateData[i] * getHeight(), mTrackPaint);
            }
        }
    }

    private Bitmap getShape(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        RectF localRectF = new RectF(0, 0, width, height);
        Paint paint = new Paint();
        paint.setAntiAlias(true); //去锯齿
        canvas.drawRect(localRectF, paint);

        return bitmap;
    }

    private Bitmap getMask(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true); //去锯齿
        paint.setColor(mForegroundColor);

        float blockWidth = width / 4;
        mLeft += mMoveByX;
        if (mLeft <= 0) {
            mLeft = 0;
        }
        if (mLeft + blockWidth >= width) {
            mLeft = width - blockWidth;
        }
        //canvas.drawRect(mLeft, 0, mLeft + blockWidth, height, paint);

        canvas.drawRect(0, 0, blockWidth, height, paint);

        return bitmap;
    }

    private Bitmap getGrayMask(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true); //去锯齿
        paint.setColor(Color.LTGRAY);

        canvas.drawRect(0, 0, (mTrackItemWidth + mSpaceSize) * 4, height, paint);
        canvas.drawRect(getWidth() - (mTrackItemWidth + mSpaceSize) * 4, 0, getWidth(), height, paint);

        return bitmap;
    }
}
