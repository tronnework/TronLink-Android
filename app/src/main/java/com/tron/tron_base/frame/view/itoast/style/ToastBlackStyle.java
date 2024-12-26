package com.tron.tron_base.frame.view.itoast.style;

import android.content.Context;
public class ToastBlackStyle extends BaseToastStyle {
    @Override
    public int getBackgroundColor() {
        return -2013265920;
    }

    @Override
    public int getTextColor() {
        return -285212673;
    }

    public ToastBlackStyle(Context context) {
        super(context);
    }

    @Override
    public int getCornerRadius() {
        return dp2px(8.0f);
    }

    @Override
    public float getTextSize() {
        return sp2px(14.0f);
    }

    @Override
    public int getPaddingStart() {
        return dp2px(24.0f);
    }

    @Override
    public int getPaddingTop() {
        return dp2px(16.0f);
    }
}
