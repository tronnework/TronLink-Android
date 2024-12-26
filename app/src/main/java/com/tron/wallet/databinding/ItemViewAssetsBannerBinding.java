package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class ItemViewAssetsBannerBinding implements ViewBinding {
    public final ConstraintLayout llDoMenu;
    private final ConstraintLayout rootView;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ItemViewAssetsBannerBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.llDoMenu = constraintLayout2;
    }

    public static ItemViewAssetsBannerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemViewAssetsBannerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_assets_banner, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemViewAssetsBannerBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        return new ItemViewAssetsBannerBinding(constraintLayout, constraintLayout);
    }
}
