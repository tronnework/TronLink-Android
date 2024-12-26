package com.tron.wallet.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.walletmanager.importwallet.AddWatchWalletActivity;
import com.tron.wallet.business.walletmanager.importwallet.ImportWalletActivity;
import java.util.Timer;
import java.util.TimerTask;
public class SoftHideKeyBoardUtil {
    public static final String TAG = "SoftHideKeyBoardUtil";
    private int contentHeight;
    private FrameLayout.LayoutParams frameLayoutParams;
    private boolean isfirst = true;
    private View mChildOfContent;
    private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;
    private int statusBarHeight;
    private int usableHeightPrevious;

    public interface OnSoftKeyBoardChangeListener {
        void keyBoardHide(int i);

        void keyBoardShow(int i);
    }

    private void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    public static void assistActivity(Activity activity, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        new SoftHideKeyBoardUtil(activity, onSoftKeyBoardChangeListener);
    }

    private SoftHideKeyBoardUtil(final Activity activity, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        View childAt = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
        this.mChildOfContent = childAt;
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (isfirst) {
                    SoftHideKeyBoardUtil softHideKeyBoardUtil = SoftHideKeyBoardUtil.this;
                    softHideKeyBoardUtil.contentHeight = softHideKeyBoardUtil.mChildOfContent.getHeight();
                    isfirst = false;
                }
                possiblyResizeChildOfContent(activity);
            }
        });
        this.frameLayoutParams = (FrameLayout.LayoutParams) this.mChildOfContent.getLayoutParams();
        this.statusBarHeight = UIUtils.getStatusBarHeight();
        this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    public void possiblyResizeChildOfContent(Activity activity) {
        OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;
        OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener2;
        int computeUsableHeight = computeUsableHeight(activity);
        if (computeUsableHeight != this.usableHeightPrevious) {
            int height = this.mChildOfContent.getRootView().getHeight();
            if (this.mChildOfContent.getHeight() != 0) {
                height = this.mChildOfContent.getHeight();
            }
            int i = this.usableHeightPrevious;
            if (i - computeUsableHeight > 200 && (onSoftKeyBoardChangeListener2 = this.onSoftKeyBoardChangeListener) != null) {
                onSoftKeyBoardChangeListener2.keyBoardShow(i - computeUsableHeight);
            }
            int i2 = this.usableHeightPrevious;
            if (computeUsableHeight - i2 > 200 && (onSoftKeyBoardChangeListener = this.onSoftKeyBoardChangeListener) != null) {
                onSoftKeyBoardChangeListener.keyBoardHide(computeUsableHeight - i2);
            }
            int i3 = height - computeUsableHeight;
            if (!(activity instanceof AddWatchWalletActivity) && !(activity instanceof ImportWalletActivity)) {
                if (i3 > height / 4) {
                    if (activity instanceof ConfirmTransactionNewActivity) {
                        this.frameLayoutParams.height = (height - (i3 / 2)) + this.statusBarHeight;
                    } else {
                        this.frameLayoutParams.height = (height - i3) + this.statusBarHeight;
                    }
                } else {
                    this.frameLayoutParams.height = this.contentHeight;
                }
                this.mChildOfContent.requestLayout();
            }
            this.usableHeightPrevious = computeUsableHeight;
        }
    }

    private int computeUsableHeight(Activity activity) {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom - rect.top;
    }

    public static void showSoftInput(final Context context, final EditText editText) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        editText.setFocusable(true);
                        editText.setFocusableInTouchMode(true);
                        editText.requestFocus();
                        ((InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(editText, 0);
                    }
                });
            }
        }, 200L);
    }

    public static void closeKeybord(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!inputMethodManager.isActive() || activity.getCurrentFocus().getWindowToken() == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
    }
}
