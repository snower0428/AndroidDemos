package com.lh.demos.widgets.audiocut;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.lh.core.utils.ScreenUtil;
import com.lh.demos.R;


/**
 * Created by leihui on 2018/12/29.
 * AudioTrackScrollView
 */

public class AudioTrackScrollView extends HorizontalScrollView {

    private Handler mScrollHandler;
    private OnScrollTrackListener mOnScrollTrackListener;
    private OnProgressRunListener mProgressRunListener;
    private OnScrollListener mOnScrollListener;

    private int mBackgroundColor = Color.LTGRAY;
    private int mForegroundColor = Color.BLUE;
    private int mSpaceSize;
    private int mTrackItemWidth;
    private int mMaskWidth;
    private int mDelayTime = 20;//ms
    private int mTrackFragmentCount = 1;
    private boolean isAutoRun = true;//是否自动跑进度
    private boolean isLoopRun = false;//是否循环跑进度
    private int mCutDuration = 10 * 1000;//裁剪区间，也就是控件左边，跑到右边的时间
    private float mSpeed = 10;
    private boolean mIsUseDefaultWidth = false; //是否使用track原始宽度

    /**
     * 滚动状态:
     * IDLE=滚动停止
     * TOUCH_SCROLL=手指拖动滚动
     * FLING=滚动
     */
    enum ScrollStatus {
        IDLE, TOUCH_SCROLL, FLING
    }

    /**
     * 记录当前滚动的距离
     */
    private int currentX = -9999999;

    /**
     * 当前滚动状态
     */
    private ScrollStatus scrollStatus = ScrollStatus.IDLE;

    private AudioTrackView track;
    private boolean disableTouch;
    private AudioTrackMoveController moveController;

    private int audioDuration;
    private int emptyWidth;
    private int startTime;

    private int downX;

    public AudioTrackScrollView(Context context) {
        super(context);
        initView(context);
    }

    public AudioTrackScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AudioTrackScrollView);

        mBackgroundColor = typedArray.getColor(R.styleable.AudioTrackScrollView_background_color, mBackgroundColor);
        mForegroundColor = typedArray.getColor(R.styleable.AudioTrackScrollView_foreground_color, mForegroundColor);

        //px
        mSpaceSize = Math.round(typedArray.getDimension(R.styleable.AudioTrackScrollView_space_size, mSpaceSize));
        mTrackItemWidth = Math.round(typedArray.getDimension(R.styleable.AudioTrackScrollView_track_item_width, mTrackItemWidth));
        isAutoRun = typedArray.getBoolean(R.styleable.AudioTrackScrollView_auto_run, isAutoRun);
        mTrackFragmentCount = typedArray.getInteger(R.styleable.AudioTrackScrollView_track_fragment_count, mTrackFragmentCount);
        mCutDuration = typedArray.getInteger(R.styleable.AudioTrackScrollView_cut_duration, mCutDuration);
        isLoopRun = typedArray.getBoolean(R.styleable.AudioTrackScrollView_loop_run, isLoopRun);

        typedArray.recycle();
        initView(context);
    }

    public AudioTrackScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        mSpaceSize = ScreenUtil.dip2px(context, 2.5f);
        mTrackItemWidth = ScreenUtil.dip2px(context, 5);
        mMaskWidth = (mTrackItemWidth + mSpaceSize) * 4;

        track = new AudioTrackView(context);
        track.setBackgroundColorInt(mBackgroundColor);
        track.setForegroundColor(mForegroundColor);
        track.setSpaceSize(mSpaceSize);
        track.setTrackFragmentCount(mTrackFragmentCount);
        track.setTrackItemWidth(mTrackItemWidth);
        track.setMaskWidth(mMaskWidth);

//        emptyWidth = (mTrackItemWidth + mSpaceSize) * 0;
//        track.setEmptyWidth(emptyWidth);

        HorizontalScrollView.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(track, lp);
        setSmoothScrollingEnabled(false);

        moveController = new AudioTrackMoveController(mDelayTime, new AudioTrackMoveController.OnProgressChangeListener() {
            @Override
            public void onProgressChange(int progress) {
                Message msg = progressHandler.obtainMessage(1);
                msg.arg1 = progress;
                progressHandler.sendMessage(msg);
            }

            @Override
            public void onProgressStart() {
                if (mProgressRunListener != null) {
                    mProgressRunListener.onTrackStart(getStartTime());
                }
            }

            @Override
            public void onProgressEnd() {
                if (mProgressRunListener != null) {
                    mProgressRunListener.onTrackEnd();
                }
            }
        });
        //moveController.setCurrentProgressPosition(mMaskWidth);
        //moveController.setScrollTrackStartX(mMaskWidth);

//        post(new Runnable() {
//            @Override
//            public void run() {
//                //可视的时候开始走进度
//                moveController.setScrollTrackViewWidth(getWidth());
//                mSpeed = ((getWidth() * 1f) / (mCutDuration * 1f));//根据时间和控件的宽度计算速度
//                float delayTime = 1f / mSpeed;//根据速度来算走每个像素点需要多久时间
//                moveController.setDelayTime(Math.round(delayTime));//四舍五入
//                moveController.setLoopRun(isLoopRun);
//                if (isAutoRun) {
//                    startMove();
//                }
//            }
//        });

        mScrollHandler = new Handler();

        //滑动状态监听
        mOnScrollTrackListener = new OnScrollTrackListener() {
            @Override
            public void onScrollChanged(ScrollStatus scrollStatus) {
                switch (scrollStatus) {
                    case IDLE:
                        if (moveController != null) {
                            moveController.setScrollTrackStartX(getScrollX() + mMaskWidth);
                            moveController.continueRun();
                        }
                        if (mProgressRunListener != null) {
                            mProgressRunListener.onTrackStartTimeChange(getStartTime());
                        }
                        if (mOnScrollListener != null) {
                            mOnScrollListener.onScrollEnd(AudioTrackScrollView.this);
                        }
                        break;
                    case FLING:
                        if (mOnScrollListener != null) {
                            mOnScrollListener.onFling(AudioTrackScrollView.this);
                        }
                        break;
                    case TOUCH_SCROLL:
                        if (moveController != null) {
                            moveController.pause();
                        }
                        if (mOnScrollListener != null) {
                            mOnScrollListener.onScroll(AudioTrackScrollView.this);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public int getCutDuration() {
        return mCutDuration;
    }

    public void setCutDuration(int cutDuration) {
        mCutDuration = cutDuration;
    }

    /**
     * 设置循环播放
     *
     * @param isLoop
     */
    public void setLoopRun(boolean isLoop) {
        isLoopRun = isLoop;
    }

    public void setTrackTemplateData(float[] data) {
        if (track != null && data != null) {
            track.setTrackTemplateData(data);
        }
    }

    public void setTrackFragmentCount(int count) {
        if (track != null) {
            track.setTrackFragmentCount(count);
        }
    }

    public void setSpaceSize(int px) {
        if (track != null) {
            track.setSpaceSize(px);
        }
    }

    public void setTrackItemWidth(int px) {
        if (track != null) {
            track.setTrackItemWidth(px);
        }
    }

    /**
     * 滚动监听runnable 方便获取滑动状态
     */
    private Runnable scrollRunnable = new Runnable() {
        @Override
        public void run() {
            if (getScrollX() == currentX) {
                //滚动停止,取消监听线程
                scrollStatus = ScrollStatus.IDLE;
                if (mOnScrollTrackListener != null) {
                    mOnScrollTrackListener.onScrollChanged(scrollStatus);
                }
                mScrollHandler.removeCallbacks(this);
                return;
            } else {
                //手指离开屏幕,但是view还在滚动
                scrollStatus = ScrollStatus.FLING;
                if (mOnScrollTrackListener != null) {
                    mOnScrollTrackListener.onScrollChanged(scrollStatus);
                }
            }
            currentX = getScrollX();
            track.setOffsetX(getScrollX());
            //滚动监听间隔:milliseconds
            mScrollHandler.postDelayed(this, 20);
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                //向右滚动
                if (ev.getX() < downX) {
                    int startTime = getStartTime();
                    if (startTime + mCutDuration >= audioDuration) {
                        return true;
                    }
                }
                this.scrollStatus = ScrollStatus.TOUCH_SCROLL;
                mOnScrollTrackListener.onScrollChanged(scrollStatus);
                mScrollHandler.removeCallbacks(scrollRunnable);

//                int touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
//                int absX = (int) Math.abs(ev.getX() - downX);
//                if (absX > touchSlop) {
//                    Log.d("lh123", "currentX:" + getScrollX());
//                    track.setOffsetX(getScrollX());
//                }
                track.setOffsetX(getScrollX());
                Log.d("lh123", "currentX:" + getScrollX());
                break;
            case MotionEvent.ACTION_UP:
                mScrollHandler.post(scrollRunnable);
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 进度控制
     */
    Handler progressHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                track.setProgress(msg.arg1);
            }
        }
    };

    /*@Override
    public void fling(int velocity) {
        super.fling(velocity / 1000);
    }*/

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (disableTouch) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 开始
     */
    public void startMove() {
        //设置进度参数
        int trackWidth = getWidth() - emptyWidth - mMaskWidth;
//        if (mIsUseDefaultWidth) {
//            trackWidth = getWidth() - mMaskWidth * 2;
//        }
        if (mCutDuration / 1000 < 22) {
            trackWidth = (mTrackItemWidth + mSpaceSize) * (mCutDuration / 1000);
        } else {
            trackWidth = (mTrackItemWidth + mSpaceSize) * 22;
        }
        moveController.setScrollTrackViewWidth(trackWidth);
        mSpeed = (trackWidth * 1f) / (mCutDuration * 1f);//根据时间和控件的宽度计算速度
        float delayTime = 1f / mSpeed;//根据速度来算走每个像素点需要多久时间
        moveController.setDelayTime(Math.round(delayTime));//四舍五入
        moveController.setLoopRun(isLoopRun);

        disableTouch = true;
        if (moveController != null) {
            moveController.start();
        }
    }

    /**
     * 重新开始播放
     */
    public void restartMove() {
        disableTouch = true;

        if (moveController != null) {
            scrollTo(0, 0);
            smoothScrollTo(0, 0);
            moveController.restart();
            if (mProgressRunListener != null) {
                mProgressRunListener.onTrackStartTimeChange(0);
            }
        }
    }

    public void stopMove() {
        disableTouch = false;
        if (moveController != null) {
            moveController.stop();
        }
    }

    public void pauseMove() {
        disableTouch = false;
        if (moveController != null) {
            moveController.pause();
        }
    }

    public void setOnProgressRunListener(OnProgressRunListener listener) {
        mProgressRunListener = listener;
    }

    public void setOnScrollListener(OnScrollListener listener) {
        mOnScrollListener = listener;
    }

    /**
     * 设置音频总时间
     */
    public void setDuration(int ms) {
        audioDuration = ms;
        mIsUseDefaultWidth = false;
        int count = ms / 1000;
        if (count <= 30) {
            count = 30;
            mIsUseDefaultWidth = true;
        }
        track.setTrackTemplateCount(count);
    }

    /**
     * 获取歌曲开始时间 (毫秒)
     */
    public int getStartTime() {
        int retValue = 0;
        if (getScrollX() % (mTrackItemWidth + mSpaceSize) == 0) {
            retValue = getScrollX() / (mTrackItemWidth + mSpaceSize);
        } else {
            retValue = getScrollX() / (mTrackItemWidth + mSpaceSize) + 1;
        }
        return retValue * 1000;

        //float rate = Math.abs(getScrollX()) / ((track.getWidth() - emptyWidth) * 1f);
        //return (int) (audioDuration * rate);
    }

    public void setStartTime(final int startTime) {
        this.startTime = startTime;

        postDelayed(new Runnable() {
            @Override
            public void run() {
                float rate = (float) startTime / (float) audioDuration;
                int scrollX = (int) (track.getWidth() * rate);
                //smoothScrollTo(scrollX, 0);
                setScrollX(scrollX);
                track.setOffsetX(getScrollX());

                if (moveController != null) {
                    moveController.setCurrentProgressPosition(scrollX);
                    moveController.setScrollTrackStartX(scrollX + mMaskWidth);
                }
            }
        }, 100);
    }

    public void setProgressContinue(boolean isContinue) {
        if (moveController != null) {
            moveController.setProgressContinue(isContinue);
        }
    }

    /**
     * 设置进度
     *
     * @param percent 浮点数，当前位置在整个view 中的比例
     */
    public void setRealProgress(float percent) {
        if (moveController != null) {
            float position = getWidth() * 1f * percent;
            moveController.setCurrentProgressPosition(Math.round(position));
        }
    }

    public void setEmptyWidth(int second) {
        if (second > 0) {
            emptyWidth = (mTrackItemWidth + mSpaceSize) * second;
            track.setEmptyWidth(emptyWidth);
        }
    }

    public int getTrackItemWidth() {
        return mTrackItemWidth;
    }

    public int getSpaceSize() {
        return mSpaceSize;
    }

    public int getTrackWidth() {
        return track.getWidth();
    }

    public void setMaskWidth(int maskWidth) {
        track.setMaskWidth(maskWidth);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopMove();
    }

    /**
     * @param velocityX 阻尼
     * 将惯性滚动速度缩小1000倍，近似drag操作。
     */
    @Override
    public void fling(int velocityX) {
        super.fling(velocityX / 1000);
    }

    private interface OnScrollTrackListener {
        void onScrollChanged(ScrollStatus scrollStatus);
    }

    /**
     * 轨道开始播放到轨道结束监听
     */
    public interface OnProgressRunListener {
        void onTrackStart(int ms);
        void onTrackStartTimeChange(int ms);
        void onTrackEnd();
    }

    public interface OnScrollListener {
        void onScroll(AudioTrackScrollView scrollTrackView);
        void onScrollEnd(AudioTrackScrollView scrollTrackView);
        void onFling(AudioTrackScrollView scrollTrackView);
    }
}
