package com.tron.tron_base.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.tron_base.R;
public final class ToastBinding implements ViewBinding {
    public final ImageView ivImg;
    public final TextView message;
    private final LinearLayout rootView;
    public final TextView tvToastTop;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ToastBinding(LinearLayout linearLayout, ImageView imageView, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.ivImg = imageView;
        this.message = textView;
        this.tvToastTop = textView2;
    }

    public static ToastBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ToastBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.toast, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ToastBinding bind(View view) {
        int i = R.id.iv_img;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = 16908299;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, 16908299);
            if (textView != null) {
                i = R.id.tv_toast_top;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    return new ToastBinding((LinearLayout) view, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
