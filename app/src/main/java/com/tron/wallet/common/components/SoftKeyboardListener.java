package com.tron.wallet.common.components;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
public class SoftKeyboardListener implements ViewTreeObserver.OnGlobalLayoutListener {
    private final View root;
    protected int scrollHeight;
    private final View target;

    public SoftKeyboardListener(View view, View view2) {
        this.root = view;
        this.target = view2;
    }

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        this.root.getWindowVisibleDisplayFrame(rect);
        int max = Math.max(Math.abs(this.root.getBottom() - rect.bottom), 0);
        this.root.scrollTo(0, max);
        this.scrollHeight = max;
    }
}
