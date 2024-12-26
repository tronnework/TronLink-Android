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
public final class ToastTopBinding implements ViewBinding {
    public final ImageView ivIndicator;
    private final RelativeLayout rootView;
    public final TextView tvInfo;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ToastTopBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView) {
        this.rootView = relativeLayout;
        this.ivIndicator = imageView;
        this.tvInfo = textView;
    }

    public static ToastTopBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ToastTopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.toast_top, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ToastTopBinding bind(View view) {
        int i = R.id.iv_indicator;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_indicator);
        if (imageView != null) {
            i = R.id.tv_info;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_info);
            if (textView != null) {
                return new ToastTopBinding((RelativeLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
