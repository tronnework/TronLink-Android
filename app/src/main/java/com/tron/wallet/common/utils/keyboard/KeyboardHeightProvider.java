package com.tron.wallet.common.utils.keyboard;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import com.tronlinkpro.wallet.R;
public class KeyboardHeightProvider extends PopupWindow {
    private static final String TAG = "sample_KeyboardHeightProvider";
    private Activity activity;
    private int keyboardLandscapeHeight;
    private int keyboardPortraitHeight;
    private KeyboardHeightObserver observer;
    private View parentView;
    private View popupView;

    public void setKeyboardHeightObserver(KeyboardHeightObserver keyboardHeightObserver) {
        this.observer = keyboardHeightObserver;
    }

    public KeyboardHeightProvider(Activity activity) {
        super(activity);
        this.activity = activity;
        View inflate = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.popwindow, (ViewGroup) null, false);
        this.popupView = inflate;
        setContentView(inflate);
        setSoftInputMode(21);
        setInputMethodMode(1);
        this.parentView = activity.findViewById(16908290);
        setWidth(0);
        setHeight(-1);
        this.popupView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (popupView != null) {
                    handleOnGlobalLayout();
                }
            }
        });
    }

    public void start() {
        if (isShowing() || this.parentView.getWindowToken() == null) {
            return;
        }
        setBackgroundDrawable(new ColorDrawable(0));
        showAtLocation(this.parentView, 0, 0, 0);
    }

    public void close() {
        this.observer = null;
        dismiss();
    }

    private int getScreenOrientation() {
        return this.activity.getResources().getConfiguration().orientation;
    }

    public void handleOnGlobalLayout() {
        Point point = new Point();
        this.activity.getWindowManager().getDefaultDisplay().getSize(point);
        Rect rect = new Rect();
        this.popupView.getWindowVisibleDisplayFrame(rect);
        int screenOrientation = getScreenOrientation();
        int i = point.y - rect.bottom;
        if (i == 0) {
            notifyKeyboardHeightChanged(0, screenOrientation);
        } else if (screenOrientation == 1) {
            this.keyboardPortraitHeight = i;
            notifyKeyboardHeightChanged(i, screenOrientation);
        } else {
            this.keyboardLandscapeHeight = i;
            notifyKeyboardHeightChanged(i, screenOrientation);
        }
    }

    private void notifyKeyboardHeightChanged(int i, int i2) {
        KeyboardHeightObserver keyboardHeightObserver = this.observer;
        if (keyboardHeightObserver != null) {
            keyboardHeightObserver.onKeyboardHeightChanged(i, i2);
        }
    }
}
