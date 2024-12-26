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
public final class ItemAuthorizedDappBinding implements ViewBinding {
    public final ImageView ivSelect;
    private final RelativeLayout rootView;
    public final TextView tvAuthorizedUrl;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemAuthorizedDappBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView) {
        this.rootView = relativeLayout;
        this.ivSelect = imageView;
        this.tvAuthorizedUrl = textView;
    }

    public static ItemAuthorizedDappBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAuthorizedDappBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_authorized_dapp, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAuthorizedDappBinding bind(View view) {
        int i = R.id.iv_select;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select);
        if (imageView != null) {
            i = R.id.tv_authorized_url;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_authorized_url);
            if (textView != null) {
                return new ItemAuthorizedDappBinding((RelativeLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
