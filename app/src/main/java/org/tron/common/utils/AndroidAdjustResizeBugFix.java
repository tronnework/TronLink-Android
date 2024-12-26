package org.tron.common.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
public class AndroidAdjustResizeBugFix {
    private FrameLayout.LayoutParams frameLayoutParams;
    private Activity mActivity;
    private View mChildOfContent;
    private int statusBarHeight = getStatusBarHeight();
    private int usableHeightPrevious;

    private AndroidAdjustResizeBugFix(Activity activity) {
        this.mActivity = activity;
        this.mChildOfContent = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
        this.mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        this.frameLayoutParams = (FrameLayout.LayoutParams) this.mChildOfContent.getLayoutParams();
    }

    public static void assistActivity(Activity activity) {
        new AndroidAdjustResizeBugFix(activity);
    }

    public void possiblyResizeChildOfContent() {
        int computeUsableHeight = computeUsableHeight();
        if (computeUsableHeight != this.usableHeightPrevious) {
            int height = this.mChildOfContent.getRootView().getHeight();
            if (height - computeUsableHeight > height / 4) {
                this.frameLayoutParams.height = height - 1;
            } else {
                this.frameLayoutParams.height = height;
            }
            this.mChildOfContent.requestLayout();
            this.usableHeightPrevious = computeUsableHeight;
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return (rect.bottom - rect.top) + this.statusBarHeight;
    }

    private int getStatusBarHeight() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return this.mActivity.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            LogUtils.e(e);
            return 0;
        }
    }
}
