package com.lh.demos.designs.observer;

/**
 * Created by leihui on 2019/4/30.
 * Subject
 * 主题
 */

public interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
