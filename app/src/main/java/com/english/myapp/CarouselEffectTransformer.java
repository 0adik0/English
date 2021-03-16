package com.english.myapp;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewParent;

import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import kotlin.TypeCastException;

public class CarouselEffectTransformer implements ViewPager.PageTransformer {
    private final int maxTranslateOffsetX;
    private ViewPager viewPager;

    public CarouselEffectTransformer(Context context) {
        this.maxTranslateOffsetX = dp2px(context);
    }

    public void transformPage(View view, float f) {
        if (this.viewPager == null) {
            ViewParent parent = view.getParent();
            if (parent != null) {
                this.viewPager = (ViewPager) parent;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type androidx.viewpager.widget.ViewPager");
            }
        }
        int left = view.getLeft();
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 == null) {
           throw new NullPointerException();
        }
        int scrollX = (left - viewPager2.getScrollX()) + (view.getMeasuredWidth() / 2);
        ViewPager viewPager3 = this.viewPager;
        if (viewPager3 == null) {
            throw new NullPointerException();
        }
        float measuredWidth = ((float) (scrollX - (viewPager3.getMeasuredWidth() / 2))) * 0.38f;
        ViewPager viewPager4 = this.viewPager;
        if (viewPager4 == null) {
            throw new NullPointerException();
        }
        float measuredWidth2 = measuredWidth / ((float) viewPager4.getMeasuredWidth());
        float abs = ((float) 1) - Math.abs(measuredWidth2);
        if (abs > ((float) 0)) {
            view.setScaleX(abs);
            view.setScaleY(abs);
            view.setTranslationX(((float) (-this.maxTranslateOffsetX)) * measuredWidth2);
        }
        ViewCompat.setElevation(view, abs);
    }

    private int dp2px(Context context) {
        Resources resources = context.getResources();
        return (int) (((float) 180.0 * resources.getDisplayMetrics().density) + 0.5f);
    }
}
