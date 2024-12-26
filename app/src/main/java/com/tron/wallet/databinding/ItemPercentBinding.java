package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemPercentBinding implements ViewBinding {
    public final TextView amountPercent100;
    public final TextView amountPercent25;
    public final TextView amountPercent50;
    public final TextView amountPercent75;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemPercentBinding(LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.amountPercent100 = textView;
        this.amountPercent25 = textView2;
        this.amountPercent50 = textView3;
        this.amountPercent75 = textView4;
    }

    public static ItemPercentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemPercentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_percent, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemPercentBinding bind(View view) {
        int i = R.id.amount_percent_100;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.amount_percent_100);
        if (textView != null) {
            i = R.id.amount_percent_25;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.amount_percent_25);
            if (textView2 != null) {
                i = R.id.amount_percent_50;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.amount_percent_50);
                if (textView3 != null) {
                    i = R.id.amount_percent_75;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.amount_percent_75);
                    if (textView4 != null) {
                        return new ItemPercentBinding((LinearLayout) view, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
