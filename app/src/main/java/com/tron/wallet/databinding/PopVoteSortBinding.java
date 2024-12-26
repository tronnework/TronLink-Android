package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopVoteSortBinding implements ViewBinding {
    public final RelativeLayout rlContent;
    public final RelativeLayout rlTop;
    private final RelativeLayout rootView;
    public final RelativeLayout rootview;
    public final RecyclerView rv;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopVoteSortBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.rlContent = relativeLayout2;
        this.rlTop = relativeLayout3;
        this.rootview = relativeLayout4;
        this.rv = recyclerView;
    }

    public static PopVoteSortBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopVoteSortBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_vote_sort, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopVoteSortBinding bind(View view) {
        int i = R.id.rl_content;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content);
        if (relativeLayout != null) {
            i = R.id.rl_top;
            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
            if (relativeLayout2 != null) {
                RelativeLayout relativeLayout3 = (RelativeLayout) view;
                i = R.id.rv;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv);
                if (recyclerView != null) {
                    return new PopVoteSortBinding(relativeLayout3, relativeLayout, relativeLayout2, relativeLayout3, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
