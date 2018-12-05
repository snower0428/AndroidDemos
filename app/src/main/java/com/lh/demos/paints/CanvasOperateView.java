package com.lh.demos.paints;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/8/29.
 * CanvasTransView
 */

public class CanvasOperateView extends View {

    private Paint mPaintGreen;
    private Paint mPaintRed;
    private Rect mRect;

    private Paint mPaintGreen2;
    private Paint mPaintRed2;
    private Rect mRect2;

    private Paint mPaintGreen3;
    private Paint mPaintRed3;
    private Rect mRect3;

    private Paint mPaintGreen4;
    private Paint mPaintRed4;
    private Rect mRect4;

    public CanvasOperateView(Context context) {
        super(context);
        init();
    }

    public CanvasOperateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasOperateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintGreen = generatePaint(Color.GREEN, Paint.Style.STROKE, 3);
        mPaintRed = generatePaint(Color.RED, Paint.Style.STROKE, 3);
        mRect = new Rect(0, 0, 400, 300);

        mPaintGreen2 = generatePaint(Color.GREEN, Paint.Style.FILL, 5);
        mPaintRed2 = generatePaint(Color.RED, Paint.Style.STROKE, 5);
        mRect2 = new Rect(300, 10, 500, 100);

        mPaintGreen3 = generatePaint(Color.GREEN, Paint.Style.STROKE, 5);
        mPaintRed3 = generatePaint(Color.RED, Paint.Style.STROKE, 5);
        mRect3 = new Rect(10, 10, 200, 100);

        mPaintGreen4 = generatePaint(Color.GREEN, Paint.Style.STROKE, 5);
        mPaintRed4 = generatePaint(Color.RED, Paint.Style.STROKE, 5);
        mRect4 = new Rect(10, 10, 200, 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1.平移
//        canvas.drawRect(mRect, mPaintGreen);
//        canvas.translate(100, 100);
//        canvas.drawRect(mRect, mPaintRed);

        // 2.旋转
//        canvas.drawRect(mRect2, mPaintRed2);
//        canvas.rotate(30);
//        canvas.drawRect(mRect2, mPaintGreen2);

        // 3.缩放
//        canvas.drawRect(mRect3, mPaintGreen3);
//        canvas.scale(0.5f, 1);
//        canvas.drawRect(mRect3, mPaintRed3);

        // 4.斜切
        // sx为倾斜角度的tan值，tan60=根号3，为1.732
//        canvas.drawRect(mRect4, mPaintGreen4);
//        canvas.skew(1.732f, 0);
//        canvas.drawRect(mRect4, mPaintRed4);

        // 5.裁剪画布
//        canvas.drawColor(Color.RED);
//        canvas.clipRect(mRect4);
//        canvas.drawColor(Color.GREEN);

        // 6.画布的保存与恢复
        canvas.drawColor(Color.RED);
        // 保存当前画布即整屏
        canvas.save();
        canvas.clipRect(mRect4);
        canvas.drawColor(Color.GREEN);
        // 恢复整屏画布
        canvas.restore();
        canvas.drawColor(Color.BLUE);
    }

    private Paint generatePaint(int color, Paint.Style style, int width) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        return paint;
    }
}
