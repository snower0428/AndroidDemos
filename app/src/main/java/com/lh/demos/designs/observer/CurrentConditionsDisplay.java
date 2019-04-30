package com.lh.demos.designs.observer;

import android.util.Log;

/**
 * Created by leihui on 2019/4/30.
 * CurrentConditionsDisplay
 * 目前状况
 */

public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private Subject mWeatherData;
    private float mTemp;
    private float mHumidity;

    public CurrentConditionsDisplay(Subject weatherData) {
        mWeatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        mTemp = temp;
        mHumidity = humidity;
        display();
    }

    @Override
    public void display() {
        Log.d("lh123", "Current conditions: " + mTemp + "F degrees and " + mHumidity + "% humidity");
    }
}
