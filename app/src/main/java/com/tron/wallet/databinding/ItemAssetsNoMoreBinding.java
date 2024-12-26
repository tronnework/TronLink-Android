package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class ItemAssetsNoMoreBinding implements ViewBinding {
    private final ConstraintLayout rootView;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ItemAssetsNoMoreBinding(ConstraintLayout constraintLayout) {
        this.rootView = constraintLayout;
    }

    public static ItemAssetsNoMoreBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAssetsNoMoreBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_assets_no_more, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAssetsNoMoreBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ItemAssetsNoMoreBinding((ConstraintLayout) view);
    }
}
