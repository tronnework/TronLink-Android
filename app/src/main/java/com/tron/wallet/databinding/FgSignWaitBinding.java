package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class FgSignWaitBinding implements ViewBinding {
    public final NestedScrollView noNet;
    public final NoNetView noNetView;
    public final RecyclerView rcList;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private FgSignWaitBinding(LinearLayout linearLayout, NestedScrollView nestedScrollView, NoNetView noNetView, RecyclerView recyclerView) {
        this.rootView = linearLayout;
        this.noNet = nestedScrollView;
        this.noNetView = noNetView;
        this.rcList = recyclerView;
    }

    public static FgSignWaitBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgSignWaitBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_sign_wait, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgSignWaitBinding bind(View view) {
        int i = R.id.no_net;
        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.no_net);
        if (nestedScrollView != null) {
            i = R.id.no_net_view;
            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
            if (noNetView != null) {
                i = R.id.rc_list;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
                if (recyclerView != null) {
                    return new FgSignWaitBinding((LinearLayout) view, nestedScrollView, noNetView, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
