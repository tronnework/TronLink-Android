package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LayoutBasepickerviewBinding implements ViewBinding {
    public final FrameLayout contentContainer;
    public final FrameLayout outmostContainer;
    private final FrameLayout rootView;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private LayoutBasepickerviewBinding(FrameLayout frameLayout, FrameLayout frameLayout2, FrameLayout frameLayout3) {
        this.rootView = frameLayout;
        this.contentContainer = frameLayout2;
        this.outmostContainer = frameLayout3;
    }

    public static LayoutBasepickerviewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutBasepickerviewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_basepickerview, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutBasepickerviewBinding bind(View view) {
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.content_container);
        if (frameLayout != null) {
            FrameLayout frameLayout2 = (FrameLayout) view;
            return new LayoutBasepickerviewBinding(frameLayout2, frameLayout, frameLayout2);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.content_container)));
    }
}
