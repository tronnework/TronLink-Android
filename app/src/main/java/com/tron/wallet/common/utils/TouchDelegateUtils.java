package com.tron.wallet.common.utils;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import com.tron.tron_base.frame.utils.LogUtils;
public class TouchDelegateUtils {
    private static final String TAG = "TouchDelegateUtils";

    public static void expandViewTouchDelegate(View view, int i) {
        expandViewTouchDelegate(view, i, i, i, i);
    }

    public static void expandViewTouchDelegate(final View view, final int i, final int i2, final int i3, final int i4) {
        ((View) view.getParent()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Rect rect = new Rect();
                view.setEnabled(true);
                view.getHitRect(rect);
                LogUtils.d(TouchDelegateUtils.TAG, "bounds.left:" + rect.left + ",bounds.top:" + rect.top + ",bounds.right:" + rect.right + ",bounds.bottom:" + rect.bottom);
                rect.top = rect.top - UIUtils.dip2px((float) i2);
                rect.bottom = rect.bottom + UIUtils.dip2px((float) i4);
                rect.left = rect.left - UIUtils.dip2px((float) i);
                rect.right = rect.right + UIUtils.dip2px((float) i3);
                TouchDelegate touchDelegate = new TouchDelegate(rect, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        }, 1000L);
    }
}
