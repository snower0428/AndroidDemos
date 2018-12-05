package com.lh.demos.animation;

import android.animation.TypeEvaluator;

/**
 * Created by leihui on 2018/11/27.
 * CharEvaluator
 */

public class CharEvaluator implements TypeEvaluator<Character>{

    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int) startValue;
        int endInt = (int) endValue;
        int curInt = (int) (startInt + (endInt - startInt) * fraction);
        char result = (char) curInt;
        return result;
    }
}
