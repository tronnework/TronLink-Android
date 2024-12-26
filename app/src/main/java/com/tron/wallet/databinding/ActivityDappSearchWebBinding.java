package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ActivityDappSearchWebBinding implements ViewBinding {
    public final ImageView ivClose;
    public final ImageView ivLeftBack;
    public final ImageView ivRefresh;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityDappSearchWebBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3) {
        this.rootView = relativeLayout;
        this.ivClose = imageView;
        this.ivLeftBack = imageView2;
        this.ivRefresh = imageView3;
    }

    public static ActivityDappSearchWebBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityDappSearchWebBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_dapp_search_web, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityDappSearchWebBinding bind(View view) {
        int i = R.id.iv_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close);
        if (imageView != null) {
            i = R.id.iv_left_back;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_left_back);
            if (imageView2 != null) {
                i = R.id.iv_refresh;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_refresh);
                if (imageView3 != null) {
                    return new ActivityDappSearchWebBinding((RelativeLayout) view, imageView, imageView2, imageView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
