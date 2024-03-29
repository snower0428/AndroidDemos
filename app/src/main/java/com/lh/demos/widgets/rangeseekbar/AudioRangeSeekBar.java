package com.lh.demos.widgets.rangeseekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.lh.core.utils.ScreenUtil;
import com.lh.demos.R;

/**
 * Created by Jacky on 2017/6/20.
 * 滑块
 */

public class AudioRangeSeekBar<T extends Number> extends View{

    private static final Integer DEFAULT_MINIMUM = 0;
    private static final Integer DEFAULT_MAXIMUM = 100;
    private T absoluteMinValue, absoluteMaxValue;
    private double minValuePrim, maxValuePrim;
    private RectF mRect;
    private RectF mRectIndicator;
    private Paint mPaint;
    private Bitmap thumbImage;
    private Bitmap thumbImagePress;
    private OnRangeSeekBarChangeListener<T> listener;
    private int mActivePointerId;
    private float mDownMotionX;
    private Thumb pressedThumb = null;
    private NumberType mNumberType;
    private boolean mIsDragging;
    private boolean mIsSingleMode;

    private int mOuterRadius;
    private int mInnerRadius;
    private int mSeekBarGap;//内外层seekBar的间距
    private int DEFAULT_TEXT_SIZE_IN_DP = 10;
    private int SEEK_BAR_OUTER_LAYER = 24;
    private int SEEK_BAR_INNER_LAYER = 8;
    private int mPadding = 0;
    private int mTextSize;
    private int mTextGap = 10;//数字与滑块间距
    private int mScaledTouchSlop;
    private int mDecimalPrecision;//数字精度
    public int ACTION_POINTER_INDEX_MASK = 0x0000ff00, ACTION_POINTER_INDEX_SHIFT = 8;
    private int outerSeekBarColor = Color.parseColor("#F2F4F5");
    private int innerSeekBarColor = Color.parseColor("#e1e1e1");
    private int innerActiveSeekBarColor = Color.parseColor("#fd3368");
    private float mThumbWidth;
    private float mThumbHalfWidth;
    private float mThumbHalfHeight;
    private double mNormalizedMinValue = 0d;
    private double mNormalizedMaxValue = 1d;
    private int mThumbInterval = 0;
    private int mBeginValue = 0;
    private int mEndValue = 0;
    private int mIndicatorPosition = 0;

    public AudioRangeSeekBar(Context context) {
        super(context);
        init(context, null);
    }

    public AudioRangeSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AudioRangeSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            setRangeToDefaultValues();
        } else {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AudioRangeSeekBar, 0, 0);
            setNumberRange(
                    getNumericValueFromAttributes(ta, R.styleable.AudioRangeSeekBar_MinValue, DEFAULT_MINIMUM),
                    getNumericValueFromAttributes(ta, R.styleable.AudioRangeSeekBar_MaxValue, DEFAULT_MAXIMUM));
            mIsSingleMode = ta.getBoolean(R.styleable.AudioRangeSeekBar_singleThumb, false);
            ta.recycle();
        }

        initRes(context);
    }

    private void initRes(Context context) {
        mRect = new RectF();
        mRectIndicator = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        thumbImage = BitmapFactory.decodeResource(getResources(), R.drawable.music_seek_thumb);
        thumbImagePress = BitmapFactory.decodeResource(getResources(), R.drawable.music_seek_thumb);
        mThumbWidth = thumbImage.getWidth();
        mThumbHalfWidth = 0.5f * mThumbWidth;
        mThumbHalfHeight = 0.5f * thumbImage.getHeight();
        mTextSize = ScreenUtil.sp2px(context, DEFAULT_TEXT_SIZE_IN_DP);
        mPadding = (getPaddingLeft() + getPaddingRight()) / 2;
        mOuterRadius = ScreenUtil.dip2px(getContext(), SEEK_BAR_OUTER_LAYER / 2);
        mInnerRadius = ScreenUtil.dip2px(getContext(), SEEK_BAR_INNER_LAYER / 2);
        mSeekBarGap = ScreenUtil.dip2px(getContext(), (SEEK_BAR_OUTER_LAYER - SEEK_BAR_INNER_LAYER) / 2);
        setFocusable(true);
        setFocusableInTouchMode(true);
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    private void setNumberRange(T minValue, T maxValue) {
        this.absoluteMinValue = minValue;
        this.absoluteMaxValue = maxValue;
        setValuePrimAndNumberType();
    }

    @SuppressWarnings("unchecked")
    private T getNumericValueFromAttributes(TypedArray a, int attribute, int defaultValue) {
        TypedValue tv = a.peekValue(attribute);
        if (tv == null) {
            return (T) Integer.valueOf(defaultValue);
        }

        int type = tv.type;
        if (type == TypedValue.TYPE_FLOAT) {
            return (T) Float.valueOf(a.getFloat(attribute, defaultValue));
        } else {
            return (T) Integer.valueOf(a.getInteger(attribute, defaultValue));
        }
    }

    @SuppressWarnings("unchecked")
    private void setRangeToDefaultValues() {
        this.absoluteMinValue = (T) DEFAULT_MINIMUM;
        this.absoluteMaxValue = (T) DEFAULT_MAXIMUM;
        setValuePrimAndNumberType();
    }

    private void setValuePrimAndNumberType() {
        minValuePrim = absoluteMinValue.doubleValue();
        maxValuePrim = absoluteMaxValue.doubleValue();
        int minPrecision = getDecimalPrecision(minValuePrim);
        int maxPrecision = getDecimalPrecision(maxValuePrim);
        mDecimalPrecision = maxPrecision > minPrecision ? maxPrecision : minPrecision;
        mNumberType = NumberType.fromNumber(absoluteMaxValue);
    }

    private int getDecimalPrecision(double value) {
        String minString = String.valueOf(value);
        if (minString.contains(".")) {
            String[] split = minString.split("\\.");
            return split[1].length();
        }
        return 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    private int measure(int measureSpec, boolean isWidth) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
            result += padding;
            if (mode == MeasureSpec.AT_MOST) {
                if (isWidth) {
                    result = Math.max(result, size);
                } else {
                    result = Math.min(result, size);
                }
            }
        }
        return result;
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return thumbImage.getHeight() * 2;
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return thumbImage.getHeight() + ScreenUtil.dip2px(getContext(), mTextGap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float minSeekValue = getSeekPosition(mNormalizedMinValue);
        float maxSeekValue = getSeekPosition(mNormalizedMaxValue);

        drawSeekBar(canvas, minSeekValue, maxSeekValue);
        //绘制按钮
        if (!mIsSingleMode) {
            drawThumb(minSeekValue, canvas, Thumb.MIN);
        }
        drawThumb(maxSeekValue, canvas, Thumb.MAX);
        //绘制文字
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(Color.parseColor("#808080"));
        if (!mIsSingleMode) {
            String minFormatValue = formatValue(normalizedToValue(mNormalizedMinValue) + "");
            setThumbValue(minFormatValue, minSeekValue, false, canvas);
        }
        String maxFormatValue = formatValue(normalizedToValue(mNormalizedMaxValue) + "");
        setThumbValue(maxFormatValue, maxSeekValue, true, canvas);
    }

    private void drawSeekBar(Canvas canvas, float minSeekValue, float maxSeekValue) {
        mPaint.setStyle(Style.FILL);

        mPaint.setColor(outerSeekBarColor);
        float outLeft = mPadding + mThumbHalfWidth;
        int outTop = getHeight() / 2 - mOuterRadius;
        float outRight = getWidth() - mPadding - mThumbHalfWidth;
        int outBottom = getHeight() / 2 + mOuterRadius;
        //mRect.set(outLeft - mSeekBarGap, outTop, outRight + mSeekBarGap, outBottom);
        //canvas.drawRoundRect(mRect, mOuterRadius, mOuterRadius, mPaint);

        mPaint.setColor(innerSeekBarColor);
        mRect.set(outLeft, outTop + mSeekBarGap, outRight, outBottom - mSeekBarGap);
        canvas.drawRoundRect(mRect, mInnerRadius, mInnerRadius, mPaint);

        mPaint.setColor(innerActiveSeekBarColor);
        mRect.set(minSeekValue, outTop + mSeekBarGap, maxSeekValue, outBottom - mSeekBarGap);
        if (minSeekValue > outLeft + mInnerRadius && maxSeekValue < outRight - mInnerRadius) {
            canvas.drawRect(mRect, mPaint);
        } else {
            canvas.drawRoundRect(mRect, mInnerRadius, mInnerRadius, mPaint);
        }

        float rate = Float.valueOf(this.absoluteMaxValue.toString()) / (outRight - outLeft);

        //Log.d("lh123", "minSeekValue:" + minSeekValue +",mIndicatorPosition:" + mIndicatorPosition + ",mBeginValue:" + mBeginValue);
        //画进度
        float indicatorLeft = minSeekValue + (float) (mIndicatorPosition - mBeginValue) / rate;
        float indicatorRight = indicatorLeft + ScreenUtil.dip2px(getContext(), 2);
        mPaint.setColor(Color.WHITE);
        mRectIndicator.set(indicatorLeft, outTop + mSeekBarGap, indicatorRight, outBottom - mSeekBarGap);
        canvas.drawRect(mRectIndicator, mPaint);
    }

    private void setThumbValue(String text, float seekValue, boolean isMaxValue, Canvas canvas) {
        float textWidth = mPaint.measureText(text);
        if (isMaxValue) {
            canvas.drawText(text, seekValue - textWidth / 2 + ScreenUtil.dip2px(getContext(), 5),
                    getHeight() / 2 - mThumbHalfHeight - ScreenUtil.sp2px(getContext(), mTextGap), mPaint);
        } else {
            canvas.drawText(text, seekValue - textWidth / 2 - ScreenUtil.dip2px(getContext(), 5),
                    getHeight() / 2 - mThumbHalfHeight - ScreenUtil.sp2px(getContext(), mTextGap), mPaint);
        }
    }

    /**
     * 绘制滑块
     * @param seekValue 滑动百分比
     */
    private void drawThumb(float seekValue, Canvas canvas, Thumb thumb) {
        if (pressedThumb == thumb) {
            canvas.drawBitmap(thumbImagePress, seekValue - mThumbHalfWidth, getHeight() / 2 - mThumbHalfHeight + mThumbHalfHeight*2, mPaint);
        } else {
            canvas.drawBitmap(thumbImage, seekValue - mThumbHalfWidth, getHeight() / 2 - mThumbHalfHeight + + mThumbHalfHeight*2, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerIndex;
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = event.getPointerId(event.getPointerCount() - 1);
                pointerIndex = event.findPointerIndex(mActivePointerId);
                mDownMotionX = event.getX(pointerIndex);
                pressedThumb = evalPressedThumb(mDownMotionX);

                if (pressedThumb == null) {
                    return super.onTouchEvent(event);
                }

                setPressed(true);
                onStartTrackingTouch();
                trackTouchEvent(event);
                attemptClaimDrag();

                if (listener != null) {
                    listener.onRangeSeekBarValuesBeginChange(this, getSelectedMinValue(), getSelectedMaxValue());
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (pressedThumb != null) {
                    if (mIsDragging) {
                        trackTouchEvent(event);
                    } else {
                        pointerIndex = event.findPointerIndex(mActivePointerId);
                        final float x = event.getX(pointerIndex);

                        if (Math.abs(x - mDownMotionX) > mScaledTouchSlop) {
                            setPressed(true);
                            invalidate();
                            onStartTrackingTouch();
                            trackTouchEvent(event);
                            attemptClaimDrag();
                        }
                    }

                    if (listener != null) {
                        listener.onRangeSeekBarValuesChanging(this, getSelectedMinValue(), getSelectedMaxValue());
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsDragging) {
                    trackTouchEvent(event);
                    onStopTrackingTouch();
                    setPressed(false);
                } else {
                    onStartTrackingTouch();
                    trackTouchEvent(event);
                    onStopTrackingTouch();
                }

                pressedThumb = null;
                invalidate();
                if (listener != null) {
                    listener.onRangeSeekBarValuesEndChange(this, getSelectedMinValue(), getSelectedMaxValue());
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int index = event.getPointerCount() - 1;
                mDownMotionX = event.getX(index);
                mActivePointerId = event.getPointerId(index);
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                onSecondaryPointerUp(event);
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mIsDragging) {
                    onStopTrackingTouch();
                    setPressed(false);
                }
                invalidate();
                break;
        }
        return true;
    }

    private boolean checkCanMove() {
        T selectMinValue = getSelectedMinValue();
        T selectMaxValue = getSelectedMaxValue();
        int interval = Integer.valueOf(selectMaxValue.toString()) - Integer.valueOf(selectMinValue.toString());
        return interval > mThumbInterval;
    }

    /**
     * @param touchX 按压的位置
     * @return 拖动的滑块
     */
    private Thumb evalPressedThumb(float touchX) {
        Thumb result = null;
        boolean minThumbPressed = isInThumbRange(touchX, mNormalizedMinValue);
        boolean maxThumbPressed = isInThumbRange(touchX, mNormalizedMaxValue);
        if (minThumbPressed && maxThumbPressed) {
            result = (touchX / getWidth() > 0.5f) ? Thumb.MIN : Thumb.MAX;
        } else if (minThumbPressed) {
            result = Thumb.MIN;
        } else if (maxThumbPressed) {
            result = Thumb.MAX;
        }
        return result;
    }

    private boolean isInThumbRange(float touchX, double normalizedThumbValue) {
        return Math.abs(touchX - getSeekPosition(normalizedThumbValue)) <= mThumbHalfWidth;
    }

    void onStartTrackingTouch() {
        mIsDragging = true;
    }

    void onStopTrackingTouch() {
        mIsDragging = false;
    }

    private void trackTouchEvent(MotionEvent event) {
        int pointerIndex = event.findPointerIndex(mActivePointerId);
        Log.i("mtag", "trackTouchEvent:     " + pointerIndex);
        if (pointerIndex > -1) {
            final float x = event.getX(pointerIndex);

            if (Thumb.MIN.equals(pressedThumb)) {
                setNormalizedMinValue(screenToNormalized(x));
            } else if (Thumb.MAX.equals(pressedThumb)) {
                setNormalizedMaxValue(screenToNormalized(x));
            }
        }
    }

    private void setNormalizedMinValue(double value) {
        mNormalizedMinValue = Math.max(0d, Math.min(1d, Math.min(value, mNormalizedMaxValue)));
        double intervalPercent = (double) mThumbInterval / absoluteMaxValue.doubleValue();
        if (mNormalizedMaxValue - mNormalizedMinValue < intervalPercent) {
            mNormalizedMinValue = mNormalizedMaxValue - intervalPercent;
        }
        invalidate();
    }

    private void setNormalizedMaxValue(double value) {
        mNormalizedMaxValue = Math.max(0d, Math.min(1d, Math.max(value, mNormalizedMinValue)));
        double intervalPercent = (double) mThumbInterval / absoluteMaxValue.doubleValue();
        if (mNormalizedMaxValue - mNormalizedMinValue < intervalPercent) {
            mNormalizedMaxValue = mNormalizedMinValue + intervalPercent;
        }
        invalidate();
    }

    /**
     * @param xPosition x坐标
     * @return seekbar滑动百分比
     */
    private double screenToNormalized(float xPosition) {
        int width = getWidth();
        if (width <= 2 * mPadding) {
            return 0d;
        } else {
            double result = (xPosition - mPadding - mThumbHalfWidth) / (width - 2 * mPadding - mThumbWidth);
            return Math.min(1d, Math.max(0d, result));
        }
    }

    private void attemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    /**
     * @param seekPercent seekbar移动的百分比
     * @return 横坐标
     */
    private float getSeekPosition(double seekPercent) {
        return (float) (seekPercent * (getWidth() - 2 * mPadding - mThumbWidth) + mPadding + mThumbHalfWidth);
    }

    /**
     * @param normalized seekbar移动的百分比
     * @return 显示值
     */
    @SuppressWarnings("unchecked")
    private T normalizedToValue(double normalized) {
        double v = minValuePrim + normalized * (maxValuePrim - minValuePrim);
        double precision = Math.pow(10, mDecimalPrecision);
        return (T) mNumberType.toNumber(Math.round(v * precision) / precision);
    }

    private String formatValue(String strNumber) {
        Integer value = Integer.valueOf(strNumber);
        int minute = value / 60;
        int second = value % 60;
        //String.format("%02d", month)；
        String strMinute = String.format("%02d", minute);
        String strSecond = String.format("%02d", second);
        return strMinute + ":" + strSecond;
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        final int pointerIndex = (ev.getAction() & ACTION_POINTER_INDEX_MASK) >> ACTION_POINTER_INDEX_SHIFT;
        final int pointerId = ev.getPointerId(pointerIndex);
        if (pointerId == mActivePointerId) {
            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            mDownMotionX = ev.getX(newPointerIndex);
            mActivePointerId = ev.getPointerId(newPointerIndex);
        }
    }

    public void setOnRangeSeekBarChangeListener(OnRangeSeekBarChangeListener<T> listener) {
        this.listener = listener;
    }

    public T getSelectedMaxValue() {
        return normalizedToValue(mNormalizedMaxValue);
    }

    private T getSelectedMinValue() {
        return normalizedToValue(mNormalizedMinValue);
    }


    public T getAbsoluteMinValue() {
        return absoluteMinValue;
    }

    public T getAbsoluteMaxValue() {
        return absoluteMaxValue;
    }

    public void setAbsoluteMinValue(T absoluteMinValue) {
        this.absoluteMinValue = absoluteMinValue;
        setValuePrimAndNumberType();
    }

    public void setAbsoluteMaxValue(T absoluteMaxValue) {
        this.absoluteMaxValue = absoluteMaxValue;
        setValuePrimAndNumberType();
    }

    public int getThumbInterval() {
        return mThumbInterval;
    }

    public void setThumbInterval(int thumbInterval) {
        mThumbInterval = thumbInterval;
    }

    public int getBeginValue() {
        return mBeginValue;
    }

    public void setBeginValue(int beginValue) {
        mBeginValue = beginValue;

        double beginPercent = (double) beginValue / absoluteMaxValue.doubleValue();
        mNormalizedMinValue = Math.max(0d, Math.min(1d, Math.min(beginPercent, mNormalizedMaxValue)));
        invalidate();
    }

    public int getEndValue() {
        return mEndValue;
    }

    public void setEndValue(int endValue) {
        mEndValue = endValue;

        double endPercent = (double) endValue / absoluteMaxValue.doubleValue();
        mNormalizedMaxValue = Math.max(0d, Math.min(1d, Math.max(endPercent, mNormalizedMinValue)));
        invalidate();
    }

    public int getIndicatorPosition() {
        return mIndicatorPosition;
    }

    public void setIndicatorPosition(int indicatorPosition) {
        mIndicatorPosition = indicatorPosition;
        invalidate();
    }

    public interface OnRangeSeekBarChangeListener<T> {

        void onRangeSeekBarValuesBeginChange(AudioRangeSeekBar<?> bar, T minValue, T maxValue);
        void onRangeSeekBarValuesChanging(AudioRangeSeekBar<?> bar, T minValue, T maxValue);
        void onRangeSeekBarValuesEndChange(AudioRangeSeekBar<?> bar, T minValue, T maxValue);
    }

    private enum Thumb {
        MIN, MAX
    }

    private enum NumberType{
        BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE;

        public static <E extends Number> NumberType fromNumber(E value) throws IllegalArgumentException {
            if (value instanceof Byte) {
                return BYTE;
            }
            if (value instanceof Short) {
                return SHORT;
            }
            if (value instanceof Integer) {
                return INTEGER;
            }
            if (value instanceof Long) {
                return LONG;
            }
            if (value instanceof Double) {
                return DOUBLE;
            }
            if (value instanceof Float) {
                return FLOAT;
            }
            throw new IllegalArgumentException("Number class '" + value.getClass().getName() + "' is not supported");
        }

        public Number toNumber(double value) {
            switch (this) {
                case BYTE:
                    return (byte) value;
                case SHORT:
                    return (short) value;
                case INTEGER:
                    return (int) value;
                case LONG:
                    return (long) value;
                case DOUBLE:
                    return value;
                case FLOAT:
                    return (float) value;
            }
            throw new InstantiationError("can't convert " + this + " to a Number object");
        }
    }
}
