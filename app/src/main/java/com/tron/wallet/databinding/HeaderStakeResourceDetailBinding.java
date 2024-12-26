package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class HeaderStakeResourceDetailBinding implements ViewBinding {
    public final LinearLayout liTotal;
    private final LinearLayout rootView;
    public final TextView tvTotalAmount;
    public final TextView tvTotalTitle;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private HeaderStakeResourceDetailBinding(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.liTotal = linearLayout2;
        this.tvTotalAmount = textView;
        this.tvTotalTitle = textView2;
    }

    public static HeaderStakeResourceDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HeaderStakeResourceDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.header_stake_resource_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HeaderStakeResourceDetailBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.tv_total_amount;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_total_amount);
        if (textView != null) {
            i = R.id.tv_total_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_total_title);
            if (textView2 != null) {
                return new HeaderStakeResourceDetailBinding(linearLayout, linearLayout, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
