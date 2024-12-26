package com.tron.wallet.common.components;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;
public class SelectionWatcherEditText extends EditText {
    private static final String TAG = "SelectionWatcherET";
    private IOnSelectionChangedCallback callback;

    public interface IOnSelectionChangedCallback {
        void onSelectionChanged(int i, int i2);
    }

    public void setOnSelectionChangedCallback(IOnSelectionChangedCallback iOnSelectionChangedCallback) {
        this.callback = iOnSelectionChangedCallback;
    }

    public SelectionWatcherEditText(Context context) {
        super(context);
    }

    public SelectionWatcherEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SelectionWatcherEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        setCursorVisible(z);
    }

    @Override
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        IOnSelectionChangedCallback iOnSelectionChangedCallback = this.callback;
        if (iOnSelectionChangedCallback != null) {
            iOnSelectionChangedCallback.onSelectionChanged(i, i3);
        }
    }

    @Override
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.onOverScrolled(i, i2, z, z2);
    }
}
