package com.tron.wallet.business.tabdapp.browser.grid.base;

import android.view.View;
public class InternalHelperKK {
    public static void clearViewPropertyAnimatorUpdateListener(View view) {
        view.animate().setUpdateListener(null);
    }
}
