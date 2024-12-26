package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemSelectAddressBinding implements ViewBinding {
    public final AppCompatCheckBox ivSelectTag;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSelectAddressBinding(RelativeLayout relativeLayout, AppCompatCheckBox appCompatCheckBox, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivSelectTag = appCompatCheckBox;
        this.tvAddress = textView;
        this.tvName = textView2;
    }

    public static ItemSelectAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSelectAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_select_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSelectAddressBinding bind(View view) {
        int i = R.id.iv_select_tag;
        AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) ViewBindings.findChildViewById(view, R.id.iv_select_tag);
        if (appCompatCheckBox != null) {
            i = R.id.tv_address;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
            if (textView != null) {
                i = R.id.tv_name;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                if (textView2 != null) {
                    return new ItemSelectAddressBinding((RelativeLayout) view, appCompatCheckBox, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
