package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class ItemAssetsPlaceholderBinding implements ViewBinding {
    public final RelativeLayout rootHolderView;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemAssetsPlaceholderBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.rootHolderView = relativeLayout2;
    }

    public static ItemAssetsPlaceholderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAssetsPlaceholderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_assets_placeholder, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAssetsPlaceholderBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        RelativeLayout relativeLayout = (RelativeLayout) view;
        return new ItemAssetsPlaceholderBinding(relativeLayout, relativeLayout);
    }
}
