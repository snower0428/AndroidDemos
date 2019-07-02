package com.lh.demos.paints;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by leihui on 2019/7/2.
 * FillTypeView
 */

public class FillTypeView extends LinearLayout {

    private Path mPath;
    private Paint mPaint;
    private float[] mCorners;

    public FillTypeView(Context context) {
        super(context);
        init(context);
    }

    public FillTypeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FillTypeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPath = new Path();
        // set odd mode
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#f0ff0000"));

        int cornerRadius = 60;
        mCorners = new float[]{
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius
        };
        //add round rect
        int width = getWidth();
        int height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPath.addRoundRect(new RectF(0, 0, getWidth(), getHeight()), mCorners, Path.Direction.CCW);

        canvas.save();
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }
}
