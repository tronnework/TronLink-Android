package com.tron.wallet.common.components.bannerview.manager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.tron.wallet.R;
public class AttributeController {
    private BannerOptions mBannerOptions;

    private void initIndicatorAttrs(TypedArray typedArray) {
    }

    public AttributeController(BannerOptions bannerOptions) {
        this.mBannerOptions = bannerOptions;
    }

    public void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BannerViewPager);
            initBannerAttrs(obtainStyledAttributes);
            initIndicatorAttrs(obtainStyledAttributes);
            obtainStyledAttributes.recycle();
        }
    }

    private void initBannerAttrs(TypedArray typedArray) {
        int integer = typedArray.getInteger(9, 3000);
        boolean z = typedArray.getBoolean(0, true);
        boolean z2 = typedArray.getBoolean(1, true);
        int i = typedArray.getInt(11, 0);
        int i2 = typedArray.getInt(14, 0);
        this.mBannerOptions.setInterval(integer);
        this.mBannerOptions.setAutoPlay(z);
        this.mBannerOptions.setCanLoop(z2);
        this.mBannerOptions.setPageMargin((int) typedArray.getDimension(10, 0.0f));
        this.mBannerOptions.setRoundRectRadius((int) typedArray.getDimension(13, 0.0f));
        this.mBannerOptions.setRightRevealWidth((int) typedArray.getDimension(12, -1000.0f));
        this.mBannerOptions.setPageStyle(i);
        this.mBannerOptions.setScrollDuration(i2);
    }
}
