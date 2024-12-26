package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class DappListInnerFragmentBinding implements ViewBinding {
    public final NestedScrollView ivLoadingView;
    public final NestedScrollView netErrorView;
    public final NoNetView noNetView;
    private final FrameLayout rootView;
    public final RecyclerView rvContent;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private DappListInnerFragmentBinding(FrameLayout frameLayout, NestedScrollView nestedScrollView, NestedScrollView nestedScrollView2, NoNetView noNetView, RecyclerView recyclerView) {
        this.rootView = frameLayout;
        this.ivLoadingView = nestedScrollView;
        this.netErrorView = nestedScrollView2;
        this.noNetView = noNetView;
        this.rvContent = recyclerView;
    }

    public static DappListInnerFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DappListInnerFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dapp_list_inner_fragment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DappListInnerFragmentBinding bind(View view) {
        int i = R.id.iv_loading_view;
        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.iv_loading_view);
        if (nestedScrollView != null) {
            i = R.id.net_error_view;
            NestedScrollView nestedScrollView2 = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.net_error_view);
            if (nestedScrollView2 != null) {
                i = R.id.no_net_view;
                NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
                if (noNetView != null) {
                    i = R.id.rv_content;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_content);
                    if (recyclerView != null) {
                        return new DappListInnerFragmentBinding((FrameLayout) view, nestedScrollView, nestedScrollView2, noNetView, recyclerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
