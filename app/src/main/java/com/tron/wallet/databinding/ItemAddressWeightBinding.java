package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemAddressWeightBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvWeight;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemAddressWeightBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.tvAddress = textView;
        this.tvWeight = textView2;
    }

    public static ItemAddressWeightBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAddressWeightBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_address_weight, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAddressWeightBinding bind(View view) {
        int i = R.id.tv_address;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
        if (textView != null) {
            i = R.id.tv_weight;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_weight);
            if (textView2 != null) {
                return new ItemAddressWeightBinding((RelativeLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
