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
public final class PullItemLoginBinding implements ViewBinding {
    public final ImageView ivPullAddress;
    private final RelativeLayout rootView;
    public final TextView tvPullAddress;
    public final TextView tvPullAddressName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PullItemLoginBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivPullAddress = imageView;
        this.tvPullAddress = textView;
        this.tvPullAddressName = textView2;
    }

    public static PullItemLoginBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PullItemLoginBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pull_item_login, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PullItemLoginBinding bind(View view) {
        int i = R.id.iv_pull_address;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_pull_address);
        if (imageView != null) {
            i = R.id.tv_pull_address;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pull_address);
            if (textView != null) {
                i = R.id.tv_pull_address_name;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pull_address_name);
                if (textView2 != null) {
                    return new PullItemLoginBinding((RelativeLayout) view, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
