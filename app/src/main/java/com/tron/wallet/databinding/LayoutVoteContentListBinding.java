package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LayoutVoteContentListBinding implements ViewBinding {
    public final NestedScrollView ivLoading;
    private final RelativeLayout rootView;
    public final RecyclerView rvContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutVoteContentListBinding(RelativeLayout relativeLayout, NestedScrollView nestedScrollView, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.ivLoading = nestedScrollView;
        this.rvContent = recyclerView;
    }

    public static LayoutVoteContentListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutVoteContentListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_vote_content_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutVoteContentListBinding bind(View view) {
        int i = R.id.iv_loading;
        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.iv_loading);
        if (nestedScrollView != null) {
            i = R.id.rv_content;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_content);
            if (recyclerView != null) {
                return new LayoutVoteContentListBinding((RelativeLayout) view, nestedScrollView, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
