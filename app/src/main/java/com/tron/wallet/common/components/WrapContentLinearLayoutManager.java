package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.utils.LogUtils;
public class WrapContentLinearLayoutManager extends LinearLayoutManager {
    private int height;
    private int width;

    public WrapContentLinearLayoutManager(Context context) {
        super(context);
    }

    public WrapContentLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public WrapContentLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public WrapContentLinearLayoutManager(Context context, int i, boolean z, int i2, int i3) {
        this(context, i, z);
        this.width = i2;
        this.height = i3;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            LogUtils.e("LinearLayoutManager", "Meet a IOOBE in RecyclerView");
            LogUtils.e(e);
        }
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.width == 0) {
            this.width = -2;
        }
        if (this.height == 0) {
            this.height = -2;
        }
        return new RecyclerView.LayoutParams(this.width, this.height);
    }
}
