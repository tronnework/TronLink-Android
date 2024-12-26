package com.tron.tron_base.frame.view.itoast.style;

import android.content.Context;
import android.util.TypedValue;
import com.tron.tron_base.frame.view.itoast.IToastStyle;
public abstract class BaseToastStyle implements IToastStyle {
    private Context mContext;

    @Override
    public int getGravity() {
        return 17;
    }

    @Override
    public int getMaxLines() {
        return 5;
    }

    @Override
    public int getXOffset() {
        return 0;
    }

    @Override
    public int getYOffset() {
        return 0;
    }

    @Override
    public int getZ() {
        return 30;
    }

    public BaseToastStyle(Context context) {
        this.mContext = context;
    }

    @Override
    public int getPaddingEnd() {
        return getPaddingStart();
    }

    @Override
    public int getPaddingBottom() {
        return getPaddingTop();
    }

    public int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, this.mContext.getResources().getDisplayMetrics());
    }

    public int sp2px(float f) {
        return (int) TypedValue.applyDimension(2, f, this.mContext.getResources().getDisplayMetrics());
    }
}
