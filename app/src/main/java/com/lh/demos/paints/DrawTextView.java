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
 * Created by Administrator on 2018/8/30.
 * DrawTextView
 */

public class DrawTextView extends View {

    private Paint mPaint;

    public DrawTextView(Context context) {
        super(context);
        init();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int baseLineX = 0;
        int baseLineY = 400;

        // 写文字
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(120);
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("harvic\'s blog", baseLineX, baseLineY, mPaint);

        /**
         * fontMetrics.ascent = ascent线的y坐标 - baseline线的y坐标;
         * fontMetrics.descent = descent线的y坐标 - baseline线的y坐标;
         * fontMetrics.top = top线的y坐标 - baseline线的y坐标;
         * fontMetrics.bottom = top线的y坐标 - baseline线的y坐标;
         */

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float ascent = baseLineY + fontMetrics.ascent;
        float descent = baseLineY + fontMetrics.descent;
        float top = baseLineY + fontMetrics.top;
        float bottom = baseLineY + fontMetrics.bottom;

        // 画基线
        mPaint.setColor(Color.RED);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, mPaint);

        // 画ascent
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(baseLineX, ascent, 3000, ascent, mPaint);

        // 画descent
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(baseLineX, descent, 3000, descent, mPaint);

        // 画top
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(baseLineX, top, 3000, top, mPaint);

        // 画bottom
        mPaint.setColor(Color.RED);
        canvas.drawLine(baseLineX, bottom, 3000, bottom, mPaint);

        baseLineY = 700;
        String strText = "harvic\'s blog";

        fontMetrics = mPaint.getFontMetrics();
        ascent = baseLineY + fontMetrics.ascent;
        descent = baseLineY + fontMetrics.descent;
        top = baseLineY + fontMetrics.top;
        bottom = baseLineY + fontMetrics.bottom;

        // 画text所占区域
        float textWidth = mPaint.measureText(strText);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(baseLineX, top, baseLineX + textWidth, bottom, mPaint);

        // 画最小矩形
        Rect bounds = new Rect();
        mPaint.setColor(Color.RED);
        mPaint.getTextBounds(strText, 0, strText.length(), bounds);
        bounds.top = baseLineY + bounds.top;
        bounds.bottom =  baseLineY + bounds.bottom;
        canvas.drawRect(bounds, mPaint);

        // 写文字
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(120);
        canvas.drawText(strText, baseLineX, baseLineY, mPaint);

        canvas.drawText(strText, 0, 0 - fontMetrics.top, mPaint);
    }
}
