package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemStakeVoteAdapterBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvSrApr;
    public final TextView tvSrName;
    public final TextView tvSrVotes;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemStakeVoteAdapterBinding(LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.tvSrApr = textView;
        this.tvSrName = textView2;
        this.tvSrVotes = textView3;
    }

    public static ItemStakeVoteAdapterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemStakeVoteAdapterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_stake_vote_adapter, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemStakeVoteAdapterBinding bind(View view) {
        int i = R.id.tv_sr_apr;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sr_apr);
        if (textView != null) {
            i = R.id.tv_sr_name;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sr_name);
            if (textView2 != null) {
                i = R.id.tv_sr_votes;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sr_votes);
                if (textView3 != null) {
                    return new ItemStakeVoteAdapterBinding((LinearLayout) view, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
