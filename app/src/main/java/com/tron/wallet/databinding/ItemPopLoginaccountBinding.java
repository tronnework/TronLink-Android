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
public final class ItemPopLoginaccountBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final ImageView selected;
    public final TextView tvAddress;
    public final TextView tvBalance;
    public final TextView tvName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemPopLoginaccountBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.selected = imageView;
        this.tvAddress = textView;
        this.tvBalance = textView2;
        this.tvName = textView3;
    }

    public static ItemPopLoginaccountBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemPopLoginaccountBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_pop_loginaccount, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemPopLoginaccountBinding bind(View view) {
        int i = R.id.selected;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.selected);
        if (imageView != null) {
            i = R.id.tv_address;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
            if (textView != null) {
                i = R.id.tv_balance;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                if (textView2 != null) {
                    i = R.id.tv_name;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                    if (textView3 != null) {
                        return new ItemPopLoginaccountBinding((RelativeLayout) view, imageView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
