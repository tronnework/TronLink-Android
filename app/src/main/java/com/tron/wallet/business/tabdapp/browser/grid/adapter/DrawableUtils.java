package com.tron.wallet.business.tabdapp.browser.grid.adapter;

import android.graphics.drawable.Drawable;
public class DrawableUtils {
    private static final int[] EMPTY_STATE = new int[0];

    public static void clearState(Drawable drawable) {
        if (drawable != null) {
            drawable.setState(EMPTY_STATE);
        }
    }
}
