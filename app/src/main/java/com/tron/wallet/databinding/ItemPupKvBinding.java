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
public final class ItemPupKvBinding implements ViewBinding {
    public final ImageView ivCopy;
    private final ConstraintLayout rootView;
    public final TextView tvLeft;
    public final TextView tvRight;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ItemPupKvBinding(ConstraintLayout constraintLayout, ImageView imageView, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.ivCopy = imageView;
        this.tvLeft = textView;
        this.tvRight = textView2;
    }

    public static ItemPupKvBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemPupKvBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_pup_kv, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemPupKvBinding bind(View view) {
        int i = R.id.iv_copy;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy);
        if (imageView != null) {
            i = R.id.tv_left;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left);
            if (textView != null) {
                i = R.id.tv_right;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right);
                if (textView2 != null) {
                    return new ItemPupKvBinding((ConstraintLayout) view, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
