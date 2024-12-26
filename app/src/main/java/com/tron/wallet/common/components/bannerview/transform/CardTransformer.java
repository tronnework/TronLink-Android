package com.tron.wallet.common.components.bannerview.transform;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
public class CardTransformer implements ViewPager2.PageTransformer {
    private static final float MAX_SCALE = 1.2f;
    private static final float MIN_SCALE = 1.0f;

    @Override
    public void transformPage(View view, float f) {
        if (f <= 1.0f) {
            float abs = ((1.0f - Math.abs(f)) * 0.20000005f) + 1.0f;
            view.setScaleX(abs);
            if (f > 0.0f) {
                view.setTranslationX((-abs) * 2.0f);
            } else if (f < 0.0f) {
                view.setTranslationX(2.0f * abs);
            }
            view.setScaleY(abs);
            return;
        }
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }
}
