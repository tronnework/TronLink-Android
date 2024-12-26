package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemCustomtokenErrorBinding implements ViewBinding {
    public final ImageView ivError;
    private final ConstraintLayout rootView;
    public final TextView tvTip;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ItemCustomtokenErrorBinding(ConstraintLayout constraintLayout, ImageView imageView, TextView textView) {
        this.rootView = constraintLayout;
        this.ivError = imageView;
        this.tvTip = textView;
    }

    public static ItemCustomtokenErrorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemCustomtokenErrorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_customtoken_error, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemCustomtokenErrorBinding bind(View view) {
        int i = R.id.iv_error;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_error);
        if (imageView != null) {
            i = R.id.tv_tip;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tip);
            if (textView != null) {
                return new ItemCustomtokenErrorBinding((ConstraintLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
