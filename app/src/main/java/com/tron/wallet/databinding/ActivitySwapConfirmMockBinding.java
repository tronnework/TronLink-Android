package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class ActivitySwapConfirmMockBinding implements ViewBinding {
    private final FrameLayout rootView;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private ActivitySwapConfirmMockBinding(FrameLayout frameLayout) {
        this.rootView = frameLayout;
    }

    public static ActivitySwapConfirmMockBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivitySwapConfirmMockBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_swap_confirm_mock, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivitySwapConfirmMockBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ActivitySwapConfirmMockBinding((FrameLayout) view);
    }
}
