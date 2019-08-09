package com.lh.demos.paints;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/8/22.
 * PathTextView
 */

public class PathTextView extends View {

    private Context mContext;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private Paint mPaint;
    private Path mLinePath;

    private Path mCCWRectPath;  // 逆时针方向
    private Path mCWRectPath;   // 顺时针方向
    private RectF mCCWRectF;
    private RectF mCWRectF;

    private Path mRoundRectPath;
    private RectF mRoundRect1;
    private RectF mRoundRect2;

    private Path mCirclePath;
    private Path mOvalPath;
    private RectF mOvalRect;

    private Path mArcPath;
    private RectF mArcRect;

    private float[] mTextPos;

    private Path mCirclePath1;
    private Path mCirclePath2;

    public PathTextView(Context context) {
        super(context);
        initView(context);
    }

    public PathTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void setWidthHeight(int width, int height) {
        this.mScreenWidth = width;
        this.mScreenHeight = height;
    }

    private void initView(Context context) {
        mContext = context;
        mPaint = new Paint();
        mLinePath = new Path();

        mCCWRectPath = new Path();
        mCWRectPath = new Path();
        mCCWRectF = new RectF(50, 200, 240, 350);
        mCWRectF = new RectF(260, 200, 450, 350);

        mRoundRectPath = new Path();
        mRoundRect1 = new RectF(50, 400, 240, 550);
        mRoundRect2 = new RectF(290, 400, 480, 550);

        mCirclePath = new Path();
        mOvalPath = new Path();
        mOvalRect = new RectF(50, 850, 240, 1000);

        mArcPath = new Path();
        mArcRect = new RectF(50, 1050, 240, 1200);

        mLinePath = new Path();

        mTextPos = new float[] {50, 2100, 50, 2200, 50, 2300, 50, 2400};

        mCirclePath1 = new Path();
        mCirclePath2 = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mScreenWidth, mScreenHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED); // 设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);   // 设置画笔宽度

        // 1.1 直线路径
        mLinePath.moveTo(50, 50);
        mLinePath.lineTo(50, 150);
        mLinePath.lineTo(350, 150);
        mLinePath.lineTo(550, 150);
        mLinePath.close();  // 闭环
        canvas.drawPath(mLinePath, mPaint);

        // 1.2 矩形路径
        mCCWRectPath.addRect(mCCWRectF, Path.Direction.CCW);
        mCWRectPath.addRect(mCWRectF, Path.Direction.CW);
        canvas.drawPath(mCCWRectPath, mPaint);
        canvas.drawPath(mCWRectPath, mPaint);

        // 逆向和顺向的区别
        String text = "风萧萧兮易水寒，壮士一去兮不复返";
        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(30);
        canvas.drawTextOnPath(text, mCCWRectPath, 0, 0, mPaint);
        canvas.drawTextOnPath(text, mCWRectPath, 0, 0, mPaint);

        // 1.3 圆角矩形路径
        mPaint.setColor(Color.RED);
        mRoundRectPath.addRoundRect(mRoundRect1, 10, 15, Path.Direction.CCW);
        canvas.drawPath(mRoundRectPath, mPaint);
        float[] radii = {10, 15, 20, 25, 30, 35, 40, 45};
        mRoundRectPath.addRoundRect(mRoundRect2, radii, Path.Direction.CCW);
        canvas.drawPath(mRoundRectPath, mPaint);

        // 1.4 圆形路径
        mCirclePath.addCircle(150, 700, 100, Path.Direction.CCW);
        canvas.drawPath(mCirclePath, mPaint);

        // 1.5 椭圆路径
        mOvalPath.addOval(mOvalRect, Path.Direction.CCW);
        canvas.drawPath(mOvalPath, mPaint);

        // 1.6 弧形路径
        mArcPath.addArc(mArcRect, 0, 100);
        canvas.drawPath(mArcPath, mPaint);

        // 2.1 文字样式
        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);

        // 填充
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("欢迎光临Harvic的博客", 50, 1300, mPaint);

        // 描边
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText("欢迎光临Harvic的博客", 50, 1400, mPaint);

        // 填充且描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("欢迎光临Harvic的博客", 50, 1500, mPaint);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);

        mPaint.setFakeBoldText(true);   // 是否粗体
        mPaint.setUnderlineText(true);  // 下划线
        mPaint.setStrikeThruText(true); // 带删除线效果

        // 设置斜体，向右斜
        mPaint.setTextSkewX((float) -0.25);
        canvas.drawText("欢迎光临Harvic的博客", 50, 1600, mPaint);

        // 向左斜
        mPaint.setTextSkewX((float) 0.25);
        canvas.drawText("欢迎光临Harvic的博客", 50, 1700, mPaint);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        mPaint.setTextSize(80);

        canvas.drawText("欢迎光临Harvic的博客", 50, 1800, mPaint);

        mPaint.setTextScaleX(2);
        canvas.drawText("欢迎光临Harvic的博客", 50, 1900, mPaint);

        mPaint.setTextScaleX(1);
        canvas.drawText("欢迎光临Harvic的博客", 50, 2000, mPaint);

        mPaint.setColor(Color.GREEN);
        mPaint.setTextScaleX(2);
        canvas.drawText("欢迎光临Harvic的博客", 50, 2000, mPaint);

        // 2.2 指定个个文字位置
        mPaint.setColor(Color.RED);
        canvas.drawPosText("画图示例", mTextPos, mPaint);

        // 2.3 沿路径绘制
        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(45);
        mPaint.setStyle(Paint.Style.STROKE);

        mCirclePath1.addCircle(230, 2650, 180, Path.Direction.CCW);
        canvas.drawPath(mCirclePath1, mPaint);

        mCirclePath2.addCircle(750, 2650, 180, Path.Direction.CCW);
        canvas.drawPath(mCirclePath2, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawTextOnPath(text, mCirclePath1, 0, 0, mPaint);
        canvas.drawTextOnPath(text, mCirclePath2, 80, 30, mPaint);

        // 2.4 字体样式设置 - 使用系统字体
        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(60);
        mPaint.setStyle(Paint.Style.STROKE);

        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.NORMAL);
        mPaint.setTypeface(font);
        canvas.drawText("欢迎光临Harvic的博客", 50, 3000, mPaint);

        // 使用自定义字体
        AssetManager assetManager = mContext.getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager, "fonts/jian_luobo.ttf");
        mPaint.setTypeface(typeface);
        canvas.drawText("欢迎光临Harvic的博客", 50, 3100, mPaint);
    }
}
