package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemContractVoteBinding implements ViewBinding {
    public final ImageView ivDelete;
    public final LinearLayout llBg;
    public final LinearLayout llDelete;
    private final FrameLayout rootView;
    public final TextView tvAddress;
    public final TextView tvIndex;
    public final TextView tvName;
    public final TextView tvVote;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private ItemContractVoteBinding(FrameLayout frameLayout, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = frameLayout;
        this.ivDelete = imageView;
        this.llBg = linearLayout;
        this.llDelete = linearLayout2;
        this.tvAddress = textView;
        this.tvIndex = textView2;
        this.tvName = textView3;
        this.tvVote = textView4;
    }

    public static ItemContractVoteBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemContractVoteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_contract_vote, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemContractVoteBinding bind(View view) {
        int i = R.id.iv_delete;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete);
        if (imageView != null) {
            i = R.id.ll_bg;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_bg);
            if (linearLayout != null) {
                i = R.id.ll_delete;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_delete);
                if (linearLayout2 != null) {
                    i = R.id.tv_address;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                    if (textView != null) {
                        i = R.id.tv_index;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_index);
                        if (textView2 != null) {
                            i = R.id.tv_name;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                            if (textView3 != null) {
                                i = R.id.tv_vote;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote);
                                if (textView4 != null) {
                                    return new ItemContractVoteBinding((FrameLayout) view, imageView, linearLayout, linearLayout2, textView, textView2, textView3, textView4);
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
