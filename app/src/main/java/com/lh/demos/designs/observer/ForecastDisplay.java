package com.lh.demos.designs.observer;

import android.util.Log;

/**
 * Created by leihui on 2019/4/30.
 * ForecastDisplay
 * 天气预报
 */

public class ForecastDisplay implements Observer, DisplayElement {

    private Subject mWeatherData;
    private float mCurrentPressure = 29.92f;
    private float mLastPressure;

    public ForecastDisplay(Subject weatherData) {
        mWeatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        mLastPressure = mCurrentPressure;
        mCurrentPressure = pressure;

        display();
    }

    @Override
    public void display() {
        if (mCurrentPressure > mLastPressure) {
            Log.d("lh123", "Forecast: Improving weather on the way!");
        } else if (mCurrentPressure == mLastPressure) {
            Log.d("lh123", "Forecast: More of the same");
        } else if (mCurrentPressure < mLastPressure) {
            Log.d("lh123", "Forecast: Watch out for cooler, rainy weather");
        }
    }
}
