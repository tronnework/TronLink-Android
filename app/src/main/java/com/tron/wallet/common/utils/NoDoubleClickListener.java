package com.tron.wallet.common.utils;

import android.view.View;
import java.util.Calendar;
public abstract class NoDoubleClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 2000;
    private long currentTime;
    private long lastClickTime = 0;

    protected abstract void onNoDoubleClick(View view);

    @Override
    public void onClick(View view) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        this.currentTime = timeInMillis;
        if (timeInMillis - this.lastClickTime > 2000) {
            this.lastClickTime = timeInMillis;
            onNoDoubleClick(view);
        }
    }
}
