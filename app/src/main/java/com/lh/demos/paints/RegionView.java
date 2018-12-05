package com.lh.demos.paints;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2018/8/23.
 * RegionView
 */

public class RegionView extends View {

    private Context mContext;
    private Paint mPaint;
    private Region mRegion;

    private Path mOvalPath;
    private RectF mOvalRect;
    private Region mClipRegion;

    private Rect mRect11, mRect12;
    private Rect mRect21, mRect22;
    private Rect mRect31, mRect32;
    private Rect mRect41, mRect42;
    private Rect mRect51, mRect52;
    private Rect mRect61, mRect62;

    private Region mRegion11, mRegion12;
    private Region mRegion21, mRegion22;
    private Region mRegion31, mRegion32;
    private Region mRegion41, mRegion42;
    private Region mRegion51, mRegion52;
    private Region mRegion61, mRegion62;

    private Paint mPaintFill;

    public RegionView(Context context) {
        super(context);
        initView(context);
    }

    public RegionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        mPaint = new Paint();
        mRegion = new Region(10, 10, 100, 100);

        mOvalPath = new Path();
        mOvalRect = new RectF(250, 50, 400, 500);
        mClipRegion = new Region(250, 50, 400, 200);

        mRect11 = new Rect(10, 500, 310, 600);
        mRect12 = new Rect(110, 400, 210, 700);
        mRect21 = new Rect(320, 500, 620, 600);
        mRect22 = new Rect(420, 400, 520, 700);
        mRect31 = new Rect(630, 500, 930, 600);
        mRect32 = new Rect(730, 400, 830, 700);
        mRect41 = new Rect(10, 810, 310, 910);
        mRect42 = new Rect(110, 710, 210, 1010);
        mRect51 = new Rect(320, 810, 620, 910);
        mRect52 = new Rect(420, 710, 520, 1010);
        mRect61 = new Rect(630, 810, 930, 910);
        mRect62 = new Rect(730, 710, 830, 1010);

        mRegion11 = new Region(mRect11);
        mRegion12 = new Region(mRect12);
        mRegion21 = new Region(mRect21);
        mRegion22 = new Region(mRect22);
        mRegion31 = new Region(mRect31);
        mRegion32 = new Region(mRect32);
        mRegion41 = new Region(mRect41);
        mRegion42 = new Region(mRect42);
        mRegion51 = new Region(mRect51);
        mRegion52 = new Region(mRect52);
        mRegion61 = new Region(mRect61);
        mRegion62 = new Region(mRect62);

        mPaintFill = new Paint();
    }

    private void drawRegion(Canvas canvas, Region rgn, Paint paint) {
        RegionIterator iterator = new RegionIterator(rgn);
        Rect rect = new Rect();
        while (iterator.next(rect)) {
            canvas.drawRect(rect, paint);
            Log.d("lh123", "rect:" + rect.toString());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);

        mRegion.set(100, 100, 200, 200);
        drawRegion(canvas, mRegion, mPaint);

        //mPaint.setStyle(Paint.Style.STROKE);
        mOvalPath.addOval(mOvalRect, Path.Direction.CCW);
        mRegion.setPath(mOvalPath, mClipRegion);
        drawRegion(canvas, mRegion, mPaint);

        // 画矩形
        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);

        canvas.drawRect(mRect11, mPaint);
        canvas.drawRect(mRect12, mPaint);
        canvas.drawRect(mRect21, mPaint);
        canvas.drawRect(mRect22, mPaint);
        canvas.drawRect(mRect31, mPaint);
        canvas.drawRect(mRect32, mPaint);
        canvas.drawRect(mRect41, mPaint);
        canvas.drawRect(mRect42, mPaint);
        canvas.drawRect(mRect51, mPaint);
        canvas.drawRect(mRect52, mPaint);
        canvas.drawRect(mRect61, mPaint);
        canvas.drawRect(mRect62, mPaint);

        mPaintFill.setColor(Color.GREEN);
        mPaintFill.setStyle(Paint.Style.FILL);

        // 最终区域为第一个区域，与第二个区域相交的区域
        mRegion11.op(mRegion12, Region.Op.INTERSECT);
        drawRegion(canvas, mRegion11, mPaintFill);

        // 最终区域为第一个区域，与第二个区域不同的区域
        mRegion21.op(mRegion22, Region.Op.DIFFERENCE);
        drawRegion(canvas, mRegion21, mPaintFill);

        // 最终区域为第一个区域，与第二个区域组合在一起的区域
        mRegion31.op(mRegion32, Region.Op.UNION);
        drawRegion(canvas, mRegion31, mPaintFill);

        // 最终区域为第一个区域，与第二个区域相交之外的区域
        mRegion41.op(mRegion42, Region.Op.XOR);
        drawRegion(canvas, mRegion41, mPaintFill);

        // 最终区域为第二个区域，与第一个区域不同的区域
        mRegion51.op(mRegion52, Region.Op.REVERSE_DIFFERENCE);
        drawRegion(canvas, mRegion51, mPaintFill);

        // 最终区域为第二个区域
        mRegion61.op(mRegion62, Region.Op.REPLACE);
        drawRegion(canvas, mRegion61, mPaintFill);
    }
}
