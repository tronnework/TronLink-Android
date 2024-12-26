package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.components.SwitchView;
import com.tronlinkpro.wallet.R;
public final class ItemICustomassetsBinding implements ViewBinding {
    public final SimpleDraweeView image;
    private final RelativeLayout rootView;
    public final SwitchView switchview;
    public final TextView tvAmount;
    public final TextView tvName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemICustomassetsBinding(RelativeLayout relativeLayout, SimpleDraweeView simpleDraweeView, SwitchView switchView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.image = simpleDraweeView;
        this.switchview = switchView;
        this.tvAmount = textView;
        this.tvName = textView2;
    }

    public static ItemICustomassetsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemICustomassetsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_i_customassets, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemICustomassetsBinding bind(View view) {
        int i = R.id.image;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.image);
        if (simpleDraweeView != null) {
            i = R.id.switchview;
            SwitchView switchView = (SwitchView) ViewBindings.findChildViewById(view, R.id.switchview);
            if (switchView != null) {
                i = R.id.tv_amount;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount);
                if (textView != null) {
                    i = R.id.tv_name;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                    if (textView2 != null) {
                        return new ItemICustomassetsBinding((RelativeLayout) view, simpleDraweeView, switchView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
