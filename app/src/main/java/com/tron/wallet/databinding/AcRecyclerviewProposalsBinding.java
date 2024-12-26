package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcRecyclerviewProposalsBinding implements ViewBinding {
    public final ImageView ivShasta;
    public final LinearLayout llNodata;
    public final RecyclerView rcOriposals;
    private final RelativeLayout rootView;
    public final TextView tvMakeProposal;
    public final TextView tvNodataContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcRecyclerviewProposalsBinding(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, RecyclerView recyclerView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivShasta = imageView;
        this.llNodata = linearLayout;
        this.rcOriposals = recyclerView;
        this.tvMakeProposal = textView;
        this.tvNodataContent = textView2;
    }

    public static AcRecyclerviewProposalsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcRecyclerviewProposalsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_recyclerview_proposals, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcRecyclerviewProposalsBinding bind(View view) {
        int i = R.id.iv_shasta;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_shasta);
        if (imageView != null) {
            i = R.id.ll_nodata;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_nodata);
            if (linearLayout != null) {
                i = R.id.rc_oriposals;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_oriposals);
                if (recyclerView != null) {
                    i = R.id.tv_make_proposal;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_make_proposal);
                    if (textView != null) {
                        i = R.id.tv_nodata_content;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_nodata_content);
                        if (textView2 != null) {
                            return new AcRecyclerviewProposalsBinding((RelativeLayout) view, imageView, linearLayout, recyclerView, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
