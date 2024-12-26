package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemDappContractParamsAdapterBinding implements ViewBinding {
    public final RelativeLayout rlMain;
    private final RelativeLayout rootView;
    public final TextView type;
    public final TextView value;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemDappContractParamsAdapterBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.rlMain = relativeLayout2;
        this.type = textView;
        this.value = textView2;
    }

    public static ItemDappContractParamsAdapterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDappContractParamsAdapterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_dapp_contract_params_adapter, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemDappContractParamsAdapterBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i = R.id.type;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.type);
        if (textView != null) {
            i = R.id.value;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.value);
            if (textView2 != null) {
                return new ItemDappContractParamsAdapterBinding(relativeLayout, relativeLayout, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
