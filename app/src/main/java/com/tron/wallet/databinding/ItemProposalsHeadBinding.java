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
public final class ItemProposalsHeadBinding implements ViewBinding {
    public final TextView account;
    public final RelativeLayout addressChange;
    public final LinearLayout llApprovedProposals;
    public final LinearLayout llMyProposals;
    public final RelativeLayout rlAddress;
    private final LinearLayout rootView;
    public final TextView tvAddress;
    public final TextView tvApprovedProposals;
    public final TextView tvMyProposals;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemProposalsHeadBinding(LinearLayout linearLayout, TextView textView, RelativeLayout relativeLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, RelativeLayout relativeLayout2, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.account = textView;
        this.addressChange = relativeLayout;
        this.llApprovedProposals = linearLayout2;
        this.llMyProposals = linearLayout3;
        this.rlAddress = relativeLayout2;
        this.tvAddress = textView2;
        this.tvApprovedProposals = textView3;
        this.tvMyProposals = textView4;
    }

    public static ItemProposalsHeadBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemProposalsHeadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_proposals_head, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemProposalsHeadBinding bind(View view) {
        int i = R.id.account;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.account);
        if (textView != null) {
            i = R.id.address_change;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.address_change);
            if (relativeLayout != null) {
                i = R.id.ll_approved_proposals;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_approved_proposals);
                if (linearLayout != null) {
                    i = R.id.ll_my_proposals;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_my_proposals);
                    if (linearLayout2 != null) {
                        i = R.id.rl_address;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                        if (relativeLayout2 != null) {
                            i = R.id.tv_address;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                            if (textView2 != null) {
                                i = R.id.tv_approved_proposals;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_approved_proposals);
                                if (textView3 != null) {
                                    i = R.id.tv_my_proposals;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_my_proposals);
                                    if (textView4 != null) {
                                        return new ItemProposalsHeadBinding((LinearLayout) view, textView, relativeLayout, linearLayout, linearLayout2, relativeLayout2, textView2, textView3, textView4);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
