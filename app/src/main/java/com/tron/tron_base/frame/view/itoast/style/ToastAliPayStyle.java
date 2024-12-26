package com.tron.tron_base.frame.view.itoast.style;

import android.content.Context;
public class ToastAliPayStyle extends BaseToastStyle {
    @Override
    public int getBackgroundColor() {
        return -296265897;
    }

    @Override
    public int getGravity() {
        return 81;
    }

    @Override
    public int getTextColor() {
        return -1;
    }

    public ToastAliPayStyle(Context context) {
        super(context);
    }

    @Override
    public int getYOffset() {
        return dp2px(100.0f);
    }

    @Override
    public int getCornerRadius() {
        return dp2px(5.0f);
    }

    @Override
    public float getTextSize() {
        return sp2px(16.0f);
    }

    @Override
    public int getPaddingStart() {
        return dp2px(16.0f);
    }

    @Override
    public int getPaddingTop() {
        return dp2px(10.0f);
    }
}
