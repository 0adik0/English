package com.english.myapp;

import android.content.Context;

public class SliderActivity1 extends CarouselEffectTransformer {

    final MainActivity activity;


    SliderActivity1(MainActivity activity, Context context) {
        super(context);
        this.activity = activity;
    }
}
