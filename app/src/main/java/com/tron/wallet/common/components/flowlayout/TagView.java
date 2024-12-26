package com.tron.wallet.common.components.flowlayout;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;
public class TagView extends FrameLayout implements Checkable {
    private static final int[] CHECK_STATE = {16842912};
    private boolean isChecked;

    @Override
    public boolean isChecked() {
        return this.isChecked;
    }

    public TagView(Context context) {
        super(context);
    }

    public View getTagView() {
        return getChildAt(0);
    }

    @Override
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CHECK_STATE);
        }
        return onCreateDrawableState;
    }

    @Override
    public void setChecked(boolean z) {
        if (this.isChecked != z) {
            this.isChecked = z;
            refreshDrawableState();
        }
    }

    @Override
    public void toggle() {
        setChecked(!this.isChecked);
    }
}
