package com.tron.wallet.business.transfer.multisignature;

import android.content.Context;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
public class CustPagerTransformer implements ViewPager2.PageTransformer {
    private int maxTranslateOffsetX;

    public CustPagerTransformer(Context context) {
        this.maxTranslateOffsetX = dp2px(context, 150.0f);
    }

    @Override
    public void transformPage(View view, float f) {
        float left = ((((view.getLeft() - view.getScrollX()) + (view.getMeasuredWidth() / 2)) - (view.getMeasuredWidth() / 2)) * 0.18f) / view.getMeasuredWidth();
        float abs = 1.0f - Math.abs(left);
        if (abs > 0.0f) {
            view.setScaleX(abs);
            view.setScaleY(abs);
            view.setTranslationX((-this.maxTranslateOffsetX) * left);
        }
    }

    private int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
