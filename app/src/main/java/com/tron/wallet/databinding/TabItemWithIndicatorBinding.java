package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class TabItemWithIndicatorBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvTitle;
    public final ImageView vNewAssetsTip;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private TabItemWithIndicatorBinding(ConstraintLayout constraintLayout, TextView textView, ImageView imageView) {
        this.rootView = constraintLayout;
        this.tvTitle = textView;
        this.vNewAssetsTip = imageView;
    }

    public static TabItemWithIndicatorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TabItemWithIndicatorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tab_item_with_indicator, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TabItemWithIndicatorBinding bind(View view) {
        int i = R.id.tv_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
        if (textView != null) {
            i = R.id.v_new_assets_tip;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.v_new_assets_tip);
            if (imageView != null) {
                return new TabItemWithIndicatorBinding((ConstraintLayout) view, textView, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
