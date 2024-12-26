package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemTokendetailPlaceholderBinding implements ViewBinding {
    public final ImageView ivTokenDetailPlaceholder;
    public final RelativeLayout rootHolderView;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemTokendetailPlaceholderBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.ivTokenDetailPlaceholder = imageView;
        this.rootHolderView = relativeLayout2;
    }

    public static ItemTokendetailPlaceholderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemTokendetailPlaceholderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_tokendetail_placeholder, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTokendetailPlaceholderBinding bind(View view) {
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_token_detail_placeholder);
        if (imageView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            return new ItemTokendetailPlaceholderBinding(relativeLayout, imageView, relativeLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.iv_token_detail_placeholder)));
    }
}
