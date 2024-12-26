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
public final class InfoPopupDynamicBinding implements ViewBinding {
    public final ImageView icArrow;
    private final RelativeLayout rootView;
    public final TextView tvContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private InfoPopupDynamicBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView) {
        this.rootView = relativeLayout;
        this.icArrow = imageView;
        this.tvContent = textView;
    }

    public static InfoPopupDynamicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static InfoPopupDynamicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.info_popup_dynamic, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static InfoPopupDynamicBinding bind(View view) {
        int i = R.id.ic_arrow;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ic_arrow);
        if (imageView != null) {
            i = R.id.tv_content;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
            if (textView != null) {
                return new InfoPopupDynamicBinding((RelativeLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
