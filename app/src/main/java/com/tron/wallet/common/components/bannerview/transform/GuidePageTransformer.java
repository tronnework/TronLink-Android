package com.tron.wallet.common.components.bannerview.transform;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
public class GuidePageTransformer implements ViewPager2.PageTransformer {
    private static final float MIN_ALPHA = 0.0f;

    @Override
    public void transformPage(View view, float f) {
        float f2 = 0.0f;
        if (0.0f <= f && f <= 0.5d) {
            f2 = 1.0f - (f * 2.0f);
        } else if (-1.0f <= f && f < 0.5f) {
            f2 = (f * 2.0f) + 1.0f;
        }
        view.setAlpha(f2);
    }
}
