package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemDelegateForMeBinding implements ViewBinding {
    public final View divider;
    private final RelativeLayout rootView;
    public final TextView tvAmount;
    public final TextView tvContractTag;
    public final TextView tvFrom;
    public final TextView tvFromAddress;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemDelegateForMeBinding(RelativeLayout relativeLayout, View view, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.divider = view;
        this.tvAmount = textView;
        this.tvContractTag = textView2;
        this.tvFrom = textView3;
        this.tvFromAddress = textView4;
    }

    public static ItemDelegateForMeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDelegateForMeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_delegate_for_me, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemDelegateForMeBinding bind(View view) {
        int i = R.id.divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (findChildViewById != null) {
            i = R.id.tv_amount;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount);
            if (textView != null) {
                i = R.id.tv_contract_tag;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_tag);
                if (textView2 != null) {
                    i = R.id.tv_from;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_from);
                    if (textView3 != null) {
                        i = R.id.tv_from_address;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_from_address);
                        if (textView4 != null) {
                            return new ItemDelegateForMeBinding((RelativeLayout) view, findChildViewById, textView, textView2, textView3, textView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
