package com.tron.wallet.business.tabdapp.browser.grid.swipeable;

import android.view.animation.Interpolator;
class RubberBandInterpolator implements Interpolator {
    private final float mLimit;

    @Override
    public float getInterpolation(float f) {
        float f2 = 1.0f - f;
        return this.mLimit * (1.0f - (f2 * f2));
    }

    public RubberBandInterpolator(float f) {
        this.mLimit = f;
    }
}
