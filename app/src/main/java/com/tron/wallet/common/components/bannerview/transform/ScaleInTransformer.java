package com.tron.wallet.common.components.bannerview.transform;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
public class ScaleInTransformer implements ViewPager2.PageTransformer {
    private static final float DEFAULT_CENTER = 0.5f;
    public static final float DEFAULT_MIN_SCALE = 0.85f;
    private float mMinScale;

    public ScaleInTransformer(float f) {
        this.mMinScale = f;
    }

    @Override
    public void transformPage(View view, float f) {
        int width = view.getWidth();
        view.setPivotY(view.getHeight() / 2.0f);
        float f2 = width;
        view.setPivotX(f2 / 2.0f);
        if (f < -1.0f) {
            view.setScaleX(this.mMinScale);
            view.setScaleY(this.mMinScale);
            view.setPivotX(f2);
        } else if (f > 1.0f) {
            view.setPivotX(0.0f);
            view.setScaleX(this.mMinScale);
            view.setScaleY(this.mMinScale);
        } else if (f < 0.0f) {
            float f3 = this.mMinScale;
            float f4 = ((f + 1.0f) * (1.0f - f3)) + f3;
            view.setScaleX(f4);
            view.setScaleY(f4);
            view.setPivotX(f2 * (((-f) * 0.5f) + 0.5f));
        } else {
            float f5 = 1.0f - f;
            float f6 = this.mMinScale;
            float f7 = ((1.0f - f6) * f5) + f6;
            view.setScaleX(f7);
            view.setScaleY(f7);
            view.setPivotX(f2 * f5 * 0.5f);
        }
    }
}
