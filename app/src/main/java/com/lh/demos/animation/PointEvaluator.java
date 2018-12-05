package com.lh.demos.animation;

import android.animation.TypeEvaluator;

/**
 * Created by leihui on 2018/11/27.
 * PointEvaluator
 */

public class PointEvaluator implements TypeEvaluator<Point> {

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        int startRadius = startValue.getRadius();
        int endRadius = endValue.getRadius();
        int curRadius = (int) (startRadius + (endRadius - startRadius) * fraction);
        return new Point(curRadius);
    }
}
