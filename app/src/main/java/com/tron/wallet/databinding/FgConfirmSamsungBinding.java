package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgConfirmSamsungBinding implements ViewBinding {
    public final FrameLayout flMain;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmSamsungBinding(RelativeLayout relativeLayout, FrameLayout frameLayout) {
        this.rootView = relativeLayout;
        this.flMain = frameLayout;
    }

    public static FgConfirmSamsungBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmSamsungBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_samsung, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmSamsungBinding bind(View view) {
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fl_main);
        if (frameLayout != null) {
            return new FgConfirmSamsungBinding((RelativeLayout) view, frameLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.fl_main)));
    }
}
