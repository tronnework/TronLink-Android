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
public final class PopupTextBinding implements ViewBinding {
    public final ImageView ivBottomArrow;
    public final ImageView ivTopArrow;
    private final LinearLayout rootView;
    public final TextView tvContent;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PopupTextBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, TextView textView) {
        this.rootView = linearLayout;
        this.ivBottomArrow = imageView;
        this.ivTopArrow = imageView2;
        this.tvContent = textView;
    }

    public static PopupTextBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupTextBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_text, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupTextBinding bind(View view) {
        int i = R.id.iv_bottom_arrow;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_bottom_arrow);
        if (imageView != null) {
            i = R.id.iv_top_arrow;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_top_arrow);
            if (imageView2 != null) {
                i = R.id.tv_content;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                if (textView != null) {
                    return new PopupTextBinding((LinearLayout) view, imageView, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
