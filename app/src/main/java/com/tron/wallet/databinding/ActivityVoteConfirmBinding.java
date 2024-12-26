package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ActivityVoteConfirmBinding implements ViewBinding {
    public final FrameLayout fragment;
    public final EmptyViewBinding llNoData;
    public final NonetViewBinding llNoNet;
    public final RelativeLayout root;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityVoteConfirmBinding(RelativeLayout relativeLayout, FrameLayout frameLayout, EmptyViewBinding emptyViewBinding, NonetViewBinding nonetViewBinding, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.fragment = frameLayout;
        this.llNoData = emptyViewBinding;
        this.llNoNet = nonetViewBinding;
        this.root = relativeLayout2;
    }

    public static ActivityVoteConfirmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityVoteConfirmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_vote_confirm, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityVoteConfirmBinding bind(View view) {
        int i = R.id.fragment;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fragment);
        if (frameLayout != null) {
            i = R.id.ll_no_data;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.ll_no_data);
            if (findChildViewById != null) {
                EmptyViewBinding bind = EmptyViewBinding.bind(findChildViewById);
                i = R.id.ll_no_net;
                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.ll_no_net);
                if (findChildViewById2 != null) {
                    RelativeLayout relativeLayout = (RelativeLayout) view;
                    return new ActivityVoteConfirmBinding(relativeLayout, frameLayout, bind, NonetViewBinding.bind(findChildViewById2), relativeLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
