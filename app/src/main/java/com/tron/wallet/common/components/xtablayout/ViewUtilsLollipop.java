package com.tron.wallet.common.components.xtablayout;

import android.view.View;
import android.view.ViewOutlineProvider;
class ViewUtilsLollipop {
    ViewUtilsLollipop() {
    }

    public static void setBoundsViewOutlineProvider(View view) {
        view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }
}
