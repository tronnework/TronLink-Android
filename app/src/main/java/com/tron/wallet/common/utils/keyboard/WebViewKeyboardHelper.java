package com.tron.wallet.common.utils.keyboard;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.tron.wallet.common.utils.UIUtils;
public class WebViewKeyboardHelper {
    private RelativeLayout.LayoutParams frameLayoutParams;
    private View mChildOfContent;
    private View targetView;
    private int usableHeightPrevious;

    private WebViewKeyboardHelper(Activity activity, View view) {
        this.targetView = view;
        View childAt = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
        this.mChildOfContent = childAt;
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        this.frameLayoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
    }

    public static void assistActivity(Activity activity, View view) {
        new WebViewKeyboardHelper(activity, view);
    }

    public void possiblyResizeChildOfContent() {
        int computeUsableHeight = computeUsableHeight();
        if (computeUsableHeight != this.usableHeightPrevious) {
            int height = this.mChildOfContent.getRootView().getHeight();
            int i = height - computeUsableHeight;
            if (i > height / 4) {
                this.frameLayoutParams.height = (height - i) - UIUtils.dip2px(86.0f);
            } else {
                this.frameLayoutParams.height = -1;
            }
            this.targetView.requestLayout();
            this.usableHeightPrevious = computeUsableHeight;
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }
}
