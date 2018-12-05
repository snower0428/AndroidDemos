package com.lh.demos.paints;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/8/21.
 * MyView
 */

public class GraphicView extends View {

    private Context mContext;
    private Paint mPaint;

    public GraphicView(Context context) {
        super(context);
        initView();
    }

    public GraphicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 设置画笔基本属性
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        mPaint.setShadowLayer(10, 15, 15, Color.GREEN);

        canvas.drawARGB(255, 255, 255, 255);
        canvas.drawCircle(190, 200, 150, mPaint);
    }
}
