package com.tron.wallet.common.utils;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
public class OutsideTouchHelper {
    public static boolean isTouchOutSide(MotionEvent motionEvent, View view) {
        if (motionEvent.getActionMasked() != 0) {
            return false;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return x < ((float) rect.left) || x > ((float) rect.right) || y < ((float) rect.top) || y > ((float) rect.bottom);
    }
}
