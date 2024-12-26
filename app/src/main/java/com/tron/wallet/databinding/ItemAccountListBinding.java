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
public final class ItemAccountListBinding implements ViewBinding {
    public final ImageView ivScam;
    public final ImageView ivScamAddress;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvExtra;
    public final TextView tvName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemAccountListBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.ivScam = imageView;
        this.ivScamAddress = imageView2;
        this.tvAddress = textView;
        this.tvExtra = textView2;
        this.tvName = textView3;
    }

    public static ItemAccountListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAccountListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_account_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAccountListBinding bind(View view) {
        int i = R.id.iv_scam;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam);
        if (imageView != null) {
            i = R.id.iv_scam_address;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam_address);
            if (imageView2 != null) {
                i = R.id.tv_address;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                if (textView != null) {
                    i = R.id.tv_extra;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_extra);
                    if (textView2 != null) {
                        i = R.id.tv_name;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                        if (textView3 != null) {
                            return new ItemAccountListBinding((RelativeLayout) view, imageView, imageView2, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
