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
public final class ItemSignAddressBinding implements ViewBinding {
    public final TextView addressWeight;
    private final RelativeLayout rootView;
    public final TextView signAddress;
    public final ImageView signState;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSignAddressBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, ImageView imageView) {
        this.rootView = relativeLayout;
        this.addressWeight = textView;
        this.signAddress = textView2;
        this.signState = imageView;
    }

    public static ItemSignAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSignAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_sign_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSignAddressBinding bind(View view) {
        int i = R.id.address_weight;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.address_weight);
        if (textView != null) {
            i = R.id.sign_address;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.sign_address);
            if (textView2 != null) {
                i = R.id.sign_state;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.sign_state);
                if (imageView != null) {
                    return new ItemSignAddressBinding((RelativeLayout) view, textView, textView2, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
