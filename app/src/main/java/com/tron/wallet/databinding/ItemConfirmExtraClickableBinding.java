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
public final class ItemConfirmExtraClickableBinding implements ViewBinding {
    public final ImageView ivArrowRight;
    private final RelativeLayout rootView;
    public final TextView tvLeft;
    public final TextView tvRight;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemConfirmExtraClickableBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivArrowRight = imageView;
        this.tvLeft = textView;
        this.tvRight = textView2;
    }

    public static ItemConfirmExtraClickableBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemConfirmExtraClickableBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_confirm_extra_clickable, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemConfirmExtraClickableBinding bind(View view) {
        int i = R.id.iv_arrow_right;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow_right);
        if (imageView != null) {
            i = R.id.tv_left;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left);
            if (textView != null) {
                i = R.id.tv_right;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right);
                if (textView2 != null) {
                    return new ItemConfirmExtraClickableBinding((RelativeLayout) view, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
