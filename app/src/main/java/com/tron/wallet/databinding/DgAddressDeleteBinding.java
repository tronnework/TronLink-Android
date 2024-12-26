package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DgAddressDeleteBinding implements ViewBinding {
    public final RelativeLayout rlRoot;
    private final RelativeLayout rootView;
    public final TextView tvCancle;
    public final TextView tvConfirm;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DgAddressDeleteBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.rlRoot = relativeLayout2;
        this.tvCancle = textView;
        this.tvConfirm = textView2;
    }

    public static DgAddressDeleteBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgAddressDeleteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_address_delete, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgAddressDeleteBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i = R.id.tv_cancle;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
        if (textView != null) {
            i = R.id.tv_confirm;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_confirm);
            if (textView2 != null) {
                return new DgAddressDeleteBinding(relativeLayout, relativeLayout, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
