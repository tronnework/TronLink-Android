package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class BatchVoteFooterResourceBinding implements ViewBinding {
    public final RelativeLayout rlCost;
    private final LinearLayout rootView;
    public final TextView tvCost;
    public final TextView tvFeeCost;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private BatchVoteFooterResourceBinding(LinearLayout linearLayout, RelativeLayout relativeLayout, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.rlCost = relativeLayout;
        this.tvCost = textView;
        this.tvFeeCost = textView2;
    }

    public static BatchVoteFooterResourceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static BatchVoteFooterResourceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.batch_vote_footer_resource, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static BatchVoteFooterResourceBinding bind(View view) {
        int i = R.id.rl_cost;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_cost);
        if (relativeLayout != null) {
            i = R.id.tv_cost;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cost);
            if (textView != null) {
                i = R.id.tv_fee_cost;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_cost);
                if (textView2 != null) {
                    return new BatchVoteFooterResourceBinding((LinearLayout) view, relativeLayout, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
