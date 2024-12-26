package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemIDappSearchHisBinding implements ViewBinding {
    public final ImageView ivLeft;
    public final View line;
    public final RelativeLayout rlCentent;
    private final RelativeLayout rootView;
    public final TextView tvDapp;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemIDappSearchHisBinding(RelativeLayout relativeLayout, ImageView imageView, View view, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.ivLeft = imageView;
        this.line = view;
        this.rlCentent = relativeLayout2;
        this.tvDapp = textView;
    }

    public static ItemIDappSearchHisBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemIDappSearchHisBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_i_dapp_search_his, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemIDappSearchHisBinding bind(View view) {
        int i = R.id.iv_left;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_left);
        if (imageView != null) {
            i = R.id.line;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
            if (findChildViewById != null) {
                i = R.id.rl_centent;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_centent);
                if (relativeLayout != null) {
                    i = R.id.tv_dapp;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_dapp);
                    if (textView != null) {
                        return new ItemIDappSearchHisBinding((RelativeLayout) view, imageView, findChildViewById, relativeLayout, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
