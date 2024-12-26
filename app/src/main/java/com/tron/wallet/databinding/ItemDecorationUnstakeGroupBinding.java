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
public final class ItemDecorationUnstakeGroupBinding implements ViewBinding {
    public final ImageView ivIconDivider;
    public final RelativeLayout rlGroupRoot;
    private final RelativeLayout rootView;
    public final TextView tvGroupDivider;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemDecorationUnstakeGroupBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.ivIconDivider = imageView;
        this.rlGroupRoot = relativeLayout2;
        this.tvGroupDivider = textView;
    }

    public static ItemDecorationUnstakeGroupBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDecorationUnstakeGroupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_decoration_unstake_group, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemDecorationUnstakeGroupBinding bind(View view) {
        int i = R.id.iv_icon_divider;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon_divider);
        if (imageView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_group_divider);
            if (textView != null) {
                return new ItemDecorationUnstakeGroupBinding(relativeLayout, imageView, relativeLayout, textView);
            }
            i = R.id.tv_group_divider;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
