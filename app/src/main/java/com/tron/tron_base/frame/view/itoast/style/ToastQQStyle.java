package com.tron.tron_base.frame.view.itoast.style;

import android.content.Context;
public class ToastQQStyle extends BaseToastStyle {
    @Override
    public int getBackgroundColor() {
        return -13421773;
    }

    @Override
    public int getTextColor() {
        return -1842205;
    }

    @Override
    public int getZ() {
        return 0;
    }

    public ToastQQStyle(Context context) {
        super(context);
    }

    @Override
    public int getCornerRadius() {
        return dp2px(4.0f);
    }

    @Override
    public float getTextSize() {
        return sp2px(12.0f);
    }

    @Override
    public int getPaddingStart() {
        return dp2px(16.0f);
    }

    @Override
    public int getPaddingTop() {
        return dp2px(14.0f);
    }
}
