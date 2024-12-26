package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemDappHomeHotItemBinding implements ViewBinding {
    public final RelativeLayout rlBg;
    private final RelativeLayout rootView;
    public final SimpleDraweeView simpleDraweeView;
    public final TextView tvName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemDappHomeHotItemBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, SimpleDraweeView simpleDraweeView, TextView textView) {
        this.rootView = relativeLayout;
        this.rlBg = relativeLayout2;
        this.simpleDraweeView = simpleDraweeView;
        this.tvName = textView;
    }

    public static ItemDappHomeHotItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDappHomeHotItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_dapp_home_hot_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemDappHomeHotItemBinding bind(View view) {
        int i = R.id.rl_bg;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bg);
        if (relativeLayout != null) {
            i = R.id.simple_drawee_view;
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.simple_drawee_view);
            if (simpleDraweeView != null) {
                i = R.id.tv_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                if (textView != null) {
                    return new ItemDappHomeHotItemBinding((RelativeLayout) view, relativeLayout, simpleDraweeView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
