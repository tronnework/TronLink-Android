package com.tron.wallet.common.components.bannerview.manager;

import android.content.Context;
import android.util.AttributeSet;
public class BannerManager {
    private BannerOptions mBannerOptions = new BannerOptions();
    private AttributeController mAttributeController = new AttributeController(this.mBannerOptions);

    public BannerOptions getBannerOptions() {
        if (this.mBannerOptions == null) {
            this.mBannerOptions = new BannerOptions();
        }
        return this.mBannerOptions;
    }

    public void initAttrs(Context context, AttributeSet attributeSet) {
        this.mAttributeController.init(context, attributeSet);
    }
}
