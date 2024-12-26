package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemVoteContentBinding implements ViewBinding {
    public final RecyclerView rcVote;
    public final RelativeLayout rlNoResult;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemVoteContentBinding(RelativeLayout relativeLayout, RecyclerView recyclerView, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.rcVote = recyclerView;
        this.rlNoResult = relativeLayout2;
    }

    public static ItemVoteContentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVoteContentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_vote_content, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemVoteContentBinding bind(View view) {
        int i = R.id.rc_vote;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_vote);
        if (recyclerView != null) {
            i = R.id.rl_noResult;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_noResult);
            if (relativeLayout != null) {
                return new ItemVoteContentBinding((RelativeLayout) view, recyclerView, relativeLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
