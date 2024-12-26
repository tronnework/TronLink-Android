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
public final class LayoutItemExportWalletBinding implements ViewBinding {
    public final ImageView ivDot;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutItemExportWalletBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivDot = imageView;
        this.tvAddress = textView;
        this.tvName = textView2;
    }

    public static LayoutItemExportWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutItemExportWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_item_export_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutItemExportWalletBinding bind(View view) {
        int i = R.id.iv_dot;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_dot);
        if (imageView != null) {
            i = R.id.tv_address;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
            if (textView != null) {
                i = R.id.tv_name;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                if (textView2 != null) {
                    return new LayoutItemExportWalletBinding((RelativeLayout) view, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
