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
public final class ItemSelectWalletDecorationBinding implements ViewBinding {
    public final ImageView ivTag;
    private final LinearLayout rootView;
    public final TextView tvTitle;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemSelectWalletDecorationBinding(LinearLayout linearLayout, ImageView imageView, TextView textView) {
        this.rootView = linearLayout;
        this.ivTag = imageView;
        this.tvTitle = textView;
    }

    public static ItemSelectWalletDecorationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSelectWalletDecorationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_select_wallet_decoration, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSelectWalletDecorationBinding bind(View view) {
        int i = R.id.iv_tag;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tag);
        if (imageView != null) {
            i = R.id.tv_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
            if (textView != null) {
                return new ItemSelectWalletDecorationBinding((LinearLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
