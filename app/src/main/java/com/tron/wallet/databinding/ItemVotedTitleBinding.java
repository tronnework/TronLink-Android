package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemVotedTitleBinding implements ViewBinding {
    public final ImageView ivTag;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final TextView tvTitle;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemVotedTitleBinding(LinearLayout linearLayout, ImageView imageView, LinearLayout linearLayout2, TextView textView) {
        this.rootView = linearLayout;
        this.ivTag = imageView;
        this.root = linearLayout2;
        this.tvTitle = textView;
    }

    public static ItemVotedTitleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVotedTitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_voted_title, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemVotedTitleBinding bind(View view) {
        int i = R.id.iv_tag;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tag);
        if (imageView != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
            if (textView != null) {
                return new ItemVotedTitleBinding(linearLayout, imageView, linearLayout, textView);
            }
            i = R.id.tv_title;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
