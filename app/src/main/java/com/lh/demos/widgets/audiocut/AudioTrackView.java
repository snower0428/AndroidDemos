package com.lh.demos.widgets.audiocut;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.lh.core.utils.ScreenUtil;


/**
 * Created by leihui on 2018/12/29.
 * AudioTrackView
 */

public class AudioTrackView extends View {

    private Paint slipPaint = null;
    private Paint maskPaint = null;
    private Paint maskOverPaint = null;
    private Paint mTrackPaint = null;

    private int progress = 0;

    private Bitmap shape = null;
    private Bitmap mask = null;
    private Bitmap maskOver = null;

    private boolean isNewMask = true;
    private int trackTemplateCount;//track 模板的竖条的个数
    private int mBackgroundColor;
    private int mForegroundColor;
    private int mSpaceSize;
    private int mTrackItemWidth;
    private int mTrackFragmentCount;//track 片段个数
    private float[] mTrackTemplateData;// = {0.80f, 0.70f, 0.40f, 0.60f, 0.40f, 0.30f, 0.50f, 0.70f, 0.65f, 0.90f};//track中一个片段中每个竖条的高度比例

    private int mEmptyWidth = 0;
    private int mOffsetX = 0;
    private int mMaskWidth = 0;

    public void setBackgroundColorInt(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
        invalidate();
    }

    public void setForegroundColor(int foregroundColor) {
        this.mForegroundColor = foregroundColor;
        invalidate();
    }

    public void setSpaceSize(int spaceSize) {
        this.mSpaceSize = spaceSize;
        invalidate();
    }

    public void setTrackItemWidth(int trackItemWidth) {
        this.mTrackItemWidth = trackItemWidth;
        invalidate();
    }

    public void setTrackFragmentCount(int trackFragmentCount) {
        this.mTrackFragmentCount = trackFragmentCount;
        invalidate();
    }

    public void setTrackTemplateCount(int count) {
        mTrackTemplateData = new float[count+8];
        for (int i = 0; i < count+8; i++) {
            float random = 30 + (((float) Math.random() * 100) % 60);
            mTrackTemplateData[i] = random / 100;
        }
        // 前面和后面count个高度置0
        int leftCount = mMaskWidth / (mTrackItemWidth + mSpaceSize);
        if (leftCount < mTrackTemplateData.length) {
            for (int i = 0; i < leftCount;  i++) {
                mTrackTemplateData[i] = 0;
                mTrackTemplateData[mTrackTemplateData.length-(i+1)] = 0;
            }
        }

        trackTemplateCount = mTrackTemplateData.length;
        invalidate();
    }

    public void setEmptyWidth(int width) {
        mEmptyWidth = width;
        invalidate();
    }

    public void setOffsetX(int offsetX) {
        mOffsetX = offsetX;
        invalidate();
    }

    public void setMaskWidth(int maskWidth) {
        mMaskWidth = maskWidth;
        invalidate();
    }

    public void setTrackTemplateData(float[] mTrackTemplateData) {
        this.mTrackTemplateData = mTrackTemplateData;
        invalidate();
    }

    public AudioTrackView(Context context) {
        super(context);
        init();
    }

    public AudioTrackView(Context context, AttributeSet paramAttributeSet) {
        super(context, paramAttributeSet);
        init();
    }

    private void init() {
        int count = 30;
        mTrackTemplateData = new float[count];
        for (int i = 0; i < count; i++) {
            float random = 30 + (((float) Math.random() * 100) % 60);
            mTrackTemplateData[i] = random / 100;
        }

        slipPaint = new Paint();
        slipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        slipPaint.setFilterBitmap(false);

        maskPaint = new Paint();
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        maskPaint.setFilterBitmap(false);

        maskOverPaint = new Paint();
        maskOverPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        maskOverPaint.setFilterBitmap(false);

        mTrackPaint = new Paint();
        mTrackPaint.setAntiAlias(true);
        mTrackPaint.setColor(Color.LTGRAY);
        mTrackPaint.setStyle(Paint.Style.FILL);
        mTrackPaint.setStrokeCap(Paint.Cap.ROUND);

        trackTemplateCount = mTrackTemplateData.length;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int height = 0;
        //获得宽度MODE
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        //获得宽度的值
        if (modeW == MeasureSpec.AT_MOST) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        if (modeW == MeasureSpec.EXACTLY) {
            width = widthMeasureSpec;
        }
        if (modeW == MeasureSpec.UNSPECIFIED) {
            //track 宽
            width = mSpaceSize + (mTrackItemWidth + mSpaceSize) * trackTemplateCount * mTrackFragmentCount + mEmptyWidth;
        }
        //获得高度MODE
        int modeH = MeasureSpec.getMode(height);
        //获得高度的值
        if (modeH == MeasureSpec.AT_MOST) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        if (modeH == MeasureSpec.EXACTLY) {
            height = heightMeasureSpec;
        }
        if (modeH == MeasureSpec.UNSPECIFIED) {
            //ScrollView和HorizontalScrollView
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        //设置宽度和高度
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            drawTrack(canvas, mBackgroundColor);
            int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
            drawTrack(canvas, mForegroundColor);
            //切割
            if (shape == null || shape.isRecycled()) {
                shape = getShape(getWidth(), getHeight());
            }
            canvas.drawBitmap(shape, 0, 0, slipPaint);

            //画透明格子
            if (isNewMask) {
                mask = getMask(getWidth(), getHeight());
                isNewMask = false;
            }
            canvas.drawBitmap(mask, 0, 0, maskPaint);

            canvas.restoreToCount(layer);

            //画左右蒙板
            layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

            drawTrack(canvas, Color.LTGRAY);
            //画透明格子
            maskOver = getMaskOver(getWidth(), getHeight());
            canvas.drawBitmap(maskOver, 0, 0, maskOverPaint);

            canvas.restoreToCount(layer);

        } catch (Exception e) {
            e.printStackTrace();
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

    private Bitmap getMaskOver(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        RectF localRectF = new RectF(0, 0, width, height);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.LTGRAY);

        RectF rectLeft = new RectF(0, 0, mOffsetX + mMaskWidth, getHeight());
        canvas.drawRect(rectLeft, paint);

        HorizontalScrollView scrollView = (HorizontalScrollView) getParent();
        float left = mOffsetX + scrollView.getWidth() - mMaskWidth;
        RectF rectRight = new RectF(left, 0, getWidth(), getHeight());
        canvas.drawRect(rectRight, paint);

        return bitmap;
    }

    private Bitmap getMask(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true); //去锯齿
        paint.setColor(mForegroundColor);
        //paint.setAlpha(160);

        canvas.drawRect(0, 0, progress, height, paint);

        return bitmap;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        isNewMask = true;
        this.invalidate();
    }

    //画竖条
    private void drawTrack(Canvas canvas, int color) {
        mTrackPaint.setColor(color);
        mTrackPaint.setStrokeWidth(mTrackItemWidth);
        if (trackTemplateCount <= 0) {
            return;
        }
        int cy = canvas.getHeight();

        for (int j = 0; j < mTrackFragmentCount; j++) {
            for (int i = 0; i < trackTemplateCount; i++) {
                int x = mSpaceSize + (mTrackItemWidth + mSpaceSize) * i + (mTrackItemWidth + mSpaceSize) * trackTemplateCount * j;
                float stopY = cy - mTrackTemplateData[i] * getHeight();
                if (mTrackTemplateData[i] == 0.f) {
                    //stopY = cy - ScreenUtil.dip2px(getContext(), 10);
                    continue;
                }
                canvas.drawLine(x, cy - ScreenUtil.dip2px(getContext(), 10), x, stopY, mTrackPaint);
            }
        }
    }
}
