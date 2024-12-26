package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class FgSignFailureBinding implements ViewBinding {
    public final NestedScrollView noNet;
    public final NoNetView noNetView;
    public final PtrHTFrameLayout rcFrame;
    public final RecyclerView rcList;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgSignFailureBinding(RelativeLayout relativeLayout, NestedScrollView nestedScrollView, NoNetView noNetView, PtrHTFrameLayout ptrHTFrameLayout, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.noNet = nestedScrollView;
        this.noNetView = noNetView;
        this.rcFrame = ptrHTFrameLayout;
        this.rcList = recyclerView;
    }

    public static FgSignFailureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgSignFailureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_sign_failure, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgSignFailureBinding bind(View view) {
        int i = R.id.no_net;
        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.no_net);
        if (nestedScrollView != null) {
            i = R.id.no_net_view;
            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
            if (noNetView != null) {
                i = R.id.rc_frame;
                PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.rc_frame);
                if (ptrHTFrameLayout != null) {
                    i = R.id.rc_list;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
                    if (recyclerView != null) {
                        return new FgSignFailureBinding((RelativeLayout) view, nestedScrollView, noNetView, ptrHTFrameLayout, recyclerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
