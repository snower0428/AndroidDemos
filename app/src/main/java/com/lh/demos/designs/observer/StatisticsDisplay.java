package com.lh.demos.designs.observer;

import android.util.Log;

/**
 * Created by leihui on 2019/4/30.
 * StatisticsDisplay
 * 气象统计
 */

public class StatisticsDisplay implements Observer, DisplayElement {

    private Subject mWeatherData;
    private float mMinTemp = 200;
    private float mMaxTemp = 0.0f;
    private float mTempSum = 0.0f;
    private int mNumReadings;

    public StatisticsDisplay(Subject weatherData) {
        mWeatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        mTempSum += temp;
        mNumReadings++;
        if (temp > mMaxTemp) {
            mMaxTemp = temp;
        }
        if (temp < mMinTemp) {
            mMinTemp = temp;
        }
        display();
    }

    @Override
    public void display() {
        Log.d("lh123", "Avg/Max/Min temperature = " + (mTempSum / mNumReadings)
                + "/" + mMaxTemp + "/" + mMinTemp);
    }
}
