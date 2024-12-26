package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LayoutProposalHeadBinding implements ViewBinding {
    public final ImageView ivLeft;
    public final LinearLayout llLeft;
    private final RelativeLayout rootView;
    public final TextView tvCreatePropose;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutProposalHeadBinding(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivLeft = imageView;
        this.llLeft = linearLayout;
        this.tvCreatePropose = textView;
        this.tvTitle = textView2;
    }

    public static LayoutProposalHeadBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutProposalHeadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_proposal_head, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutProposalHeadBinding bind(View view) {
        int i = R.id.iv_left;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_left);
        if (imageView != null) {
            i = R.id.ll_left;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_left);
            if (linearLayout != null) {
                i = R.id.tv_create_propose;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_create_propose);
                if (textView != null) {
                    i = R.id.tv_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                    if (textView2 != null) {
                        return new LayoutProposalHeadBinding((RelativeLayout) view, imageView, linearLayout, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
