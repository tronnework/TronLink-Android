package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FeeItemBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView tvAmount;
    public final TextView tvName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FeeItemBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.tvAmount = textView;
        this.tvName = textView2;
    }

    public static FeeItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FeeItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fee_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FeeItemBinding bind(View view) {
        int i = R.id.tv_amount;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount);
        if (textView != null) {
            i = R.id.tv_name;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
            if (textView2 != null) {
                return new FeeItemBinding((RelativeLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
