package com.lh.demos.designs.observer;

/**
 * Created by leihui on 2019/4/30.
 * Observer
 */

public interface Observer {

    void update(float temp, float humidity, float pressure);
}
