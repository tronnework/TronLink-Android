package com.tron.wallet.common.components;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.tron.tron_base.frame.utils.LogUtils;
public class AndroidBug5497Workaround {
    private FrameLayout.LayoutParams frameLayoutParams;
    private View mChildOfContent;
    private int usableHeightPrevious;

    public static void assistActivity(Activity activity) {
        new AndroidBug5497Workaround(activity);
    }

    private AndroidBug5497Workaround(Activity activity) {
        try {
            View childAt = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
            this.mChildOfContent = childAt;
            childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    possiblyResizeChildOfContent();
                }
            });
            this.frameLayoutParams = (FrameLayout.LayoutParams) this.mChildOfContent.getLayoutParams();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void possiblyResizeChildOfContent() {
        try {
            int computeUsableHeight = computeUsableHeight();
            if (computeUsableHeight != this.usableHeightPrevious) {
                int height = this.mChildOfContent.getRootView().getHeight();
                int i = height - computeUsableHeight;
                if (i > height / 4) {
                    this.frameLayoutParams.height = height - i;
                } else {
                    this.frameLayoutParams.height = height;
                }
                this.mChildOfContent.requestLayout();
                this.usableHeightPrevious = computeUsableHeight;
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private int computeUsableHeight() {
        try {
            Rect rect = new Rect();
            this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
            return rect.bottom - rect.top;
        } catch (Exception e) {
            LogUtils.e(e);
            return 0;
        }
    }
}
