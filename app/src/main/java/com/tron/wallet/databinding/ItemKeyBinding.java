package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemKeyBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvAddress;
    public final TextView tvThreshold;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemKeyBinding(LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.tvAddress = textView;
        this.tvThreshold = textView2;
    }

    public static ItemKeyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemKeyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_key, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemKeyBinding bind(View view) {
        int i = R.id.tv_address;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
        if (textView != null) {
            i = R.id.tv_threshold;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_threshold);
            if (textView2 != null) {
                return new ItemKeyBinding((LinearLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
