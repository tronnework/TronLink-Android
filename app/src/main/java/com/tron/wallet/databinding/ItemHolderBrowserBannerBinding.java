package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemHolderBrowserBannerBinding implements ViewBinding {
    public final SimpleDraweeView dappBannerItemImage;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemHolderBrowserBannerBinding(RelativeLayout relativeLayout, SimpleDraweeView simpleDraweeView) {
        this.rootView = relativeLayout;
        this.dappBannerItemImage = simpleDraweeView;
    }

    public static ItemHolderBrowserBannerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemHolderBrowserBannerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_holder_browser_banner, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemHolderBrowserBannerBinding bind(View view) {
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.dapp_banner_item_image);
        if (simpleDraweeView != null) {
            return new ItemHolderBrowserBannerBinding((RelativeLayout) view, simpleDraweeView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.dapp_banner_item_image)));
    }
}
