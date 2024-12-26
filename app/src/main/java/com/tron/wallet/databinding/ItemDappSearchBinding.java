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
public final class ItemDappSearchBinding implements ViewBinding {
    public final RelativeLayout rlBg;
    private final RelativeLayout rootView;
    public final SimpleDraweeView sdIcon;
    public final TextView tvDescription;
    public final TextView tvName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemDappSearchBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, SimpleDraweeView simpleDraweeView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.rlBg = relativeLayout2;
        this.sdIcon = simpleDraweeView;
        this.tvDescription = textView;
        this.tvName = textView2;
    }

    public static ItemDappSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDappSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_dapp_search, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemDappSearchBinding bind(View view) {
        int i = R.id.rl_bg;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bg);
        if (relativeLayout != null) {
            i = R.id.sd_icon;
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.sd_icon);
            if (simpleDraweeView != null) {
                i = R.id.tv_description;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_description);
                if (textView != null) {
                    i = R.id.tv_name;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                    if (textView2 != null) {
                        return new ItemDappSearchBinding((RelativeLayout) view, relativeLayout, simpleDraweeView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
