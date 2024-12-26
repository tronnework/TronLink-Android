package com.tron.wallet.common.components.pickerview.pickerview.wheelview.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.tron.wallet.common.components.pickerview.pickerview.wheelview.view.WheelView;
public final class LoopViewGestureListener extends GestureDetector.SimpleOnGestureListener {
    private final WheelView wheelView;

    public LoopViewGestureListener(WheelView wheelView) {
        this.wheelView = wheelView;
    }

    @Override
    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.wheelView.scrollBy(f2);
        return true;
    }
}
