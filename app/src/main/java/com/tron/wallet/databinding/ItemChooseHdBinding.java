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
public final class ItemChooseHdBinding implements ViewBinding {
    public final AppCompatCheckBox ivSelectTag;
    private final RelativeLayout rootView;
    public final TextView tvAccounts;
    public final TextView tvAddress;
    public final TextView tvName;
    public final TextView tvPath;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemChooseHdBinding(RelativeLayout relativeLayout, AppCompatCheckBox appCompatCheckBox, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.ivSelectTag = appCompatCheckBox;
        this.tvAccounts = textView;
        this.tvAddress = textView2;
        this.tvName = textView3;
        this.tvPath = textView4;
    }

    public static ItemChooseHdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemChooseHdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_choose_hd, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemChooseHdBinding bind(View view) {
        int i = R.id.iv_select_tag;
        AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) ViewBindings.findChildViewById(view, R.id.iv_select_tag);
        if (appCompatCheckBox != null) {
            i = R.id.tv_accounts;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_accounts);
            if (textView != null) {
                i = R.id.tv_address;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                if (textView2 != null) {
                    i = R.id.tv_name;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                    if (textView3 != null) {
                        i = R.id.tv_path;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_path);
                        if (textView4 != null) {
                            return new ItemChooseHdBinding((RelativeLayout) view, appCompatCheckBox, textView, textView2, textView3, textView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
