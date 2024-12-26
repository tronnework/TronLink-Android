package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class AcWebBinding implements ViewBinding {
    public final NoNetView noNetView;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final FrameLayout webView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcWebBinding(RelativeLayout relativeLayout, NoNetView noNetView, RelativeLayout relativeLayout2, FrameLayout frameLayout) {
        this.rootView = relativeLayout;
        this.noNetView = noNetView;
        this.root = relativeLayout2;
        this.webView = frameLayout;
    }

    public static AcWebBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcWebBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_web, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcWebBinding bind(View view) {
        int i = R.id.no_net_view;
        NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
        if (noNetView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.web_view);
            if (frameLayout != null) {
                return new AcWebBinding(relativeLayout, noNetView, relativeLayout, frameLayout);
            }
            i = R.id.web_view;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
